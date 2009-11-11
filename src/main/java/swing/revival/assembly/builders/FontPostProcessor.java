/**
 * swing-revival:
 * Swing Revival Toolkit
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Sep 30, 2009
 */
package swing.revival.assembly.builders;

import static swing.revival.util.SyntaticSugar.ifNull;

import java.lang.reflect.Field;

import javax.swing.JComponent;

import swing.revival.annotations.Font;
import swing.revival.assembly.context.AssemblyContext;
import swing.revival.assembly.inspectors.ComponentInspector;
import swing.revival.assembly.inspectors.ContainerInspector;
import swing.revival.assembly.model.ComponentDefinition;
import swing.revival.assembly.model.ContainerDefinition;
import swing.revival.assembly.model.FontInfo;
import swing.revival.assembly.postprocessors.AssemblyPostProcessor;

/**
 * @author Alistair A. Israel
 */
public class FontPostProcessor implements ContainerInspector, ComponentInspector, AssemblyPostProcessor {

    private FontInfo defaultFontInfo;

    /**
     * {@inheritDoc}
     *
     * @see swing.revival.assembly.inspectors.ContainerInspector#inspect(swing.revival.assembly.context.AssemblyContext,
     *      swing.revival.assembly.model.ContainerDefinition.Builder)
     */
    @Override
    public final void inspect(final AssemblyContext context,
            final ContainerDefinition.Builder containerDefinitionBuilder) {
        final Font fontAnnotation = containerDefinitionBuilder.getContainerClass().getAnnotation(Font.class);
        if (fontAnnotation != null) {
            defaultFontInfo = FontInfo.fromAnnotation(fontAnnotation);
            containerDefinitionBuilder.setDefaultFontInfo(defaultFontInfo);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see ComponentInspector#inspect(ComponentDefinition)
     */
    @Override
    public final void inspect(final ComponentDefinition definition) {
        final Field field = definition.getField();
        if (field.isAnnotationPresent(Font.class)) {
            final FontInfo fontInfo = FontInfo.fromAnnotation(field.getAnnotation(Font.class));
            definition.setFontInfo(fontInfo);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see AssemblyPostProcessor#postProcess(AssemblyContext, ComponentDefinition, JComponent)
     */
    @Override
    public final void postProcess(final AssemblyContext context, final ComponentDefinition definition,
            final JComponent component) {
        final FontInfo fontInfo = ifNull(definition.getFontInfo(), defaultFontInfo);
        if (fontInfo != null) {
            final java.awt.Font font = createFont(fontInfo, component);
            if (font != null) {
                component.setFont(font);
            }
        }
    }

    /**
     * @param fontInfo
     *        the font annotation to process
     * @param component
     *        the component we're processing
     * @return the {@link java.awt.Font}
     */
    private static java.awt.Font createFont(final FontInfo fontInfo, final JComponent component) {
        if (fontInfo != null) {
            final int size;
            if (fontInfo.getSize() != null) {
                size = fontInfo.getSize().intValue();
            } else {
                size = component.getFont().getSize();
            }
            return new java.awt.Font(fontInfo.getName(), fontInfo.getStyle(), size);
        }
        return null;
    }
}

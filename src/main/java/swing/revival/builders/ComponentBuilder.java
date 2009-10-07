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
package swing.revival.builders;

import java.lang.reflect.Field;
import java.util.logging.Logger;

import javax.swing.JComponent;

import swing.revival.ComponentBuilderContext;

/**
 * @param <C>
 * @author Alistair A. Israel
 */
public abstract class ComponentBuilder<C extends JComponent> extends
        ComponentBuilderContext.Aware {

    private static final Logger LOGGER = Logger.getLogger(ComponentBuilder.class.getName());

    /**
     *
     */
    private static final String TOOL_TIP_TEXT = ".toolTipText";

    private final Field field;

    /**
     * @param context
     *        the {@link ComponentBuilderContext} we're building in
     * @param field
     *        the field we're building for
     */
    public ComponentBuilder(final ComponentBuilderContext context, final Field field) {
        super(context);
        this.field = field;
    }

    /**
     * @return the field
     */
    public final Field getField() {
        return field;
    }

    /**
     * @return the constructed {@link JComponent}
     */
    protected abstract C constructComponent();

    /**
     * @return the base name
     */
    public abstract String getBaseName();

    /**
     * @return the built {@link JComponent}
     */
    public final C build() {
        final C component = constructComponent();
        final String name = getField().getName();
        LOGGER.finest("Setting component name to \"" + name + "\"...");
        component.setName(name);
        getFontPostProcessor().setFontOn(getField(), component);
        setToolTipTextOn(component);
        return component;
    }

    /**
     * @param component
     *        the component to set the tooltip text on
     */
    private void setToolTipTextOn(final C component) {
        final String baseName = getBaseName();
        if (containsResourceKey(baseName + TOOL_TIP_TEXT)) {
            final String toolTipText = getResourceString(baseName + TOOL_TIP_TEXT);
            component.setToolTipText(toolTipText);
        }
    }
}
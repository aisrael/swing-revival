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

import static swing.revival.util.SyntaticSugar.ifNull;

import javax.swing.JComponent;

import swing.revival.context.ComponentFieldInfo;
import swing.revival.context.FontInfo;

/**
 * @author Alistair A. Israel
 */
public class FontPostProcessor {

    private final FontInfo defaultFontInfo;

    /**
     * @param clazz
     *        the component class
     */
    public FontPostProcessor(final Class<?> clazz) {
        if (clazz.isAnnotationPresent(swing.revival.annotations.Font.class)) {
            defaultFontInfo = FontInfo.fromAnnotation(clazz.getAnnotation(swing.revival.annotations.Font.class));
        } else {
            defaultFontInfo = null;
        }
    }

    /**
     * @return the defaultFont
     */
    public final FontInfo getDefaultFontInfo() {
        return defaultFontInfo;
    }

    /**
     * @param fieldInfo
     *        the {@link ComponentFieldInfo} we're post-processing for
     * @param component
     *        the component to set the font on
     */
    public final void setFontOn(final ComponentFieldInfo fieldInfo, final JComponent component) {
        final FontInfo fontInfo = ifNull(fieldInfo.getFontInfo(), defaultFontInfo);
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

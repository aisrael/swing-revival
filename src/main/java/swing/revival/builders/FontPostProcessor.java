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

import java.awt.Font;
import java.lang.reflect.Field;

import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author Alistair A. Israel
 */
public class FontPostProcessor {

    private final Font defaultFont;

    /**
     * @param clazz
     *        the component class
     */
    public FontPostProcessor(final Class<?> clazz) {
        final swing.revival.annotations.Font fontAnnotation =
                clazz.getAnnotation(swing.revival.annotations.Font.class);
        defaultFont = createFont(fontAnnotation, new JLabel());
    }

    /**
     * @return the defaultFont
     */
    public final Font getDefaultFont() {
        return defaultFont;
    }

    /**
     * @param fontAnnotation
     *        the font annotation to process
     * @param component
     *        the component we're processing
     * @return the {@link Font}
     */
    private static java.awt.Font createFont(
            final swing.revival.annotations.Font fontAnnotation, final JComponent component) {
        java.awt.Font font = null;
        if (fontAnnotation != null) {
            int size = fontAnnotation.size();
            if (size == -1) {
                size = component.getFont().getSize();
            }
            font = new java.awt.Font(fontAnnotation.name(), fontAnnotation.style().intValue(), size);
        }
        return font;
    }

    /**
     * @param field
     *        the {@link Field} we're post-processing for
     * @param component
     *        the component to set the font on
     */
    public final void setFontOn(final Field field, final JComponent component) {
        java.awt.Font font = defaultFont;
        final swing.revival.annotations.Font fontAnnotation =
                field.getAnnotation(swing.revival.annotations.Font.class);
        if (fontAnnotation != null) {
            font = createFont(fontAnnotation, component);
        }
        if (font != null) {
            component.setFont(font);
        }
    }

}

/**
 * swing-revival:
 * Swing Revival Toolkit
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Oct 8, 2009
 */
package swing.revival.builders;

import static java.lang.reflect.Modifier.isStatic;
import static swing.revival.util.StringUtils.isNullOrEmpty;

import java.awt.Container;
import java.lang.reflect.Field;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JTextField;

import swing.revival.ComponentBuilderContext;
import swing.revival.annotations.Button;
import swing.revival.annotations.Font;
import swing.revival.annotations.RadioButton;
import swing.revival.annotations.TextField;
import swing.revival.context.ComponentField;
import swing.revival.context.FontInfo;
import swing.revival.context.InspectionResult;
import swing.revival.context.ComponentField.Builder;
import swing.revival.util.ResourceBundleHelper;
import swing.revival.util.Resources;

import com.sun.codemodel.internal.JLabel;

/**
 *
 * @author Alistair A. Israel
 */
public final class SwingAnnotationPostProcessor {

    /**
     *
     */
    public SwingAnnotationPostProcessor() {

    }

    /**
     * @param container
     *        the {@link Container}-derived object
     */
    public void process(final Container container) {
        final ComponentBuilderContext context = new ComponentBuilderContext((JComponent) container);
        final InspectionResult swair = inspect(container.getClass());
        for (final ComponentField componentField : swair.listComponentFields()) {
            final Class<?> type = componentField.getType();
        }
    }

    /**
     * @param container
     *        the {@link Container}-derived object
     */
    public static void postProcess(final Container container) {
        new SwingAnnotationPostProcessor().process(container);
    }

    /**
     * @param clazz
     *        {@link Container} class
     * @return {@link InspectionResult}
     */
    public InspectionResult inspect(final Class<? extends Container> clazz) {
        return new Inspector(clazz).inspect();
    }

    /**
     * Inspects the given {@link Container} subclass (typically, a JPanel or
     * JFrame) for Swing Revival annotations.
     *
     * @author Alistair A. Israel
     */
    public class Inspector {

        private final Class<? extends Container> clazz;

        private final ResourceBundleHelper helper;

        private final InspectionResult.Builder results = new InspectionResult.Builder();

        /**
         * @param clazz
         *        the {@link Container} subclass
         */
        public Inspector(final Class<? extends Container> clazz) {
            this.clazz = clazz;
            this.helper = Resources.find(clazz);
        }

        /**
         * @return {@link InspectionResult}
         */
        public final InspectionResult inspect() {
            inspectClass();
            for (final Field field : clazz.getDeclaredFields()) {
                if (!isStatic(field.getModifiers())) {
                    inspectInstanceField(field);
                }
            }
            return results.build();
        }

        /**
         *
         */
        private void inspectClass() {
            final Font fontAnnotation = clazz.getAnnotation(Font.class);
            if (fontAnnotation != null) {
                results.setDefaultFontInfo(FontInfo.fromAnnotation(fontAnnotation));
            }
        }

        /**
         * @param field
         *        the {@link Field} to inspect
         */
        private void inspectInstanceField(final Field field) {
            final Class<?> fieldClass = field.getType();
            if (JTextField.class.isAssignableFrom(fieldClass)
                    || JLabel.class.isAssignableFrom(fieldClass)
                    || AbstractButton.class.isAssignableFrom(fieldClass)) {
                final String name = determineFieldName(field);

                final Builder builder = new ComponentField.Builder(name, field);
                final String[] keys = helper.listKeysStartingWith(name);
                for (final String key : keys) {
                    final String value = helper.getString(key);
                    final String subkey = key.substring(name.length() + 1);
                    builder.addProperty(subkey, value);
                }

                final FontInfo fontInfo = determineFontInfo(field);
                if (fontInfo != null) {
                    builder.setFontInfo(fontInfo);
                }
                results.addComponentField(builder.build());
            }
        }

        /**
         * @param field
         *        the {@link Field}
         * @return the field name
         */
        private String determineFieldName(final Field field) {
            String name = null;
            if (field.getAnnotation(TextField.class) != null) {
                name = field.getAnnotation(TextField.class).name();
            } else if (field.getAnnotation(Button.class) != null) {
                name = field.getAnnotation(Button.class).name();
            } else if (field.getAnnotation(RadioButton.class) != null) {
                name = field.getAnnotation(RadioButton.class).name();
            }
            if (isNullOrEmpty(name)) {
                name = field.getName();
            }
            return name;
        }

        /**
         * @param field
         *        the {@link Field}
         * @return {@link FontInfo}
         */
        private FontInfo determineFontInfo(final Field field) {
            if (field.getAnnotation(Font.class) != null) {
                return FontInfo.fromAnnotation(field.getAnnotation(Font.class));
            }
            return null;
        }

    }

}

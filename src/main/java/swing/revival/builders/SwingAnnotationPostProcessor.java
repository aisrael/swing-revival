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

import static swing.revival.util.StringUtils.isNullOrEmpty;

import java.awt.Container;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JTextField;

import swing.revival.annotations.Button;
import swing.revival.annotations.Font;
import swing.revival.annotations.RadioButton;
import swing.revival.annotations.TextField;
import swing.revival.context.ComponentBuilderContext;
import swing.revival.context.ComponentBuilderRegistry;
import swing.revival.context.ComponentField;
import swing.revival.context.DefaultComponentBuilderRegistry;
import swing.revival.context.FontInfo;
import swing.revival.context.InspectionResult;
import swing.revival.context.ComponentField.Builder;
import swing.revival.util.ResourceBundleHelper;
import swing.revival.util.Resources;
import swing.revival.util.reflect.ClassWrapper;
import swing.revival.util.reflect.FieldWrapper;

import com.sun.codemodel.internal.JLabel;

/**
 * @author Alistair A. Israel
 */
public final class SwingAnnotationPostProcessor {

    private ComponentBuilderRegistry componentBuilderRegistry = DefaultComponentBuilderRegistry.getInstance();

    /**
     *
     */
    public SwingAnnotationPostProcessor() {

    }

    /**
     * @return the componentBuilderRegistry
     */
    public ComponentBuilderRegistry getComponentBuilderRegistry() {
        return componentBuilderRegistry;
    }

    /**
     * @param componentBuilderRegistry
     *        the componentBuilderRegistry to set
     */
    public void setComponentBuilderRegistry(final ComponentBuilderRegistry componentBuilderRegistry) {
        this.componentBuilderRegistry = componentBuilderRegistry;
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
     * Inspects the given {@link Container} subclass (typically, a JPanel or JFrame) for Swing Revival annotations.
     * 
     * @author Alistair A. Israel
     */
    public class Inspector {

        private final ClassWrapper clazz;

        private final ResourceBundleHelper helper;

        private final InspectionResult.Builder results = new InspectionResult.Builder();

        /**
         * @param clazz
         *        the {@link Container} subclass
         */
        public Inspector(final Class<? extends Container> clazz) {
            this.clazz = new ClassWrapper(clazz);
            this.helper = Resources.find(clazz);
        }

        /**
         * @return {@link InspectionResult}
         */
        public final InspectionResult inspect() {
            inspectClass();
            for (final FieldWrapper field : clazz.listAllInstanceFields()) {
                inspectInstanceField(field);
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
         *        the {@link FieldWrapper} to inspect
         */
        private void inspectInstanceField(final FieldWrapper field) {
            final Class<?> fieldClass = field.getType();
            if (JTextField.class.isAssignableFrom(fieldClass) || JLabel.class.isAssignableFrom(fieldClass)
                    || AbstractButton.class.isAssignableFrom(fieldClass)) {
                final String name = determineFieldName(field);

                final Builder builder = new ComponentField.Builder(name, field.getField());
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
         *        the {@link FieldWrapper}
         * @return the field name
         */
        private String determineFieldName(final FieldWrapper field) {
            String name = null;
            if (field.isAnnotationPresent(TextField.class)) {
                name = field.getAnnotation(TextField.class).name();
            } else if (field.isAnnotationPresent(Button.class)) {
                name = field.getAnnotation(Button.class).name();
            } else if (field.isAnnotationPresent(RadioButton.class)) {
                name = field.getAnnotation(RadioButton.class).name();
            }
            if (isNullOrEmpty(name)) {
                name = field.getName();
            }
            return name;
        }

        /**
         * @param field
         *        the {@link FieldWrapper}
         * @return {@link FontInfo}
         */
        private FontInfo determineFontInfo(final FieldWrapper field) {
            if (field.isAnnotationPresent(Font.class)) {
                return FontInfo.fromAnnotation(field.getAnnotation(Font.class));
            }
            return null;
        }

    }

}

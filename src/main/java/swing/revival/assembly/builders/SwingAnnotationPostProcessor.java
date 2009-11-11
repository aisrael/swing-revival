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
package swing.revival.assembly.builders;

import static swing.revival.util.StringUtils.isNullOrEmpty;

import java.awt.Container;

import javax.swing.JComponent;

import swing.revival.annotations.Button;
import swing.revival.annotations.Font;
import swing.revival.annotations.RadioButton;
import swing.revival.annotations.TextField;
import swing.revival.assembly.context.AssemblyContext;
import swing.revival.assembly.context.ComponentBuilderFactoryRegistry;
import swing.revival.assembly.context.DefaultComponentBuilderFactoryRegistry;
import swing.revival.assembly.model.ComponentDefinition;
import swing.revival.assembly.model.ContainerDefinition;
import swing.revival.assembly.model.FontInfo;
import swing.revival.assembly.postprocessors.AssemblyPostProcessor;
import swing.revival.util.BeanWrapper;
import swing.revival.util.ResourceBundleHelper;
import swing.revival.util.Resources;
import swing.revival.util.reflect.ClassWrapper;
import swing.revival.util.reflect.FieldWrapper;

/**
 * @author Alistair A. Israel
 */
public final class SwingAnnotationPostProcessor {

    private ComponentBuilderFactoryRegistry componentBuilderFactoryRegistry = DefaultComponentBuilderFactoryRegistry
            .getInstance();

    /**
     *
     */
    public SwingAnnotationPostProcessor() {
    }

    /**
     * @return the componentBuilderFactoryRegistry
     */
    public ComponentBuilderFactoryRegistry getComponentBuilderFactoryRegistry() {
        return componentBuilderFactoryRegistry;
    }

    /**
     * @param componentBuilderFactoryRegistry
     *        the componentBuilderFactoryRegistry to set
     */
    public void setComponentBuilderFactoryRegistry(final ComponentBuilderFactoryRegistry componentBuilderFactoryRegistry) {
        this.componentBuilderFactoryRegistry = componentBuilderFactoryRegistry;
    }

    /**
     * @param container
     *        the {@link Container}-derived object
     */
    public void process(final Container container) {
        final ContainerDefinition containerDefinition = inspect(container.getClass());
        assemble(container, containerDefinition);
    }

    /**
     * @param container
     *        the Container object
     * @param definition
     *        the {@code ContainerDefinition}
     */
    private void assemble(final Container container, final ContainerDefinition definition) {
        final AssemblyContext context = new AssemblyContext(container);
        final BeanWrapper<Container> beanWrapper = new BeanWrapper<Container>(container);
        for (final ComponentDefinition componentDefinition : definition.listComponentDefinitions()) {
            final Class<? extends JComponent> type = componentDefinition.getType();
            final ComponentBuilderFactory<? extends JComponent> factory = componentBuilderFactoryRegistry
                    .getFactory(type);
            final JComponent component = factory.getBuilder(context, componentDefinition).build();
            container.add(component);
            beanWrapper.set(componentDefinition.getField(), component);
            for (final AssemblyPostProcessor postProcessor : context.getPostProcessors()) {
                postProcessor.postProcess(context, componentDefinition, component);
            }
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
     * @return {@link ContainerDefinition}
     */
    public ContainerDefinition inspect(final Class<? extends Container> clazz) {
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

        private final ContainerDefinition.Builder container = new ContainerDefinition.Builder();

        /**
         * @param clazz
         *        the {@link Container} subclass
         */
        public Inspector(final Class<? extends Container> clazz) {
            this.clazz = new ClassWrapper(clazz);
            this.helper = Resources.find(clazz);
        }

        /**
         * @return {@link ContainerDefinition}
         */
        public final ContainerDefinition inspect() {
            inspectClass();
            for (final FieldWrapper field : clazz.listAllInstanceFields()) {
                final Class<?> fieldType = field.getType();
                if (JComponent.class.isAssignableFrom(fieldType)) {
                    inspectComponentField(field);
                }
            }
            return container.build();
        }

        /**
         *
         */
        private void inspectClass() {
            final Font fontAnnotation = clazz.getAnnotation(Font.class);
            if (fontAnnotation != null) {
                container.setDefaultFontInfo(FontInfo.fromAnnotation(fontAnnotation));
            }
        }

        /**
         * @param field
         *        the {@link FieldWrapper}
         */
        @SuppressWarnings("unchecked")
        private void inspectComponentField(final FieldWrapper field) {
            final Class<? extends JComponent> componentType = (Class<? extends JComponent>) field.getType();
            if (componentBuilderFactoryRegistry.hasFactory(componentType)) {
                final String name = determineFieldName(field);

                final ComponentDefinition component = new ComponentDefinition(name, field.getField());
                final String[] keys = helper.listKeysStartingWith(name);
                for (final String key : keys) {
                    final String value = helper.getString(key);
                    final String subkey = key.substring(name.length() + 1);
                    component.addProperty(subkey, value);
                }

                final FontInfo fontInfo = determineFontInfo(field);
                if (fontInfo != null) {
                    component.setFontInfo(fontInfo);
                }
                container.addComponentDefinition(component);
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

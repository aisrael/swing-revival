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
package swing.revival.assembly.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import swing.revival.util.BidiMap;

/**
 * Results of performing reflection on a Container class looking for Swing Revival annotations, performed by
 * {@link swing.revival.assembly.builders.SwingAnnotationPostProcessor.Inspector}.
 *
 * @author Alistair A. Israel
 * @see swing.revival.assembly.builders.SwingAnnotationPostProcessor.Inspector
 */
public class ContainerDefinition {

    private FontInfo defaultFontInfo;

    private final BidiMap<String, String> componentLabelsMap = new BidiMap<String, String>();

    private final Map<String, ComponentDefinition> components = new Hashtable<String, ComponentDefinition>();

    /**
     * @return List of {@link ComponentDefinition}
     */
    public final List<ComponentDefinition> listComponentDefinitions() {
        return new ArrayList<ComponentDefinition>(components.values());
    }

    /**
     * @return the default {@link FontInfo}
     */
    public final FontInfo getDefaultFontInfo() {
        return defaultFontInfo;
    }

    /**
     * @return Set of field names
     */
    public final Set<String> listComponentNames() {
        return components.keySet();
    }

    /**
     * @param componentName
     *        the component name
     * @return the label definition
     */
    public final ComponentDefinition getLabelFor(final String componentName) {
        if (componentLabelsMap.containsKey(componentName)) {
            final String labelName = componentLabelsMap.get(componentName);
            return components.get(labelName);
        }
        return null;
    }

    /**
     * @param name
     *        the field name
     * @return the component field
     */
    public final ComponentDefinition getComponentNamed(final String name) {
        return components.get(name);
    }

    /**
     * A builder pattern.
     *
     * @author Alistair A. Israel
     */
    public static class Builder {

        private final ContainerDefinition container = new ContainerDefinition();

        /**
         * @param fontInfo
         *        the default {@link FontInfo}
         */
        public final void setDefaultFontInfo(final FontInfo fontInfo) {
            container.defaultFontInfo = fontInfo;
        }

        /**
         * @param definition
         *        the {@link ComponentDefinition} to add
         */
        public final void addComponentDefinition(final ComponentDefinition definition) {
            container.components.put(definition.getName(), definition);
        }

        /**
         * @param name
         *        the field name
         * @return the component field
         */
        public final ComponentDefinition getComponentNamed(final String name) {
            return container.components.get(name);
        }

        /**
         * @param componentName
         *        the component name
         * @param labelDefinition
         *        the labelDefinition
         */
        public final void addLabelFor(final String componentName, final ComponentDefinition labelDefinition) {
            addComponentDefinition(labelDefinition);
            container.componentLabelsMap.put(componentName, labelDefinition.getName());
        }

        /**
         * @return the {@link ContainerDefinition}
         */
        public final ContainerDefinition build() {
            return container;
        }
    }
}

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
package swing.revival.metadata;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Results of performing reflection on a Container class looking for Swing Revival annotations, performed by
 * {@link swing.revival.builders.SwingAnnotationPostProcessor.Inspector}.
 *
 * @author Alistair A. Israel
 * @see swing.revival.builders.SwingAnnotationPostProcessor.Inspector
 */
public class ContainerDefinition {

    private FontInfo defaultFontInfo;

    private final Map<String, String> labelsMap = new Hashtable<String, String>();

    private final Map<String, ComponentDefinition> components = new Hashtable<String, ComponentDefinition>();

    /**
     * @return List of {@link ComponentDefinition}
     */
    public final List<ComponentDefinition> listComponentDefinitions() {
        return new ArrayList<ComponentDefinition>(components.values());
    }

    /**
     * @return the defaultFontInfo
     */
    public final FontInfo getDefaultFontInfo() {
        return defaultFontInfo;
    }

    /**
     * @return Set of field names
     */
    public final Set<String> listFieldNames() {
        return components.keySet();
    }

    /**
     * @param name
     *        the field name
     * @return the component field
     */
    public final ComponentDefinition getField(final String name) {
        return components.get(name);
    }

    /**
     * A builder pattern.
     *
     * @author Alistair A. Israel
     */
    public static class Builder {

        private final ContainerDefinition results = new ContainerDefinition();

        /**
         * @param fontInfo
         *        the default {@link FontInfo}
         */
        public final void setDefaultFontInfo(final FontInfo fontInfo) {
            results.defaultFontInfo = fontInfo;
        }

        /**
         * @param definition
         *        the {@link ComponentDefinition} to add
         */
        public final void addComponentDefinition(final ComponentDefinition definition) {
            results.components.put(definition.getName(), definition);
        }

        /**
         * @return the {@link ContainerDefinition}
         */
        public final ContainerDefinition build() {
            return results;
        }
    }
}

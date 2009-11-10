/**
 * swing-revival:
 * Swing Revival Toolkit
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Nov 10, 2009
 */
package swing.revival.construction.postprocessors;

import static swing.revival.util.StringUtils.chomp;

import java.util.Hashtable;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;

import swing.revival.context.AssemblyContext;
import swing.revival.metadata.ComponentDefinition;
import swing.revival.util.ClassUtils;

/**
 * @author Alistair A. Israel
 * @since 0.1
 */
public class LabelsPostProcessor implements AssemblyPostProcessor {

    private final Map<String, JComponent> baseNameComponentMap = new Hashtable<String, JComponent>();

    /**
     * {@inheritDoc}
     *
     * @see AssemblyPostProcessor#postProcess(AssemblyContext, ComponentDefinition, JComponent)
     */
    @Override
    public final void postProcess(final AssemblyContext context, final ComponentDefinition definition,
            final JComponent component) {
        final String componentName = definition.getName();
        if (component instanceof JLabel) {
            final JLabel label = (JLabel) component;

            final String targetName = chomp(componentName, "Label");
            JComponent target = context.getComponentNamed(targetName);
            if (target == null && baseNameComponentMap.containsKey(targetName)) {
                target = baseNameComponentMap.get(targetName);
            }
            if (target != null) {
                label.setLabelFor(target);
            } else {
                baseNameComponentMap.put(targetName, label);
            }
        } else {
            final Class<? extends JComponent> componentType = definition.getType();
            final String baseName = determineBaseName(componentName, componentType);
            if (baseNameComponentMap.containsKey(baseName)) {
                final JLabel label = (JLabel) baseNameComponentMap.get(baseName);
                label.setLabelFor(component);
            } else {
                baseNameComponentMap.put(baseName, component);
            }
        }
    }

    /**
     * @param componentName
     *        the component name
     * @param componentType
     *        the component type
     * @return the base name
     */
    private String determineBaseName(final String componentName, final Class<? extends JComponent> componentType) {
        String baseName = componentName;
        final String suffix = ClassUtils.getShortName(componentType);
        if (componentName.endsWith(suffix)) {
            baseName = chomp(componentName, suffix);
        } else if (suffix.charAt(0) == 'J') {
            final String shortSuffix = suffix.substring(1);
            if (componentName.endsWith(shortSuffix)) {
                baseName = chomp(componentName, shortSuffix);
            }
        }
        return baseName;
    }

}

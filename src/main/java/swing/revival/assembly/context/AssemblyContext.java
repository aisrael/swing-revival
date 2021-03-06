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
package swing.revival.assembly.context;

import java.awt.Container;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;

import swing.revival.assembly.builders.FontPostProcessor;
import swing.revival.assembly.inspectors.ComponentInspector;
import swing.revival.assembly.inspectors.ContainerInspector;
import swing.revival.assembly.model.ContainerContext;
import swing.revival.assembly.postprocessors.AssemblyPostProcessor;
import swing.revival.assembly.postprocessors.LabelsPostProcessor;
import swing.revival.util.ResourceBundleHelper;

/**
 * @author Alistair A. Israel
 */
public class AssemblyContext {

    private final ContainerContext containerContext;

    private final Map<String, JComponent> components = new Hashtable<String, JComponent>();

    private final List<ContainerInspector> containerInspectors = new ArrayList<ContainerInspector>();

    private final List<ComponentInspector> componentInspectors = new ArrayList<ComponentInspector>();

    private final List<AssemblyPostProcessor> postProcessors = new ArrayList<AssemblyPostProcessor>();

    /**
     * @param container
     *        {@link java.awt.Container}, typically a JPanel or a JFrame
     */
    public AssemblyContext(final Container container) {
        this.containerContext = new ContainerContext(container);

        final FontPostProcessor fontPostProcessor = new FontPostProcessor();
        containerInspectors.add(fontPostProcessor);
        componentInspectors.add(fontPostProcessor);
        addPostProcessor(fontPostProcessor);

        addPostProcessor(new LabelsPostProcessor());
    }

    /**
     * @return the container
     */
    public final Container getContainer() {
        return containerContext.getContainer();
    }

    /**
     * @return the containerInspectors
     */
    public final List<ContainerInspector> getContainerInspectors() {
        return containerInspectors;
    }

    /**
     * @return the componentInspectors
     */
    public final List<ComponentInspector> getComponentInspectors() {
        return componentInspectors;
    }

    /**
     * @return the postProcessors
     */
    public final List<AssemblyPostProcessor> getPostProcessors() {
        return postProcessors;
    }

    /**
     * @param postProcessor
     *        the {@link AssemblyPostProcessor} to add
     */
    public final void addPostProcessor(final AssemblyPostProcessor postProcessor) {
        postProcessors.add(postProcessor);
    }

    /**
     * @return the resources
     */
    public final ResourceBundleHelper getResources() {
        return containerContext.getResources();
    }

    /**
     * @param name
     *        the component name
     * @return the {@link JComponent}
     */
    public final JComponent getComponentNamed(final String name) {
        return components.get(name);
    }

    /**
     * AssemblyContext.Aware is basically just a base class for subclasses that need AssemblyContext awareness.
     *
     * @author Alistair A. Israel
     */
    public static class Aware {

        private final AssemblyContext context;

        /**
         * @param context
         *        the {@link AssemblyContext}
         */
        public Aware(final AssemblyContext context) {
            this.context = context;
        }

        /**
         * @return the context
         */
        public final AssemblyContext getContext() {
            return context;
        }

        /**
         * @param key
         *        the 'short' key for the desired string
         * @return <code>true</code> if the <code>prefix</code>.<code>key</code> is contained in the underlying
         *         <code>ResourceBundle</code> or its parent bundles; <code>false</code> otherwise.
         * @see ResourceBundleHelper#containsKey(java.lang.String)
         */
        public final boolean containsResourceKey(final String key) {
            return context.containerContext.getResources().containsKey(key);
        }

        /**
         * @param key
         *        the 'short' key for the desired string
         * @return the resource string
         * @see java.util.ResourceBundle#getString(java.lang.String)
         * @see ResourceBundleHelper#getString(java.lang.String)
         */
        public final String getResourceString(final String key) {
            return context.containerContext.getResources().getString(key);
        }

    }

}

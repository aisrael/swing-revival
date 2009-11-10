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
package swing.revival.context;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import swing.revival.builders.FontPostProcessor;
import swing.revival.builders.LabelsPostProcessor;
import swing.revival.construction.postprocessors.JComponentFieldPostProcessor;
import swing.revival.util.ResourceBundleHelper;

/**
 * @author Alistair A. Israel
 */
public class ComponentBuilderContext {

    private final List<JComponentFieldPostProcessor> postProcessors = new ArrayList<JComponentFieldPostProcessor>();

    private final FontPostProcessor fontPostProcessor;

    private final ResourceBundleHelper resources;

    /**
     * @param container
     *        {@link java.awt.Container}, typically a JPanel or a JFrame
     */
    public ComponentBuilderContext(final Container container) {
        final Class<? extends Container> containerClass = container.getClass();
        resources = ResourceBundleHelper.forClass(containerClass);
        fontPostProcessor = new FontPostProcessor(container.getClass());
        addPostProcessor(new LabelsPostProcessor(this));
    }

    /**
     * @return the postProcessors
     */
    public final List<JComponentFieldPostProcessor> getPostProcessors() {
        return postProcessors;
    }

    /**
     * @param postProcessor
     *        the {@link JComponentFieldPostProcessor} to add
     */
    public final void addPostProcessor(final JComponentFieldPostProcessor postProcessor) {
        postProcessors.add(postProcessor);
    }

    /**
     * @return the {@link FontPostProcessor}
     */
    public final FontPostProcessor getFontPostProcessor() {
        return fontPostProcessor;
    }

    /**
     * @return the default {@link FontInfo}
     * @see swing.revival.builders.FontPostProcessor#getDefaultFontInfo()
     */
    public final FontInfo getDefaultFontInfo() {
        return fontPostProcessor.getDefaultFontInfo();
    }

    /**
     * @return the resources
     */
    public final ResourceBundleHelper getResources() {
        return resources;
    }

    /**
     * ComponentBuilderContext.Aware is basically just a base class for subclasses that need ComponentBuilderContext
     * awareness.
     *
     * @author Alistair A. Israel
     */
    public static class Aware {

        private final ComponentBuilderContext context;

        /**
         * @param context
         *        the {@link ComponentBuilderContext}
         */
        public Aware(final ComponentBuilderContext context) {
            this.context = context;
        }

        /**
         * @return the context
         */
        public final ComponentBuilderContext getContext() {
            return context;
        }

        /**
         * @return the {@link FontPostProcessor}
         * @see swing.revival.context.ComponentBuilderContext#getFontPostProcessor()
         */
        public final FontPostProcessor getFontPostProcessor() {
            return context.getFontPostProcessor();
        }

        /**
         * @return the default FontInfo
         * @see swing.revival.context.ComponentBuilderContext#getDefaultFontInfo()
         */
        public final FontInfo getDefaultFontInfo() {
            return context.getDefaultFontInfo();
        }

        /**
         * @param key
         *        the 'short' key for the desired string
         * @return <code>true</code> if the <code>prefix</code>.<code>key</code> is contained in the underlying
         *         <code>ResourceBundle</code> or its parent bundles; <code>false</code> otherwise.
         * @see ResourceBundleHelper#containsKey(java.lang.String)
         */
        public final boolean containsResourceKey(final String key) {
            return context.resources.containsKey(key);
        }

        /**
         * @param key
         *        the 'short' key for the desired string
         * @return the resource string
         * @see java.util.ResourceBundle#getString(java.lang.String)
         * @see ResourceBundleHelper#getString(java.lang.String)
         */
        public final String getResourceString(final String key) {
            return context.resources.getString(key);
        }

    }

}

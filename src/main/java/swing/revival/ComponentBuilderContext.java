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
package swing.revival;

import java.awt.Font;

import javax.swing.JComponent;

import swing.revival.builders.FontPostProcessor;
import swing.revival.util.ResourceBundleHelper;

/**
 *
 * @author Alistair A. Israel
 */
public class ComponentBuilderContext extends ResourceBundleHelper {

    private final FontPostProcessor fontPostProcessor;

    /**
     * @param component
     *        {@link JComponent}, typically a JPanel
     */
    public ComponentBuilderContext(final JComponent component) {
        super(component.getClass());
        fontPostProcessor = new FontPostProcessor(component.getClass());
    }

    /**
     * @return the {@link FontPostProcessor}
     */
    public final FontPostProcessor getFontPostProcessor() {
        return fontPostProcessor;
    }

    /**
     * @return the default font
     * @see swing.revival.builders.FontPostProcessor#getDefaultFont()
     */
    public final Font getDefaultFont() {
        return fontPostProcessor.getDefaultFont();
    }

    /**
     * ComponentBuilderContext.Aware is basically just a base class for
     * subclasses that need ComponentBuilderContext awareness.
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
         * @see swing.revival.ComponentBuilderContext#getFontPostProcessor()
         */
        public final FontPostProcessor getFontPostProcessor() {
            return context.getFontPostProcessor();
        }

        /**
         * @return the defaultFont
         * @see swing.revival.ComponentBuilderContext#getDefaultFont()
         */
        public final Font getDefaultFont() {
            return context.getDefaultFont();
        }

        /**
         * @param key
         *        the 'short' key for the desired string
         * @return <code>true</code> if the <code>prefix</code>.<code>key</code>
         *         is contained in the underlying <code>ResourceBundle</code> or
         *         its parent bundles; <code>false</code> otherwise.
         * @see ResourceBundleHelper#containsKey(java.lang.String)
         */
        public final boolean containsResourceKey(final String key) {
            return context.containsKey(key);
        }

        /**
         * @param key
         *        the 'short' key for the desired string
         * @return the resource string
         * @see java.util.ResourceBundle#getString(java.lang.String)
         * @see ResourceBundleHelper#getString(java.lang.String)
         */
        public final String getResourceString(final String key) {
            return context.getString(key);
        }

    }

}

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

import java.awt.Container;

import javax.swing.JComponent;

import swing.revival.context.ComponentBuilderContext;
import swing.revival.metadata.ComponentDefinition;

/**
 * @author Alistair A. Israel
 * @since 0.1
 */
public interface JComponentFieldPostProcessor {

    /**
     * @param container
     *        the {@link Container}
     * @param context
     *        the {@link ComponentBuilderContext}
     * @param definition
     *        the {@link ComponentDefinition}
     * @param component
     *        the {@link JComponent}F
     */
    void postProcess(final Container container, final ComponentBuilderContext context,
            final ComponentDefinition definition, final JComponent component);
}

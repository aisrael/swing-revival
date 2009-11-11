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
package swing.revival.assembly.postprocessors;

import javax.swing.JComponent;

import swing.revival.assembly.model.ComponentDefinition;
import swing.revival.context.AssemblyContext;

/**
 * @author Alistair A. Israel
 * @since 0.1
 */
public interface AssemblyPostProcessor {

    /**
     * @param context
     *        the {@link AssemblyContext}
     * @param definition
     *        the {@link ComponentDefinition}
     * @param component
     *        the {@link JComponent}F
     */
    void postProcess(final AssemblyContext context, final ComponentDefinition definition, final JComponent component);
}

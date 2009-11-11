/**
 * swing-revival:
 * Swing Revival Toolkit
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Nov 11, 2009
 */
package swing.revival.assembly.inspectors;

import swing.revival.assembly.context.AssemblyContext;
import swing.revival.assembly.model.ContainerDefinition;

/**
 * @author Alistair A. Israel
 * @since 0.2
 */
public interface ContainerInspector {

    /**
     * @param context
     *        the {@link AssemblyContext}
     * @param containerDefinitionBuilder
     *        the {@link ContainerDefinition.Builder}
     */
    void inspect(final AssemblyContext context, final ContainerDefinition.Builder containerDefinitionBuilder);
}

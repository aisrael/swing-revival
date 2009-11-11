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

import swing.revival.assembly.model.ComponentDefinition;

/**
 * @author Alistair A. Israel
 * @since 0.2
 */
public interface ComponentInspector {

    /**
     * @param definition
     *        the {@link ComponentDefinition}
     */
    void inspect(final ComponentDefinition definition);
}

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

import javax.swing.JComponent;

import swing.revival.metadata.ComponentFieldInfo;

/**
 * @author Alistair A. Israel
 * @since 0.1
 */
public interface JComponentFieldPostProcessor {

    /**
     * @param fieldInfo
     *        the {@link ComponentFieldInfo}
     * @param component the {@link JComponent}F
     */
    void postProcess(final ComponentFieldInfo fieldInfo, final JComponent component);
}

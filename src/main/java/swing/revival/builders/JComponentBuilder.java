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
package swing.revival.builders;

import javax.swing.JComponent;

/**
 *
 * @author Alistair A. Israel
 */
public interface JComponentBuilder<T extends JComponent> {

    /**
     * @return the built JComponent
     */
    T build();

    /**
     *
     * @author Alistair A. Israel
     */
    interface Factory {

        JComponentBuilder<?> getBuilder();
    }
}

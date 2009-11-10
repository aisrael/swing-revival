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
package swing.revival.context;

import javax.swing.JComponent;

import swing.revival.builders.ComponentBuilder;


/**
 *
 * @author Alistair A. Israel
 */
public interface ComponentBuilderRegistry {

    /**
     * @param type
     * @return
     */
    boolean hasBuilderFor(final Class<? extends JComponent> type);

    /**
     * @param <C>
     * @param type
     * @return
     */
    <C extends JComponent> ComponentBuilder<C> getBuilderFor(final Class<C> type);
}

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
 * @author Alistair A. Israel
 */
public interface ComponentBuilderRegistry {

    /**
     * @param type
     *        the class object representing the component type
     * @return <code>true</code> if the registry contains a builder for the specified component type
     */
    boolean hasBuilderFor(final Class<? extends JComponent> type);

    /**
     * @param <C>
     *        the component type
     * @param type
     *        the class object representing the component type
     * @return the ComponentBuilder for the specified type
     */
    <C extends JComponent> ComponentBuilder<C> getBuilderFor(final Class<C> type);
}

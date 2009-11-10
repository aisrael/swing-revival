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

import swing.revival.builders.ComponentBuilderFactory;

/**
 * @author Alistair A. Israel
 */
public interface ComponentBuilderFactoryRegistry {

    /**
     * @param type
     *        the class object representing the component type
     * @return <code>true</code> if the registry contains a builder for the specified component type
     */
    boolean hasFactoryFor(final Class<? extends JComponent> type);

    /**
     * @param <C>
     *        the component type
     * @param type
     *        the class object representing the component type
     * @return the ComponentBuilder for the specified type
     */
    <C extends JComponent> ComponentBuilderFactory<C> getFactoryFor(final Class<C> type);
}

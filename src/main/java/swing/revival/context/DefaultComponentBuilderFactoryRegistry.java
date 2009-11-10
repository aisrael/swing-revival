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
package swing.revival.context;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.swing.JComponent;

import swing.revival.builders.ComponentBuilderFactory;

/**
 * @author Alistair A. Israel
 * @since 0.1
 */
public final class DefaultComponentBuilderFactoryRegistry implements ComponentBuilderFactoryRegistry {

    private final ConcurrentMap<Class<? extends JComponent>, ComponentBuilderFactory<? extends JComponent>> componentBuilderFactoryMap
        = new ConcurrentHashMap<Class<? extends JComponent>, ComponentBuilderFactory<? extends JComponent>>();

    /**
     * Initialization on demand holder idiom
     * 
     * @see <a href="http://en.wikipedia.org/wiki/Initialization_on_demand_holder_idiom">Wikipedia: Initialization on
     *      demand holder idiom</a>
     */
    // CHECKSTYLE:OFF
    private static class Holder {
        // CHECKSTYLE:ON
        private static final DefaultComponentBuilderFactoryRegistry INSTANCE = new DefaultComponentBuilderFactoryRegistry();
    }

    /**
     * Static factory method that returns the {@link DefaultComponentBuilderFactoryRegistry} singleton.
     * 
     * @return the {@link DefaultComponentBuilderFactoryRegistry} instance
     */
    public static DefaultComponentBuilderFactoryRegistry getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Hidden constructor. Initializes the component builder registry with the most common component builders.
     */
    private DefaultComponentBuilderFactoryRegistry() {
    }

    /**
     * {@inheritDoc}
     * 
     * @see swing.revival.context.ComponentBuilderFactoryRegistry#hasFactory(java.lang.Class)
     */
    @Override
    public boolean hasFactory(final Class<? extends JComponent> type) {
        return componentBuilderFactoryMap.containsKey(type);
    }

    /**
     * {@inheritDoc}
     * 
     * @see swing.revival.context.ComponentBuilderFactoryRegistry#getFactory(java.lang.Class)
     */
    @Override
    @SuppressWarnings("unchecked")
    public <C extends JComponent> ComponentBuilderFactory<C> getFactory(final Class<C> type) {
        return (ComponentBuilderFactory<C>) componentBuilderFactoryMap.get(type);
    }

}

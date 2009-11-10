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

import swing.revival.builders.ComponentBuilder;

/**
 * @author Alistair A. Israel
 * @since 0.1
 */
public final class DefaultComponentBuilderRegistry implements ComponentBuilderRegistry {

    private final ConcurrentMap<Class<? extends JComponent>, ComponentBuilder<? extends JComponent>> componentBuilderMap
        = new ConcurrentHashMap<Class<? extends JComponent>, ComponentBuilder<? extends JComponent>>();

    /**
     * Initialization on demand holder idiom
     * 
     * @see <a href="http://en.wikipedia.org/wiki/Initialization_on_demand_holder_idiom">Wikipedia: Initialization on
     *      demand holder idiom</a>
     */
    // CHECKSTYLE:OFF
    private static class Holder {
        // CHECKSTYLE:ON
        private static final DefaultComponentBuilderRegistry INSTANCE = new DefaultComponentBuilderRegistry();
    }

    /**
     * Static factory method that returns the {@link DefaultComponentBuilderRegistry} singleton.
     * 
     * @return the {@link DefaultComponentBuilderRegistry} instance
     */
    public static DefaultComponentBuilderRegistry getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Hidden constructor. Initializes the component builder registry with the most common component builders.
     */
    private DefaultComponentBuilderRegistry() {
    }

    /**
     * {@inheritDoc}
     * 
     * @see swing.revival.context.ComponentBuilderRegistry#hasBuilderFor(java.lang.Class)
     */
    @Override
    public boolean hasBuilderFor(final Class<? extends JComponent> type) {
        return componentBuilderMap.containsKey(type);
    }

    /**
     * {@inheritDoc}
     * 
     * @see swing.revival.context.ComponentBuilderRegistry#getBuilderFor(java.lang.Class)
     */
    @Override
    @SuppressWarnings("unchecked")
    public <C extends JComponent> ComponentBuilder<C> getBuilderFor(final Class<C> type) {
        return (ComponentBuilder<C>) componentBuilderMap.get(type);
    }

}

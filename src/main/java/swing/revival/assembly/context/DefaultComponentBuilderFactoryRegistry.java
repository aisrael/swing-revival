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
package swing.revival.assembly.context;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import swing.revival.SwingRevivalException;
import swing.revival.assembly.builders.ComponentBuilderFactory;
import swing.revival.assembly.builders.JLabelBuilder;
import swing.revival.assembly.builders.JPasswordFieldBuilder;
import swing.revival.assembly.builders.JTextFieldBuilder;

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
        addFactoryClass(JTextField.class, JTextFieldBuilder.Factory.class);
        addFactoryClass(JPasswordField.class, JPasswordFieldBuilder.Factory.class);
        addFactoryClass(JLabel.class, JLabelBuilder.Factory.class);
    }

    /**
     * @param <T>
     *        a JComponent-derived type
     * @param type
     *        the class representing the type
     * @param factoryClass
     *        the factory class
     */
    public <T extends JComponent> void addFactoryClass(final Class<T> type,
            final Class<? extends ComponentBuilderFactory<T>> factoryClass) {
        try {
            final ComponentBuilderFactory<T> factory = factoryClass.newInstance();
            componentBuilderFactoryMap.put(type, factory);
        } catch (final InstantiationException e) {
            throw new SwingRevivalException(e);
        } catch (final IllegalAccessException e) {
            throw new SwingRevivalException(e);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see swing.revival.assembly.context.ComponentBuilderFactoryRegistry#hasFactory(java.lang.Class)
     */
    @Override
    public boolean hasFactory(final Class<? extends JComponent> type) {
        return componentBuilderFactoryMap.containsKey(type);
    }

    /**
     * {@inheritDoc}
     *
     * @see swing.revival.assembly.context.ComponentBuilderFactoryRegistry#getFactory(java.lang.Class)
     */
    @Override
    @SuppressWarnings("unchecked")
    public <C extends JComponent> ComponentBuilderFactory<C> getFactory(final Class<C> type) {
        return (ComponentBuilderFactory<C>) componentBuilderFactoryMap.get(type);
    }

}

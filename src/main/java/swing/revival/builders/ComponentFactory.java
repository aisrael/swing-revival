/**
 * swing-revival:
 * Swing Revival Toolkit
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Sep 30, 2009
 */
package swing.revival.builders;

import java.lang.reflect.Field;

import javax.swing.JComponent;

import swing.revival.ComponentBuilderContext;

/**
 * @param <C>
 *        a type
 * @author Alistair A. Israel
 */
public interface ComponentFactory<C extends JComponent> {

    /**
     * @param context
     *        the {@link ComponentBuilderContext} we're building in
     * @param field
     *        the {@link Field} to return a builder for
     * @return the field {@link ComponentBuilder}
     */
    ComponentBuilder<C> build(ComponentBuilderContext context, Field field);
}

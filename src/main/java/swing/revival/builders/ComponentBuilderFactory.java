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

import javax.swing.JComponent;

import swing.revival.context.ComponentBuilderContext;
import swing.revival.metadata.ComponentDefinition;

/**
 * @param <C>
 *        a type
 * @author Alistair A. Israel
 */
public interface ComponentBuilderFactory<C extends JComponent> {

    /**
     * @param context
     *        the {@link ComponentBuilderContext} we're building in
     * @param fieldInfo
     *        the {@link ComponentDefinition} to return a builder for
     * @return the field {@link ComponentBuilder}
     */
    ComponentBuilder<C> getBuilder(ComponentBuilderContext context, ComponentDefinition fieldInfo);
}

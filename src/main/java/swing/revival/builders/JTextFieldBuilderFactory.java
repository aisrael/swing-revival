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

import javax.swing.JTextField;

import swing.revival.context.ComponentBuilderContext;

/**
 * @author Alistair A. Israel
 */
public class JTextFieldBuilderFactory implements ComponentBuilderFactory<JTextField> {

    /**
     * {@inheritDoc}
     * 
     * @see swing.revival.builders.ComponentBuilderFactory#build(swing.revival.context.ComponentBuilderContext,
     *      java.lang.reflect.Field)
     */
    @Override
    public final ComponentBuilder<JTextField> build(final ComponentBuilderContext context, final Field field) {
        return new JTextFieldBuilder(context, field);
    }

}

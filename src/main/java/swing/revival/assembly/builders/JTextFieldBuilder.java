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
package swing.revival.assembly.builders;

import javax.swing.JTextField;

import swing.revival.annotations.TextField;
import swing.revival.assembly.context.AssemblyContext;
import swing.revival.assembly.model.ComponentDefinition;

/**
 * @author Alistair A. Israel
 * @since 0.1
 */
public class JTextFieldBuilder extends ComponentBuilder<JTextField> {

    /**
     * @param context
     *        the {@link AssemblyContext} we're building in
     * @param fieldInfo
     *        the field we're building for
     */
    public JTextFieldBuilder(final AssemblyContext context, final ComponentDefinition fieldInfo) {
        super(context, fieldInfo);
    }

    /**
     * {@inheritDoc}
     *
     * @see swing.revival.assembly.builders.ComponentBuilder#build()
     */
    @Override
    protected final JTextField constructComponent() {
        final TextField textFieldAnnotation = getFieldInfo().getField().getAnnotation(TextField.class);
        int columns = -1;
        if (textFieldAnnotation != null) {
            columns = textFieldAnnotation.columns();
        }

        if (columns > 0) {
            return new JTextField(columns);
        } else {
            return new JTextField();
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see swing.revival.assembly.builders.ComponentBuilder#getBaseName()
     */
    @Override
    public final String getBaseName() {
        final String name = getFieldInfo().getName();
        if (name.endsWith("TextField")) {
            return name.substring(0, name.length() - "TextField".length());
        }
        return name;
    }

    /**
     *
     */
    public static class Factory implements ComponentBuilderFactory<JTextField> {

        /**
         * {@inheritDoc}
         *
         * @see swing.revival.assembly.builders.ComponentBuilderFactory#getBuilder(swing.revival.assembly.context.AssemblyContext,
         *      java.lang.reflect.Field)
         */
        @Override
        public final ComponentBuilder<JTextField> getBuilder(final AssemblyContext context,
                final ComponentDefinition fieldInfo) {
            return new JTextFieldBuilder(context, fieldInfo);
        }

    }

}
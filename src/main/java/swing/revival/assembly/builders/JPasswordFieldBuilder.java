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
package swing.revival.assembly.builders;

import javax.swing.JPasswordField;

import swing.revival.annotations.TextField;
import swing.revival.assembly.context.AssemblyContext;
import swing.revival.assembly.model.ComponentDefinition;

/**
 * @author Alistair A. Israel
 */
public class JPasswordFieldBuilder extends ComponentBuilder<JPasswordField> {

    /**
     * @param context
     *        the {@link AssemblyContext} we're building in
     * @param fieldInfo
     *        the field we're building for
     */
    public JPasswordFieldBuilder(final AssemblyContext context, final ComponentDefinition fieldInfo) {
        super(context, fieldInfo);
    }

    /**
     * {@inheritDoc}
     *
     * @see swing.revival.assembly.builders.ComponentBuilder#build()
     */
    @Override
    protected final JPasswordField constructComponent() {
        final TextField textFieldAnnotation = getFieldInfo().getField().getAnnotation(TextField.class);
        int columns = -1;
        if (textFieldAnnotation != null) {
            columns = textFieldAnnotation.columns();
        }

        if (columns > 0) {
            return new JPasswordField(columns);
        } else {
            return new JPasswordField();
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
        if (name.endsWith("PasswordField")) {
            return name.substring(0, name.length() - "PasswordField".length());
        }
        return name;
    }

    /**
     *
     */
    public static class Factory implements ComponentBuilderFactory<JPasswordField> {

        /**
         * {@inheritDoc}
         *
         * @see swing.revival.assembly.builders.ComponentBuilderFactory#getBuilder(swing.revival.assembly.context.AssemblyContext,
         *      java.lang.reflect.Field)
         */
        @Override
        public final ComponentBuilder<JPasswordField> getBuilder(final AssemblyContext context,
                final ComponentDefinition fieldInfo) {
            return new JPasswordFieldBuilder(context, fieldInfo);
        }

    }

}

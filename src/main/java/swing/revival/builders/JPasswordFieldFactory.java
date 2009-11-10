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

import javax.swing.JPasswordField;

import swing.revival.annotations.TextField;
import swing.revival.context.ComponentBuilderContext;
import swing.revival.context.ComponentFieldInfo;

/**
 * @author Alistair A. Israel
 */
public class JPasswordFieldFactory implements ComponentBuilderFactory<JPasswordField> {

    /**
     * {@inheritDoc}
     * 
     * @see swing.revival.builders.ComponentBuilderFactory#build(swing.revival.context.ComponentBuilderContext,
     *      java.lang.reflect.Field)
     */
    @Override
    public final ComponentBuilder<JPasswordField> build(final ComponentBuilderContext context,
            final ComponentFieldInfo fieldInfo) {
        return new JPasswordFieldBuilder(context, fieldInfo);
    }

    /**
     * @author Alistair A. Israel
     */
    public static class JPasswordFieldBuilder extends ComponentBuilder<JPasswordField> {

        /**
         * @param context
         *        the {@link ComponentBuilderContext} we're building in
         * @param fieldInfo
         *        the field we're building for
         */
        public JPasswordFieldBuilder(final ComponentBuilderContext context, final ComponentFieldInfo fieldInfo) {
            super(context, fieldInfo);
        }

        /**
         * {@inheritDoc}
         * 
         * @see swing.revival.builders.ComponentBuilder#build()
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
         * @see swing.revival.builders.ComponentBuilder#getBaseName()
         */
        @Override
        public final String getBaseName() {
            final String name = getFieldInfo().getName();
            if (name.endsWith("PasswordField")) {
                return name.substring(0, name.length() - "PasswordField".length());
            }
            return name;
        }

    }

}

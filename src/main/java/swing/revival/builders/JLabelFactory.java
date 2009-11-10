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

import javax.swing.JLabel;

import swing.revival.context.ComponentBuilderContext;
import swing.revival.util.StringUtils;

/**
 *
 * @author Alistair A. Israel
 */
public class JLabelFactory implements ComponentFactory<JLabel> {

    /**
     * {@inheritDoc}
     *
     * @see swing.revival.builders.ComponentFactory#build(swing.revival.context.ComponentBuilderContext,
     *      java.lang.reflect.Field)
     */
    @Override
    public final swing.revival.builders.ComponentBuilder<JLabel> build(
            final ComponentBuilderContext context, final Field field) {
        return new JLabelBuilder(context, field);
    }

    /**
     *
     * @author Alistair A. Israel
     */
    public static class JLabelBuilder extends ComponentBuilder<JLabel> {

        /**
         * @param context
         *        the {@link ComponentBuilderContext} we're building in
         * @param field
         *        the field we're building for
         */
        public JLabelBuilder(final ComponentBuilderContext context, final Field field) {
            super(context, field);
        }

        /**
         * {@inheritDoc}
         *
         * @see swing.revival.builders.ComponentBuilder#constructComponent()
         */
        @Override
        protected final JLabel constructComponent() {
            final JLabel label = new JLabel();
            final String name = getField().getName();
            final String text = getResourceString(name + ".text");
            if (StringUtils.hasLength(text)) {
                label.setText(text);
            } else {
                label.setText(name);
            }
            return label;
        }

        /**
         * {@inheritDoc}
         *
         * @see swing.revival.builders.ComponentBuilder#getBaseName()
         */
        @Override
        public final String getBaseName() {
            final String name = getField().getName();
            if (name.endsWith("Label")) {
                return name.substring(0, name.length() - "Label".length());
            }
            return name;
        }

    }

}

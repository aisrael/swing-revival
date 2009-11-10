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
package swing.revival.builders;

import javax.swing.JLabel;

import swing.revival.context.AssemblyContext;
import swing.revival.metadata.ComponentDefinition;
import swing.revival.util.StringUtils;

/**
 * @author Alistair A. Israel
 */
public class JLabelBuilder extends ComponentBuilder<JLabel> {

    /**
     * @param context
     *        the {@link AssemblyContext} we're building in
     * @param fieldInfo
     *        the field we're building for
     */
    public JLabelBuilder(final AssemblyContext context, final ComponentDefinition fieldInfo) {
        super(context, fieldInfo);
    }

    /**
     * {@inheritDoc}
     *
     * @see swing.revival.builders.ComponentBuilder#constructComponent()
     */
    @Override
    protected final JLabel constructComponent() {
        final JLabel label = new JLabel();
        final String name = getFieldInfo().getName();
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
        final String name = getFieldInfo().getName();
        if (name.endsWith("Label")) {
            return name.substring(0, name.length() - "Label".length());
        }
        return name;
    }

    /**
     *
     */
    public static class Factory implements ComponentBuilderFactory<JLabel> {

        /**
         * {@inheritDoc}
         *
         * @see swing.revival.builders.ComponentBuilderFactory#getBuilder(swing.revival.context.AssemblyContext,
         *      java.lang.reflect.Field)
         */
        @Override
        public final ComponentBuilder<JLabel> getBuilder(final AssemblyContext context,
                final ComponentDefinition fieldInfo) {
            return new JLabelBuilder(context, fieldInfo);
        }

    }

}
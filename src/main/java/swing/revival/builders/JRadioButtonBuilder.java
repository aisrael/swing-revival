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

import javax.swing.JRadioButton;

import swing.revival.context.ComponentBuilderContext;
import swing.revival.metadata.ComponentFieldInfo;

/**
 *
 * @author Alistair A. Israel
 */
public class JRadioButtonBuilder extends ComponentBuilder<JRadioButton> {

    /**
     * @param context
     *        the {@link ComponentBuilderContext} we're building in
     * @param field
     *        the field we're building for
     */
    public JRadioButtonBuilder(final ComponentBuilderContext context, final ComponentFieldInfo field) {
        super(context, field);
    }

    /**
     * {@inheritDoc}
     *
     * @see swing.revival.builders.ComponentBuilder#constructComponent()
     */
    @Override
    protected final JRadioButton constructComponent() {
        return new JRadioButton();
    }

    /**
     * {@inheritDoc}
     *
     * @see swing.revival.builders.ComponentBuilder#getBaseName()
     */
    @Override
    public final String getBaseName() {
        final String name = getFieldInfo().getName();
        final String baseName;
        if (name.endsWith("RadioButton")) {
            baseName = name.substring(0, name.length() - "RadioButton".length());
        } else if (name.endsWith("Button")) {
            baseName = name.substring(0, name.length() - "Button".length());
        } else {
            baseName = name;
        }
        return baseName;
    }
}

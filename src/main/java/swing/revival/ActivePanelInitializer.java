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
package swing.revival;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Logger;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import swing.revival.builders.ComponentBuilder;
import swing.revival.builders.JLabelFactory;
import swing.revival.builders.JPasswordFieldFactory;
import swing.revival.builders.JTextFieldFactory;
import swing.revival.util.BeanWrapper;

/**
 *
 * @author Alistair A. Israel
 */
public class ActivePanelInitializer extends BeanWrapper.Support<ActivePanel> {

    private static final Logger LOGGER = Logger.getLogger(ActivePanel.class.getName());

    private static final JLabelFactory LABEL_FACTORY = new JLabelFactory();

    private static final JTextFieldFactory TEXT_FIELD_FACTORY = new JTextFieldFactory();

    private static final JPasswordFieldFactory PASSWORD_FIELD_FACTORY =
            new JPasswordFieldFactory();

    private final Map<String, JLabel> labels = new Hashtable<String, JLabel>();

    private final Map<String, JComponent> components = new Hashtable<String, JComponent>();

    private final ActivePanel activePanel;

    /**
     * @param activePanel
     *        the {@link JComponent}
     */
    public ActivePanelInitializer(final ActivePanel activePanel) {
        super(activePanel);
        this.activePanel = activePanel;
    }

    /**
    *
    */
    public final void build() {
        final ComponentBuilderContext context = new ComponentBuilderContext(activePanel);
        for (final Field field : getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers())) {
                final Object value = get(field);
                final Class<?> type = field.getType();
                LOGGER.finest("Got field \"" + field.getName() + "\" of declared type "
                        + type.getName());
                if (JComponent.class.isAssignableFrom(type) && value == null) {
                    final JComponent component = createComponent(context, field);
                    if (component != null) {
                        set(field, component);
                        activePanel.add(component);
                    }
                }
            }
        }
    }

    /**
     * @param context
     *        the {@link ComponentBuilderContext}
     * @param field
     *        the {@link Field} we're building for
     * @return the built component
     */
    private JComponent createComponent(final ComponentBuilderContext context,
            final Field field) {
        final String name = field.getName();
        JComponent component = null;
        String baseName = name;
        if (JLabel.class.isAssignableFrom(field.getType())) {
            final ComponentBuilder<JLabel> builder = LABEL_FACTORY.build(context, field);
            final JLabel label = builder.build();
            baseName = builder.getBaseName();
            if (components.containsKey(baseName)) {
                label.setLabelFor(components.get(baseName));
            } else {
                labels.put(baseName, label);
            }
            component = label;
        } else {
            if (JTextField.class.isAssignableFrom(field.getType())) {
                if (field.getType() == JTextField.class) {
                    final ComponentBuilder<JTextField> builder =
                            TEXT_FIELD_FACTORY.build(context, field);
                    baseName = builder.getBaseName();
                    component = builder.build();
                } else if (field.getType() == JPasswordField.class) {
                    final ComponentBuilder<JPasswordField> builder =
                            PASSWORD_FIELD_FACTORY.build(context, field);
                    baseName = builder.getBaseName();
                    component = builder.build();
                }
            }
            if (component != null) {
                if (labels.containsKey(baseName)) {
                    labels.get(baseName).setLabelFor(component);
                } else {
                    components.put(baseName, component);
                }
            }
        }
        return component;
    }

}
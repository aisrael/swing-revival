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

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JRadioButton;

import swing.revival.annotations.RadioButton;
import swing.revival.builders.ActionPostProcessor;
import swing.revival.util.BeanWrapper;


/**
 *
 * @author Alistair A. Israel
 */
public class ActivePanel extends GroupLayoutJPanel {

    /**
     *
     */
    private static final long serialVersionUID = 161401078114379749L;

    private ComponentContext componentContext = new ComponentContext();

    /**
     *
     */
    public ActivePanel() {
        new ActivePanelInitializer(this).build();
    }

    /**
     * Ugly hack. Might have to re-think our whole strategy.
     */
    protected final void postProcess() {
        final BeanWrapper<ActivePanel> bean = BeanWrapper.wrap(this);
        final ComponentBuilderContext context = new ComponentBuilderContext(this);
        final ActionPostProcessor actionPostProcessor = new ActionPostProcessor(context);

        for (final Field field : bean.getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers())) {
                final Object value = bean.get(field);
                final Class<?> type = field.getType();
                if (Action.class.isAssignableFrom(type) && value != null) {
                    final Action action = (Action) value;
                    actionPostProcessor.process(field, action);
                    final AbstractButton button = createButtonForActionField(context, field);
                    if (button != null) {
                        button.setAction(action);
                        componentContext.addComponentFor(action, button);
                        add(button);
                    }
                }
            }
        }
    }

    /**
     * @param action
     *        {@link Action}
     * @return {@link JComponent}
     * @see swing.revival.ComponentContext#componentFor(javax.swing.Action)
     */
    public final JComponent componentFor(final Action action) {
        return componentContext.componentFor(action);
    }

    /**
     * @param context
     *        the {@link ComponentBuilderContext}
     * @param actionField
     *        the {@link Field} we're building for
     * @return the built component
     */
    private static AbstractButton createButtonForActionField(
            final ComponentBuilderContext context, final Field actionField) {
        AbstractButton button = null;
        final RadioButton radioButtonAnnotation = actionField.getAnnotation(RadioButton.class);
        if (radioButtonAnnotation != null) {
            final String name;
            if (radioButtonAnnotation.name().isEmpty()) {
                name = actionField.getName();
            } else {
                name = radioButtonAnnotation.name();
            }
            button = new JRadioButton();
            button.setName(name);
            context.getFontPostProcessor().setFontOn(actionField, button);
        }
        return button;
    }
}

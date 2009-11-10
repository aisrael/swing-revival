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
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JRadioButton;

import swing.revival.annotations.Button;
import swing.revival.annotations.RadioButton;
import swing.revival.builders.ActionPostProcessor;
import swing.revival.builders.SwingAnnotationPostProcessor;
import swing.revival.context.AssemblyContext;
import swing.revival.metadata.ComponentDefinition;
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

    private final ComponentContext componentContext = new ComponentContext();

    /**
     *
     */
    public ActivePanel() {
        createComponents();
        SwingAnnotationPostProcessor.postProcess(this);
        postProcess();
        layoutComponents();
    }

    /**
     *
     */
    protected void createComponents() {

    }

    /**
     *
     */
    protected void layoutComponents() {

    }

    /**
     * Ugly hack. Might have to re-think our whole strategy.
     */
    protected final void postProcess() {
        final BeanWrapper<ActivePanel> bean = BeanWrapper.wrap(this);
        final AssemblyContext context = new AssemblyContext(this);
        final ActionPostProcessor actionPostProcessor = new ActionPostProcessor(context);

        for (final Field field : bean.getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers())) {
                final Object value = bean.get(field);
                final Class<?> type = field.getType();
                if (Action.class.isAssignableFrom(type) && value != null) {
                    final Action action = (Action) value;
                    actionPostProcessor.postProcess(field, action);
                    final AbstractButton button =
                            createButtonForActionField(context, field, action);
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
     * @return the componentContext
     */
    public final ComponentContext getComponentContext() {
        return componentContext;
    }

    /**
     * @param context
     *        the {@link AssemblyContext}
     * @param actionField
     *        the {@link Field} we're building for
     * @param action
     *        the {@link Action} we're building for
     * @return the built component
     */
    private static AbstractButton createButtonForActionField(
            final AssemblyContext context, final Field actionField, final Action action) {
        AbstractButton button = null;
        final RadioButton radioButtonAnnotation = actionField.getAnnotation(RadioButton.class);
        if (radioButtonAnnotation != null) {
            button = new JRadioButton();
        }
        final Button buttonAnnotation = actionField.getAnnotation(Button.class);
        if (buttonAnnotation != null) {
            button = new JButton();
        }
        if (button != null) {
            final String name = (String) action.getValue(Action.NAME);
            button.setName(name);
            final ComponentDefinition actionFieldInfo = ComponentDefinition.wrap(actionField);
            context.getFontPostProcessor().setFontOn(actionFieldInfo, button);
        }
        return button;
    }
}

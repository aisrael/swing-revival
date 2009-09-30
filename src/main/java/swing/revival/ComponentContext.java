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

import java.util.Hashtable;
import java.util.Map;

import javax.swing.Action;
import javax.swing.JComponent;

/**
 * Basically just a place to 'park' some stuff used by ActivePanel.
 *
 * @author Alistair A. Israel
 */
public class ComponentContext {

    private final Map<Action, JComponent> actionComponentMap =
            new Hashtable<Action, JComponent>();

    /**
     * @param action
     *        {@link Action}
     * @return {@link JComponent}
     */
    public final JComponent componentFor(final Action action) {
        return actionComponentMap.get(action);
    }

    /**
     * @param action
     *        {@link Action}
     * @param component
     *        {@link JComponent}
     */
    public final void addComponentFor(final Action action, final JComponent component) {
        actionComponentMap.put(action, component);
    }

}

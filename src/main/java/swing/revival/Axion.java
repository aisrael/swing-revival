/**
 * swing-revival:
 * Swing Revival Toolkit
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Oct 7, 2009
 */
package swing.revival;

import javax.swing.AbstractAction;

/**
 *
 * @author Alistair A. Israel
 */
public abstract class Axion extends AbstractAction {

    /**
     *
     */
    private static final long serialVersionUID = 8373630255741675993L;

    /**
     * @return the action name
     */
    public final String getName() {
        return (String) getValue(NAME);
    }

    /**
     * @param name
     *        the action name to set
     */
    public final void setName(final String name) {
        putValue(NAME, name);
    }

    /**
     * @return the short description
     */
    public final String getShortDescription() {
        return (String) getValue(SHORT_DESCRIPTION);
    }

    /**
     * @param shortDescription
     *        the short description to set
     */
    public final void setShortDescription(final String shortDescription) {
        putValue(SHORT_DESCRIPTION, shortDescription);
    }

    /**
     * @param selected
     *        boolean
     */
    public final void setSelected(final boolean selected) {
        putValue(SELECTED_KEY, selected);
    }

    /**
     * @return true if this action's {@link javax.swing.Action#SELECTED_KEY} is set.
     */
    public final boolean isSelected() {
        final Object value = getValue(SELECTED_KEY);
        return value != null && value instanceof Boolean && ((Boolean) value).booleanValue();
    }
}

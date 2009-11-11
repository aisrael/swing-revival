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
package swing.revival.assembly.builders;

import static javax.swing.Action.NAME;
import static javax.swing.Action.SHORT_DESCRIPTION;
import static swing.revival.util.StringUtils.chomp;
import static swing.revival.util.StringUtils.isNullOrEmpty;

import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Logger;

import javax.swing.Action;

import swing.revival.annotations.Button;
import swing.revival.annotations.RadioButton;
import swing.revival.context.AssemblyContext;

/**
 * Initializes a pre-constructed {@link Action} object based on its field annotations.
 *
 * @author Alistair A. Israel
 */
public class ActionPostProcessor extends AssemblyContext.Aware {

    private static final Logger LOGGER = Logger.getLogger(ActionPostProcessor.class.getName());

    /**
     * @param context
     *        the {@link AssemblyContext}
     */
    public ActionPostProcessor(final AssemblyContext context) {
        super(context);
    }

    private static final Map<String, String[]> KEY_MAP = new Hashtable<String, String[]>();
    static {
        KEY_MAP.put(NAME, new String[] { NAME, "name", "text" });
        KEY_MAP.put(SHORT_DESCRIPTION, new String[] { SHORT_DESCRIPTION, "shortDescription", "toolTipText" });
    }

    /**
     * @param field
     *        the {@link Field} we're post-processing for
     * @param action
     *        the {@link Action} to post-process
     */
    public final void postProcess(final Field field, final Action action) {
        final String name = determineName(field);
        for (final Map.Entry<String, String[]> keyMapEntry : KEY_MAP.entrySet()) {
            final String propertyKey = keyMapEntry.getKey();

            final String[] suffixes = keyMapEntry.getValue();
            final String value = getContext().getResources().findString(name, suffixes);
            if (value != null) {
                action.putValue(propertyKey, value);
            } else {
                LOGGER.warning("Unable to find resource for \"" + name + "." + propertyKey + "\"");
            }
        }
    }

    /**
     * @param field
     *        the {@link Field}
     * @return the name
     */
    private String determineName(final Field field) {
        final String nameFromAnnotation = determineNameFromAnnotation(field);
        final String name;
        if (isNullOrEmpty(nameFromAnnotation)) {
            name = chomp(field.getName(), "Action");
        } else {
            name = nameFromAnnotation;
        }
        return name;
    }

    /**
     * @param field
     *        the Field to inspect
     * @return the name determined from any compatible annotations
     */
    private String determineNameFromAnnotation(final Field field) {
        String name = null;
        final Button buttonAnnotation = field.getAnnotation(Button.class);
        if (buttonAnnotation != null) {
            name = buttonAnnotation.name();
        } else {
            final RadioButton radioButtonAnnotation = field.getAnnotation(RadioButton.class);
            name = radioButtonAnnotation.name();
        }
        return name;
    }
}

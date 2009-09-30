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

import static javax.swing.Action.NAME;
import static javax.swing.Action.SHORT_DESCRIPTION;
import static swing.revival.util.CollectionUtils.list;
import static swing.revival.util.StringUtils.chomp;
import static swing.revival.util.StringUtils.isNullOrEmpty;

import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.swing.Action;

import swing.revival.ComponentBuilderContext;
import swing.revival.annotations.RadioButton;
import swing.revival.util.StringUtils;

/**
 * Initializes a pre-constructed {@link Action} object based on its field
 * annotations.
 *
 * @author Alistair A. Israel
 */
public class ActionPostProcessor extends ComponentBuilderContext.Aware {

    private static final Logger LOGGER = Logger.getLogger(ActionPostProcessor.class.getName());

    /**
     * @param context
     *        the {@link ComponentBuilderContext}
     */
    public ActionPostProcessor(final ComponentBuilderContext context) {
        super(context);
    }

    private static final Map<String, List<String>> KEY_MAP =
            new Hashtable<String, List<String>>();
    static {
        KEY_MAP.put(NAME, list(NAME, "name", "text"));
        KEY_MAP.put(SHORT_DESCRIPTION, list(SHORT_DESCRIPTION, "shortDescription",
                "toolTipText"));
    }

    /**
     * @param field
     *        the {@link Field} we're post-processing for
     * @param action
     *        the {@link Action} to post-process
     */
    public final void process(final Field field, final Action action) {
        String name = null;
        final RadioButton annotation = field.getAnnotation(RadioButton.class);
        if (annotation != null) {
            name = annotation.name();
        }
        if (isNullOrEmpty(name)) {
            name = chomp(field.getName(), "Action");
        }
        if (StringUtils.hasLength(name)) {
            for (final Map.Entry<String, List<String>> keyMapEntry : KEY_MAP.entrySet()) {
                final String propertyKey = keyMapEntry.getKey();
                final List<String> suffixes = keyMapEntry.getValue();
                String value = null;
                for (final String suffix : suffixes) {
                    final String resourceKey = name + "." + suffix;
                    if (containsResourceKey(resourceKey)) {
                        value = getResourceString(resourceKey);
                        break;
                    }
                }
                if (value != null) {
                    action.putValue(propertyKey, value);
                } else {
                    LOGGER.warning("Unable to find resource for \"" + name + "." + propertyKey
                            + "\"");
                }
            }
        }
    }

}

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
package swing.revival.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.logging.Logger;

/**
 *
 * @author Alistair A. Israel
 */
public final class Resources {

    private static final Logger LOGGER = Logger.getLogger(Resources.class.getName());

    /**
     * The fallback default resource bundle name,
     * {@value Resources#DEFAULT_RESOURCE_BUNDLE_NAME}
     */
    public static final String DEFAULT_RESOURCE_BUNDLE_NAME = "messages";

    private static final Set<String> NON_EXISTENT_RESOURCE_BUNDLES =
            new ConcurrentSkipListSet<String>();

    /**
     * Utility classes should not have a public or default constructor.
     */
    private Resources() {
        // noop
    }

    /**
     * @param clazz
     *        the class to find the {@link ResourceBundle} for
     * @return ResourceBundle if found, or <code>null</code>
     */
    public static ResourceBundleHelper find(final Class<?> clazz) {
        final String className = ResourceBundleHelper.classToResourceKeyPrefix(clazz);
        String baseName = className;

        ResourceBundle bundle = quietlyGetBundle(baseName);
        if (bundle == null) {
            int x = baseName.lastIndexOf('.');
            if (x > 0) {
                while (bundle == null && x > 0) {
                    baseName = baseName.substring(0, x);
                    bundle = quietlyGetBundle(baseName);
                    if (bundle != null) {
                        break;
                    }
                    x = baseName.lastIndexOf('.');
                }
            }
            if (bundle == null) {
                baseName = baseName.substring(baseName.lastIndexOf('.') + 1);
                bundle = quietlyGetBundle(baseName);
                if (bundle == null) {
                    bundle = quietlyGetBundle(DEFAULT_RESOURCE_BUNDLE_NAME);
                    if (bundle != null) {
                        LOGGER.finest("Using default resources from \""
                                + DEFAULT_RESOURCE_BUNDLE_NAME + "\"");
                    } else {
                        LOGGER.warning("Unable to find any suitable resource bundle for "
                                + clazz);
                    }
                }
            }
        }
        final String prefix;
        if (baseName.length() < className.length()) {
            prefix = className.substring(baseName.length() + 1);
        } else {
            prefix = className.substring(baseName.length());
        }
        return new ResourceBundleHelper(bundle, prefix);
    }

    /**
     * @param baseName
     *        the resource bundle name
     * @return ResourceBundle if found, or <code>null</code>
     */
    public static ResourceBundle quietlyGetBundle(final String baseName) {
        if (NON_EXISTENT_RESOURCE_BUNDLES.contains(baseName)) {
            return null;
        }
        try {
            return ResourceBundle.getBundle(baseName);
        } catch (final MissingResourceException e) {
            LOGGER.finest(e.getMessage());
        }
        NON_EXISTENT_RESOURCE_BUNDLES.add(baseName);
        return null;
    }
}

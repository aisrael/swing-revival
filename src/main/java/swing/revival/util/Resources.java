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
        final String packageName = clazz.getPackage().getName();
        final String withoutPackageName = clazz.getName().substring(packageName.length() + 1);
        String name = withoutPackageName;
        ResourceBundle bundle = findLongOrShort(packageName, name);
        if (bundle != null) {
            return new ResourceBundleHelper(bundle, "");
        }
        if (clazz.isMemberClass()) {
            int x = name.lastIndexOf('$');
            while (x > 0) {
                name = name.substring(0, x);
                x = name.lastIndexOf('$');
                bundle = findLongOrShort(packageName, name);
                if (bundle != null) {
                    final String prefix =
                            withoutPackageName.substring(name.length() + 1).replace('$', '.');
                    return new ResourceBundleHelper(bundle, prefix);
                }
            }
        }
        final String prefix = clazz.getName().replace('$', '.');
        bundle = quietlyGetBundle(DEFAULT_RESOURCE_BUNDLE_NAME);
        if (bundle != null) {
            LOGGER.finest("Using default resources from \""
                    + DEFAULT_RESOURCE_BUNDLE_NAME + "\"");
        } else {
            LOGGER.warning("Unable to find any suitable resource bundle for "
                    + clazz);
        }
        return new ResourceBundleHelper(bundle, prefix);
    }

    /**
     * @param packageName
     *        the package name
     * @param className
     *        the class name
     * @return the ResourceBundle
     */
    private static ResourceBundle findLongOrShort(final String packageName,
            final String className) {
        ResourceBundle bundle = quietlyGetBundle(packageName + "." + className);
        if (bundle == null) {
            bundle = quietlyGetBundle(className);
        }
        return bundle;
    }

    /**
     * @param baseName
     *        the resource bundle name
     * @return ResourceBundle if found, or <code>null</code>
     */
    public static ResourceBundle quietlyGetBundle(final String baseName) {
        final String name = baseName.replace('$', '.');
        if (NON_EXISTENT_RESOURCE_BUNDLES.contains(name)) {
            return null;
        }
        try {
            return ResourceBundle.getBundle(name);
        } catch (final MissingResourceException e) {
            LOGGER.finest(e.getMessage());
        }
        NON_EXISTENT_RESOURCE_BUNDLES.add(name);
        return null;
    }
}

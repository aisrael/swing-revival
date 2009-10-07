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

import java.util.ResourceBundle;

/**
 * Copyright (c) 2009 by Alistair A. Israel. This software is made available
 * under the terms of the MIT License.
 *
 * Created Sep 30, 2009
 */
public class ResourceBundleHelper {

    private final ResourceBundle resourceBundle;

    private final String prefix;

    /**
     * @param resourceBundle
     *        the underlying {@link ResourceBundle} to wrap
     * @param prefix
     *        a prefix for all strings to be returned by this helper
     */
    public ResourceBundleHelper(final ResourceBundle resourceBundle, final String prefix) {
        super();
        this.resourceBundle = resourceBundle;
        if (prefix.endsWith(".")) {
            this.prefix = prefix;
        } else {
            this.prefix = prefix + ".";
        }
    }

    /**
     * @param clazz
     *        a Class
     * @return {@link ResourceBundleHelper}
     */
    public static ResourceBundleHelper forClass(final Class<?> clazz) {
        final ResourceBundle resourceBundle = Resources.find(clazz);
        if (resourceBundle != null) {
            return new ResourceBundleHelper(resourceBundle, classToResourceKeyPrefix(clazz));
        }
        return null;
    }

    /**
     * @param key
     *        the 'short' key for the desired string
     * @return the prefixed key
     */
    protected final String prefix(final String key) {
        return prefix + key;
    }

    /**
     * @param key
     *        the 'short' key for the desired string
     * @return <code>true</code> if the <code>prefix</code>.<code>key</code> is
     *         contained in the underlying <code>ResourceBundle</code> or its
     *         parent bundles; <code>false</code> otherwise.
     */
    public final boolean containsKey(final String key) {
        return resourceBundle.containsKey(prefix + key);
    }

    /**
     * @param key
     *        the 'short' key for the desired string
     * @return the resource string
     * @see java.util.ResourceBundle#getString(java.lang.String)
     */
    public final String getString(final String key) {
        return resourceBundle.getString(prefix(key));
    }

    /**
     * @param base
     *        the base key
     * @param suffixes
     *        a set of suffixes to try
     * @return the found resource string
     */
    public final String findString(final String base, final String... suffixes) {
        for (final String suffix : suffixes) {
            final String key = base + "." + suffix;
            if (containsKey(key)) {
                return getString(key);
            }
        }
        return null;
    }

    /**
     * @param key
     *        the 'short' key for the desired string
     * @param def
     *        the default value if the key is not found
     * @return the resource string
     * @see java.util.ResourceBundle#getString(java.lang.String)
     */
    public final String get(final String key, final String def) {
        if (resourceBundle.containsKey(prefix + key)) {
            return resourceBundle.getString(prefix + key);
        }
        return def;
    }

    /**
     * @param clazz
     *        {@link Class}
     * @return the class name as a string, with inner class separators turned
     *         into dots (<code>'.'</code>)
     */
    public static String classToResourceKeyPrefix(final Class<?> clazz) {
        return clazz.getName().replace('$', '.');
    }

}

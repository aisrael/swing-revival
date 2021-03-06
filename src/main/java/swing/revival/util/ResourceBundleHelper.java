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

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Copyright (c) 2009 by Alistair A. Israel. This software is made available under the terms of the MIT License. Created
 * Sep 30, 2009
 */
public class ResourceBundleHelper {

    /**
     * {@value #EMPTY_PREFIX}
     */
    public static final String EMPTY_PREFIX = "";

    private final ResourceBundle resourceBundle;

    private final String root;

    /**
     * @param resourceBundle
     *        the underlying {@link ResourceBundle} to wrap
     */
    public ResourceBundleHelper(final ResourceBundle resourceBundle) {
        Assert.notNull(resourceBundle, "'resourceBundle' cannot be null!");
        this.resourceBundle = resourceBundle;
        this.root = EMPTY_PREFIX;
    }

    /**
     * @param resourceBundle
     *        the underlying {@link ResourceBundle} to wrap
     * @param root
     *        a root for all strings to be returned by this helper
     */
    public ResourceBundleHelper(final ResourceBundle resourceBundle, final String root) {
        Assert.notNull(resourceBundle, "'resourceBundle' cannot be null!");
        Assert.hasLength(root, "'root' cannot be null or empty!");
        this.resourceBundle = resourceBundle;
        if (root.isEmpty() || root.endsWith(".")) {
            this.root = root;
        } else {
            this.root = root + ".";
        }
    }

    /**
     * @return the root
     */
    public final String getPrefix() {
        return root;
    }

    /**
     * @param clazz
     *        a Class
     * @return {@link ResourceBundleHelper}
     */
    public static ResourceBundleHelper forClass(final Class<?> clazz) {
        return Resources.find(clazz);
    }

    /**
     * @param key
     *        the 'short' key for the desired string
     * @return the prefixed key
     */
    protected final String prefix(final String key) {
        return root + key;
    }

    /**
     * @param key
     *        the 'short' key for the desired string
     * @return <code>true</code> if the <code>root</code>.<code>key</code> is contained in the underlying
     *         <code>ResourceBundle</code> or its parent bundles; <code>false</code> otherwise.
     */
    public final boolean containsKey(final String key) {
        return resourceBundle.containsKey(prefix(key));
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
        if (resourceBundle.containsKey(prefix(key))) {
            return resourceBundle.getString(prefix(key));
        }
        return def;
    }

    /**
     * @param prefix
     *        the 'short' key prefix
     * @return list of keys
     */
    public final String[] listKeysStartingWith(final String prefix) {
        final List<String> keys = new ArrayList<String>();
        final String rootedPrefix = prefix(prefix + ".");
        for (final String key : resourceBundle.keySet()) {
            if (key.startsWith(rootedPrefix)) {
                keys.add(removePrefix(key));
            }
        }
        return keys.toArray(new String[keys.size()]);
    }

    /**
     * @param key
     *        the key
     * @return the key with this helper's root removed
     */
    private String removePrefix(final String key) {
        return StringUtils.unfix(root, key);
    }

    /**
     * @param clazz
     *        {@link Class}
     * @return the class name as a string, with inner class separators turned into dots (<code>'.'</code>)
     */
    public static String classToResourceKeyPrefix(final Class<?> clazz) {
        return clazz.getName().replace('$', '.');
    }

}

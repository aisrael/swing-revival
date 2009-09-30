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
     */
    public ResourceBundleHelper(final Class<?> clazz) {
        this(Resources.find(clazz), Resources.classToResourceKeyPrefix(clazz));
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

}

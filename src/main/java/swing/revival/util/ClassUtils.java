/**
 * swing-revival:
 * Swing Revival Toolkit
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Nov 10, 2009
 */
package swing.revival.util;

/**
 * @author Alistair A. Israel
 * @since 0.1
 */
public final class ClassUtils {

    /**
     * Utility classes should not have a public or default constructor.
     */
    private ClassUtils() {
        // noop
    }

    /**
     * @param clazz
     *        a {@link Class}
     * @return the class' short name
     */
    public static String getShortName(final Class<?> clazz) {
        return getShortName(clazz.getName());
    }

    /**
     * @param className
     *        a class name
     * @return the short name
     */
    public static String getShortName(final String className) {
        int r = className.lastIndexOf('$');
        if (r == -1) {
            r = className.lastIndexOf('.');
        }
        if (r != -1) {
            return className.substring(r + 1);
        }
        return className;
    }

}

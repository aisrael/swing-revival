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

/**
 *
 * @author Alistair A. Israel
 */
public final class StringUtils {

    /**
     * Utility classes should not have a public or default constructor.
     */
    private StringUtils() {
        // noop
    }

    /**
     * @param s
     *        a string
     * @return true if the given string is null or 0-length (<code>""</code>)
     */
    public static boolean isNullOrEmpty(final String s) {
        return null == s || s.length() == 0;
    }

    /**
     * @param s
     *        a string
     * @return true if the given string is not null, and not 0-length
     */
    public static boolean hasLength(final String s) {
        return !isNullOrEmpty(s);
    }

    /**
     * Remove the given suffix from the given string, if present.
     *
     * @param s
     *        the string
     * @param suffix
     *        the suffix to remove, if present
     * @return if the string ends with the given suffix, the string with the
     *         suffix removed. Otherwise, return the original string
     */
    public static String chomp(final String s, final String suffix) {
        if (hasLength(s) && hasLength(suffix) && s.endsWith(suffix)) {
            return s.substring(0, s.lastIndexOf(suffix));
        }
        return s;
    }

}

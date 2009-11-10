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
     * @param s
     *        the string to check
     * @param ifNullOrEmpty
     *        the string to return if the first string is <code>null</code> or empty
     * @return <code>s</code>, or <code>ifNullOrEmpty</code> if <code>s</code> is <code>null</code> or empty
     */
    public static String ifNullOrEmpty(final String s, final String ifNullOrEmpty) {
        if (isNullOrEmpty(s)) {
            return ifNullOrEmpty;
        }
        return s;
    }

    /**
     * Remove the given prefix from the given string, if present.
     *
     * @param prefix
     *        the prefix to remove, if present
     * @param s
     *        the string
     * @return if the string begins with the given prefix, the string with the prefix removed. Otherwise, returns the
     *         original string
     */
    public static String unfix(final String prefix, final String s) {
        if (hasLength(s) && hasLength(prefix) && s.startsWith(prefix)) {
            return s.substring(prefix.length());
        }
        return s;
    }

    /**
     * Remove the given suffix from the given string, if present.
     *
     * @param s
     *        the string
     * @param suffix
     *        the suffix to remove, if present
     * @return if the string ends with the given suffix, the string with the suffix removed. Otherwise, return the
     *         original string
     */
    public static String chomp(final String s, final String suffix) {
        if (hasLength(s) && hasLength(suffix) && s.endsWith(suffix)) {
            return s.substring(0, s.length() - suffix.length());
        }
        return s;
    }

}

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
public final class Assert {

    /**
     * Utility classes should not have a public or default constructor.
     */
    private Assert() {
        // noop
    }

    /**
     * @param condition
     *        the boolean expression to check
     * @param message
     *        the exception message
     */
    public static void isTrue(final boolean condition, final String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * @param superType
     *        the super type to check
     * @param subType
     *        the sub type to check
     * @param message
     *        the exception message
     */
    public static void isAssignable(final Class<?> superType, final Class<?> subType, final String message) {
        isTrue(superType.isAssignableFrom(subType), message);
    }

    /**
     * @param obj
     *        an object
     */
    public static void notNull(final Object obj) {
        if (null == obj) {
            throw new NullPointerException();
        }
    }

    /**
     * @param obj
     *        an object
     * @param message
     *        the exception message
     */
    public static void notNull(final Object obj, final String message) {
        if (null == obj) {
            throw new NullPointerException(message);
        }
    }

    /**
     * @param s
     *        a String
     */
    public static void hasLength(final String s) {
        if (!StringUtils.hasLength(s)) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * @param s
     *        a String
     * @param message
     *        the exception message
     */
    public static void hasLength(final String s, final String message) {
        if (!StringUtils.hasLength(s)) {
            throw new IllegalArgumentException(message);
        }
    }
}

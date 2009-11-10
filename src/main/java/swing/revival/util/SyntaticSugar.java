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
public final class SyntaticSugar {

    /**
     * Utility classes should not have a public or default constructor.
     */
    private SyntaticSugar() {
        // noop
    }

    /**
     * Returns obj if obj is not null, or val.
     * 
     * @param <T>
     *        a type
     * @param obj
     *        the object to test
     * @param valIfNull
     *        the value to return if obj is null
     * @return val if obj is null, or obj
     */
    public static <T> T ifNull(final T obj, final T valIfNull) {
        return ifNull(obj, valIfNull, obj);
    }

    /**
     * Returns obj if obj is not null, or val.
     * 
     * @param <T>
     *        a type
     * @param obj
     *        the object to test
     * @param valIfNull
     *        the value to return if obj is null
     * @param valIfNotNull
     *        the value to return if obj is not null
     * @return valIfNull if obj is null, or valIfNotNull
     */
    public static <T> T ifNull(final T obj, final T valIfNull, final T valIfNotNull) {
        if (obj == null) {
            return valIfNull;
        }
        return valIfNotNull;
    }

    /**
     * Returns the first non-null expression among its arguments.
     * 
     * @param <T>
     *        a type (can be Object)
     * @param args
     *        the arguments (varargs)
     * @return the first non-null expression among the arguments, or null if all are null
     */
    public static <T> T coalesce(final T... args) {
        for (final T arg : args) {
            if (arg != null) {
                return arg;
            }
        }
        return null;
    }

}

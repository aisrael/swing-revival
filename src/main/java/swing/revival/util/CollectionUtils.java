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

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Alistair A. Israel
 */
public final class CollectionUtils {

    /**
     * Utility classes should not have a public or default constructor.
     */
    private CollectionUtils() {
        // noop
    }

    /**
     * @param <T>
     *        a type
     * @param values
     *        values of the type
     * @return List containing the given values
     */
    public static <T> List<T> list(final T... values) {
        return Arrays.asList(values);
    }
}

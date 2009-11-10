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

import java.util.Hashtable;
import java.util.Map;

/**
 * @param <K>
 *        the key type
 * @param <V>
 *        the value type
 * @author Alistair A. Israel
 * @since 0.2
 */
public class BidiMap<K, V> extends Hashtable<K, V> {

    /**
     *
     */
    private static final long serialVersionUID = -5415401160814419262L;

    private Map<V, K> inverse = new Hashtable<V, K>();

    /**
     * {@inheritDoc}
     *
     * @see java.util.Hashtable#put(java.lang.Object, java.lang.Object)
     */
    @Override
    public final synchronized V put(final K key, final V value) {
        inverse.put(value, key);
        return super.put(key, value);
    }

    /**
     * Returns the key to which the specified value is mapped, or {@code null} if this map contains no key for the
     * value.
     *
     * @param value
     *        the value whose associated value is to be returned
     * @return the key to which the specified value is mapped, or {@code null} if this map contains no mapping for the
     *         value
     */
    public final K getKey(final V value) {
        return inverse.get(value);
    }

}

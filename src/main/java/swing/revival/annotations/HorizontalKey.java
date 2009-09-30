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
package swing.revival.annotations;

import javax.swing.SwingConstants;

/**
 * Corresponds to SwingConstants values used in
 * <code>horizontaTextPosition</code> and <code>horizontalAlignment</code>
 * properties.
 */
public enum HorizontalKey {

    /**
     *
     */
    DEFAULT(Integer.MIN_VALUE),

    /**
     *
     */
    RIGHT(SwingConstants.RIGHT),

    /**
     *
     */
    LEFT(SwingConstants.LEFT),

    /**
     *
     */
    CENTER(SwingConstants.CENTER),

    /**
     *
     */
    LEADING(SwingConstants.LEADING),

    /**
     *
     */
    TRAILING(SwingConstants.TRAILING);

    private final int intValue;

    /**
     * @param intValue
     *        the corresponding {@link SwingConstants} integer value
     */
    private HorizontalKey(final int intValue) {
        this.intValue = intValue;
    }

    /**
     * @return the intValue
     */
    public final int getIntValue() {
        return intValue;
    }

}
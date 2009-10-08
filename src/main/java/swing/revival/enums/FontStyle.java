/**
 * swing-revival:
 * Swing Revival Toolkit
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Oct 8, 2009
 */
package swing.revival.enums;

import java.awt.Font;

/**
 *
 * @author Alistair A. Israel
 */
public enum FontStyle {

    /**
     * {@link Font#PLAIN}
     */
    PLAIN(Font.PLAIN),

    /**
     * {@link Font#BOLD}
     */
    BOLD(Font.BOLD),

    /**
     * {@link Font#ITALIC}
     */
    ITALIC(Font.ITALIC),

    /**
     * {@link Font#BOLD} | {@link Font#ITALIC}
     */
    BOLD_ITALIC(Font.BOLD | Font.ITALIC);

    private final int intValue;

    /**
     * @param intValue
     *        the integer value corresponding to the {@link Font} style constant
     */
    private FontStyle(final int intValue) {
        this.intValue = intValue;
    }

    /**
     * @return the intValue
     */
    public final int intValue() {
        return intValue;
    }

}

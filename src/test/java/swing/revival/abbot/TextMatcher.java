/**
 * swing-revival:
 * Swing Revival Toolkit
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Oct 7, 2009
 */
package swing.revival.abbot;


import java.awt.Component;
import java.lang.reflect.Method;

import abbot.finder.matchers.AbstractMatcher;

/**
 *
 * @author Alistair A. Israel
 */
public class TextMatcher extends AbstractMatcher {

    private final String text;

    /**
     * @param text
     *        the text to match
     */
    public TextMatcher(final String text) {
        super();
        if (null == text) {
            throw new NullPointerException("text cannot be null!");
        }
        this.text = text;
    }

    /**
     * {@inheritDoc}
     *
     * @see abbot.finder.Matcher#matches(java.awt.Component)
     */
    @Override
    public final boolean matches(final Component c) {
        try {
            Method getTextMethod = null;
            try {
                getTextMethod = c.getClass().getMethod("getText");
            } catch (final NoSuchMethodException e) {
                return false;
            }
            if (getTextMethod != null) {
                final Object obj = getTextMethod.invoke(c);
                if (String.class.isInstance(obj)) {
                    final String actual = (String) obj;
                    return text.equals(actual);
                }
            }
        } catch (final Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see abbot.finder.matchers.AbstractMatcher#toString()
     */
    // CHECKSTYLE:OFF
    @Override
    public String toString() {
        // CHECKSTYLE:ON
        return "getText() method returns \"" + text + "\"";
    }
}

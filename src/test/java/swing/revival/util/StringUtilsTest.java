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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test for {@link StringUtils}.
 *
 * @author Alistair A. Israel
 */
public final class StringUtilsTest {

    /**
     * Test for {@link StringUtils#isNullOrEmpty(String)}.
     */
    @Test
    public void testIsNullOrEmpty() {
        final String[] s = { null, "", "abc" };
        final boolean[] expected = { true, true, false };
        for (int i = 0; i < expected.length; ++i) {
            assertEquals(expected[i], StringUtils.isNullOrEmpty(s[i]));
        }
    }

    /**
     * Test for {@link StringUtils#hasLength(String)}.
     */
    @Test
    public void testHasLength() {
        final String[] s = { null, "", "abc" };
        final boolean[] expected = { false, false, true };
        for (int i = 0; i < expected.length; ++i) {
            assertEquals(expected[i], StringUtils.hasLength(s[i]));
        }
    }

    /**
     * Test for {@link StringUtils#unfix(String, String)}.
     */
    @Test
    public void testUnfix() {
        final String[] s = { null, "", "abc.def", "abc.def", "testUnfix" };
        final String[] prefixes = { "abc.", "abc.", "", "abc.", "test" };
        final String[] expected = { null, "", "abc.def", "def", "Unfix" };
        for (int i = 0; i < expected.length; ++i) {
            assertEquals(expected[i], StringUtils.unfix(prefixes[i], s[i]));
        }

    }

    /**
     * Test for {@link StringUtils#chomp(String, String)}.
     */
    @Test
    public void testChomp() {
        final String[] s = { null, "", "abc.def", "abc.def", "testChomp" };
        final String[] suffixes = { ".txt", ".txt", "", ".def", "Chomp" };
        final String[] expected = { null, "", "abc.def", "abc", "test" };
        for (int i = 0; i < expected.length; ++i) {
            assertEquals(expected[i], StringUtils.chomp(s[i], suffixes[i]));
        }
    }

}

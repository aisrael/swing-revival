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

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnit test for {@link ClassUtils}.
 * 
 * @author Alistair A. Israel
 * @since 0.1
 */
public final class ClassUtilsTest {

    /**
     * A dummy inner class just for testing.
     * 
     * @author Alistair A. Israel
     */
    public static class Foo {

        /**
         * A dummy inner^2 class just for testing.
         * 
         * @author Alistair A. Israel
         */
        public class Bar {

        }
    }

    /**
     * Test method for {@link swing.revival.util.ClassUtils#getShortName(java.lang.Class)}.
     */
    @Test
    public void testGetShortNameClass() {
        assertEquals("ClassUtilsTest", ClassUtils.getShortName(ClassUtilsTest.class));
        assertEquals("Foo", ClassUtils.getShortName(Foo.class));
        assertEquals("Bar", ClassUtils.getShortName(Foo.Bar.class));
    }
}

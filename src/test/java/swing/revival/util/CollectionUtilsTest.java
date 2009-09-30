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
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

/**
 * JUnit test for {@link CollectionUtils}.
 *
 * @author Alistair A. Israel
 */
public final class CollectionUtilsTest {

    /**
     * Test for {@link swing.revival.util.CollectionUtils#list(T[])}.
     */
    @Test
    public void testList() {
        final List<String> list = CollectionUtils.list("a", "b", "c");
        assertNotNull(list);
        assertEquals(3, list.size());
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("c", list.get(2));
    }

}

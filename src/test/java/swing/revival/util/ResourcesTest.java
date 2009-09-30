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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ResourceBundle;

import org.junit.Test;

import swing.revival.ActivePanelTest;
import swing.revival.ActivePanelTest.MyPanel;

/**
 * JUnit test for {@link Resources}.
 *
 * @author Alistair A. Israel
 */
public final class ResourcesTest {

    /**
     * Test for {@link Resources#find(java.lang.Class)}.
     */
    @Test
    public void testFind() {
        final ResourceBundle bundle = Resources.find(ActivePanelTest.MyPanel.class);
        assertNotNull("Resources.find(ActivePanelTest.MyPanel.class) returned null!", bundle);
        assertTrue(bundle.containsKey("swing.revival.ActivePanelTest.MyPanel.field1Label.text"));
        assertNull("Resources.find(ResourcesTest.class) should be null!", Resources
                .find(ResourcesTest.class));
    }

    /**
     * Test for {@link Resources#quietlyGetBundle(java.lang.String)}.
     */
    @Test
    public void testQuietlyGetBundle() {
        assertNotNull("quietlyGetBundle(\"swing.revival.ActivePanelTest\") returned null!",
                Resources.quietlyGetBundle("swing.revival.ActivePanelTest"));
        assertNull("quietlyGetBundle(\"MyPanel\") should be null!", Resources
                .quietlyGetBundle("MyPanel"));
    }

    /**
     * Test for {@link Resources#classToResourceKeyPrefix(java.lang.Class)} .
     */
    @Test
    public void testClassToResourceKeyPrefix() {
        assertEquals("swing.revival.ActivePanelTest.MyPanel", Resources
                .classToResourceKeyPrefix(MyPanel.class));
    }

}

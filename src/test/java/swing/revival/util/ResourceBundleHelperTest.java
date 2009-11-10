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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import swing.revival.ActivePanelTest.MyPanel;

/**
 * JUnit test for {@link ResourceBundleHelper}.
 *
 * @author Alistair A. Israel
 */
public final class ResourceBundleHelperTest {

    private ResourceBundleHelper helper;

    /**
     *
     */
    @Before
    public void setUp() {
        helper = ResourceBundleHelper.forClass(MyPanel.class);
    }

    /**
     *
     */
    @After
    public void tearDown() {
        helper = null;
    }

    /**
     * Test for {@link ResourceBundleHelper#containsKey(java.lang.String)}
     */
    @Test
    public void testContainsKey() {
        assertTrue(helper.containsKey("field1Label.text"));
        assertFalse(helper.containsKey("not a key"));
    }

    /**
     * Test for {@link ResourceBundleHelper#getString(java.lang.String)}
     */
    @Test
    public void testGetString() {
        assertEquals("First field", helper.getString("field1Label.text"));
        assertEquals("First field tool-tip text", helper.getString("field1.toolTipText"));
    }

    /**
     * Test for
     * {@link ResourceBundleHelper#get(java.lang.String, java.lang.String)}
     */
    @Test
    public void testGet() {
        assertEquals("First field", helper.get("field1Label.text", "null"));
        assertEquals("Default", helper.get("not a key", "Default"));
    }

    /**
     * Test for
     * {@link ResourceBundleHelper#classToResourceKeyPrefix(java.lang.Class)}
     */
    @Test
    public void testClassToResourceKeyPrefix() {
        assertEquals("swing.revival.ActivePanelTest.MyPanel", ResourceBundleHelper
                .classToResourceKeyPrefix(MyPanel.class));
    }

    /**
     * Test for {@link ResourceBundleHelper#listKeysStartingWith(String)}
     */
    @Test
    public void testListKeysStartingWith() {
        final String[] keys = helper.listKeysStartingWith("test");
        assertEquals(2, keys.length);
    }
}

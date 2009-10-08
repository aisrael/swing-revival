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

import org.junit.Test;

import com.example.ui.ExamplePanel;
import com.example.ui.FullyQualifiedPanel;
import com.example.ui.SomeContainer;

/**
 * JUnit test for {@link Resources}.
 *
 * @author Alistair A. Israel
 */
public final class ResourcesTest {

    /**
     * Test {@link Resources#find(Class)} on fully qualified
     * "com.example.ClassName" from "messages.properties".
     */
    @Test
    public void testFindFullyQualified() {
        final ResourceBundleHelper resources = Resources.find(FullyQualifiedPanel.class);
        assertNotNull("Resources.find(FullyQualifiedPanel.class) returned null!", resources);
        assertEquals(FullyQualifiedPanel.class.getName() + ".", resources.getPrefix());
        assertEquals("Fully qualified panel", resources.getString("title"));
    }

    /**
     * Test {@link Resources#find(Class)} on direct "ClassName.properties"
     */
    @Test
    public void testFindDirect() {
        final ResourceBundleHelper resources = Resources.find(ExamplePanel.class);
        assertNotNull("Resources.find(ExamplePanel.class) returned null!", resources);
        assertTrue(resources.getPrefix().isEmpty());
    }

    /**
     * Test {@link Resources#find(Class)} on embedded "Container$Embedded.class"
     * finds "Container.properties"
     */
    @Test
    public void testFindEmbedded() {
        final ResourceBundleHelper resources =
                Resources.find(SomeContainer.EmbeddedPanel.class);
        assertNotNull("Resources.find(SomeContainer.EmbeddedPanel.class) returned null!",
                resources);
        assertEquals("EmbeddedPanel.", resources.getPrefix());
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
}

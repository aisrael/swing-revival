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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.example.ui.ExamplePanel;

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
        final ResourceBundleHelper resources = Resources.find(ExamplePanel.class);
        assertNotNull("Resources.find(ExamplePanel.class) returned null!", resources);
        assertTrue(resources.getPrefix().isEmpty());
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

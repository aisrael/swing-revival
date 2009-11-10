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
package swing.revival;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test for {@link ActiveFrame}.
 * 
 * @author Alistair A. Israel
 * @since 0.1
 */
public final class ActiveFrameTest {

    /**
     * 
     */
    @SuppressWarnings("serial")
    public static class FrameWithNoTitle extends ActiveFrame {
    }

    /**
     * 
     */
    @Test
    public void testActiveFrameGetsDefaultTitle() {
        final FrameWithNoTitle frame = new FrameWithNoTitle();
        assertEquals("FrameWithNoTitle", frame.getTitle());
    }

    /**
     * 
     */
    @SuppressWarnings("serial")
    public static class MyFrame extends ActiveFrame {
    }

    /**
     * 
     */
    @Test
    public void testActiveFrameGetsTitleFromResource() {
        final MyFrame frame = new MyFrame();
        assertEquals("My Active Frame", frame.getTitle());
    }

}

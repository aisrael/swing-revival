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
package swing.revival.builders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Component;

import javax.swing.JTextField;

import org.junit.Test;

import swing.revival.assembly.builders.SwingAnnotationPostProcessor;

import abbot.finder.BasicFinder;
import abbot.finder.ComponentFinder;
import abbot.finder.matchers.NameMatcher;

import com.example.ui.ExamplePanel;

/**
 * JUnit test for {@link SwingAnnotationPostProcessor}.
 *
 * @author Alistair A. Israel
 */
public final class SwingAnnotationPostProcessorTest {

    private static final SwingAnnotationPostProcessor POST_PROCESSOR =
            new SwingAnnotationPostProcessor();

    /**
     * @throws Exception
     *         on exception
     */
    @Test
    public void testPostProcess() throws Exception {
        final ComponentFinder finder = BasicFinder.getDefault();

        final ExamplePanel panel = new ExamplePanel();
        POST_PROCESSOR.process(panel);

        final Component c = finder.find(panel, new NameMatcher("usernameField"));
        assertNotNull(c);
        assertTrue(c instanceof JTextField);
        final JTextField usernameField = (JTextField) c;
        assertEquals(10, usernameField.getColumns());
    }
}

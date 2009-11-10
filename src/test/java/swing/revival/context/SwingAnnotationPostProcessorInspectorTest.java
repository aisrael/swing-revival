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
package swing.revival.context;

import static java.awt.Font.BOLD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Set;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Test;

import swing.revival.builders.SwingAnnotationPostProcessor;
import swing.revival.metadata.ComponentDefinition;
import swing.revival.metadata.ContainerDefinition;
import swing.revival.metadata.FontInfo;

import com.example.ui.ExamplePanel;

/**
 * JUnit test for {@link SwingAnnotationPostProcessor.Inspector}
 *
 * @author Alistair A. Israel
 */
public final class SwingAnnotationPostProcessorInspectorTest {

    /**
     * Test for {@link SwingAnnotationPostProcessor#inspect(Class)}
     */
    @Test
    public void testInspectClass() {
        final SwingAnnotationPostProcessor swapp = new SwingAnnotationPostProcessor();
        final ContainerDefinition swair = swapp.inspect(ExamplePanel.class);

        final Set<String> fieldNames = swair.listComponentNames();
        assertEquals(2, fieldNames.size());

        final ComponentDefinition usernameField = swair.getComponentNamed("usernameField");
        assertNotNull(usernameField);
        assertEquals(JTextField.class, usernameField.getType());
        assertEquals("Username", usernameField.getString("label.text"));
        final FontInfo fontInfo = usernameField.getFontInfo();
        assertNotNull(fontInfo);
        assertEquals(BOLD, fontInfo.getStyle());
        assertNull(fontInfo.getSize());

        final ComponentDefinition passwordField = swair.getComponentNamed("passwordField");
        assertNotNull(passwordField);
        assertEquals(JPasswordField.class, passwordField.getType());

        final FontInfo defaultFontInfo = swair.getDefaultFontInfo();
        assertNotNull(defaultFontInfo);
        assertEquals("Tahoma", defaultFontInfo.getName());
        assertNotNull(defaultFontInfo.getSize());
        assertEquals(11, defaultFontInfo.getSize().intValue());
    }

}

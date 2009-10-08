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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Test;

import com.example.ui.ExamplePanel;

/**
 * JUnit test for {@link SwingAnnotationInspector}.
 *
 * @author Alistair A. Israel
 */
public final class SwingAnnotationInspectorTest {

    /**
     * Test for {@link SwingAnnotationInspector#inspect(java.lang.Class)} .
     */
    @Test
    public void testInspectClass() {
        final SwingAnnotationInspectionResults swair =
                SwingAnnotationInspector.inspect(ExamplePanel.class);
        final Set<String> fieldNames = swair.listFieldNames();
        assertEquals(2, fieldNames.size());

        final ComponentField usernameField = swair.getField("usernameField");
        assertNotNull(usernameField);
        assertEquals(JTextField.class, usernameField.getType());
        assertEquals("Username", usernameField.getString("label.text"));

        final ComponentField passwordField = swair.getField("passwordField");
        assertNotNull(passwordField);
        assertEquals(JPasswordField.class, passwordField.getType());
    }

}

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
package com.example.ui;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import swing.revival.annotations.Font;
import swing.revival.annotations.TextField;

/**
 *
 * @author Alistair A. Israel
 */
@SuppressWarnings({ "serial", "unused" })
@Font(name = "Tahoma", size = 11)
public class ExamplePanel extends JPanel {

    @TextField(columns = 10)
    private JTextField usernameField;

    @TextField(columns = 10)
    private JPasswordField passwordField;

}

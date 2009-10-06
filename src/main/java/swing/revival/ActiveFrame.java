/**
 * swing-revival:
 * Swing Revival Toolkit
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Oct 6, 2009
 */
package swing.revival;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import swing.revival.util.ResourceBundleHelper;

/**
 *
 * @author Alistair A. Israel
 */
public class ActiveFrame extends JFrame {

    private final ResourceBundleHelper resourceHelper;

    private final WindowListener windowListener = new WindowAdapter() {
        /**
         * {@inheritDoc}
         *
         * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
         */
        @Override
        public void windowClosing(final WindowEvent e) {
            onWindowClosing();
        }
    };

    /**
     * @param title
     *        the title for the frame
     */
    public ActiveFrame(final String title) {
        this();
        setTitle(title);
    }

    /**
     *
     */
    public ActiveFrame() {
        super();
        resourceHelper = ResourceBundleHelper.forClass(getClass());
        setTitle(resourceHelper.getString("title"));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(windowListener);
        initializeComponents();
    }

    /**
     *
     */
    protected void initializeComponents() {
    }

    /**
     *
     */
    protected void onWindowClosing() {
    }

}

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
package swing.revival;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.Group;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

/**
 * Copyright (c) 2009 by Alistair A. Israel.
 * This software is made available under the terms of the MIT License.
 *
 * Created Sep 30, 2009
 */
public class GroupLayoutJPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = -8039850274015880060L;

    private final GroupLayout layout;

    /**
     *
     */
    public GroupLayoutJPanel() {
        super();
        layout = new GroupLayout(this);
        layout.setAutoCreateContainerGaps(true);
        setLayout(layout);
    }

    /**
     * @return the layout
     */
    @Override
    public final GroupLayout getLayout() {
        return layout;
    }

    /**
     * @return a new {@code ParallelGroup}
     * @see javax.swing.GroupLayout#createParallelGroup()
     */
    public final ParallelGroup createParallelGroup() {
        return layout.createParallelGroup();
    }

    /**
     * @param alignment
     *        the alignment for the elements of the group
     * @param resizable
     *        {@code true} if the group is resizable; if the group is not
     *        resizable the preferred size is used for the minimum and maximum
     *        size of the group
     * @return a new {@code ParallelGroup}
     * @see javax.swing.GroupLayout#createParallelGroup(javax.swing.GroupLayout.Alignment,
     *      boolean)
     */
    public final ParallelGroup createParallelGroup(final Alignment alignment,
            final boolean resizable) {
        return layout.createParallelGroup(alignment, resizable);
    }

    /**
     * @param alignment
     *        {@link Alignment}
     * @return {@link ParallelGroup}
     * @see javax.swing.GroupLayout#createParallelGroup(javax.swing.GroupLayout.Alignment)
     */
    public final ParallelGroup createParallelGroup(final Alignment alignment) {
        return layout.createParallelGroup(alignment);
    }

    /**
     * @return {@link SequentialGroup}
     * @see javax.swing.GroupLayout#createSequentialGroup()
     */
    public final SequentialGroup createSequentialGroup() {
        return layout.createSequentialGroup();
    }

    /**
     * @param group
     *        {@link Group}
     * @see javax.swing.GroupLayout#setHorizontalGroup(javax.swing.GroupLayout.Group)
     */
    public final void setHorizontalGroup(final Group group) {
        layout.setHorizontalGroup(group);
    }

    /**
     * @param group
     *        {@link Group}
     * @see javax.swing.GroupLayout#setVerticalGroup(javax.swing.GroupLayout.Group)
     */
    public final void setVerticalGroup(final Group group) {
        layout.setVerticalGroup(group);
    }

}

/**
 * swing-revival:
 * Swing Revival Toolkit
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Nov 11, 2009
 */
package swing.revival.assembly.model;

import java.awt.Container;

import swing.revival.util.ResourceBundleHelper;

/**
 * @author Alistair A. Israel
 * @since 0.2
 */
public class ContainerContext {

    private final Container container;

    private final ResourceBundleHelper resources;

    /**
     * @param container
     *        the {@link Container}
     */
    public ContainerContext(final Container container) {
        this.container = container;
        resources = ResourceBundleHelper.forClass(container.getClass());
    }

    /**
     * @return the container
     */
    public final Container getContainer() {
        return container;
    }

    /**
     * @return the resources
     */
    public final ResourceBundleHelper getResources() {
        return resources;
    }

}

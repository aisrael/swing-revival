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
package swing.revival.builders;

import javax.swing.JComponent;

import swing.revival.construction.postprocessors.JComponentFieldPostProcessor;
import swing.revival.context.ComponentBuilderContext;
import swing.revival.metadata.ComponentFieldInfo;

/**
 * @author Alistair A. Israel
 * @since 0.1
 */
public class LabelsPostProcessor extends ComponentBuilderContext.Aware implements JComponentFieldPostProcessor {

    /**
     * @param context
     *        the {@link ComponentBuilderContext}
     */
    public LabelsPostProcessor(final ComponentBuilderContext context) {
        super(context);
    }

    /**
     * {@inheritDoc}
     *
     * @see swing.revival.construction.postprocessors.JComponentFieldPostProcessor#postProcess(swing.revival.metadata.ComponentFieldInfo)
     */
    @Override
    public void postProcess(final ComponentFieldInfo fieldInfo, final JComponent component) {

    }

}

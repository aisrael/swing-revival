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
package swing.revival.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Alistair A. Israel
 */
@Target({ ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RadioButton {

    /**
     * A specific name to use
     */
    String name() default "";

    /**
     * The button group this button should belong to
     */
    String buttonGroup() default "";

    /**
     * Used for
     * {@link javax.swing.AbstractButton#setHorizontalTextPosition(int)}
     */
    HorizontalKey horizontalTextPosition() default HorizontalKey.DEFAULT;
}

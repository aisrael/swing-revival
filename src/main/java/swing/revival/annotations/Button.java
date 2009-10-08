/**
 * swing-revival:
 * Swing Revival Toolkit
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Oct 7, 2009
 */
package swing.revival.annotations;

import static swing.revival.enums.ButtonType.BUTTON;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import swing.revival.enums.ButtonType;

/**
 *
 * @author Alistair A. Israel
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Button {

    /**
     * The button name to use
     */
    String name() default "";

    /**
     * The button type
     */
    ButtonType type() default BUTTON;
}

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
package swing.revival.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @param <T>
 *        a type
 * @author Alistair A. Israel
 */
public class BeanWrapper<T> {

    private static final Logger LOGGER = Logger.getLogger(BeanWrapper.class.getName());

    private final Class<T> clazz;

    private final T object;

    /**
     * @param object
     *        the object to wrap
     */
    @SuppressWarnings("unchecked")
    public BeanWrapper(final T object) {
        this.object = object;
        this.clazz = (Class<T>) object.getClass();
    }

    /**
     * @param <T>
     *        a type
     * @param object
     *        an object of the type
     * @return {@link BeanWrapper}
     */
    public static <T> BeanWrapper<T> wrap(final T object) {
        return new BeanWrapper<T>(object);
    }

    /**
     * Returns an array of <code>Field</code> objects reflecting all the fields
     * declared by the class of the underlying bean. This includes public,
     * protected, default
     *
     * @return the array of <code>Field</code> objects representing all the
     *         declared fields of the underlying bean class
     * @see java.lang.Class#getDeclaredFields()
     */
    public final Field[] getDeclaredFields() {
        return clazz.getDeclaredFields();
    }

    /**
     * @param <A>
     *        an annotation type
     * @param annotationClass
     *        the class of the annotation we're looking for
     * @return the annotation
     * @see java.lang.Class#getAnnotation(java.lang.Class)
     */
    public final <A extends Annotation> A getAnnotation(final Class<A> annotationClass) {
        return clazz.getAnnotation(annotationClass);
    }

    /**
     * @param field
     *        the field to set
     * @param value
     *        the value to set it to
     */
    public final void set(final Field field, final Object value) {
        final boolean accessible = field.isAccessible();
        field.setAccessible(true);
        try {
            field.set(object, value);
        } catch (final IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        } catch (final IllegalAccessException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
        if (!accessible) {
            field.setAccessible(accessible);
        }
    }

    /**
     * @param field
     *        the field to get
     * @return the field's value
     */
    public final Object get(final Field field) {
        final boolean accessible = field.isAccessible();
        field.setAccessible(true);
        try {
            return field.get(object);
        } catch (final IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        } catch (final IllegalAccessException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        } finally {
            if (!accessible) {
                field.setAccessible(accessible);
            }
        }
        return null;
    }

    /**
     * @param <T>
     *        a type
     * @author Alistair A. Israel
     */
    public static class Support<T> {

        private final BeanWrapper<T> beanWrapper;

        /**
         * @param object
         *        the object to wrap
         */
        public Support(final T object) {
            this.beanWrapper = new BeanWrapper<T>(object);
        }

        /**
         * @param field
         *        the field to set
         * @param value
         *        the value to set it to
         * @see swing.revival.util.BeanWrapper#set(java.lang.reflect.Field,
         *      java.lang.Object)
         */
        public final void set(final Field field, final Object value) {
            beanWrapper.set(field, value);
        }

        /**
         * @param field
         *        the field to get
         * @return the field's value
         * @see swing.revival.util.BeanWrapper#get(java.lang.reflect.Field)
         */
        public final Object get(final Field field) {
            return beanWrapper.get(field);
        }

        /**
         * @return Field[]
         * @see swing.revival.util.BeanWrapper#getDeclaredFields()
         */
        public final Field[] getDeclaredFields() {
            return beanWrapper.getDeclaredFields();
        }

        /**
         * @param <A>
         *        an annotation type
         * @param annotationClass
         *        the class of the annotation we're looking for
         * @return the annotation
         * @see swing.revival.util.BeanWrapper#getAnnotation(java.lang.Class)
         */
        public final <A extends Annotation> A getAnnotation(final Class<A> annotationClass) {
            return beanWrapper.getAnnotation(annotationClass);
        }

    }

}

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
package swing.revival.util.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import swing.revival.util.ClassUtils;

/**
 * If Java were more dynamic, we wouldn't need wrappers and decorators.
 *
 * @author Alistair A. Israel
 * @since 0.1
 */
public class ClassWrapper {

    private final Class<?> wrappedClass;

    private final String shortName;

    /**
     * @param clazz
     *        the class to wrap
     */
    public ClassWrapper(final Class<?> clazz) {
        this.wrappedClass = clazz;
        this.shortName = ClassUtils.getShortName(wrappedClass);

    }

    /**
     * @return the wrappedClass
     */
    public final Class<?> getWrappedClass() {
        return wrappedClass;
    }

    /**
     * Returns the underlying <code>Class</code>'s annotation for the specified type if such an annotation is present,
     * else <code>null</code>.
     *
     * @param <A>
     *        the annotation type
     * @param annotationClass
     *        the Class object corresponding to the annotation type
     * @return the underlying <code>Class</code>'s annotation for the specified annotation type if present on this
     *         element, else <code>null</code>
     * @see java.lang.Class#getAnnotation(java.lang.Class)
     */
    public final <A extends Annotation> A getAnnotation(final Class<A> annotationClass) {
        return wrappedClass.getAnnotation(annotationClass);
    }

    /**
     * Returns all annotations present on the underlying <code>Class</code>. (Returns an array of length zero if the
     * <code>Field</code> element has no annotations.)
     *
     * @return all annotations present on the underlying <code>Class</code>
     * @see java.lang.Class#getAnnotations()
     */
    public final Annotation[] getAnnotations() {
        return wrappedClass.getAnnotations();
    }

    /**
     * Returns true if an annotation for the specified type is present on the underlying <code>Class</code>, else
     * <code>false</code>. This method is designed primarily for convenient access to marker annotations.
     *
     * @param annotationClass
     *        the Class object corresponding to the annotation type
     * @return <code>true</code> true if an annotation for the specified annotation type is present on the underlying
     *         <code>Class</code>, else <code>false</code>
     * @see java.lang.Class#isAnnotationPresent(java.lang.Class)
     */
    public final boolean isAnnotationPresent(final Class<? extends Annotation> annotationClass) {
        return wrappedClass.isAnnotationPresent(annotationClass);
    }

    /**
     * @return {@link FieldWrapper}[]
     */
    public final FieldWrapper[] listAllFields() {
        final List<FieldWrapper> results = new ArrayList<FieldWrapper>();
        for (final Field field : wrappedClass.getDeclaredFields()) {
            results.add(new FieldWrapper(field));
        }
        return results.toArray(new FieldWrapper[results.size()]);
    }

    /**
     * @return {@link FieldWrapper}[]
     */
    public final FieldWrapper[] listAllInstanceFields() {
        final List<FieldWrapper> results = new ArrayList<FieldWrapper>();
        for (final Field field : wrappedClass.getDeclaredFields()) {
            final FieldWrapper fieldWrapper = new FieldWrapper(field);
            if (!fieldWrapper.isStatic()) {
                results.add(fieldWrapper);
            }
        }
        return results.toArray(new FieldWrapper[results.size()]);
    }

    /**
     * @return the shortName
     */
    public final String getShortName() {
        return shortName;
    }

}

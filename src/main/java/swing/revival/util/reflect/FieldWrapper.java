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
import java.lang.reflect.Modifier;

/**
 * @author Alistair A. Israel
 * @since 0.1
 */
public class FieldWrapper {

    private final Field field;

    private final int mod;

    /**
     * @param field
     *        the {@link Field} to wrap
     */
    public FieldWrapper(final Field field) {
        this.field = field;
        this.mod = field.getModifiers();
    }

    /**
     * @return the field
     */
    public final Field getField() {
        return field;
    }

    /**
     * Returns the underlying <code>Field</code>'s annotation for the specified type if such an annotation is present,
     * else <code>null</code>.
     * 
     * @param <T>
     *        the annotation type
     * @param annotationClass
     *        the Class object corresponding to the annotation type
     * @return the underlying <code>Field</code>'ss annotation for the specified annotation type if present on this
     *         element, else <code>null</code>
     * @see java.lang.reflect.Field#getAnnotation(java.lang.Class)
     */
    public final <T extends Annotation> T getAnnotation(final Class<T> annotationClass) {
        return field.getAnnotation(annotationClass);
    }

    /**
     * Returns all annotations present on the underlying <code>Field</code>. (Returns an array of length zero if the
     * <code>Field</code> element has no annotations.)
     * 
     * @return all annotations present on the underlying <code>Field</code>
     * @see java.lang.reflect.AccessibleObject#getAnnotations()
     */
    public final Annotation[] getAnnotations() {
        return field.getAnnotations();
    }

    /**
     * Returns the Java language modifiers for the field wrapped by this <code>FieldWrapper</code> object, as an
     * integer. The <code>Modifier</code> class should be used to decode the modifiers.
     * 
     * @return the field modifiers
     * @see java.lang.reflect.Field#getModifiers()
     */
    public final int getModifiers() {
        return field.getModifiers();
    }

    /**
     * Returns the name of the field wrapped by this <code>FieldWrapper</code> object.
     * 
     * @return the name of the underlying field
     * @see java.lang.reflect.Field#getName()
     */
    public final String getName() {
        return field.getName();
    }

    /**
     * Returns a <code>Class</code> object that identifies the declared type for the underlying field wrapped by this
     * <code>FieldWrapper</code> object.
     * 
     * @return a <code>Class</code> object identifying the declared type of the underlying field
     * @see java.lang.reflect.Field#getType()
     */
    public final Class<?> getType() {
        return field.getType();
    }

    /**
     * Get the value of the accessible flag for the underlying field.
     * 
     * @return the value of the object's <code>accessible</code> flag
     * @see java.lang.reflect.AccessibleObject#isAccessible()
     */
    public final boolean isAccessible() {
        return field.isAccessible();
    }

    /**
     * Returns true if an annotation for the specified type is present on the underlying field, else false. This method
     * is designed primarily for convenient access to marker annotations.
     * 
     * @param annotationClass
     *        the Class object corresponding to the annotation type
     * @return <code>true</code> true if an annotation for the specified annotation type is present on this element,
     *         else <code>false</code>
     * @see java.lang.reflect.AccessibleObject#isAnnotationPresent(java.lang.Class)
     */
    public final boolean isAnnotationPresent(final Class<? extends Annotation> annotationClass) {
        return field.isAnnotationPresent(annotationClass);
    }

    /**
     * Returns <code>true</code> if the underlying field represents an element of an enumerated type; returns
     * <code>false</code> otherwise.
     * 
     * @return <code>true</code> if and only if the underlying field represents an element of an enumerated type.
     * @see java.lang.reflect.Field#isEnumConstant()
     */
    public final boolean isEnumConstant() {
        return field.isEnumConstant();
    }

    /**
     * Returns <code>true</code> if the underlying field is a synthetic field; returns <code>false</code> otherwise.
     * 
     * @return true if and only if the underlying field is a synthetic field as defined by the Java Language
     *         Specification.
     * @see java.lang.reflect.Field#isSynthetic()
     */
    public final boolean isSynthetic() {
        return field.isSynthetic();
    }

    /**
     * Set the <code>accessible</code> flag for the underlying field to the indicated boolean value. A value of
     * <code>true</code> indicates that the reflected object should suppress Java language access checking when it is
     * used. A value of <code>false</code> indicates that the reflected object should enforce Java language access
     * checks.
     * 
     * @param flag
     *        the new value for the <code>accessible</code> flag
     * @throws SecurityException
     *         if the request is denied.
     * @see java.lang.reflect.AccessibleObject#setAccessible(boolean)
     */
    public final void setAccessible(final boolean flag) throws SecurityException {
        field.setAccessible(flag);
    }

    /**
     * @return true if the underlying <code>Field</code> was declared <code>static</code>
     */
    public final boolean isStatic() {
        return Modifier.isStatic(mod);
    }

    /**
     * @return true if the underlying <code>Field</code> was declared <code>public</code>
     */
    public final boolean isPublic() {
        return Modifier.isPublic(mod);
    }
}

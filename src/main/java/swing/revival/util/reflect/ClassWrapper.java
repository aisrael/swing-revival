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

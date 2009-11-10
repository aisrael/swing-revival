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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import org.junit.Test;

/**
 * JUnit test for {@link ClassWrapper}.
 * 
 * @author Alistair A. Israel
 * @since 0.1
 */
public final class ClassWrapperTest {

    /**
     * 
     */
    // CHECKSTYLE:OFF
    @SuppressWarnings("unused")
    public static class SomeClass {

        public static final int CONSTANT = -1;

        private final Date timestamp = new Date();
    }

    // CHECKSTYLE:ON

    /**
     * Test for {@link ClassWrapper#listAllFields()}
     */
    @Test
    public void testListAllFields() {
        final ClassWrapper classWrapper = new ClassWrapper(SomeClass.class);
        final Map<String, FieldWrapper> fields = buildFieldsByNameMap(classWrapper.listAllFields());
        assertEquals(2, fields.size());

        final FieldWrapper constantField = fields.get("CONSTANT");
        assertEquals(int.class, constantField.getType());
        assertTrue(constantField.isStatic());
        assertTrue(constantField.isPublic());

        assertEquals(Date.class, fields.get("timestamp").getType());
    }

    /**
     * @param allFields
     *        array of <code>FieldWrapper</code>s
     * @return <code>Map</code> of <code>FieldWrapper</code> indexed by name
     */
    private Map<String, FieldWrapper> buildFieldsByNameMap(final FieldWrapper[] allFields) {
        final Map<String, FieldWrapper> fieldsByName = new Hashtable<String, FieldWrapper>();
        for (final FieldWrapper fieldWrapper : allFields) {
            fieldsByName.put(fieldWrapper.getName(), fieldWrapper);
        }
        return fieldsByName;
    }
}

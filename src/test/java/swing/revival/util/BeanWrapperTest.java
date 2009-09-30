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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * JUnit test for {@link BeanWrapper}.
 *
 * @author Alistair A. Israel
 */
public final class BeanWrapperTest {

    /**
     *
     */
    @Ignore
    public static final class Bean {

        private long currentTimeMillis = System.currentTimeMillis();

        private String name = Long.toString(currentTimeMillis);

        /**
         * @return the currentTimeMillis
         */
        public long getCurrentTimeMillis() {
            return currentTimeMillis;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

    }

    private Bean bean;

    private BeanWrapper<Bean> wrapper;

    /**
     *
     */
    @Before
    public void setUp() {
        bean = new Bean();
        wrapper = BeanWrapper.wrap(bean);
    }

    /**
     *
     */
    @After
    public void tearDown() {
        wrapper = null;
        bean = null;
    }

    /**
     * Test for {@link BeanWrapper#getDeclaredFields()}.
     */
    @Test
    public void testGetDeclaredFields() {
        final Field[] fields = wrapper.getDeclaredFields();
        assertNotNull(fields);
        assertEquals(2, fields.length);
    }

    /**
     * Test for {@link BeanWrapper#getAnnotation(java.lang.Class)}.
     */
    @Test
    public void testGetAnnotation() {
        assertNotNull(wrapper.getAnnotation(Ignore.class));
    }

    /**
     * Test for {@link BeanWrapper#get(java.lang.reflect.Field)}.
     *
     * @throws Exception
     *         on exception
     */
    @Test
    public void testGet() throws Exception {
        final Field ctmField = wrapper.getDeclaredField("currentTimeMillis");
        assertEquals(bean.currentTimeMillis, wrapper.get(ctmField));

        final Field nameField = wrapper.getDeclaredField("name");
        assertSame(bean.name, wrapper.get(nameField));
    }

    /**
     * Test for
     * {@link BeanWrapper#set(java.lang.reflect.Field, java.lang.Object)} .
     *
     * @throws Exception
     *         on exception
     */
    @Test
    public void testSet() throws Exception {
        final Field nameField = wrapper.getDeclaredField("name");
        final String newValue = "new name";
        wrapper.set(nameField, newValue);
        assertSame(newValue, bean.name);
    }

}

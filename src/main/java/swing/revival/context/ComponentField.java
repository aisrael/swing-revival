/**
 * swing-revival:
 * Swing Revival Toolkit
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Oct 8, 2009
 */
package swing.revival.context;

import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author Alistair A. Israel
 */
public class ComponentField {

    private final String name;

    private final Field field;

    private final Map<String, String> properties = new Hashtable<String, String>();

    private FontInfo fontInfo;

    /**
     * @param name
     *        the component name
     * @param field
     *        the actual {@link Field}
     */
    public ComponentField(final String name, final Field field) {
        this.name = name;
        this.field = field;
    }

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @return the field
     */
    public final Field getField() {
        return field;
    }

    /**
     * @return the fontInfo
     */
    public final FontInfo getFontInfo() {
        return fontInfo;
    }

    /**
     * @return the component field type
     * @see java.lang.reflect.Field#getType()
     */
    public final Class<?> getType() {
        return field.getType();
    }

    /**
     * @param key
     *        the key to get
     * @param def
     *        the default in case the property isn't found
     * @return the property value, or the default
     */
    public final String get(final String key, final String def) {
        if (properties.containsKey(key)) {
            return properties.get(key);
        }
        return def;
    }

    /**
     * @param key
     *        the key to get
     * @return the property value, or null
     */
    public final String getString(final String key) {
        return properties.get(key);
    }

    /**
     *
     */
    public static class Builder {

        private final ComponentField componentField;

        /**
         * @param name
         *        the component name
         * @param field
         *        the actual {@link Field}
         */
        public Builder(final String name, final Field field) {
            componentField = new ComponentField(name, field);
        }

        /**
         * @param fontInfo
         *        the {@link FontInfo} to set
         */
        public final void setFontInfo(final FontInfo fontInfo) {
            componentField.fontInfo = fontInfo;
        }

        /**
         * @param key
         *        the property key
         * @param value
         *        the value
         */
        public final void addProperty(final String key, final String value) {
            componentField.properties.put(key, value);
        }

        /**
         * @return the {@link ComponentField}
         */
        public final ComponentField build() {
            return componentField;
        }
    }
}

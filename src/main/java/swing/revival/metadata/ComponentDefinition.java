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
package swing.revival.metadata;

import static swing.revival.util.ClassUtils.getShortName;

import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;

import swing.revival.util.Assert;

/**
 * Holds meta-data about a component field.
 *
 * @author Alistair A. Israel
 */
public class ComponentDefinition {

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
    public ComponentDefinition(final String name, final Field field) {
        Assert.isAssignable(JComponent.class, field.getType(), "'field' must represent a JComponent-derived type!");
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
     * @param fontInfo
     *        the fontInfo to set
     */
    public final void setFontInfo(final FontInfo fontInfo) {
        this.fontInfo = fontInfo;
    }

    /**
     * @return the component field type
     * @see java.lang.reflect.Field#getType()
     */
    @SuppressWarnings("unchecked")
    public final Class<? extends JComponent> getType() {
        return (Class<? extends JComponent>) field.getType();
    }

    /**
     * @param key
     *        the key to set
     * @param value
     *        the value
     */
    public final void addProperty(final String key, final String value) {
        properties.put(key, value);
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
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {
        return name + " (" + getShortName(field.getType()) + ")";
    }

    /**
     * @param field
     *        a {@link Field}
     * @return {@link ComponentDefinition}
     */
    public static ComponentDefinition wrap(final Field field) {
        return new ComponentDefinition(field.getName(), field);
    }

    /**
     * @return true if this component definition represents a {@link JLabel}-derived field
     */
    public final boolean isLabel() {
        return JLabel.class.isAssignableFrom(field.getType());
    }
}

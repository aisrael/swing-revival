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

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Results of performing reflection on a Container class looking for Swing
 * Revival annotations, performed by {@link Inspector}.
 *
 * @author Alistair A. Israel
 */
public class InspectionResult {

    private FontInfo defaultFontInfo;

    private final List<ComponentField> componentFields = new ArrayList<ComponentField>();

    private final Map<String, ComponentField> fieldMap =
            new Hashtable<String, ComponentField>();

    /**
     * @return List of {@link ComponentField}
     */
    public final List<ComponentField> listComponentFields() {
        return this.componentFields;
    }

    /**
     * @return the defaultFontInfo
     */
    public final FontInfo getDefaultFontInfo() {
        return defaultFontInfo;
    }

    /**
     * @return Set of field names
     */
    public final Set<String> listFieldNames() {
        return fieldMap.keySet();
    }

    /**
     * @param name
     *        the field name
     * @return the component field
     */
    public final ComponentField getField(final String name) {
        return fieldMap.get(name);
    }

    /**
     * A builder pattern.
     *
     * @author Alistair A. Israel
     */
    public static class Builder {

        private final InspectionResult results = new InspectionResult();

        /**
         * @param fontInfo
         *        the default {@link FontInfo}
         */
        public final void setDefaultFontInfo(final FontInfo fontInfo) {
            results.defaultFontInfo = fontInfo;
        }

        /**
         * @param field
         *        the {@link ComponentField} to add
         */
        public final void addComponentField(final ComponentField field) {
            results.componentFields.add(field);
            results.fieldMap.put(field.getName(), field);
        }

        /**
         * @return the {@link InspectionResult}
         */
        public final InspectionResult build() {
            return results;
        }
    }
}
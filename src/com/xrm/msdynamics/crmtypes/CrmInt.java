package com.xrm.msdynamics.crmtypes;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 *
 * @author rguluarte
 */
public class CrmInt extends BaseType {

    private final String key;
    private final int value;

    public CrmInt(String key, int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String ToXML() {
        return "<a:KeyValuePairOfstringanyType>\n"
                + "<b:key>" + StringEscapeUtils.escapeXml(this.key) + "</b:key>\n"
                + "<b:value i:type=\"c:int\" xmlns:c=\"http://www.w3.org/2001/XMLSchema\">" + this.value + "</b:value>\n"
                + "</a:KeyValuePairOfstringanyType>\n";
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }

}

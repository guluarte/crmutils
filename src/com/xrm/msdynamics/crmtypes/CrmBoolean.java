package com.xrm.msdynamics.crmtypes;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 *
 * @author rguluarte
 */
public class CrmBoolean extends BaseType {
    
    private final String key;
    private final boolean value;
    
    public CrmBoolean(String key, boolean value ) {
        this.key = key;
        this.value = value;
    }
    
    @Override
    public String ToXML() {
        return "<a:KeyValuePairOfstringanyType>\n" +
                "<b:key>"+ StringEscapeUtils.escapeXml(this.key) +"</b:key>\n" +
                "<b:value i:type=\"c:boolean\" xmlns:c=\"http://www.w3.org/2001/XMLSchema\">"+ this.value +"</b:value>\n" +
                "</a:KeyValuePairOfstringanyType>\n";
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
        if(this.value) {
            return "true";
        }
        return "false";
    }
    
}
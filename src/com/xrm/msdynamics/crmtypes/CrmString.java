package com.xrm.msdynamics.crmtypes;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 *
 * @author rguluarte
 */
public class CrmString extends BaseType {
    private final String name;
    private final String value;

    public CrmString(String name, String value) {
        this.name = name;
        this.value = value;
    }
    @Override
    public String ToXML() {        
        return "<a:KeyValuePairOfstringanyType>\n" +
"                 <b:key>"+ StringEscapeUtils.escapeXml(this.name) +"</b:key>\n" +
"                 <b:value i:type=\"c:string\" xmlns:c=\"http://www.w3.org/2001/XMLSchema\">"+ StringEscapeUtils.escapeXml(this.value) +"</b:value>\n" +
"               </a:KeyValuePairOfstringanyType>\n";
    }

    @Override
    public String getKey() {
        return this.name;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    public boolean isEmpty() {
        return this.value == null ||  this.value.isEmpty();
    }
    
    @Override
    public String toString() {
        return this.value;
    }
    
}

package com.xrm.msdynamics.crmtypes;

import java.math.BigDecimal;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 *
 * @author rguluarte
 */
public class Money extends BaseType {
    private final String key;
    private final BigDecimal value;
    public Money(String key, BigDecimal value) {
        this.key = key;
        this.value = value;
    }
    @Override
    public String ToXML() {
        return "<a:KeyValuePairOfstringanyType>\n" +
"            <b:key>"+ StringEscapeUtils.escapeXml(this.key) +"</b:key>\n" +
"            <b:value i:type=\"a:Money\">\n" +
"               <a:Value>"+this.value.toPlainString()+"</a:Value>\n" +
"            </b:value>\n" +
"         </a:KeyValuePairOfstringanyType>\n";
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
        return this.value.toPlainString();
    }
}
package com.xrm.msdynamics.crmtypes;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author rguluarte
 */
public class DateTime extends BaseType {
    private final String key;
    private final Date value;
    //2012-06-08T10:42:45.8655676-05:00
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZ") {
        public StringBuffer format(Date date, StringBuffer toAppendTo, java.text.FieldPosition pos) {
            StringBuffer toFix = super.format(date, toAppendTo, pos);
            return toFix.insert(toFix.length()-2, ':');
        };
    };
    
    public DateTime(String key, Date value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String ToXML() {
        return "<a:KeyValuePairOfstringanyType>\n" +
                "<b:key>"+this.key+"</b:key>\n" +
                "<b:value i:type=\"c:dateTime\" xmlns:c=\"http://www.w3.org/2001/XMLSchema\">"+DATE_FORMAT.format(value)+"</b:value>\n" +
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
        return DATE_FORMAT.format(value).toString();
    }
    

}

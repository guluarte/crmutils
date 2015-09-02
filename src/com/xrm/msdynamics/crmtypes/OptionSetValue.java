package com.xrm.msdynamics.crmtypes;

/**
 *
 * @author rguluarte
 */
public class OptionSetValue extends BaseType {
    private final String key;
    private int value;

    public OptionSetValue(String key, int value) {
        this.key = key;
        this.value = value;
    }

    
    @Override
    public String ToXML() {
        return "<a:KeyValuePairOfstringanyType>\n" +
                "<b:key>"+this.key+"</b:key>\n" +
                "<b:value i:type=\"a:OptionSetValue\">\n" +
                "<a:Value>"+this.value+"</a:Value>\n" +
                "</b:value>\n" +
                "</a:KeyValuePairOfstringanyType>\n";
    }

    public void setValue(int value) {
        this.value = value;
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
        return Integer.toString(value);
    }
    
}
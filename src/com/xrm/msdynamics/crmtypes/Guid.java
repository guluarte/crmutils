package com.xrm.msdynamics.crmtypes;

/**
 *
 * @author rguluarte
 */
public class Guid extends BaseType {

    private String key;
    private final String value;

    public static final String EMPTY_GUID = "00000000-0000-0000-0000-000000000000";

    public Guid(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public Guid(String value) {
        this.value = value;
    }

    @Override
    public String ToXML() {
        return "<a:KeyValuePairOfstringanyType>\n"
                + "<b:key>" + this.key + "</b:key>\n"
                + "<b:value i:type=\"c:guid\" xmlns:c=\"http://schemas.microsoft.com/2003/10/Serialization/\">" + this.value + "</b:value>\n"
                + " </a:KeyValuePairOfstringanyType>\n";
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
        return this.value;
    }

}

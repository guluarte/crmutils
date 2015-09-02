package com.xrm.msdynamics.crmtypes;

/**
 *
 * @author rguluarte
 */
public class CrmDouble implements XmlSerializable {

    private final String key;
    private final Double value;

    public CrmDouble(String key, Double value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String ToXML() {
        return "<a:KeyValuePairOfstringanyType>\n"
                + "                  <b:key>" + this.key + "</b:key>\n"
                + "                  <b:value i:type=\"c:double\" xmlns:c=\"http://www.w3.org/2001/XMLSchema\">" + this.value + "</b:value>\n"
                + "                </a:KeyValuePairOfstringanyType>\n";
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
        return this.value.toString();
    }

}

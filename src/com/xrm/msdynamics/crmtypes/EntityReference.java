package com.xrm.msdynamics.crmtypes;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 *
 * @author rguluarte
 */
public class EntityReference extends BaseType {

    private final String id;
    private final String logicalName;
    private final String key;

    public EntityReference(String logicalName, String key, String id) {
        this.id = id;
        this.logicalName = logicalName;
        this.key = key;
    }

    @Override
    public String ToXML() {
        return " <a:KeyValuePairOfstringanyType>\n"
                + "<b:key>" + StringEscapeUtils.escapeXml(this.key) + "</b:key>\n"
                + "<b:value i:type=\"a:EntityReference\">\n"
                + "  <a:Id>" + StringEscapeUtils.escapeXml(this.id) + "</a:Id>\n"
                + "  <a:LogicalName>" + StringEscapeUtils.escapeXml(this.logicalName) + "</a:LogicalName>\n"
                + "  <a:Name i:nil=\"true\" />\n"
                + "</b:value>\n"
                + "</a:KeyValuePairOfstringanyType>\n";

    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public Object getValue() {
        return this.id;
    }

    public String getLogicalName() {
        return this.logicalName;
    }

    @Override
    public String toString() {
        return this.id;
    }

}

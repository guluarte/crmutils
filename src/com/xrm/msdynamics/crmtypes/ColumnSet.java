package com.xrm.msdynamics.crmtypes;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 *
 * @author rguluarte
 */
public class ColumnSet implements XmlSerializable {

    private final String[] columns;

    public ColumnSet(String[] columns) {
        this.columns = columns;
    }

    private String getColumnsAsXml() {
        StringBuilder sb = new StringBuilder();

        for (String column : columns) {
            sb.append("<c:string>").append(StringEscapeUtils.escapeXml(column)).append("</c:string>\n");
        }

        return sb.toString();
    }

    @Override
    public String ToXML() {
        if (columns.length > 0) {
            return "<a:KeyValuePairOfstringanyType>\n"
                    + "            <b:key>ColumnSet</b:key>\n"
                    + "            <b:value i:type=\"a:ColumnSet\">\n"
                    + "              <a:AllColumns>false</a:AllColumns>\n"
                    + "              <a:Columns xmlns:c=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\">\n"
                    + getColumnsAsXml()
                    + "              </a:Columns>\n"
                    + "            </b:value>\n"
                    + "          </a:KeyValuePairOfstringanyType>\n";
        } else {
            return "<a:KeyValuePairOfstringanyType>\n"
                    + "                        <b:key>ColumnSet</b:key>\n"
                    + "                        <b:value i:type=\"a:ColumnSet\">\n"
                    + "                          <a:AllColumns>true</a:AllColumns>\n"
                    + "                          <a:Columns xmlns:c=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\" />\n"
                    + "                        </b:value>\n"
                    + "                      </a:KeyValuePairOfstringanyType>\n";
        }
    }

    @Override
    public String getKey() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

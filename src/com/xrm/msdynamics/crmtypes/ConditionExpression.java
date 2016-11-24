/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.crmtypes;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 *
 * @author rguluarte
 */
public class ConditionExpression extends BaseType {

    private final String atributeName;
    private final String value;
    private final String operator;

    public class Operator {

        public static final String Equal = "Equal";
    }

    public ConditionExpression(String operator, String atributeName, String value) {
        this.atributeName = atributeName;
        this.value = value;
        this.operator = operator;
    }

    @Override
    public String ToXML() {
        return "<a:ConditionExpression>\n"
                + "                        <a:AttributeName>" + StringEscapeUtils.escapeXml(atributeName) + "</a:AttributeName>\n"
                + "                        <a:Operator>" + operator + "</a:Operator>\n"
                + "                        <a:Values xmlns:c=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\">\n"
                + "                          <c:anyType i:type=\"d:string\" xmlns:d=\"http://www.w3.org/2001/XMLSchema\">" + StringEscapeUtils.escapeXml(value) + "</c:anyType>\n"
                + "                        </a:Values>\n"
                + "</a:ConditionExpression>\n";
    }

    @Override
    public String getKey() {
        return atributeName;
    }

    @Override
    public Object getValue() {
        return value;
    }

}

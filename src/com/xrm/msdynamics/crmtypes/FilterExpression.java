/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.crmtypes;

import java.util.ArrayList;

/**
 *
 * @author rguluarte
 */
public class FilterExpression extends BaseType {

    private final String operator;
    private final ArrayList<ConditionExpression> conditionalExpressions;

    public FilterExpression(String filterOperator, ConditionExpression conditionExpression) {
        this.operator = filterOperator;
        this.conditionalExpressions = new ArrayList<>();
        this.conditionalExpressions.add(conditionExpression);
    }

    public void addCondition(ConditionExpression conditionExpression) {
        this.conditionalExpressions.add(conditionExpression);
    }

    @Override
    public String ToXML() {
        String conditionsXml = "";
        for (ConditionExpression conditionalExpression : conditionalExpressions) {
            conditionsXml += conditionalExpression.ToXML();
        }

        return "<a:FilterExpression>\n"
                + "                    <a:Conditions>\n"
                + conditionsXml
                + "                    </a:Conditions>\n"
                + "                    <a:FilterOperator>" + operator + "</a:FilterOperator>\n"
                + "                    <a:Filters />\n"
                + "</a:FilterExpression>\n";
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

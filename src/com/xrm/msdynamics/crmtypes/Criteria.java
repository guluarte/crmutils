package com.xrm.msdynamics.crmtypes;

import java.util.ArrayList;

/**
 *
 * @author rguluarte
 */
public class Criteria extends BaseType {

    private final ArrayList<FilterExpression> filters;
    private final String filterOperator;

    public class FilterOperator {

        public static final String Or = "Or";
        public static final String And = "And";
    }

    public Criteria(String filterOperator, FilterExpression filter) {
        this.filters = new ArrayList<>();
        this.filters.add(filter);
        this.filterOperator = filterOperator;
    }

    public void addFilter(FilterExpression filter) {
        this.filters.add(filter);
    }

    @Override
    public String ToXML() {

        String conditionsXml = "";
        for (FilterExpression conditionalExpression : filters) {
            conditionsXml += conditionalExpression.ToXML();
        }

        return "<a:Criteria>\n"
                + "                <a:Conditions />\n"
                + "                <a:FilterOperator>" + filterOperator + "</a:FilterOperator>\n"
                + "                <a:Filters>\n"
                + conditionsXml
                + "                </a:Filters>\n"
                + "</a:Criteria>\n";
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

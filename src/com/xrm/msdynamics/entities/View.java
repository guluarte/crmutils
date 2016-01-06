/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.entities;

import com.xrm.msdynamics.Enums;
import com.xrm.msdynamics.crmtypes.CrmString;
import com.xrm.msdynamics.crmtypes.Entity;
import com.xrm.msdynamics.crmtypes.XmlSerializable;

import java.util.ArrayList;
import org.w3c.dom.NodeList;

/**
 *
 * @author rguluarte
 */
public class View extends BaseEntity {

    Entity entity;

    private CrmString fetchxml;
    private CrmString returnedtypecode;
    private CrmString name;

    public static final String ID = "savedqueryid";
    public static final String FETCHXML = "fetchxml";
    public static final String RETURNEDTYPECODE = "returnedtypecode";
    public static final String NAME = "name";

    public static String[] Columns = new String[]{
        ID,
        NAME,
        FETCHXML,
        RETURNEDTYPECODE
    };

    public View(NodeList nodes) {
        super(nodes);
    }

    public View() {
        super();
    }

    @Override
    public void setAttribute(String key, String value, String logicalName) {

        switch (key) {

            case ID: {
                this.setId(value);
                break;
            }

            case NAME: {
                this.setName(value);
                break;
            }

            case FETCHXML: {
                this.setFetchxml(value);
                break;
            }

            case RETURNEDTYPECODE: {
                this.setReturnedtypecode(value);
                break;
            }

        }
    }

    @Override
    public String[] getDefaultColumns() {
        return Columns;
    }

    @Override
    protected ArrayList<XmlSerializable> getAttributeCollection() {

        ArrayList<XmlSerializable> parameters = new ArrayList<>();

        if (getName() != null) {
            parameters.add(getName());
        }

        if (getFetchxml() != null) {
            parameters.add(getFetchxml());
        }

        if (getReturnedtypecode() != null) {
            parameters.add(getReturnedtypecode());
        }

        return parameters;
    }

    @Override
    protected Entity getEntity() {
        if (entity == null) {
            entity = new Entity(Enums.EntityName.View);
        }

        return entity;
    }

    @Override
    public String getLogicalName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the fetchxml
     */
    public CrmString getFetchxml() {
        return fetchxml;
    }

    /**
     * @param fetchxml the fetchxml to set
     */
    public void setFetchxml(String fetchxml) {
        this.fetchxml = new CrmString(FETCHXML, fetchxml);
    }

    /**
     * @return the returnedtypecode
     */
    public CrmString getReturnedtypecode() {
        return returnedtypecode;
    }

    /**
     * @param returnedtypecode the returnedtypecode to set
     */
    public void setReturnedtypecode(String returnedtypecode) {
        this.returnedtypecode = new CrmString(RETURNEDTYPECODE, returnedtypecode);
    }

    /**
     * @return the name
     */
    public CrmString getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = new CrmString(NAME, name);
    }

}

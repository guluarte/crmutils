/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.entities;

import com.xrm.msdynamics.Enums.EntityName;
import com.xrm.msdynamics.crmtypes.Entity;
import com.xrm.msdynamics.crmtypes.XmlSerializable;
import java.util.ArrayList;
import org.w3c.dom.NodeList;

/**
 *
 * @author rguluarte
 */
public class CustomerAddress extends BaseEntity {

    private Entity entity = new Entity(EntityName.CustomerAddress);

    public CustomerAddress() {

    }

    public CustomerAddress(NodeList documentAttributes) {
        super(documentAttributes);
    }
    
    public static String[] CustomerAddressColumns = new String[]{
        "line1",
        "line2",
        "city",
        "country",
        "latitude",
        "longitude",
        "primarycontactname",
        "telephone1",
        "stateorprovince",
        "postalcode"
    };

    @Override
    public void setAttribute(String key, String value, String logicalName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getDefaultColumns() {
        return CustomerAddressColumns;
    }

    @Override
    protected ArrayList<XmlSerializable> getAttributeCollection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Entity getEntity() {
        return entity;
    }

    @Override
    public String getLogicalName() {
        return EntityName.CustomerAddress;
    }

}

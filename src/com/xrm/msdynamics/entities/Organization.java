/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.entities;

import com.xrm.msdynamics.Enums.EntityName;
import com.xrm.msdynamics.crmtypes.Entity;
import com.xrm.msdynamics.crmtypes.XmlSerializable;
import java.text.ParseException;
import java.util.ArrayList;
import org.w3c.dom.NodeList;

/**
 *
 * @author rguluarte
 */
public class Organization extends BaseEntity {

    private Entity entity;

    public static final String[] OrganizationColumns = new String[]{};

    public Organization() {

    }

    public Organization(NodeList documentAttributes) {
        super(documentAttributes);
    }

    @Override
    public void setAttribute(String key, String value, String logicalName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getDefaultColumns() {
        return OrganizationColumns;
    }

    @Override
    protected ArrayList<XmlSerializable> getAttributeCollection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Entity getEntity() {

        if (entity == null) {
            entity = new Entity(EntityName.Organization);
        }
        return entity;
    }

    @Override
    public String getLogicalName() {
        return EntityName.Organization;
    }

}

/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.entities;

import com.xrm.msdynamics.Enums.EntityName;
import com.xrm.msdynamics.crmtypes.Entity;
import com.xrm.msdynamics.crmtypes.XmlSerializable;
import java.util.ArrayList;

/**
 *
 * @author rguluarte
 */
public class Email extends BaseEntity {

    private Entity entity;

    @Override
    public void setAttribute(String key, String value, String logicalName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getDefaultColumns() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ArrayList<XmlSerializable> getAttributeCollection() {
        ArrayList<XmlSerializable> ops = new ArrayList<>();

        return ops;
    }

    @Override
    protected Entity getEntity() {

        if (entity == null) {
            entity = new Entity(EntityName.Email);
        }
        
        return entity;
    }

    @Override
    public String getLogicalName() {
        return EntityName.Email;
    }

}

/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.entities;

import com.xrm.msdynamics.Enums.EntityName;
import com.xrm.msdynamics.crmtypes.CrmString;
import com.xrm.msdynamics.crmtypes.Entity;
import com.xrm.msdynamics.crmtypes.EntityReference;
import com.xrm.msdynamics.crmtypes.XmlSerializable;

import java.util.ArrayList;
import org.w3c.dom.NodeList;

/**
 *
 * @author rguluarte
 */
public class Account extends BaseEntity {

    private final Entity entity = new Entity(EntityName.Account);

    private CrmString name;

    private EntityReference primarycontactid;

    public static final String ID_COLUMN = "accountid";
    public static final String NAME_COLUMN = "name";
    public static final String PRIMARYCONTACT_COLUMN = "primarycontactid";

    public static String[] AccountColumns = new String[]{
        ID_COLUMN,
        NAME_COLUMN,
        PRIMARYCONTACT_COLUMN,};

    public Account() {

    }

    public Account(NodeList documentAttributes) {
        super(documentAttributes);
    }

    @Override
    public void setAttribute(String key, String value, String logicalName) {
        switch (key) {

            case ID_COLUMN: {
                this.setId(value);
                break;
            }

            case NAME_COLUMN: {
                this.setName(value);
                break;
            }

            case PRIMARYCONTACT_COLUMN: {
                this.setPrimarycontactid(value);
                break;
            }
        }
    }

    @Override
    public String[] getDefaultColumns() {
        return AccountColumns;
    }

    @Override
    protected ArrayList<XmlSerializable> getAttributeCollection() {
        ArrayList<XmlSerializable> options = new ArrayList<>();

        if (getPrimarycontactid() != null) {
            options.add(getPrimarycontactid());
        }

        if (getName() != null) {
            options.add(getName());
        }

        return options;
    }

    @Override
    protected Entity getEntity() {
        return entity;
    }

    @Override
    public String getLogicalName() {
        return EntityName.Account;
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
        this.name = new CrmString(NAME_COLUMN, name);
    }

    /**
     * @return the primarycontactid
     */
    public EntityReference getPrimarycontactid() {
        return primarycontactid;
    }

    /**
     * @param primarycontactid the primarycontactid to set
     */
    public void setPrimarycontactid(String primarycontactid) {
        this.primarycontactid = new EntityReference(EntityName.Contact, PRIMARYCONTACT_COLUMN, primarycontactid);
    }

}

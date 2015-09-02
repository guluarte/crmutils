/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.entities;

import com.xrm.msdynamics.Enums.EntityName;
import com.xrm.msdynamics.crmtypes.CrmString;
import com.xrm.msdynamics.crmtypes.DateTime;
import com.xrm.msdynamics.crmtypes.Entity;
import com.xrm.msdynamics.crmtypes.XmlSerializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.NodeList;

/**
 *
 * @author rguluarte
 */
public class User extends BaseEntity {

    private final Entity entity = new Entity(EntityName.SystemUser);

    private DateTime createdOn;
    private CrmString firstName;
    private CrmString lastName;
    private CrmString organizationId;

    private static final String ID = "systemuserid";
    private static final String CREATEDON = "createdon";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String ORGANIZATIONID = "organizationid";

    public static final String[] UserColumns = new String[]{
        ID,
        FIRSTNAME,
        LASTNAME,
        ORGANIZATIONID
    };

    public User(NodeList userNodes) {
        super(userNodes);
    }

    public User() {

    }

    @Override
    public void setAttribute(String key, String value, String logicalName) {

        switch (key) {
            case ID: {
                setId(value);
                break;
            }
            case CREATEDON: {
                try {
                    setCreatedOn(DATE_FORMAT.parse(value));
                    break;
                } catch (ParseException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case FIRSTNAME: {
                setFirstName(value);
                break;
            }
            case LASTNAME: {
                setLastName(value);
                break;
            }
            case ORGANIZATIONID: {
                setOrganizationId(value);
                break;
            }
        }

    }

    @Override
    public String[] getDefaultColumns() {
        return UserColumns;
    }

    @Override
    protected ArrayList<XmlSerializable> getAttributeCollection() {
        ArrayList<XmlSerializable> options = new ArrayList<>();

        if (getFirstName() != null) {
            options.add(getFirstName());
        }
        if (getLastName() != null) {
            options.add(getLastName());
        }

        return options;
    }

    @Override
    protected Entity getEntity() {
        return entity;
    }

    @Override
    public String getLogicalName() {
        return EntityName.SystemUser;
    }

    /**
     * @return the createdOn
     */
    public DateTime getCreatedOn() {
        return createdOn;
    }

    /**
     * @param createdOn the createdOn to set
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = new DateTime(CREATEDON, createdOn);
    }

    /**
     * @return the firstName
     */
    public CrmString getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = new CrmString(FIRSTNAME, firstName);
    }

    /**
     * @return the lastName
     */
    public CrmString getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = new CrmString(LASTNAME, lastName);
    }

    /**
     * @return the organizationId
     */
    public CrmString getOrganizationId() {
        return organizationId;
    }

    /**
     * @param organizationId the organizationId to set
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = new CrmString(ORGANIZATIONID, organizationId);
    }

}

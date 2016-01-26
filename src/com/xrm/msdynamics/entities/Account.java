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

    private Entity entity;

    private CrmString name;
    private CrmString telephone;
    private CrmString fax;
    private CrmString websiteUrl;
    private CrmString tickerSymbol;
    private CrmString description;

    private EntityReference primarycontactid;
    private EntityReference parentAccountId;

    public static final String ID_COLUMN = "accountid";
    public static final String NAME_COLUMN = "name";
    public static final String PRIMARYCONTACT_COLUMN = "primarycontactid";
    public static final String TELEPHONE = "telephone1";
    public static final String FAX = "fax";
    public static final String WEBSITEURL = "websiteurl";
    public static final String PARENTACCOUNTID = "parentaccountid";
    public static final String TICKERSYMBOL = "tickersymbol";
    public static final String DESCRIPTION = "description";

    public static String[] AccountColumns = new String[]{
        ID_COLUMN,
        NAME_COLUMN,
        PRIMARYCONTACT_COLUMN,
        TELEPHONE,
        FAX,
        WEBSITEURL,
        PARENTACCOUNTID,
        TICKERSYMBOL,
        DESCRIPTION
    };

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
            case TELEPHONE: {
                this.setTelephone(value);
                break;
            }
            case FAX: {
                this.setFax(value);
                break;
            }
            case WEBSITEURL: {
                this.setWebsiteUrl(value);
                break;
            }
            case PARENTACCOUNTID: {
                this.setParentAccountId(value);
                break;
            }
            case TICKERSYMBOL: {
                this.setTickerSymbol(value);
                break;
            }
            case DESCRIPTION: {
                this.setDescription(value);
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
        
        if (getTelephone()!= null) {
            options.add(getTelephone());
        }
        
        if (getFax()!= null) {
            options.add(getFax());
        }
        
        if (getWebsiteUrl()!= null) {
            options.add(getWebsiteUrl());
        }
        
        if (getParentAccountId()!= null) {
            options.add(getParentAccountId());
        }
        
        if (getTickerSymbol()!= null) {
            options.add(getTickerSymbol());
        }
        
        if (getDescription()!= null) {
            options.add(getDescription());
        }

        return options;
    }

    @Override
    protected Entity getEntity() {

        if (entity == null) {
            entity = new Entity(EntityName.Account);
        }

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

    /**
     * @return the telephone
     */
    public CrmString getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = new CrmString(TELEPHONE, telephone);
    }

    /**
     * @return the fax
     */
    public CrmString getFax() {
        return fax;
    }

    /**
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        this.fax = new CrmString(FAX, fax);
    }

    /**
     * @return the websiteUrl
     */
    public CrmString getWebsiteUrl() {
        return websiteUrl;
    }

    /**
     * @param websiteUrl the websiteUrl to set
     */
    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = new CrmString(WEBSITEURL, websiteUrl);
    }

    /**
     * @return the tickerSymbol
     */
    public CrmString getTickerSymbol() {
        return tickerSymbol;
    }

    /**
     * @param tickerSymbol the tickerSymbol to set
     */
    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = new CrmString(TICKERSYMBOL, tickerSymbol);
    }

    /**
     * @return the description
     */
    public CrmString getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = new CrmString(DESCRIPTION, description);
    }

    /**
     * @return the parentAccountId
     */
    public EntityReference getParentAccountId() {
        return parentAccountId;
    }

    /**
     * @param parentAccountId the parentAccountId to set
     */
    public void setParentAccountId(String parentAccountId) {
        this.parentAccountId = new EntityReference(EntityName.Account, PARENTACCOUNTID, parentAccountId);
    }

}

/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.entities;

import com.xrm.msdynamics.Enums.EntityName;
import com.xrm.msdynamics.crmtypes.CrmString;
import com.xrm.msdynamics.crmtypes.DateTime;
import com.xrm.msdynamics.crmtypes.Entity;
import com.xrm.msdynamics.crmtypes.EntityReference;
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
public class PriceList extends BaseEntity {

    private Entity entity = new Entity(EntityName.PriceList);

    private CrmString name;
    private CrmString description;

    private EntityReference transactionCurrencyId;
    private DateTime endDate;

    private static final String ID = "pricelevelid";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String TRANSACTIONCURRENCYID = "transactioncurrencyid";
    private static final String ENDDATE = "enddate";

    public String[] PriceListColumns = new String[]{
        ID,
        NAME,
        DESCRIPTION,
        TRANSACTIONCURRENCYID,
        ENDDATE
    };

    public PriceList() {

    }

    public PriceList(NodeList documentAttributes) {
        super(documentAttributes);
    }

    @Override
    public void setAttribute(String key, String value, String logicalName) {
        switch (key) {
            case ID: {
                setId(value);
                break;
            }
            case NAME: {
                setName(value);
                break;
            }
            case DESCRIPTION: {
                setDescription(value);
                break;
            }
            case TRANSACTIONCURRENCYID: {
                setTransactionCurrencyId(value);
                break;
            }
            case ENDDATE: {
                setEndDate(value);
                break;
            }
        }
    }

    @Override
    public String[] getDefaultColumns() {
        return PriceListColumns;
    }

    @Override
    protected ArrayList<XmlSerializable> getAttributeCollection() {
        ArrayList<XmlSerializable> options = new ArrayList<>();

        if (getName() != null) {
            options.add(getName());
        }
        if (getDescription() != null) {
            options.add(getDescription());
        }
        if (getTransactionCurrencyId() != null) {
            options.add(getTransactionCurrencyId());
        }
        if (getEndDate() != null) {
            options.add(getEndDate());
        }

        return options;
    }

    @Override
    protected Entity getEntity() {
        return entity;
    }

    @Override
    public String getLogicalName() {
        return EntityName.PriceList;
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
     * @return the transactionCurrencyId
     */
    public EntityReference getTransactionCurrencyId() {
        return transactionCurrencyId;
    }

    /**
     * @param transactionCurrencyId the transactionCurrencyId to set
     */
    public void setTransactionCurrencyId(String transactionCurrencyId) {
        this.transactionCurrencyId = new EntityReference(EntityName.Currency, TRANSACTIONCURRENCYID, transactionCurrencyId);
    }

    /**
     * @return the endDate
     */
    public DateTime getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = new DateTime(ENDDATE, endDate);
    }

    /**
     * @param endDate the endDate to set
     */
    private void setEndDate(String endDate) {
        try {
            setEndDate(DATE_FORMAT.parse(endDate));
        } catch (ParseException ex) {
            Logger.getLogger(PriceList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

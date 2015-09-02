/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.entities;

import com.xrm.msdynamics.Enums.EntityName;
import com.xrm.msdynamics.crmtypes.CrmString;
import com.xrm.msdynamics.crmtypes.DateTime;
import com.xrm.msdynamics.crmtypes.Entity;
import com.xrm.msdynamics.crmtypes.OptionSetValue;
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
public class Product extends BaseEntity {

    private final Entity entity = new Entity(EntityName.Product);

    private CrmString description;
    private CrmString name;
    private CrmString vendorName;
    private CrmString productNumber;

    private DateTime createdOn;
    private DateTime modifiedOn;
    private DateTime validFromDate;

    private OptionSetValue productTypeCode;
    private OptionSetValue stateCode;
    private OptionSetValue statusCode;

    private static final String ID = "productid";
    private static final String DESCRIPTION = "description";
    private static final String NAME = "name";
    private static final String VENDORNAME = "vendorname";
    private static final String PRODUCTNUMBER = "productnumber";
    private static final String CREATEDON = "createdon";
    private static final String MODIFIEDON = "modifiedon";
    private static final String VALIDFROMDATE = "validfromdate";
    private static final String PRODUCTTYPECODE = "producttypecode";
    private static final String STATECODE = "statecode";
    private static final String STATUSCODE = "statuscode";

    public static String[] ProductColumns = new String[]{
        ID,
        DESCRIPTION,
        NAME,
        VENDORNAME,
        PRODUCTNUMBER,
        CREATEDON,
        MODIFIEDON,
        VALIDFROMDATE,
        PRODUCTTYPECODE,
        STATECODE,
        STATUSCODE
    };

    public Product() {

    }

    public Product(NodeList documentAttributes) {
        super(documentAttributes);
    }

    @Override
    public void setAttribute(String key, String value, String logicalName) {
        switch (key) {
            case ID: {
                setId(value);
                break;
            }
            case DESCRIPTION: {
                setDescription(value);
                break;
            }
            case NAME: {
                setName(value);
                break;
            }
            case VENDORNAME: {
                setVendorName(value);
                break;
            }
            case PRODUCTNUMBER: {
                setProductNumber(value);
                break;
            }
            case CREATEDON: {
                try {
                    setCreatedOn(DATE_FORMAT.parse(value));
                    break;
                } catch (ParseException ex) {
                    Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case MODIFIEDON: {
                try {
                    setModifiedOn(DATE_FORMAT.parse(value));
                } catch (ParseException ex) {
                    Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case VALIDFROMDATE: {
                try {
                    setValidFromDate(DATE_FORMAT.parse(value));
                } catch (ParseException ex) {
                    Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case PRODUCTTYPECODE: {
                setProductTypeCode(Integer.parseInt(value));
                break;
            }
            case STATECODE: {
                setStateCode(Integer.parseInt(value));
                break;
            }
            case STATUSCODE: {
                setStatusCode(Integer.parseInt(value));
                break;
            }
        }
    }

    @Override
    public String[] getDefaultColumns() {
        return ProductColumns;
    }

    @Override
    protected ArrayList<XmlSerializable> getAttributeCollection() {
        ArrayList<XmlSerializable> options = new ArrayList<>();

        if (getDescription() != null) {
            options.add(getDescription());
        }
        if (getName() != null) {
            options.add(getName());
        }
        if (getVendorName() != null) {
            options.add(getVendorName());
        }
        if (getProductNumber() != null) {
            options.add(getProductNumber());
        }
        if (getCreatedOn() != null) {
            options.add(getCreatedOn());
        }
        if (getModifiedOn() != null) {
            options.add(getModifiedOn());
        }
        if (getValidFromDate() != null) {
            options.add(getValidFromDate());
        }
        if (getProductTypeCode() != null) {
            options.add(getProductTypeCode());
        }

        return options;
    }

    @Override
    protected Entity getEntity() {
        return entity;
    }

    @Override
    public String getLogicalName() {
        return EntityName.Product;
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
     * @return the vendorName
     */
    public CrmString getVendorName() {
        return vendorName;
    }

    /**
     * @param vendorName the vendorName to set
     */
    public void setVendorName(String vendorName) {
        this.vendorName = new CrmString(VENDORNAME, vendorName);
    }

    /**
     * @return the productNumber
     */
    public CrmString getProductNumber() {
        return productNumber;
    }

    /**
     * @param productNumber the productNumber to set
     */
    public void setProductNumber(String productNumber) {
        this.productNumber = new CrmString(PRODUCTNUMBER, productNumber);
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
     * @return the modifiedOn
     */
    public DateTime getModifiedOn() {
        return modifiedOn;
    }

    /**
     * @param modifiedOn the modifiedOn to set
     */
    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = new DateTime(MODIFIEDON, modifiedOn);
    }

    /**
     * @return the validFromDate
     */
    public DateTime getValidFromDate() {
        return validFromDate;
    }

    /**
     * @param validFromDate the validFromDate to set
     */
    public void setValidFromDate(Date validFromDate) {
        this.validFromDate = new DateTime(VALIDFROMDATE, validFromDate);
    }

    /**
     * @return the productTypeCode
     */
    public OptionSetValue getProductTypeCode() {
        return productTypeCode;
    }

    /**
     * @param productTypeCode the productTypeCode to set
     */
    public void setProductTypeCode(int productTypeCode) {
        this.productTypeCode = new OptionSetValue(PRODUCTTYPECODE, productTypeCode);
    }

    /**
     * @return the stateCode
     */
    public OptionSetValue getStateCode() {
        return stateCode;
    }

    /**
     * @param stateCode the stateCode to set
     */
    public void setStateCode(int stateCode) {
        this.stateCode = new OptionSetValue(STATECODE, stateCode);
    }

    /**
     * @return the statusCode
     */
    public OptionSetValue getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = new OptionSetValue(STATUSCODE, statusCode);
    }

}

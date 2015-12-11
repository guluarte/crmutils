/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.entities;

import com.xrm.msdynamics.Enums.EntityName;
import com.xrm.msdynamics.crmtypes.CrmBoolean;
import com.xrm.msdynamics.crmtypes.CrmInt;
import com.xrm.msdynamics.crmtypes.CrmString;
import com.xrm.msdynamics.crmtypes.Entity;
import com.xrm.msdynamics.crmtypes.EntityReference;
import com.xrm.msdynamics.crmtypes.Money;
import com.xrm.msdynamics.crmtypes.XmlSerializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import org.w3c.dom.NodeList;

/**
 *
 * @author rguluarte
 */
public class OpportunityProduct extends BaseProduct {

    private CrmString productDescription;

    private Money pricePerUnit;
    private Money tax;
    private Money manualDiscountAmount;

    private EntityReference productId;
    private EntityReference opportunityId;
    private EntityReference uomId;
    private EntityReference transactionCurrencyId;
    private EntityReference ownerId;

    private CrmInt quantity;
    private CrmInt lineitemNumber;

    private CrmBoolean isProductOverridden;
    private CrmBoolean isPriceOverridden;

    private Entity entity;

    public static final String ID_COLUMN = "opportunityproductid";
    public static final String LINEITEMNUMBER_COLUMN = "lineitemnumber";
    public static final String PRICEPERNUNIT_COLUMN = "priceperunit";
    public static final String PRODUCTID_COLUMN = "productid";
    public static final String QUANTITY_COLUMN = "quantity";
    public static final String OPPORTUNITYID_COLUMN = "opportunityid";
    public static final String UOMID_COLUMN = "uomid";
    public static final String TRANSACTIONCURRENCYID_COLUMN = "transactioncurrencyid";
    public static final String TAX_COLUMN = "tax";
    public static final String ISPRODUCTOVERRRIDEN_COLUMN = "isproductoverridden";
    public static final String MANUALDISCOUNT_COLUMN = "manualdiscountamount";

    public static final String PRODUCTDESCRIPTION_COLUMN = "productdescription";
    public static final String ISPRICEOVERRIDEN_COLUMN = "ispriceoverridden";
    public static final String OWNERID_COLUMN = "ownerid";

    private static final String[] OpportunityProductColumns = new String[]{
        ID_COLUMN,
        LINEITEMNUMBER_COLUMN,
        PRICEPERNUNIT_COLUMN,
        PRODUCTID_COLUMN,
        QUANTITY_COLUMN,
        TAX_COLUMN,
        ISPRODUCTOVERRRIDEN_COLUMN,
        MANUALDISCOUNT_COLUMN,
        OPPORTUNITYID_COLUMN,
        UOMID_COLUMN,
        TRANSACTIONCURRENCYID_COLUMN,
        PRODUCTDESCRIPTION_COLUMN,
        ISPRICEOVERRIDEN_COLUMN,
        OWNERID_COLUMN
    };

    @Override
    public void setAttribute(String key, String value, String logicalName) {
        switch (key) {
            case ID_COLUMN: {
                setId(value);
                break;
            }
            case LINEITEMNUMBER_COLUMN: {
                setLineitemNumber(value);
                break;
            }
            case PRICEPERNUNIT_COLUMN: {
                setPricePerUnit(value);
                break;
            }
            case PRODUCTID_COLUMN: {
                setProductId(value);
                break;
            }
            case QUANTITY_COLUMN: {
                setQuantity(value);
                break;
            }
            case TAX_COLUMN: {
                setTax(value);
                break;
            }
            case ISPRODUCTOVERRRIDEN_COLUMN: {
                setIsProductOverridden(value);
                break;
            }
            case MANUALDISCOUNT_COLUMN: {
                setManualDiscountAmount(value);
                break;
            }
            case OPPORTUNITYID_COLUMN: {
                setOpportunityId(value);
                break;
            }
            case UOMID_COLUMN: {
                setUomId(value);
                break;
            }
            case TRANSACTIONCURRENCYID_COLUMN: {
                setTransactionCurrencyId(value);
                break;
            }

            case ISPRICEOVERRIDEN_COLUMN: {
                setIsPriceOverridden(value);
                break;
            }
            case OWNERID_COLUMN: {
                setOwnerId(value);
                break;
            }
            case PRODUCTDESCRIPTION_COLUMN: {
                setProductDescription(value);
                break;
            }

        }
    }

    public OpportunityProduct() {

    }

    public OpportunityProduct(NodeList documentAttributes) {
        Load(documentAttributes);
    }

    @Override
    public String[] getDefaultColumns() {
        return OpportunityProductColumns;
    }

    @Override
    protected ArrayList<XmlSerializable> getAttributeCollection() {
        ArrayList<XmlSerializable> options = new ArrayList<>();

        if (getLineitemNumber() != null) {
            options.add(getLineitemNumber());
        }
        if (getPricePerUnit() != null) {
            options.add(getPricePerUnit());
        }
        if (getProductId() != null) {
            options.add(getProductId());
        }
        if (getQuantity() != null) {
            options.add(getQuantity());
        }
        if (getTax() != null) {
            options.add(getTax());
        }
        if (getIsProductOverridden() != null) {
            options.add(getIsProductOverridden());
        }
        if (getManualDiscountAmount() != null) {
            options.add(getManualDiscountAmount());
        }
        if (getOpportunityId() != null) {
            options.add(getOpportunityId());
        }
        if (getUomId() != null) {
            options.add(getUomId());
        }
        if (getTransactionCurrencyId() != null) {
            options.add(getTransactionCurrencyId());
        }
        if (getIsPriceOverridden() != null) {
            options.add(getIsPriceOverridden());
        }
        if (getOwnerId() != null) {
            options.add(getOwnerId());
        }

        if (getProductDescription() != null) {
            options.add(getProductDescription());
        }

        return options;
    }

    @Override
    protected Entity getEntity() {
        
        if (entity == null) {
            entity = new Entity(EntityName.OpportunityProduct);
        }
        return entity;
    }

    @Override
    public String getLogicalName() {
        return EntityName.OpportunityProduct;
    }

    /**
     * @return the pricePerUnit
     */
    public Money getPricePerUnit() {
        return pricePerUnit;
    }

    /**
     * @param pricePerUnit the pricePerUnit to set
     */
    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = new Money(PRICEPERNUNIT_COLUMN, pricePerUnit);
    }

    /**
     * @param pricePerUnit the pricePerUnit to set
     */
    private void setPricePerUnit(String pricePerUnit) {
        setPricePerUnit(new BigDecimal(pricePerUnit));
    }

    /**
     * @return the tax
     */
    public Money getTax() {
        return tax;
    }

    /**
     * @param tax the tax to set
     */
    public void setTax(BigDecimal tax) {
        this.tax = new Money(TAX_COLUMN, tax);
    }

    /**
     * @param tax the tax to set
     */
    private void setTax(String tax) {
        setTax(new BigDecimal(tax));
    }

    /**
     * @return the manualDiscountAmount
     */
    public Money getManualDiscountAmount() {
        return manualDiscountAmount;
    }

    /**
     * @param manualDiscountAmount the manualDiscountAmount to set
     */
    public void setManualDiscountAmount(BigDecimal manualDiscountAmount) {
        this.manualDiscountAmount = new Money(MANUALDISCOUNT_COLUMN, manualDiscountAmount);
    }

    /**
     * @param manualDiscountAmount the manualDiscountAmount to set
     */
    private void setManualDiscountAmount(String manualDiscountAmount) {
        setManualDiscountAmount(new BigDecimal(manualDiscountAmount));
    }

    /**
     * @return the productId
     */
    public EntityReference getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(String productId) {
        this.productId = new EntityReference(EntityName.Product, PRODUCTID_COLUMN, productId);
    }

    /**
     * @return the opportunityId
     */
    public EntityReference getOpportunityId() {
        return opportunityId;
    }

    /**
     * @param opportunityId the opportunityId to set
     */
    public void setOpportunityId(String opportunityId) {
        this.opportunityId = new EntityReference(EntityName.Opportunity, OPPORTUNITYID_COLUMN, opportunityId);
    }

    /**
     * @return the uomId
     */
    public EntityReference getUomId() {
        return uomId;
    }

    /**
     * @param uomId the uomId to set
     */
    public void setUomId(String uomId) {
        this.uomId = new EntityReference(EntityName.Unit, UOMID_COLUMN, uomId);
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
        this.transactionCurrencyId = new EntityReference(EntityName.Currency, TRANSACTIONCURRENCYID_COLUMN, transactionCurrencyId);
    }

    /**
     * @return the ownerId
     */
    public EntityReference getOwnerId() {
        return ownerId;
    }

    /**
     * @param ownerId the ownerId to set
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = new EntityReference(EntityName.SystemUser, OWNERID_COLUMN, ownerId);
    }

    /**
     * @return the quantity
     */
    public CrmInt getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = new CrmInt(QUANTITY_COLUMN, quantity);
    }

    /**
     * @param quantity the quantity to set
     */
    private void setQuantity(String quantity) {
        setQuantity(Integer.parseInt(quantity));
    }

    /**
     * @return the lineitemNumber
     */
    public CrmInt getLineitemNumber() {
        return lineitemNumber;
    }

    /**
     * @param lineitemNumber the lineitemNumber to set
     */
    public void setLineitemNumber(int lineitemNumber) {
        this.lineitemNumber = new CrmInt(LINEITEMNUMBER_COLUMN, lineitemNumber);
    }

    /**
     * @param lineitemNumber the lineitemNumber to set
     */
    private void setLineitemNumber(String lineitemNumber) {
        setLineitemNumber(Integer.parseInt(lineitemNumber));
    }

    /**
     * @return the isProductOverridden
     */
    public CrmBoolean getIsProductOverridden() {
        return isProductOverridden;
    }

    /**
     * @param isProductOverridden the isProductOverridden to set
     */
    public void setIsProductOverridden(boolean isProductOverridden) {
        this.isProductOverridden = new CrmBoolean(ISPRODUCTOVERRRIDEN_COLUMN, isProductOverridden);
    }

    /**
     * @param isProductOverridden the isProductOverridden to set
     */
    private void setIsProductOverridden(String isProductOverridden) {
        setIsProductOverridden(Boolean.parseBoolean(isProductOverridden));
    }

    /**
     * @return the isPriceOverridden
     */
    public CrmBoolean getIsPriceOverridden() {
        return isPriceOverridden;
    }

    /**
     * @param isPriceOverridden the isPriceOverridden to set
     */
    public void setIsPriceOverridden(boolean isPriceOverridden) {
        this.isPriceOverridden = new CrmBoolean(ISPRICEOVERRIDEN_COLUMN, isPriceOverridden);
    }

    /**
     * @param isPriceOverridden the isPriceOverridden to set
     */
    private void setIsPriceOverridden(String isPriceOverridden) {
        setIsPriceOverridden(Boolean.parseBoolean(isPriceOverridden));
    }

    /**
     * @return the productDescription
     */
    public CrmString getProductDescription() {
        return productDescription;
    }

    /**
     * @param productDescription the productDescription to set
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = new CrmString(PRODUCTDESCRIPTION_COLUMN, productDescription);
    }

}

/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.entities;

import com.xrm.msdynamics.Enums.EntityName;
import com.xrm.msdynamics.crmtypes.CrmBoolean;
import com.xrm.msdynamics.crmtypes.CrmInt;
import com.xrm.msdynamics.crmtypes.CrmString;
import com.xrm.msdynamics.crmtypes.DateTime;
import com.xrm.msdynamics.crmtypes.Entity;
import com.xrm.msdynamics.crmtypes.EntityReference;
import com.xrm.msdynamics.crmtypes.Money;
import com.xrm.msdynamics.crmtypes.OptionSetValue;
import com.xrm.msdynamics.crmtypes.XmlSerializable;

import java.math.BigDecimal;
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
public class OrderProduct extends BaseProduct {

    private final Entity entity = new Entity(EntityName.OrderProduct);

    private CrmBoolean iProductOverridden;
    private CrmBoolean isPriceOverridden;
    private CrmBoolean willCall;

    private CrmString productDescription;
    private CrmString shipToName;
    private CrmString shipToLine1;
    private CrmString shipToLine2;
    private CrmString shipToLine3;
    private CrmString shipToCity;
    private CrmString shipToStateOrProvince;
    private CrmString shipToPostalCode;
    private CrmString shipToCountry;
    private CrmString shipToTelephone;
    private CrmString shipToFax;
    private CrmString shipToContactName;

    private EntityReference productId;
    private EntityReference uomId;
    private EntityReference salesRepId;
    private EntityReference salesOrderId;

    private Money pricePerUnit;
    private Money volumeDiscountAmount;
    private Money baseAmount;
    private Money manualDiscountAmount;
    private Money tax;
    private Money extendedAmount;

    private CrmInt quantity;
    private CrmInt quantityShipped;
    private CrmInt quantitybBckordered;
    private CrmInt quantityCancelled;

    private DateTime requestDeliveryBy;

    private OptionSetValue shipToFreightTermsCode;

    private static final String ID = "salesorderdetailid";
    private static final String SALESORDERID = "salesorderid";
    private static final String ISPRODUCTOVERRIDDEN = "isproductoverridden";
    private static final String PRODUCTDESCRIPTION = "productdescription";
    private static final String PRODUCTID = "productid";
    private static final String UOMID = "uomid";
    private static final String ISPRICEOVERRIDDEN = "ispriceoverridden";
    private static final String PRICEPERUNIT = "priceperunit";
    private static final String VOLUMEDISCOUNTAMOUNT = "volumediscountamount";
    private static final String QUANTITY = "quantity";
    private static final String BASEAMOUNT = "baseamount";
    private static final String MANUALDISCOUNTAMOUNT = "manualdiscountamount";
    private static final String TAX = "tax";
    private static final String EXTENDEDAMOUNT = "extendedamount";
    private static final String REQUESTDELIVERYBY = "requestdeliveryby";
    private static final String SALESREPID = "salesrepid";
    private static final String QUANTITYSHIPPED = "quantityshipped";
    private static final String QUANTITYBACKORDERED = "quantitybackordered";
    private static final String QUANTITYCANCELLED = "quantitycancelled";
    private static final String WILLCALL = "willcall";
    private static final String SHIPTONAME = "shipto_name";
    private static final String SHIPTOLINE1 = "shipto_line1";
    private static final String SHIPTOLINE2 = "shipto_line2";
    private static final String SHIPTOLINE3 = "shipto_line3";
    private static final String SHIPTOCITY = "shipto_city";
    private static final String SHIPTOSTATEORPROVINCE = "shipto_stateorprovince";
    private static final String SHIPTOPOSTALCODE = "shipto_postalcode";
    private static final String SHIPTOCOUNTRY = "shipto_country";
    private static final String SHIPTOTELEPHONE = "shipto_telephone";
    private static final String SHIPTOFAX = "shipto_fax";
    private static final String SHIPTOFREIGHTTERMSCODE = "shipto_freighttermscode";
    private static final String SHIPTOCONTACTNAME = "shipto_contactname";

    public static String[] OrderProductColumns = new String[]{
        ID,
        ISPRODUCTOVERRIDDEN,
        PRODUCTDESCRIPTION,
        PRODUCTID,
        UOMID,
        ISPRICEOVERRIDDEN,
        PRICEPERUNIT,
        VOLUMEDISCOUNTAMOUNT,
        QUANTITY,
        BASEAMOUNT,
        MANUALDISCOUNTAMOUNT,
        TAX,
        EXTENDEDAMOUNT,
        REQUESTDELIVERYBY,
        SALESREPID,
        QUANTITYSHIPPED,
        QUANTITYBACKORDERED,
        QUANTITYCANCELLED,
        WILLCALL,
        SHIPTONAME,
        SHIPTOLINE1,
        SHIPTOLINE2,
        SHIPTOLINE3,
        SHIPTOCITY,
        SHIPTOSTATEORPROVINCE,
        SHIPTOPOSTALCODE,
        SHIPTOCOUNTRY,
        SHIPTOTELEPHONE,
        SHIPTOFAX,
        SHIPTOFREIGHTTERMSCODE,
        SHIPTOCONTACTNAME
    };

    public OrderProduct() {

    }

    public OrderProduct(NodeList documentAttributes) {
        Load(documentAttributes);
    }

    @Override
    public void setAttribute(String key, String value, String logicalName) {
        switch (key) {
            case ID: {
                setId(value);
                break;
            }
            case ISPRODUCTOVERRIDDEN: {
                setiProductOverridden(value);
                break;
            }
            case PRODUCTDESCRIPTION: {
                setProductDescription(value);
                break;
            }
            case PRODUCTID: {
                setProductId(value);
                break;
            }
            case UOMID: {
                setUomId(value);
                break;
            }
            case ISPRICEOVERRIDDEN: {
                setIsPriceOverridden(value);
                break;
            }
            case PRICEPERUNIT: {
                setPricePerUnit(value);
                break;
            }
            case VOLUMEDISCOUNTAMOUNT: {
                setVolumeDiscountAmount(value);
                break;
            }
            case QUANTITY: {
                setQuantity(value);
                break;
            }
            case BASEAMOUNT: {
                setBaseAmount(value);
                break;
            }
            case MANUALDISCOUNTAMOUNT: {
                setManualDiscountAmount(value);
                break;
            }
            case TAX: {
                setTax(value);
                break;
            }
            case EXTENDEDAMOUNT: {
                setExtendedAmount(value);
                break;
            }
            case REQUESTDELIVERYBY: {
                setRequestDeliveryBy(value);
                break;
            }
            case SALESREPID: {
                setSalesRepId(value);
                break;
            }
            case QUANTITYSHIPPED: {
                setQuantityShipped(value);
                break;
            }
            case QUANTITYBACKORDERED: {
                setQuantitybBckordered(value);
                break;
            }
            case QUANTITYCANCELLED: {
                setQuantityCancelled(value);
                break;
            }
            case WILLCALL: {
                setWillCall(value);
                break;
            }
            case SHIPTONAME: {
                setShipToName(value);
                break;
            }
            case SHIPTOLINE1: {
                setShipToLine1(value);
                break;
            }
            case SHIPTOLINE2: {
                setShipToLine2(value);
                break;
            }
            case SHIPTOLINE3: {
                setShipToLine3(value);
                break;
            }
            case SHIPTOCITY: {
                setShipToCity(value);
                break;
            }
            case SHIPTOSTATEORPROVINCE: {
                setShipToStateOrProvince(value);
                break;
            }
            case SHIPTOPOSTALCODE: {
                setShipToPostalCode(value);
                break;
            }
            case SHIPTOCOUNTRY: {
                setShipToCountry(value);
                break;
            }
            case SHIPTOTELEPHONE: {
                setShipToTelephone(value);
                break;
            }
            case SHIPTOFAX: {
                setShipToFax(value);
                break;
            }
            case SHIPTOFREIGHTTERMSCODE: {
                setShipToFreightTermsCode(value);
                break;
            }
            case SHIPTOCONTACTNAME: {
                setShipToContactName(value);
                break;
            }

            case SALESORDERID: {
                setSalesOrderId(value);
                break;
            }
        }
    }

    @Override
    public String[] getDefaultColumns() {
        return OrderProductColumns;
    }

    @Override
    protected ArrayList<XmlSerializable> getAttributeCollection() {
        ArrayList<XmlSerializable> options = new ArrayList<>();

        if (getiProductOverridden() != null) {
            options.add(getiProductOverridden());
        }
        if (getProductDescription() != null) {
            options.add(getProductDescription());
        }
        if (getProductId() != null) {
            options.add(getProductId());
        }
        if (getUomId() != null) {
            options.add(getUomId());
        }
        if (getIsPriceOverridden() != null) {
            options.add(getIsPriceOverridden());
        }
        if (getPricePerUnit() != null) {
            options.add(getPricePerUnit());
        }
        if (getVolumeDiscountAmount() != null) {
            options.add(getVolumeDiscountAmount());
        }

        if (getQuantity() != null) {
            options.add(getQuantity());
        }
        if (getBaseAmount() != null) {
            options.add(getBaseAmount());
        }
        if (getManualDiscountAmount() != null) {
            options.add(getManualDiscountAmount());
        }
        if (getTax() != null) {
            options.add(getTax());
        }
        if (getExtendedAmount() != null) {
            options.add(getExtendedAmount());
        }
        if (getRequestDeliveryBy() != null) {
            options.add(getRequestDeliveryBy());
        }
        if (getSalesRepId() != null) {
            options.add(getSalesRepId());
        }

        if (getQuantityShipped() != null) {
            options.add(getQuantityShipped());
        }
        if (getQuantitybBckordered() != null) {
            options.add(getQuantitybBckordered());
        }
        if (getQuantityCancelled() != null) {
            options.add(getQuantityCancelled());
        }
        if (getWillCall() != null) {
            options.add(getWillCall());
        }
        if (getShipToName() != null) {
            options.add(getShipToName());
        }
        if (getShipToLine1() != null) {
            options.add(getShipToLine1());
        }
        if (getShipToLine2() != null) {
            options.add(getShipToLine2());
        }
        if (getShipToLine3() != null) {
            options.add(getShipToLine3());
        }

        if (getShipToCity() != null) {
            options.add(getShipToCity());
        }

        if (getShipToStateOrProvince() != null) {
            options.add(getShipToStateOrProvince());
        }

        if (getShipToPostalCode() != null) {
            options.add(getShipToPostalCode());
        }

        if (getShipToCountry() != null) {
            options.add(getShipToCountry());
        }

        if (getShipToTelephone() != null) {
            options.add(getShipToTelephone());
        }

        if (getShipToFax() != null) {
            options.add(getShipToFax());
        }

        if (getShipToFreightTermsCode() != null) {
            options.add(getShipToFreightTermsCode());
        }

        if (getShipToContactName() != null) {
            options.add(getShipToContactName());
        }

        if (getSalesOrderId() != null) {
            options.add(getSalesOrderId());
        }

        return options;
    }

    @Override
    protected Entity getEntity() {
        return entity;
    }

    @Override
    public String getLogicalName() {
        return EntityName.OrderProduct;
    }

    /**
     * @return the iProductOverridden
     */
    public CrmBoolean getiProductOverridden() {
        return iProductOverridden;
    }

    /**
     * @param iProductOverridden the iProductOverridden to set
     */
    public void setiProductOverridden(boolean iProductOverridden) {
        this.iProductOverridden = new CrmBoolean(ISPRODUCTOVERRIDDEN, iProductOverridden);
    }

    /**
     * @param iProductOverridden the iProductOverridden to set
     */
    private void setiProductOverridden(String iProductOverridden) {
        setiProductOverridden(Boolean.parseBoolean(iProductOverridden));
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
        this.isPriceOverridden = new CrmBoolean(ISPRICEOVERRIDDEN, isPriceOverridden);
    }

    /**
     * @param isPriceOverridden the isPriceOverridden to set
     */
    private void setIsPriceOverridden(String isPriceOverridden) {
        setIsPriceOverridden(Boolean.parseBoolean(isPriceOverridden));
    }

    /**
     * @return the willCall
     */
    public CrmBoolean getWillCall() {
        return willCall;
    }

    /**
     * @param willCall the willCall to set
     */
    public void setWillCall(boolean willCall) {
        this.willCall = new CrmBoolean((WILLCALL), willCall);
    }

    /**
     * @param willCall the willCall to set
     */
    private void setWillCall(String willCall) {
        setWillCall(Boolean.parseBoolean(willCall));
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
        this.productDescription = new CrmString(PRODUCTDESCRIPTION, productDescription);
    }

    /**
     * @return the shipToName
     */
    public CrmString getShipToName() {
        return shipToName;
    }

    /**
     * @param shipToName the shipToName to set
     */
    public void setShipToName(String shipToName) {
        this.shipToName = new CrmString(SHIPTONAME, shipToName);
    }

    /**
     * @return the shipToLine1
     */
    public CrmString getShipToLine1() {
        return shipToLine1;
    }

    /**
     * @param shipToLine1 the shipToLine1 to set
     */
    public void setShipToLine1(String shipToLine1) {
        this.shipToLine1 = new CrmString(SHIPTOLINE1, shipToLine1);
    }

    /**
     * @return the shipToLine2
     */
    public CrmString getShipToLine2() {
        return shipToLine2;
    }

    /**
     * @param shipToLine2 the shipToLine2 to set
     */
    public void setShipToLine2(String shipToLine2) {
        this.shipToLine2 = new CrmString(SHIPTOLINE2, shipToLine2);
    }

    /**
     * @return the shipToLine3
     */
    public CrmString getShipToLine3() {
        return shipToLine3;
    }

    /**
     * @param shipToLine3 the shipToLine3 to set
     */
    public void setShipToLine3(String shipToLine3) {
        this.shipToLine3 = new CrmString(SHIPTOLINE3, shipToLine3);
    }

    /**
     * @return the shipToCity
     */
    public CrmString getShipToCity() {
        return shipToCity;
    }

    /**
     * @param shipToCity the shipToCity to set
     */
    public void setShipToCity(String shipToCity) {
        this.shipToCity = new CrmString(SHIPTOCITY, shipToCity);
    }

    /**
     * @return the shipToStateOrProvince
     */
    public CrmString getShipToStateOrProvince() {
        return shipToStateOrProvince;
    }

    /**
     * @param shipToStateOrProvince the shipToStateOrProvince to set
     */
    public void setShipToStateOrProvince(String shipToStateOrProvince) {
        this.shipToStateOrProvince = new CrmString(SHIPTOSTATEORPROVINCE, shipToStateOrProvince);
    }

    /**
     * @return the shipToPostalCode
     */
    public CrmString getShipToPostalCode() {
        return shipToPostalCode;
    }

    /**
     * @param shipToPostalCode the shipToPostalCode to set
     */
    public void setShipToPostalCode(String shipToPostalCode) {
        this.shipToPostalCode = new CrmString(SHIPTOPOSTALCODE, shipToPostalCode);
    }

    /**
     * @return the shipToCountry
     */
    public CrmString getShipToCountry() {
        return shipToCountry;
    }

    /**
     * @param shipToCountry the shipToCountry to set
     */
    public void setShipToCountry(String shipToCountry) {
        this.shipToCountry = new CrmString(SHIPTOCOUNTRY, shipToCountry);
    }

    /**
     * @return the shipToTelephone
     */
    public CrmString getShipToTelephone() {
        return shipToTelephone;
    }

    /**
     * @param shipToTelephone the shipToTelephone to set
     */
    public void setShipToTelephone(String shipToTelephone) {
        this.shipToTelephone = new CrmString(SHIPTOTELEPHONE, shipToTelephone);
    }

    /**
     * @return the shipToFax
     */
    public CrmString getShipToFax() {
        return shipToFax;
    }

    /**
     * @param shipToFax the shipToFax to set
     */
    public void setShipToFax(String shipToFax) {
        this.shipToFax = new CrmString(SHIPTOFAX, shipToFax);
    }

    /**
     * @return the shipToContactName
     */
    public CrmString getShipToContactName() {
        return shipToContactName;
    }

    /**
     * @param shipToContactName the shipToContactName to set
     */
    public void setShipToContactName(String shipToContactName) {
        this.shipToContactName = new CrmString(SHIPTOCONTACTNAME, shipToContactName);
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
        this.productId = new EntityReference(EntityName.Product, PRODUCTID, productId);
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
        this.uomId = new EntityReference(EntityName.Unit, UOMID, uomId);
    }

    /**
     * @return the salesRepId
     */
    public EntityReference getSalesRepId() {
        return salesRepId;
    }

    /**
     * @param salesRepId the salesRepId to set
     */
    public void setSalesRepId(String salesRepId) {
        this.salesRepId = new EntityReference(EntityName.SystemUser, SALESREPID, salesRepId);
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
        this.pricePerUnit = new Money(PRICEPERUNIT, pricePerUnit);
    }

    /**
     * @param pricePerUnit the pricePerUnit to set
     */
    private void setPricePerUnit(String pricePerUnit) {
        setPricePerUnit(new BigDecimal(pricePerUnit));
    }

    /**
     * @return the volumeDiscountAmount
     */
    public Money getVolumeDiscountAmount() {
        return volumeDiscountAmount;
    }

    /**
     * @param volumeDiscountAmount the volumeDiscountAmount to set
     */
    public void setVolumeDiscountAmount(BigDecimal volumeDiscountAmount) {
        this.volumeDiscountAmount = new Money(VOLUMEDISCOUNTAMOUNT, volumeDiscountAmount);
    }

    /**
     * @param volumeDiscountAmount the volumeDiscountAmount to set
     */
    private void setVolumeDiscountAmount(String volumeDiscountAmount) {
        setVolumeDiscountAmount(new BigDecimal(volumeDiscountAmount));
    }

    /**
     * @return the baseAmount
     */
    public Money getBaseAmount() {
        return baseAmount;
    }

    /**
     * @param baseAmount the baseAmount to set
     */
    public void setBaseAmount(BigDecimal baseAmount) {
        this.baseAmount = new Money(BASEAMOUNT, baseAmount);
    }

    /**
     * @param baseAmount the baseAmount to set
     */
    private void setBaseAmount(String baseAmount) {
        setBaseAmount(new BigDecimal(baseAmount));
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
        this.manualDiscountAmount = new Money(MANUALDISCOUNTAMOUNT, manualDiscountAmount);
    }

    /**
     * @param manualDiscountAmount the manualDiscountAmount to set
     */
    private void setManualDiscountAmount(String manualDiscountAmount) {
        setManualDiscountAmount(new BigDecimal(manualDiscountAmount));
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
        this.tax = new Money(TAX, tax);
    }

    /**
     * @param tax the tax to set
     */
    private void setTax(String tax) {
        setTax(new BigDecimal(tax));
    }

    /**
     * @return the extendedAmount
     */
    public Money getExtendedAmount() {
        return extendedAmount;
    }

    /**
     * @param extendedAmount the extendedAmount to set
     */
    public void setExtendedAmount(BigDecimal extendedAmount) {
        this.extendedAmount = new Money(EXTENDEDAMOUNT, extendedAmount);
    }

    /**
     * @param extendedAmount the extendedAmount to set
     */
    private void setExtendedAmount(String extendedAmount) {
        setExtendedAmount(new BigDecimal(extendedAmount));
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
        this.quantity = new CrmInt(QUANTITY, quantity);
    }

    /**
     * @param quantity the quantity to set
     */
    private void setQuantity(String quantity) {
        setQuantity(Integer.parseInt(quantity));
    }

    /**
     * @return the quantityShipped
     */
    public CrmInt getQuantityShipped() {
        return quantityShipped;
    }

    /**
     * @param quantityShipped the quantityShipped to set
     */
    public void setQuantityShipped(int quantityShipped) {
        this.quantityShipped = new CrmInt(QUANTITYSHIPPED, quantityShipped);
    }

    /**
     * @param quantityShipped the quantityShipped to set
     */
    private void setQuantityShipped(String quantityShipped) {
        setQuantityShipped(Integer.parseInt(quantityShipped));
    }

    /**
     * @return the quantitybBckordered
     */
    public CrmInt getQuantitybBckordered() {
        return quantitybBckordered;
    }

    /**
     * @param quantitybBckordered the quantitybBckordered to set
     */
    public void setQuantitybBckordered(int quantitybBckordered) {
        this.quantitybBckordered = new CrmInt(QUANTITYBACKORDERED, quantitybBckordered);
    }

    /**
     * @param quantitybBckordered the quantitybBckordered to set
     */
    private void setQuantitybBckordered(String quantitybBckordered) {
        setQuantitybBckordered(Integer.parseInt(quantitybBckordered));
    }

    /**
     * @return the quantityCancelled
     */
    public CrmInt getQuantityCancelled() {
        return quantityCancelled;
    }

    /**
     * @param quantityCancelled the quantityCancelled to set
     */
    public void setQuantityCancelled(int quantityCancelled) {
        this.quantityCancelled = new CrmInt(QUANTITYCANCELLED, quantityCancelled);
    }

    /**
     * @param quantityCancelled the quantityCancelled to set
     */
    private void setQuantityCancelled(String quantityCancelled) {
        setQuantityCancelled(Integer.parseInt(quantityCancelled));
    }

    /**
     * @return the requestDeliveryBy
     */
    public DateTime getRequestDeliveryBy() {
        return requestDeliveryBy;
    }

    /**
     * @param requestDeliveryBy the requestDeliveryBy to set
     */
    public void setRequestDeliveryBy(Date requestDeliveryBy) {
        this.requestDeliveryBy = new DateTime(REQUESTDELIVERYBY, requestDeliveryBy);
    }

    /**
     * @param requestDeliveryBy the requestDeliveryBy to set
     */
    private void setRequestDeliveryBy(String requestDeliveryBy) {
        try {
            setRequestDeliveryBy(DATE_FORMAT.parse(requestDeliveryBy));
        } catch (ParseException ex) {
            Logger.getLogger(OrderProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the shipToFreightTermsCode
     */
    public OptionSetValue getShipToFreightTermsCode() {
        return shipToFreightTermsCode;
    }

    /**
     * @param shipToFreightTermsCode the shipToFreightTermsCode to set
     */
    public void setShipToFreightTermsCode(int shipToFreightTermsCode) {
        this.shipToFreightTermsCode = new OptionSetValue(SHIPTOFREIGHTTERMSCODE, shipToFreightTermsCode);
    }

    /**
     * @param shipToFreightTermsCode the shipToFreightTermsCode to set
     */
    private void setShipToFreightTermsCode(String shipToFreightTermsCode) {
        setShipToFreightTermsCode(Integer.parseInt(shipToFreightTermsCode));
    }

    /**
     * @return the salesOrderId
     */
    public EntityReference getSalesOrderId() {
        return salesOrderId;
    }

    /**
     * @param salesOrderId the salesOrderId to set
     */
    public void setSalesOrderId(String salesOrderId) {
        this.salesOrderId = new EntityReference(EntityName.Order, SALESORDERID, salesOrderId);
    }

}

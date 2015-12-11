/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.entities;

import com.xrm.msdynamics.Enums.EntityName;
import com.xrm.msdynamics.crmtypes.CrmInt;
import com.xrm.msdynamics.crmtypes.CrmString;
import com.xrm.msdynamics.crmtypes.DateTime;
import com.xrm.msdynamics.crmtypes.Entity;
import com.xrm.msdynamics.crmtypes.EntityReference;
import com.xrm.msdynamics.crmtypes.Money;
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
public class Order extends BaseEntity {

    private final ArrayList<OrderProduct> orderProducts = new ArrayList<>();

    private Entity entity;

    private DateTime createdOn;
    private DateTime submitDate;
    private DateTime modifiedOn;

    private EntityReference transactionCurrencyId;
    private EntityReference customerId;

    private CrmInt orderNumber;

    private Money totalLineItemDiscountAmount;
    private Money totalLineItemAmount;
    private Money totalAmount;
    private Money totalTax;

    private CrmString name;
    private CrmString description;
    private CrmString billtoLine1;
    private CrmString billtoLine2;
    private CrmString billtoCity;
    private CrmString billtoCountry;
    private CrmString billtoTelephone;
    private CrmString billtoStateorprovince;
    private CrmString billtoPostalcode;
    private CrmString billtoContactname;
    private CrmString shiptoLine1;
    private CrmString shiptoLine2;
    private CrmString shiptoCity;
    private CrmString shiptoCountry;
    private CrmString shiptoTelephone;
    private CrmString shiptoStateOrProvince;
    private CrmString shiptoPostalCode;
    private CrmString shiptoContactName;

    private static final String ID = "salesorderid";
    private static final String CREATEDON = "createdon";
    private static final String TRANSACTIONCURRENCYID = "transactioncurrencyid";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String ORDERNUMBER = "ordernumber";
    private static final String SUBMITDATE = "submitdate";
    private static final String TOTALLINEITEMDISCOUNTAMOUNT = "totallineitemdiscountamount";
    private static final String TOTALLINEITEMAMOUNT = "totallineitemamount";
    private static final String TOTALAMOUNT = "totalamount";
    private static final String TOTALTAX = "totaltax";
    private static final String MODIFIEDON = "modifiedon";
    private static final String CUSTOMERID = "customerid";
    private static final String BILLTOLINE1 = "billto_line1";
    private static final String BILLTOLINE2 = "billto_line2";
    private static final String BILLTOCITY = "billto_city";
    private static final String BILLTOCOUNTRY = "billto_country";
    private static final String BILLTOTELEPHONE = "billto_telephone";
    private static final String BILLTOSTATEORPROVINCE = "billto_stateorprovince";
    private static final String BILLTOPOSTALCODE = "billto_postalcode";
    private static final String BILLTOCONTACTNAME = "billto_contactname";
    private static final String SHIPTOLINE1 = "shipto_line1";
    private static final String SHIPTOLINE2 = "shipto_line2";
    private static final String SHIPTOCITY = "shipto_city";
    private static final String SHIPTOCOUNTRY = "shipto_country";
    private static final String SHIPTOTELEPHONE = "shipto_telephone";
    private static final String SHIPTOSTATEORPROVINCE = "shipto_stateorprovince";
    private static final String SHIPTOPOSTALCODE = "shipto_postalcode";
    private static final String SHIPTOCONTACTNAME = "shipto_contactname";

    public static String[] OrderColumns = new String[]{
        ID,
        CREATEDON,
        TRANSACTIONCURRENCYID,
        NAME,
        DESCRIPTION,
        ORDERNUMBER,
        SUBMITDATE,
        TOTALLINEITEMDISCOUNTAMOUNT,
        TOTALLINEITEMAMOUNT,
        TOTALAMOUNT,
        TOTALTAX,
        MODIFIEDON,
        CUSTOMERID,
        BILLTOLINE1,
        BILLTOLINE2,
        BILLTOCITY,
        BILLTOCOUNTRY,
        BILLTOTELEPHONE,
        BILLTOSTATEORPROVINCE,
        BILLTOPOSTALCODE,
        BILLTOCONTACTNAME,
        SHIPTOLINE1,
        SHIPTOLINE2,
        SHIPTOCITY,
        SHIPTOCOUNTRY,
        SHIPTOTELEPHONE,
        SHIPTOSTATEORPROVINCE,
        SHIPTOPOSTALCODE,
        SHIPTOCONTACTNAME
    };

    @Override
    public void setAttribute(String key, String value, String logicalName) {
        switch (key) {
            case ID: {
                setId(value);
                break;
            }
            case CREATEDON: {
                setCreatedOn(value);
                break;
            }
            case TRANSACTIONCURRENCYID: {
                setTransactionCurrencyId(value);
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
            case ORDERNUMBER: {
                setOrderNumber(value);
                break;
            }
            case SUBMITDATE: {
                setSubmitDate(value);
                break;
            }
            case TOTALLINEITEMDISCOUNTAMOUNT: {
                setTotalLineItemDiscountAmount(value);
                break;
            }
            case TOTALLINEITEMAMOUNT: {
                setTotalAmount(value);
                break;
            }
            case TOTALAMOUNT: {
                setTotalAmount(value);
                break;
            }
            case TOTALTAX: {
                setTotalTax(value);
                break;
            }
            case MODIFIEDON: {
                setModifiedOn(value);
                break;
            }
            case CUSTOMERID: {
                setCustomerId(value);
                break;
            }
            case BILLTOLINE1: {
                setBilltoLine1(value);
                break;
            }
            case BILLTOLINE2: {
                setBilltoLine2(value);
                break;
            }
            case BILLTOCITY: {
                setBilltoCity(value);
                break;
            }
            case BILLTOCOUNTRY: {
                setBilltoCountry(value);
                break;
            }
            case BILLTOTELEPHONE: {
                setBilltoTelephone(value);
                break;
            }
            case BILLTOSTATEORPROVINCE: {
                setBilltoStateorprovince(value);
                break;
            }
            case BILLTOPOSTALCODE: {
                setBilltoPostalcode(value);
                break;
            }
            case BILLTOCONTACTNAME: {
                setBilltoContactname(value);
                break;
            }
            case SHIPTOLINE1: {
                setShiptoLine1(value);
                break;
            }
            case SHIPTOLINE2: {
                setShiptoLine2(value);
                break;
            }
            case SHIPTOCITY: {
                setShiptoCity(value);
                break;
            }
            case SHIPTOCOUNTRY: {
                setShiptoCountry(value);
                break;
            }
            case SHIPTOTELEPHONE: {
                setShiptoTelephone(value);
                break;
            }
            case SHIPTOSTATEORPROVINCE: {
                setShiptoStateOrProvince(value);
                break;
            }
            case SHIPTOPOSTALCODE: {
                setShiptoPostalCode(value);
                break;
            }
            case SHIPTOCONTACTNAME: {
                setShiptoContactName(value);
                break;
            }

        }
    }

    public Order() {

    }

    public Order(NodeList documentAttributes) {
        super(documentAttributes);
    }

    @Override
    public String[] getDefaultColumns() {
        return OrderColumns;
    }

    @Override
    protected ArrayList<XmlSerializable> getAttributeCollection() {
        ArrayList<XmlSerializable> options = new ArrayList<>();

        if (getCreatedOn() != null) {
            options.add(getCreatedOn());
        }
        if (getTransactionCurrencyId() != null) {
            options.add(getTransactionCurrencyId());
        }
        if (getName() != null) {
            options.add(getName());
        }
        if (getDescription() != null) {
            options.add(getDescription());
        }
        if (getOrderNumber() != null) {
            options.add(getOrderNumber());
        }
        if (getSubmitDate() != null) {
            options.add(getSubmitDate());
        }
        if (getTotalLineItemDiscountAmount() != null) {
            options.add(getTotalLineItemDiscountAmount());
        }
        if (getTotalLineItemAmount() != null) {
            options.add(getTotalLineItemAmount());
        }
        if (getTotalAmount() != null) {
            options.add(getTotalAmount());
        }
        if (getTotalTax() != null) {
            options.add(getTotalTax());
        }
        if (getModifiedOn() != null) {
            options.add(getModifiedOn());
        }
        if (getBilltoLine1() != null) {
            options.add(getBilltoLine1());
        }
        if (getCustomerId() != null) {
            options.add(getCustomerId());
        }
        if (getBilltoLine2() != null) {
            options.add(getBilltoLine2());
        }
        if (getBilltoCity() != null) {
            options.add(getBilltoCity());
        }
        if (getBilltoCountry() != null) {
            options.add(getBilltoCountry());
        }
        if (getBilltoTelephone() != null) {
            options.add(getBilltoTelephone());
        }
        if (getBilltoStateorprovince() != null) {
            options.add(getBilltoStateorprovince());
        }
        if (getBilltoPostalcode() != null) {
            options.add(getBilltoPostalcode());
        }
        if (getBilltoContactname() != null) {
            options.add(getBilltoContactname());
        }
        if (getShiptoLine1() != null) {
            options.add(getShiptoLine1());
        }
        if (getShiptoLine2() != null) {
            options.add(getShiptoLine2());
        }
        if (getShiptoCity() != null) {
            options.add(getShiptoCity());
        }
        if (getShiptoCountry() != null) {
            options.add(getShiptoCountry());
        }
        if (getShiptoTelephone() != null) {
            options.add(getShiptoTelephone());
        }
        if (getShiptoStateOrProvince() != null) {
            options.add(getShiptoStateOrProvince());
        }
        if (getShiptoPostalCode() != null) {
            options.add(getShiptoPostalCode());
        }
        if (getShiptoContactName() != null) {
            options.add(getShiptoContactName());
        }

        return options;
    }

    @Override
    protected Entity getEntity() {

        if (entity == null) {
            entity = new Entity(EntityName.Order);
        }

        return entity;
    }

    @Override
    public String getLogicalName() {
        return EntityName.Order;
    }

    public void addOrderProduct(OrderProduct orderProduct) {
        int idx = orderProducts.indexOf(orderProduct);

        if (idx < 0) {
            orderProducts.add(orderProduct);
        }
    }

    public ArrayList<OrderProduct> getOrderProducts() {

        for (OrderProduct orderProduct : orderProducts) {
            orderProduct.setSalesOrderId(getId());
        }

        return orderProducts;
    }

    public boolean removeOrderProduct(OrderProduct orderProduct) {
        int idx = orderProducts.indexOf(orderProduct);

        if (idx >= 0) {
            orderProducts.remove(orderProduct);

            return true;
        }

        return false;
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
     * @param createdOn the createdOn to set
     */
    private void setCreatedOn(String createdOn) {
        try {
            setCreatedOn(DATE_FORMAT.parse(createdOn));
        } catch (ParseException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the submitDate
     */
    public DateTime getSubmitDate() {
        return submitDate;
    }

    /**
     * @param submitDate the submitDate to set
     */
    public void setSubmitDate(Date submitDate) {
        this.submitDate = new DateTime(SUBMITDATE, submitDate);
    }

    /**
     * @param submitDate the submitDate to set
     */
    private void setSubmitDate(String submitDate) {
        try {
            setSubmitDate(DATE_FORMAT.parse(submitDate));
        } catch (ParseException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
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
     * @param modifiedOn the modifiedOn to set
     */
    private void setModifiedOn(String modifiedOn) {
        try {
            setModifiedOn(DATE_FORMAT.parse(modifiedOn));
        } catch (ParseException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
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
     * @return the customerId
     */
    public EntityReference getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = new EntityReference(EntityName.Contact, CUSTOMERID, customerId);
    }

    /**
     * @return the orderNumber
     */
    public CrmInt getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = new CrmInt(ORDERNUMBER, orderNumber);
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    private void setOrderNumber(String orderNumber) {
        setOrderNumber(Integer.parseInt(orderNumber));
    }

    /**
     * @return the totalLineItemDiscountAmount
     */
    public Money getTotalLineItemDiscountAmount() {
        return totalLineItemDiscountAmount;
    }

    /**
     * @param totalLineItemDiscountAmount the totalLineItemDiscountAmount to set
     */
    public void setTotalLineItemDiscountAmount(BigDecimal totalLineItemDiscountAmount) {
        this.totalLineItemDiscountAmount = new Money(TOTALLINEITEMDISCOUNTAMOUNT, totalLineItemDiscountAmount);
    }

    /**
     * @param totalLineItemDiscountAmount the totalLineItemDiscountAmount to set
     */
    private void setTotalLineItemDiscountAmount(String totalLineItemDiscountAmount) {
        setTotalLineItemDiscountAmount(new BigDecimal(totalLineItemDiscountAmount));
    }

    /**
     * @return the totalLineItemAmount
     */
    public Money getTotalLineItemAmount() {
        return totalLineItemAmount;
    }

    /**
     * @param totalLineItemAmount the totalLineItemAmount to set
     */
    public void setTotalLineItemAmount(BigDecimal totalLineItemAmount) {
        this.totalLineItemAmount = new Money(TOTALLINEITEMAMOUNT, totalLineItemAmount);
    }

    /**
     * @param totalLineItemAmount the totalLineItemAmount to set
     */
    private void setTotalLineItemAmount(String totalLineItemAmount) {
        setTotalLineItemAmount(new BigDecimal(totalLineItemAmount));
    }

    /**
     * @return the totalAmount
     */
    public Money getTotalAmount() {
        return totalAmount;
    }

    /**
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = new Money(TOTALAMOUNT, totalAmount);
    }

    /**
     * @param totalAmount the totalAmount to set
     */
    private void setTotalAmount(String totalAmount) {
        setTotalAmount(new BigDecimal(totalAmount));
    }

    /**
     * @return the totalTax
     */
    public Money getTotalTax() {
        return totalTax;
    }

    /**
     * @param totalTax the totalTax to set
     */
    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = new Money(TOTALTAX, totalTax);
    }

    /**
     * @param totalTax the totalTax to set
     */
    private void setTotalTax(String totalTax) {
        setTotalTax(new BigDecimal(totalTax));
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
     * @return the billtoLine1
     */
    public CrmString getBilltoLine1() {
        return billtoLine1;
    }

    /**
     * @param billtoLine1 the billtoLine1 to set
     */
    public void setBilltoLine1(String billtoLine1) {
        this.billtoLine1 = new CrmString(BILLTOLINE1, billtoLine1);
    }

    /**
     * @return the billtoLine2
     */
    public CrmString getBilltoLine2() {
        return billtoLine2;
    }

    /**
     * @param billtoLine2 the billtoLine2 to set
     */
    public void setBilltoLine2(String billtoLine2) {
        this.billtoLine2 = new CrmString(BILLTOLINE2, billtoLine2);
    }

    /**
     * @return the billtoCity
     */
    public CrmString getBilltoCity() {
        return billtoCity;
    }

    /**
     * @param billtoCity the billtoCity to set
     */
    public void setBilltoCity(String billtoCity) {
        this.billtoCity = new CrmString(BILLTOCITY, billtoCity);
    }

    /**
     * @return the billtoCountry
     */
    public CrmString getBilltoCountry() {
        return billtoCountry;
    }

    /**
     * @param billtoCountry the billtoCountry to set
     */
    public void setBilltoCountry(String billtoCountry) {
        this.billtoCountry = new CrmString(BILLTOCOUNTRY, billtoCountry);
    }

    /**
     * @return the billtoTelephone
     */
    public CrmString getBilltoTelephone() {
        return billtoTelephone;
    }

    /**
     * @param billtoTelephone the billtoTelephone to set
     */
    public void setBilltoTelephone(String billtoTelephone) {
        this.billtoTelephone = new CrmString(BILLTOTELEPHONE, billtoTelephone);
    }

    /**
     * @return the billtoStateorprovince
     */
    public CrmString getBilltoStateorprovince() {
        return billtoStateorprovince;
    }

    /**
     * @param billtoStateorprovince the billtoStateorprovince to set
     */
    public void setBilltoStateorprovince(String billtoStateorprovince) {
        this.billtoStateorprovince = new CrmString(BILLTOSTATEORPROVINCE, billtoStateorprovince);
    }

    /**
     * @return the billtoPostalcode
     */
    public CrmString getBilltoPostalcode() {
        return billtoPostalcode;
    }

    /**
     * @param billtoPostalcode the billtoPostalcode to set
     */
    public void setBilltoPostalcode(String billtoPostalcode) {
        this.billtoPostalcode = new CrmString(BILLTOPOSTALCODE, billtoPostalcode);
    }

    /**
     * @return the billtoContactname
     */
    public CrmString getBilltoContactname() {
        return billtoContactname;
    }

    /**
     * @param billtoContactname the billtoContactname to set
     */
    public void setBilltoContactname(String billtoContactname) {
        this.billtoContactname = new CrmString(BILLTOCONTACTNAME, billtoContactname);
    }

    /**
     * @return the shiptoLine1
     */
    public CrmString getShiptoLine1() {
        return shiptoLine1;
    }

    /**
     * @param shiptoLine1 the shiptoLine1 to set
     */
    public void setShiptoLine1(String shiptoLine1) {
        this.shiptoLine1 = new CrmString(SHIPTOLINE1, shiptoLine1);
    }

    /**
     * @return the shiptoLine2
     */
    public CrmString getShiptoLine2() {
        return shiptoLine2;
    }

    /**
     * @param shiptoLine2 the shiptoLine2 to set
     */
    public void setShiptoLine2(String shiptoLine2) {
        this.shiptoLine2 = new CrmString(SHIPTOLINE2, shiptoLine2);
    }

    /**
     * @return the shiptoCity
     */
    public CrmString getShiptoCity() {
        return shiptoCity;
    }

    /**
     * @param shiptoCity the shiptoCity to set
     */
    public void setShiptoCity(String shiptoCity) {
        this.shiptoCity = new CrmString(SHIPTOCITY, shiptoCity);
    }

    /**
     * @return the shiptoCountry
     */
    public CrmString getShiptoCountry() {
        return shiptoCountry;
    }

    /**
     * @param shiptoCountry the shiptoCountry to set
     */
    public void setShiptoCountry(String shiptoCountry) {
        this.shiptoCountry = new CrmString(SHIPTOCOUNTRY, shiptoCountry);
    }

    /**
     * @return the shiptoTelephone
     */
    public CrmString getShiptoTelephone() {
        return shiptoTelephone;
    }

    /**
     * @param shiptoTelephone the shiptoTelephone to set
     */
    public void setShiptoTelephone(String shiptoTelephone) {
        this.shiptoTelephone = new CrmString(SHIPTOTELEPHONE, shiptoTelephone);
    }

    /**
     * @return the shiptoStateOrProvince
     */
    public CrmString getShiptoStateOrProvince() {
        return shiptoStateOrProvince;
    }

    /**
     * @param shiptoStateOrProvince the shiptoStateOrProvince to set
     */
    public void setShiptoStateOrProvince(String shiptoStateOrProvince) {
        this.shiptoStateOrProvince = new CrmString(SHIPTOSTATEORPROVINCE, shiptoStateOrProvince);
    }

    /**
     * @return the shiptoPostalCode
     */
    public CrmString getShiptoPostalCode() {
        return shiptoPostalCode;
    }

    /**
     * @param shiptoPostalCode the shiptoPostalCode to set
     */
    public void setShiptoPostalCode(String shiptoPostalCode) {
        this.shiptoPostalCode = new CrmString(SHIPTOPOSTALCODE, shiptoPostalCode);
    }

    /**
     * @return the shiptoContactName
     */
    public CrmString getShiptoContactName() {
        return shiptoContactName;
    }

    /**
     * @param shiptoContactName the shiptoContactName to set
     */
    public void setShiptoContactName(String shiptoContactName) {
        this.shiptoContactName = new CrmString(SHIPTOCONTACTNAME, shiptoContactName);
    }

}

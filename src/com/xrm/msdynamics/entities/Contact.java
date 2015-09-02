package com.xrm.msdynamics.entities;

import com.xrm.msdynamics.Enums;
import com.xrm.msdynamics.Enums.EntityName;

import com.xrm.msdynamics.crmtypes.CrmBoolean;
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
public class Contact extends BaseEntity {

    Entity entity = new Entity(Enums.EntityName.Contact);

    private DateTime createdon;
    private DateTime modifiedon;
    private DateTime birthdate;
    private DateTime anniversary;

    private CrmString emailaddress1;
    private CrmString firstname;
    private CrmString lastname;
    private CrmString description;
    private CrmString jobTitle;
    private CrmString telephone;
    private CrmString mobilephone;
    private CrmString fax;
    private CrmString spousesName;

    private EntityReference parentcustomerid;
    private EntityReference transactioncurrencyId;
    private EntityReference originatingLeadId;

    private OptionSetValue preferredContactmethodCode;
    private OptionSetValue genderCode;
    private OptionSetValue familyStatusCode;
    private OptionSetValue paymentTermsCode;
    private OptionSetValue address1FreightTermsCode;
    private OptionSetValue address1ShippingMethodCode;

    private CrmBoolean doNotEmail;
    private CrmBoolean doNotBulkEmail;
    private CrmBoolean doNotPhone;
    private CrmBoolean doNotFax;
    private CrmBoolean doNotPostalMail;
    private CrmBoolean donotsendmm;

    private Money creditLimit;
    private Money creditOnHold;

    public static final String ID_COLUMN = "contactid";
    public static final String DONOTSENDMM_COLUMN = "donotsendmm";
    public static final String CREATEDON_COLUMN = "createdon";
    public static final String EMAILADDRESS1_COLUMN = "emailaddress1";
    public static final String FIRSTNAME_COLUMN = "firstname";
    public static final String LASTNAME_COLUMN = "lastname";
    public static final String DESCRIPTION_COLUMN = "description";
    public static final String MODIFIEDON_COLUMN = "modifiedon";
    public static final String PARENTCUSTOMERID_COLUMN = "parentcustomerid";
    public static final String JOBTITLE_COLUMN = "jobtitle";
    public static final String TELEPHONE1_COLUMN = "telephone1";
    public static final String MOBILEPHONE_COLUMN = "mobilephone";
    public static final String FAX_COLUMN = "fax";
    public static final String PREFERREDCONTACTMETHODCODE_COLUMN = "preferredcontactmethodcode";
    public static final String GENDERCODE_COLUMN = "gendercode";
    public static final String FAMILYSTATUSCODE_COLUMN = "familystatuscode";
    public static final String SPOUSESNAME_COLUMN = "spousesname";
    public static final String BIRTHDATE_COLUMN = "birthdate";
    public static final String ANNIVERSARY_COLUMN = "anniversary";
    public static final String ORIGINATINGLEADID_COLUMN = "originatingleadid";
    public static final String DONOTDENDMM_COLUMN = "donotsendmm";
    public static final String DONOTEMAIL_COLUMN = "donotemail";
    public static final String DONOTBULKEMAIL_COLUMN = "donotbulkemail";
    public static final String DONOTPHONE_COLUMN = "donotphone";
    public static final String DONOTFAX_COLUMN = "donotfax";
    public static final String DONOTPOSTALMAIL_COLUMN = "donotpostalmail";
    public static final String TRANSACTIONCURRENCYID_COLUMN = "transactioncurrencyid";
    public static final String CREDITLIMIT_COLUMN = "creditlimit";
    public static final String CREDITONHOLD_COLUMN = "creditonhold";
    public static final String PAYMENTTERMSCODE_COLUMN = "paymenttermscode";
    public static final String SHIPPINGMETHODCODE_COLUMN = "address1_shippingmethodcode";
    public static final String FREIGHTTERMSCODE_COLUMN = "address1_freighttermscode";

    public static String[] ContactColumns = new String[]{
        ID_COLUMN,
        CREATEDON_COLUMN,
        EMAILADDRESS1_COLUMN,
        FIRSTNAME_COLUMN,
        LASTNAME_COLUMN,
        DESCRIPTION_COLUMN,
        MODIFIEDON_COLUMN,
        PARENTCUSTOMERID_COLUMN,
        JOBTITLE_COLUMN,
        TELEPHONE1_COLUMN,
        MOBILEPHONE_COLUMN,
        FAX_COLUMN,
        PREFERREDCONTACTMETHODCODE_COLUMN,
        GENDERCODE_COLUMN,
        FAMILYSTATUSCODE_COLUMN,
        SPOUSESNAME_COLUMN,
        BIRTHDATE_COLUMN,
        ANNIVERSARY_COLUMN,
        ORIGINATINGLEADID_COLUMN,
        DONOTDENDMM_COLUMN,
        DONOTEMAIL_COLUMN,
        DONOTBULKEMAIL_COLUMN,
        DONOTPHONE_COLUMN,
        DONOTFAX_COLUMN,
        DONOTPOSTALMAIL_COLUMN,
        TRANSACTIONCURRENCYID_COLUMN,
        CREDITLIMIT_COLUMN,
        CREDITONHOLD_COLUMN,
        PAYMENTTERMSCODE_COLUMN,
        SHIPPINGMETHODCODE_COLUMN
    };

    public Contact() {

    }

    public Contact(NodeList documentAttributes) {
        super(documentAttributes);
    }

    @Override
    public String[] getDefaultColumns() {
        return ContactColumns;
    }

    @Override
    public void setAttribute(String key, String value, String logicalName) {
        switch (key) {

            case ID_COLUMN: {
                this.setId(value);
                break;
            }

            case CREATEDON_COLUMN: {
                try {
                    this.setCreatedon(DATE_FORMAT.parse(value));
                } catch (ParseException ex) {
                    Logger.getLogger(Contact.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }

            case EMAILADDRESS1_COLUMN: {
                this.setEmailaddress1(value);
                break;
            }

            case FIRSTNAME_COLUMN: {
                this.setFirstname(value);
                break;
            }

            case LASTNAME_COLUMN: {
                this.setLastname(value);
                break;
            }

            case DESCRIPTION_COLUMN: {
                this.setDescription(value);
                break;
            }

            case MODIFIEDON_COLUMN: {
                try {
                    this.setModifiedon(DATE_FORMAT.parse(value));
                    break;
                } catch (ParseException ex) {
                    Logger.getLogger(Contact.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            case PARENTCUSTOMERID_COLUMN: {
                this.setParentcustomerid(value);
                break;
            }

            case JOBTITLE_COLUMN: {
                this.setJobTitle(value);
                break;
            }

            case TELEPHONE1_COLUMN: {
                this.setTelephone(value);
                break;
            }
            case MOBILEPHONE_COLUMN: {
                this.setMobilephone(value);
                break;
            }
            case FAX_COLUMN: {
                this.setFax(value);
                break;
            }
            case PREFERREDCONTACTMETHODCODE_COLUMN: {
                this.setPreferredContactmethodCode(value);
                break;
            }
            case GENDERCODE_COLUMN: {
                this.setGenderCode(value);
                break;
            }
            case FAMILYSTATUSCODE_COLUMN: {
                this.setFamilyStatusCode(value);
                break;
            }
            case SPOUSESNAME_COLUMN: {
                this.setSpousesName(value);
                break;
            }
            case BIRTHDATE_COLUMN: {
                this.setBirthdate(value);
                break;
            }
            case ANNIVERSARY_COLUMN: {
                this.setAnniversary(value);
                break;
            }
            case DONOTDENDMM_COLUMN: {
                this.donotsendmm(value);
                break;
            }
            case DONOTEMAIL_COLUMN: {
                this.setDoNotEmail(value);
                break;
            }
            case DONOTBULKEMAIL_COLUMN: {
                this.setDoNotBulkEmail(value);
                break;
            }
            case DONOTPHONE_COLUMN: {
                this.setDoNotPhone(value);
                break;
            }
            case DONOTFAX_COLUMN: {
                this.setDoNotFax(value);
                break;
            }
            case DONOTPOSTALMAIL_COLUMN: {
                this.setDoNotPostalMail(value);
                break;
            }
            case TRANSACTIONCURRENCYID_COLUMN: {
                this.setTransactioncurrencyId(value);
                break;
            }
            case CREDITLIMIT_COLUMN: {
                this.setCreditLimit(value);
                break;
            }
            case CREDITONHOLD_COLUMN: {
                this.setCreditOnHold(value);
                break;
            }
            case PAYMENTTERMSCODE_COLUMN: {
                this.setPaymentTermsCode(value);
                break;
            }
            case SHIPPINGMETHODCODE_COLUMN: {
                this.setAddress1ShippingMethodCode(value);
                break;
            }

            case ORIGINATINGLEADID_COLUMN: {
                this.setOriginatingLeadId(value);
                break;
            }

        }
    }

    @Override
    protected ArrayList<XmlSerializable> getAttributeCollection() {
        ArrayList<XmlSerializable> parameters = new ArrayList<>();

        if (getCreatedon() != null) {
            parameters.add(getCreatedon());
        }

        if (getEmailaddress1() != null) {
            parameters.add(getEmailaddress1());
        }

        if (getFirstname() != null) {
            parameters.add(getFirstname());
        }

        if (getLastname() != null) {
            parameters.add(getLastname());
        }

        if (getDescription() != null) {
            parameters.add(getDescription());
        }

        if (getModifiedon() != null) {
            parameters.add(getModifiedon());
        }

        if (getParentcustomerid() != null) {
            parameters.add(getParentcustomerid());
        }

        if (getBirthdate() != null) {
            parameters.add(getBirthdate());
        }

        if (getAnniversary() != null) {
            parameters.add(getAnniversary());
        }

        if (getJobTitle() != null) {
            parameters.add(getJobTitle());
        }
        if (getTelephone() != null) {
            parameters.add(getTelephone());
        }
        if (getMobilephone() != null) {
            parameters.add(getMobilephone());
        }
        if (getFax() != null) {
            parameters.add(getFax());
        }
        if (getSpousesName() != null) {
            parameters.add(getSpousesName());
        }
        if (getTransactioncurrencyId() != null) {
            parameters.add(getTransactioncurrencyId());
        }

        if (getPreferredContactmethodCode() != null) {
            parameters.add(getPreferredContactmethodCode());
        }
        if (getGenderCode() != null) {
            parameters.add(getGenderCode());
        }
        if (getFamilyStatusCode() != null) {
            parameters.add(getFamilyStatusCode());
        }
        if (getPaymentTermsCode() != null) {
            parameters.add(getPaymentTermsCode());
        }
        if (getAddress1FreightTermsCode() != null) {
            parameters.add(getAddress1FreightTermsCode());
        }
        if (getAddress1ShippingMethodCode() != null) {
            parameters.add(getAddress1ShippingMethodCode());
        }
        if (getDoNotEmail() != null) {
            parameters.add(getDoNotEmail());
        }
        if (getDoNotBulkEmail() != null) {
            parameters.add(getDoNotBulkEmail());
        }
        if (getDoNotPhone() != null) {
            parameters.add(getDoNotPhone());
        }
        if (getDoNotFax() != null) {
            parameters.add(getDoNotFax());
        }
        if (getDoNotPostalMail() != null) {
            parameters.add(getDoNotPostalMail());
        }
        if (getDonotsendmm() != null) {
            parameters.add(getDonotsendmm());
        }
        if (getCreditLimit() != null) {
            parameters.add(getCreditLimit());
        }
        if (getCreditOnHold() != null) {
            parameters.add(getCreditOnHold());
        }
        if (getOriginatingLeadId() != null) {
            parameters.add(getOriginatingLeadId());
        }

        return parameters;
    }

    @Override
    protected Entity getEntity() {
        return entity;
    }

    @Override
    public String getLogicalName() {
        return Enums.EntityName.Contact;
    }

    /**
     * @return the donotsendmm
     */
    public CrmBoolean getDonotsendmm() {
        return donotsendmm;
    }

    /**
     * @param donotsendmm the donotsendmm to set
     */
    public void setDonotsendmm(boolean donotsendmm) {
        this.donotsendmm = new CrmBoolean(DONOTSENDMM_COLUMN, donotsendmm);
    }

    /**
     * @param donotsendmm the donotsendmm to set
     */
    private void setDonotsendmm(String donotsendmm) {
        setDonotsendmm(Boolean.parseBoolean(donotsendmm));
    }

    /**
     * @return the createdon
     */
    public DateTime getCreatedon() {
        return createdon;
    }

    /**
     * @param createdon the createdon to set
     */
    public void setCreatedon(Date createdon) {
        this.createdon = new DateTime(CREATEDON_COLUMN, createdon);
    }

    /**
     * @return the modifiedon
     */
    public DateTime getModifiedon() {
        return modifiedon;
    }

    /**
     * @param modifiedon the modifiedon to set
     */
    public void setModifiedon(Date modifiedon) {
        this.modifiedon = new DateTime(MODIFIEDON_COLUMN, modifiedon);
    }

    /**
     * @return the emailaddress1
     */
    public CrmString getEmailaddress1() {
        return emailaddress1;
    }

    /**
     * @param emailaddress1 the emailaddress1 to set
     */
    public void setEmailaddress1(String emailaddress1) {
        this.emailaddress1 = new CrmString(EMAILADDRESS1_COLUMN, emailaddress1);
    }

    /**
     * @return the firstname
     */
    public CrmString getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = new CrmString(FIRSTNAME_COLUMN, firstname);
    }

    /**
     * @return the lastname
     */
    public CrmString getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = new CrmString(LASTNAME_COLUMN, lastname);
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
        this.description = new CrmString(DESCRIPTION_COLUMN, description);
    }

    /**
     * @return the parentcustomerid
     */
    public EntityReference getParentcustomerid() {
        return parentcustomerid;
    }

    /**
     * @param parentcustomerid the parentcustomerid to set
     */
    public void setParentcustomerid(String parentcustomerid) {
        this.parentcustomerid = new EntityReference(EntityName.Account, PARENTCUSTOMERID_COLUMN, parentcustomerid);
    }

    /**
     * @return the jobTitle
     */
    public CrmString getJobTitle() {
        return jobTitle;
    }

    /**
     * @param jobTitle the jobTitle to set
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = new CrmString(JOBTITLE_COLUMN, jobTitle);
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
        this.telephone = new CrmString(TELEPHONE1_COLUMN, telephone);
    }

    /**
     * @return the mobilephone
     */
    public CrmString getMobilephone() {
        return mobilephone;
    }

    /**
     * @param mobilephone the mobilephone to set
     */
    public void setMobilephone(String mobilephone) {
        this.mobilephone = new CrmString(MOBILEPHONE_COLUMN, mobilephone);
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
        this.fax = new CrmString(FAX_COLUMN, fax);
    }

    /**
     * @return the spousesName
     */
    public CrmString getSpousesName() {
        return spousesName;
    }

    /**
     * @param spousesName the spousesName to set
     */
    public void setSpousesName(String spousesName) {
        this.spousesName = new CrmString(SPOUSESNAME_COLUMN, spousesName);
    }

    /**
     * @return the preferredContactmethodCode
     */
    public OptionSetValue getPreferredContactmethodCode() {
        return preferredContactmethodCode;
    }

    /**
     * @param preferredContactmethodCode the preferredContactmethodCode to set
     */
    public void setPreferredContactmethodCode(int preferredContactmethodCode) {
        this.preferredContactmethodCode = new OptionSetValue(PREFERREDCONTACTMETHODCODE_COLUMN, preferredContactmethodCode);
    }

    /**
     * @param preferredContactmethodCode the preferredContactmethodCode to set
     */
    private void setPreferredContactmethodCode(String preferredContactmethodCode) {
        setPreferredContactmethodCode(Integer.parseInt(preferredContactmethodCode));
    }

    /**
     * @return the genderCode
     */
    public OptionSetValue getGenderCode() {
        return genderCode;
    }

    /**
     * @param genderCode the genderCode to set
     */
    public void setGenderCode(int genderCode) {
        this.genderCode = new OptionSetValue(GENDERCODE_COLUMN, genderCode);
    }

    /**
     * @param genderCode the genderCode to set
     */
    private void setGenderCode(String genderCode) {
        setGenderCode(Integer.parseInt(genderCode));
    }

    /**
     * @return the familyStatusCode
     */
    public OptionSetValue getFamilyStatusCode() {
        return familyStatusCode;
    }

    /**
     * @param familyStatusCode the familyStatusCode to set
     */
    public void setFamilyStatusCode(int familyStatusCode) {
        this.familyStatusCode = new OptionSetValue(FAMILYSTATUSCODE_COLUMN, familyStatusCode);
    }

    /**
     * @param familyStatusCode the familyStatusCode to set
     */
    private void setFamilyStatusCode(String familyStatusCode) {
        setFamilyStatusCode(Integer.parseInt(familyStatusCode));
    }

    /**
     * @return the transactioncurrencyId
     */
    public EntityReference getTransactioncurrencyId() {
        return transactioncurrencyId;
    }

    /**
     * @param transactioncurrencyId the transactioncurrencyId to set
     */
    public void setTransactioncurrencyId(String transactioncurrencyId) {
        this.transactioncurrencyId = new EntityReference(EntityName.Currency, TRANSACTIONCURRENCYID_COLUMN, transactioncurrencyId);
    }

    /**
     * @return the paymentTermsCode
     */
    public OptionSetValue getPaymentTermsCode() {
        return paymentTermsCode;
    }

    /**
     * @param paymentTermsCode the paymentTermsCode to set
     */
    public void setPaymentTermsCode(int paymentTermsCode) {
        this.paymentTermsCode = new OptionSetValue(PAYMENTTERMSCODE_COLUMN, paymentTermsCode);
    }

    /**
     * @param paymentTermsCode the paymentTermsCode to set
     */
    private void setPaymentTermsCode(String paymentTermsCode) {
        setPaymentTermsCode(Integer.parseInt(paymentTermsCode));
    }

    /**
     * @return the address1FreightTermsCode
     */
    public OptionSetValue getAddress1FreightTermsCode() {
        return address1FreightTermsCode;
    }

    /**
     * @param address1FreightTermsCode the address1FreightTermsCode to set
     */
    public void setAddress1FreightTermsCode(int address1FreightTermsCode) {
        this.address1FreightTermsCode = new OptionSetValue(FREIGHTTERMSCODE_COLUMN, address1FreightTermsCode);
    }

    /**
     * @param address1FreightTermsCode the address1FreightTermsCode to set
     */
    private void setAddress1FreightTermsCode(String address1FreightTermsCode) {
        setAddress1FreightTermsCode(Integer.parseInt(address1FreightTermsCode));
    }

    /**
     * @return the address1ShippingMethodCode
     */
    public OptionSetValue getAddress1ShippingMethodCode() {
        return address1ShippingMethodCode;
    }

    /**
     * @param address1ShippingMethodCode the address1ShippingMethodCode to set
     */
    public void setAddress1ShippingMethodCode(int address1ShippingMethodCode) {
        this.address1ShippingMethodCode = new OptionSetValue(SHIPPINGMETHODCODE_COLUMN, address1ShippingMethodCode);
    }

    /**
     * @param address1ShippingMethodCode the address1ShippingMethodCode to set
     */
    private void setAddress1ShippingMethodCode(String address1ShippingMethodCode) {
        setAddress1ShippingMethodCode(Integer.parseInt(address1ShippingMethodCode));
    }

    /**
     * @return the doNotEmail
     */
    public CrmBoolean getDoNotEmail() {
        return doNotEmail;
    }

    /**
     * @param doNotEmail the doNotEmail to set
     */
    public void setDoNotEmail(boolean doNotEmail) {
        this.doNotEmail = new CrmBoolean(DONOTEMAIL_COLUMN, doNotEmail);
    }

    /**
     * @param doNotEmail the doNotEmail to set
     */
    private void setDoNotEmail(String doNotEmail) {
        setDoNotEmail(Boolean.parseBoolean(doNotEmail));
    }

    /**
     * @return the doNotBulkEmail
     */
    public CrmBoolean getDoNotBulkEmail() {
        return doNotBulkEmail;
    }

    /**
     * @param doNotBulkEmail the doNotBulkEmail to set
     */
    public void setDoNotBulkEmail(boolean doNotBulkEmail) {
        this.doNotBulkEmail = new CrmBoolean(DONOTBULKEMAIL_COLUMN, doNotBulkEmail);
    }

    /**
     * @param doNotBulkEmail the doNotBulkEmail to set
     */
    private void setDoNotBulkEmail(String doNotBulkEmail) {
        setDoNotBulkEmail(Boolean.parseBoolean(doNotBulkEmail));
    }

    /**
     * @return the doNotPhone
     */
    public CrmBoolean getDoNotPhone() {
        return doNotPhone;
    }

    /**
     * @param doNotPhone the doNotPhone to set
     */
    public void setDoNotPhone(boolean doNotPhone) {
        this.doNotPhone = new CrmBoolean(DONOTPHONE_COLUMN, doNotPhone);
    }

    /**
     * @param doNotPhone the doNotPhone to set
     */
    private void setDoNotPhone(String doNotPhone) {
        setDoNotPhone(Boolean.parseBoolean(doNotPhone));
    }

    /**
     * @return the doNotFax
     */
    public CrmBoolean getDoNotFax() {
        return doNotFax;
    }

    /**
     * @param doNotFax the doNotFax to set
     */
    public void setDoNotFax(boolean doNotFax) {
        this.doNotFax = new CrmBoolean(DONOTFAX_COLUMN, doNotFax);
    }

    /**
     * @param doNotFax the doNotFax to set
     */
    private void setDoNotFax(String doNotFax) {
        setDoNotFax(Boolean.parseBoolean(doNotFax));
    }

    /**
     * @return the doNotPostalMail
     */
    public CrmBoolean getDoNotPostalMail() {
        return doNotPostalMail;
    }

    /**
     * @param doNotPostalMail the doNotPostalMail to set
     */
    private void setDoNotPostalMail(String doNotPostalMail) {
        setDoNotPostalMail(Boolean.parseBoolean(doNotPostalMail));
    }

    /**
     * @param doNotPostalMail the doNotPostalMail to set
     */
    private void setDoNotPostalMail(boolean doNotPostalMail) {
        this.doNotPostalMail = new CrmBoolean(DONOTPOSTALMAIL_COLUMN, doNotPostalMail);
    }

    /**
     * @return the birthdate
     */
    public DateTime getBirthdate() {
        return birthdate;
    }

    /**
     * @param birthdate the birthdate to set
     */
    private void setBirthdate(String birthdate) {
        try {
            this.setBirthdate(DATE_FORMAT.parse(birthdate));
        } catch (ParseException ex) {
            Logger.getLogger(Contact.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param birthdate the birthdate to set
     */
    public void setBirthdate(Date birthdate) {
        this.anniversary = new DateTime(BIRTHDATE_COLUMN, birthdate);

    }

    /**
     * @return the anniversary
     */
    public DateTime getAnniversary() {
        return anniversary;
    }

    /**
     * @param anniversary the anniversary to set
     */
    private void setAnniversary(String anniversary) {
        try {
            this.setAnniversary(DATE_FORMAT.parse(anniversary));
        } catch (ParseException ex) {
            Logger.getLogger(Contact.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param anniversary the anniversary to set
     */
    public void setAnniversary(Date anniversary) {
        this.anniversary = new DateTime(ANNIVERSARY_COLUMN, anniversary);
    }

    /**
     * @return the creditLimit
     */
    public Money getCreditLimit() {
        return creditLimit;
    }

    /**
     * @param creditLimit the creditLimit to set
     */
    public void setCreditLimit(String creditLimit) {
        this.setCreditLimit(new BigDecimal(creditLimit));
    }

    /**
     * @param creditLimit the creditLimit to set
     */
    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = new Money(CREDITLIMIT_COLUMN, creditLimit);
    }

    /**
     * @return the creditOnHold
     */
    public Money getCreditOnHold() {
        return creditOnHold;
    }

    /**
     * @param creditOnHold the creditOnHold to set
     */
    private void setCreditOnHold(String creditOnHold) {
        this.setCreditOnHold(new BigDecimal(creditOnHold));
    }

    /**
     * @param creditOnHold the creditOnHold to set
     */
    public void setCreditOnHold(BigDecimal creditOnHold) {
        this.creditOnHold = new Money(CREDITONHOLD_COLUMN, creditOnHold);
    }

    private void donotsendmm(String value) {
        setDonotsendmm(Boolean.parseBoolean(value));
    }

    /**
     * @return the originatingLeadId
     */
    public EntityReference getOriginatingLeadId() {
        return originatingLeadId;
    }

    /**
     * @param originatingLeadId the originatingLeadId to set
     */
    public void setOriginatingLeadId(String originatingLeadId) {
        this.originatingLeadId = new EntityReference(EntityName.Lead, ORIGINATINGLEADID_COLUMN, originatingLeadId);
    }

}

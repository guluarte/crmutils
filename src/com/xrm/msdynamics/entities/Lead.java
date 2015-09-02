package com.xrm.msdynamics.entities;

import com.xrm.msdynamics.Enums;
import com.xrm.msdynamics.Enums.EntityName;
import com.xrm.msdynamics.crmtypes.CrmBoolean;
import com.xrm.msdynamics.crmtypes.CrmDouble;
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
public class Lead extends BaseEntity {

    Entity entity = new Entity(EntityName.Lead);

    private CrmString subject;
    private CrmString firstname;
    private CrmString lastname;
    private CrmString jobtitle;
    private CrmString emailaddress1;
    private CrmString telephone1;
    private CrmString mobilephone;
    private CrmString fax;
    private CrmString description;
    private CrmString websiteurl;
    private CrmString companyname;
    private CrmString sic;
    private CrmString address1_line1;
    private CrmString address1_line2;
    private CrmString address1_city;
    private CrmString address1_country;

    private CrmString address1_telephone1;
    private CrmString address1_stateorprovince;
    private CrmString address1_postalcode;

    private EntityReference transactioncurrencyid;
    private EntityReference ownerid;
    private EntityReference campaignid;
    private EntityReference parentcontactid;
    private EntityReference parentaccountid;

    private DateTime createdon;
    private DateTime modifiedon;

    private CrmDouble address1_latitude;
    private CrmDouble address1_longitude;

    private CrmBoolean donotemail;
    private CrmBoolean donotbulkemail;
    private CrmBoolean donotphone;
    private CrmBoolean donotfax;
    private CrmBoolean donotpostalmail;
    private CrmBoolean donotsendmm;
    private CrmBoolean confirminterest;
    private CrmBoolean decisionmaker;

    private OptionSetValue leadqualitycode;
    private OptionSetValue leadsourcecode;
    private OptionSetValue preferredcontactmethodcode;
    private OptionSetValue industrycode;
    private OptionSetValue purchasetimeframe;
    private OptionSetValue purchaseprocess;

    private Money revenue;
    private Money budgetamount;

    private CrmInt numberofemployees;

    public static final String ID_COLUMN = "leadid";
    public static final String SUBJECT_COLUMN = "subject";
    public static final String FIRSTNAME_COLUMN = "firstname";
    public static final String LASTNAME_COLUMN = "lastname";
    public static final String LEADQUALITYCODE_COLUMN = "leadqualitycode";
    public static final String LEADSOURCECODE_COLUMN = "leadsourcecode";
    public static final String CREATEON_COLUMN = "createdon";
    public static final String EMAIL_COLUMN = "emailaddress1";
    public static final String MODIFIEDON_COLUMN = "modifiedon";
    public static final String DONOTSENDMM_COLUMN = "donotsendmm";
    public static final String DESCRIPTION_COLUMN = "description";
    public static final String ADDRESS1_LINE1_COLUMN = "address1_line1";
    public static final String ADDRESS1_LINE2_COLUMN = "address1_line2";
    public static final String ADDRESS1_CITY_COLUMN = "address1_city";
    public static final String ADDRESS1_COUNTRY_COLUMN = "address1_country";
    public static final String ADDRESS1_LATITUDE_COLUMN = "address1_latitude";
    public static final String ADDRESS1_LONGITUDE_COLUMN = "address1_longitude";
    public static final String ADDRESS1_TELEPHONE_COLUMN = "address1_telephone1";
    public static final String ADDRESS1_STATEORPROVINCE_COLUMN = "address1_stateorprovince";
    public static final String ADDRESS1_POSTALCODE_COLUMN = "address1_postalcode";
    public static final String PARENTACCOUNTID_COLUMN = "parentaccountid";
    public static final String COMPANYNAME_COLUMN = "companyname";
    public static final String CONFIRMINTEREST_COLUMN = "confirminterest";
    public static final String PARENTCONTACTID_COLUMN = "parentcontactid";
    public static final String JOBTITLE_COLUMN = "jobtitle";
    public static final String TELEPHONE1_COLUMN = "telephone1";
    public static final String MOBILEPHONE_COLUMN = "mobilephone";
    public static final String FAX_COLUMN = "fax";
    public static final String PREFEREDCONTACTMETHOD_COLUMN = "preferredcontactmethodcode";
    public static final String PARENTCUSTOMERID_COLUMN = "parentcustomerid";
    public static final String TRANSACTIONCURRENCYID_COLUMN = "transactioncurrencyid";
    public static final String DONOTEMAIL_COLUMN = "donotemail";
    public static final String DONOTBULKEMAIL_COLUMN = "donotbulkemail";
    public static final String DONOTPHONE_COLUMN = "donotphone";
    public static final String DONOTFAX_COLUMN = "donotfax";
    public static final String DONOTPOSTALEMAIL_COLUMN = "donotpostalmail";
    public static final String OWNERID_COLUMN = "ownerid";
    public static final String WEBSITEURL_COLUMN = "websiteurl";
    public static final String INDUSCTRYCODE_COLUMN = "industrycode";
    public static final String REVENUE_COLUMN = "revenue";
    public static final String NUMBEROFEMPLOYEES_COLUMN = "numberofemployees";
    public static final String SIC_COLUMN = "sic";
    public static final String CAMPAIGNID_COLUMN = "campaignid";
    public static final String PURCHASETIMEFRAME_COLUMN = "purchasetimeframe";
    public static final String BUDGETAMOUNT_COLUMN = "budgetamount";
    public static final String PURCHASEPROCESS_COLUMN = "purchaseprocess";
    public static final String DECISIONMAKER_COLUMN = "decisionmaker";

    public static String[] LeadColumns = new String[]{
        SUBJECT_COLUMN,
        FIRSTNAME_COLUMN,
        LASTNAME_COLUMN,
        LEADQUALITYCODE_COLUMN,
        LEADSOURCECODE_COLUMN,
        CREATEON_COLUMN,
        EMAIL_COLUMN,
        MODIFIEDON_COLUMN,
        DONOTSENDMM_COLUMN,
        DESCRIPTION_COLUMN,
        ADDRESS1_LINE1_COLUMN,
        ADDRESS1_LINE2_COLUMN,
        ADDRESS1_CITY_COLUMN,
        ADDRESS1_COUNTRY_COLUMN,
        ADDRESS1_LATITUDE_COLUMN,
        ADDRESS1_LONGITUDE_COLUMN,
        ADDRESS1_TELEPHONE_COLUMN,
        ADDRESS1_STATEORPROVINCE_COLUMN,
        ADDRESS1_POSTALCODE_COLUMN,
        PARENTACCOUNTID_COLUMN,
        COMPANYNAME_COLUMN,
        CONFIRMINTEREST_COLUMN,
        PARENTCONTACTID_COLUMN,
        JOBTITLE_COLUMN,
        TELEPHONE1_COLUMN,
        MOBILEPHONE_COLUMN,
        FAX_COLUMN,
        PREFEREDCONTACTMETHOD_COLUMN,
        TRANSACTIONCURRENCYID_COLUMN,
        DONOTEMAIL_COLUMN,
        DONOTBULKEMAIL_COLUMN,
        DONOTPHONE_COLUMN,
        DONOTFAX_COLUMN,
        DONOTPOSTALEMAIL_COLUMN,
        OWNERID_COLUMN,
        WEBSITEURL_COLUMN,
        INDUSCTRYCODE_COLUMN,
        REVENUE_COLUMN,
        NUMBEROFEMPLOYEES_COLUMN,
        SIC_COLUMN,
        CAMPAIGNID_COLUMN,
        PURCHASETIMEFRAME_COLUMN,
        BUDGETAMOUNT_COLUMN,
        PURCHASEPROCESS_COLUMN,
        DECISIONMAKER_COLUMN

    };

    public static class LeadQualityCode {

        public static final int Hot = 1;
        public static final int Warm = 2;
        public static final int Cold = 3;
    }

    public static class IndustryCode {

        public static final int Accounting = 1;
        public static final int AgricultureAndNonPetrolNaturalResourceExtraction = 2;
        public static final int BroadCastingPrintingAndPublishing = 3;
        public static final int Brokers = 4;
        public static final int BuildingSupplyRetail = 5;
        public static final int BusinessServices = 6;
        public static final int Consulting = 7;
        public static final int ConsumerServices = 8;
        public static final int DesignDirectionAndCreativeManagment = 9;
        public static final int DistributorsDispatchersAndProcessors = 10;
        public static final int DoctorsOfficesAndClinics = 11;
        public static final int DurableManufacturing = 12;
        public static final int EatingAndDrinkingPlaces = 13;
        public static final int EntertainmentRetail = 14;
        public static final int EquipmentRentalAndLeasing = 15;
        public static final int Financial = 16;
        public static final int FoodAndTobaccoProcessing = 17;
        public static final int InboundCapitalIntensiveProcessing = 18;
        public static final int InboundRepairAndServices = 19;
        public static final int Insurance = 20;
        public static final int LegalServices = 21;
        public static final int NonDurableMerchandiseRetail = 22;
        public static final int OutboundConsumerService = 23;
        public static final int PetrochemicalExtractionAndDistribution = 24;
        public static final int ServiceRetail = 25;
        public static final int SIGAffiliations = 26;
        public static final int SocialServices = 27;
        public static final int SpecialOutboundTradeContractors = 28;
        public static final int SpecialtyRealty = 29;
        public static final int Transportation = 30;
        public static final int UtilityCreationAndDistribution = 31;
        public static final int VehicleRetail = 32;
        public static final int Wholesale = 33;
    }

    public static class LeadSourceCode {

        public static final int Advertisment = 1;
        public static final int EmployeReferal = 2;
        public static final int ExternalReferal = 3;
        public static final int Partner = 4;
        public static final int PublicRelations = 5;
        public static final int Seminar = 6;
        public static final int TradeShow = 7;
        public static final int Web = 8;
        public static final int WordOfMouth = 9;
        public static final int Other = 10;
    }

    public static final String OpenAndNew = "Open - New";
    public static final String OpenAndContacted = "Open - Contacted";
    public static final String QualifiedAndQualified = "Qualified - Qualified";
    public static final String DisqualifiedAndLost = "Disqualified - Lost";
    public static final String DisqualifiedAnCannotContact = "Disqualified - Cannot Contact";
    public static final String DisqualifiedAndNoLongerInterested = "Disqualified - No Longer Interested";
    public static final String DisqualifiedAndCanceled = "Disqualified - Canceled";

    public static final String[] LeadStatuses = new String[]{
        OpenAndNew,
        OpenAndContacted,
        QualifiedAndQualified,
        DisqualifiedAndLost,
        DisqualifiedAnCannotContact,
        DisqualifiedAndNoLongerInterested,
        DisqualifiedAndCanceled
    };

    public Lead(NodeList nodes) {
        super(nodes);
    }

    public Lead() {
        super();
    }

    public String[] getLeadStatuses() {
        return LeadStatuses;
    }

    @Override
    public void setAttribute(String key, String value, String logicalName) {

        switch (key) {
            case ID_COLUMN: {
                this.setId(value);
                break;
            }
            case SUBJECT_COLUMN: {
                this.setSubject(value);
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

            case LEADQUALITYCODE_COLUMN: {
                this.setLeadqualitycode(Integer.parseInt(value));
                break;
            }

            case LEADSOURCECODE_COLUMN: {
                this.setLeadsourcecode(Integer.parseInt(value));
                break;
            }

            case REVENUE_COLUMN: {
                this.setRevenue(new BigDecimal(value));
                break;
            }

            case DONOTPHONE_COLUMN: {
                this.setDonotphone(Boolean.parseBoolean(value));
                break;
            }

            case CREATEON_COLUMN: {
                try {
                    this.setCreatedon(DATE_FORMAT.parse(value));
                    break;
                } catch (ParseException ex) {
                    Logger.getLogger(Lead.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            case EMAIL_COLUMN: {
                this.setEmailaddress1(value);
                break;
            }

            case MODIFIEDON_COLUMN: {
                try {
                    this.setModifiedon(DATE_FORMAT.parse(value));
                    break;
                } catch (ParseException ex) {
                    Logger.getLogger(Lead.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            case DONOTSENDMM_COLUMN: {
                this.setDonotsendmm(Boolean.parseBoolean(value));
                break;
            }

            case DESCRIPTION_COLUMN: {
                this.setDescription(value);
                break;
            }

            case ADDRESS1_LINE1_COLUMN: {
                this.setAddress1_line1(value);
                break;
            }

            case ADDRESS1_LINE2_COLUMN: {
                this.setAddress1_line2(value);
                break;
            }

            case ADDRESS1_CITY_COLUMN: {
                this.setAddress1_city(value);
                break;
            }

            case ADDRESS1_COUNTRY_COLUMN: {
                this.setAddress1_country(value);
                break;
            }

            case ADDRESS1_LATITUDE_COLUMN: {
                this.setAddress1_latitude(Double.parseDouble(value));
                break;
            }

            case ADDRESS1_LONGITUDE_COLUMN: {
                this.setAddress1_longitude(Double.parseDouble(value));
                break;
            }

            case ADDRESS1_TELEPHONE_COLUMN: {
                this.setAddress1_telephone1(value);
                break;
            }
            case ADDRESS1_STATEORPROVINCE_COLUMN: {
                this.setAddress1_stateorprovince(value);
                break;
            }
            case ADDRESS1_POSTALCODE_COLUMN: {
                this.setAddress1_postalcode(value);
                break;
            }
            case PARENTACCOUNTID_COLUMN: {
                this.setParentaccountid(value);
                break;
            }
            case COMPANYNAME_COLUMN: {
                this.setCompanyname(value);
                break;
            }
            case CONFIRMINTEREST_COLUMN: {
                this.setConfirminterest(Boolean.parseBoolean(value));
                break;
            }
            case PARENTCONTACTID_COLUMN: {
                this.setParentcontactid(value);
                break;
            }
            case JOBTITLE_COLUMN: {
                this.setJobtitle(value);
                break;
            }
            case TELEPHONE1_COLUMN: {
                this.setTelephone1(value);
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
            case PREFEREDCONTACTMETHOD_COLUMN: {
                this.setPreferredcontactmethodcode(Integer.parseInt(value));
                break;
            }
            case TRANSACTIONCURRENCYID_COLUMN: {
                this.setTransactioncurrencyid(value);
                break;
            }
            case DONOTEMAIL_COLUMN: {
                this.setDonotemail(Boolean.parseBoolean(value));
                break;
            }
            case DONOTBULKEMAIL_COLUMN: {
                this.setDonotbulkemail(Boolean.parseBoolean(value));
                break;
            }
            case DONOTFAX_COLUMN: {
                this.setDonotfax(Boolean.parseBoolean(value));
                break;
            }
            case DONOTPOSTALEMAIL_COLUMN: {
                this.setDonotpostalmail(Boolean.parseBoolean(value));
                break;
            }
            case OWNERID_COLUMN: {
                this.setOwnerid(value);
                break;
            }
            case WEBSITEURL_COLUMN: {
                this.setWebsiteurl(value);
                break;
            }

            case INDUSCTRYCODE_COLUMN: {
                this.setIndustrycode(Integer.parseInt(value));
                break;
            }

            case NUMBEROFEMPLOYEES_COLUMN: {
                this.setNumberofemployees(Integer.parseInt(value));
                break;
            }

            case SIC_COLUMN: {
                this.setSic(value);
                break;
            }

            case CAMPAIGNID_COLUMN: {
                this.setCampaignid(value);
                break;
            }

            case PURCHASETIMEFRAME_COLUMN: {
                this.setPurchasetimeframe(Integer.parseInt(value));
                break;
            }

            case PURCHASEPROCESS_COLUMN: {
                this.setPurchaseprocess(Integer.parseInt(value));
                break;
            }

            case DECISIONMAKER_COLUMN: {
                this.setDecisionmaker(Boolean.parseBoolean(value));
                break;
            }

            case BUDGETAMOUNT_COLUMN: {
                this.setBudgetamount(new BigDecimal(value));
                break;
            }

        }
    }

    @Override
    public String[] getDefaultColumns() {
        return LeadColumns;
    }

    @Override
    protected ArrayList<XmlSerializable> getAttributeCollection() {
        ArrayList<XmlSerializable> parameters = new ArrayList<>();

        if (getSubject() != null && !getSubject().isEmpty()) {
            parameters.add(getSubject());
        }

        if (getFirstname() != null && !getFirstname().isEmpty()) {
            parameters.add(getFirstname());
        }

        if (getLastname() != null && !getLastname().isEmpty()) {
            parameters.add(getLastname());
        }

        if (getLeadqualitycode() != null) {
            parameters.add(getLeadqualitycode());
        }

        if (getLeadsourcecode() != null) {
            parameters.add(getLeadsourcecode());
        }

        if (getRevenue() != null) {
            parameters.add(getRevenue());
        }

        if (getNumberofemployees() != null) {
            parameters.add(getNumberofemployees());
        }

        if (isDonotemail() != null) {
            parameters.add(isDonotemail());
        }

        if (getOwnerid() != null) {
            parameters.add(getOwnerid());
        }

        if (getModifiedon() != null) {
            parameters.add(getModifiedon());
        }

        if (getCreatedon() != null) {
            parameters.add(getCreatedon());
        }
        if (getEmailaddress1() != null) {
            parameters.add(getEmailaddress1());
        }
        if (getDonotsendmm() != null) {
            parameters.add(getDonotsendmm());
        }
        if (getDescription() != null) {
            parameters.add(getDescription());
        }
        if (getAddress1_line1() != null && !getAddress1_line1().isEmpty()) {
            parameters.add(getAddress1_line1());
        }
        if (getAddress1_line2() != null && !getAddress1_line2().isEmpty()) {
            parameters.add(getAddress1_line2());
        }
        if (getAddress1_city() != null && !getAddress1_city().isEmpty()) {
            parameters.add(getAddress1_city());
        }

        if (getAddress1_country() != null && !getAddress1_country().isEmpty()) {
            parameters.add(getAddress1_country());
        }
        if (getAddress1_latitude() != null) {
            parameters.add(getAddress1_latitude());
        }
        if (getAddress1_longitude() != null) {
            parameters.add(getAddress1_longitude());
        }
        if (getAddress1_telephone1() != null && !getAddress1_telephone1().isEmpty()) {
            parameters.add(getAddress1_telephone1());
        }
        if (getAddress1_stateorprovince() != null && !getAddress1_stateorprovince().isEmpty()) {
            parameters.add(getAddress1_stateorprovince());
        }
        if (getAddress1_postalcode() != null && !getAddress1_postalcode().isEmpty()) {
            parameters.add(getAddress1_postalcode());
        }
        if (getParentaccountid() != null) {
            parameters.add(getParentaccountid());
        }
        if (getCompanyname() != null) {
            parameters.add(getCompanyname());
        }
        if (getConfirminterest() != null) {
            parameters.add(getConfirminterest());
        }
        if (getParentcontactid() != null) {
            parameters.add(getParentcontactid());
        }
        if (getJobtitle() != null) {
            parameters.add(getJobtitle());
        }
        if (getTelephone1() != null) {
            parameters.add(getTelephone1());
        }
        if (getMobilephone() != null) {
            parameters.add(getMobilephone());
        }
        if (getFax() != null) {
            parameters.add(getFax());
        }
        if (getPreferredcontactmethodcode() != null) {
            parameters.add(getPreferredcontactmethodcode());
        }
        if (isDonotbulkemail() != null) {
            parameters.add(isDonotbulkemail());
        }
        if (isDonotphone() != null) {
            parameters.add(isDonotphone());
        }
        if (isDonotfax() != null) {
            parameters.add(isDonotfax());
        }
        if (isDonotpostalmail() != null) {
            parameters.add(isDonotpostalmail());
        }
        if (getWebsiteurl() != null) {
            parameters.add(getWebsiteurl());
        }
        if (getIndustrycode() != null) {
            parameters.add(getIndustrycode());
        }

        if (getSic() != null) {
            parameters.add(getSic());
        }

        if (getCampaignid() != null) {
            parameters.add(getCampaignid());
        }

        if (getPurchasetimeframe() != null) {
            parameters.add(getPurchasetimeframe());
        }

        if (getBudgetamount() != null) {
            parameters.add(getBudgetamount());
        }

        if (getPurchaseprocess() != null) {
            parameters.add(getPurchaseprocess());
        }

        if (getDecisionmaker() != null) {
            parameters.add(getDecisionmaker());
        }

        return parameters;
    }

    @Override
    public String getLogicalName() {
        return Enums.EntityName.Lead;
    }

    @Override
    protected Entity getEntity() {
        return entity;
    }

    /**
     * @return the subject
     */
    public CrmString getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = new CrmString(SUBJECT_COLUMN, subject);
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(CrmString subject) {
        this.subject = subject;
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
     * @param firstname the firstname to set
     */
    public void setFirstname(CrmString firstname) {
        this.firstname = firstname;
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
     * @param lastname the lastname to set
     */
    public void setLastname(CrmString lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the leadqualitycode
     */
    public OptionSetValue getLeadqualitycode() {
        return leadqualitycode;
    }

    /**
     * @param leadqualitycode the leadqualitycode to set
     */
    public void setLeadqualitycode(int leadqualitycode) {
        OptionSetValue optionSet = new OptionSetValue(LEADQUALITYCODE_COLUMN, leadqualitycode);
        this.leadqualitycode = optionSet;
    }

    /**
     * @return the leadsourcecode
     */
    public OptionSetValue getLeadsourcecode() {
        return leadsourcecode;
    }

    /**
     * @param leadsourcecode the leadsourcecode to set
     */
    public void setLeadsourcecode(int leadsourcecode) {
        OptionSetValue optionSet = new OptionSetValue(LEADSOURCECODE_COLUMN, leadsourcecode);
        this.leadsourcecode = optionSet;
    }

    private void setLeadqualitycode(OptionSetValue optionSetValue) {
        this.leadqualitycode = optionSetValue;
    }

    private void setLeadsourcecode(OptionSetValue optionSetValue) {
        this.leadsourcecode = optionSetValue;
    }

    /**
     * @return the jobtitle
     */
    public CrmString getJobtitle() {
        return jobtitle;
    }

    /**
     * @param jobtitle the jobtitle to set
     */
    public void setJobtitle(String jobtitle) {
        this.jobtitle = new CrmString(JOBTITLE_COLUMN, jobtitle);
    }

    /**
     * @return the telephone1
     */
    public CrmString getTelephone1() {
        return telephone1;
    }

    /**
     * @param telephone1 the telephone1 to set
     */
    public void setTelephone1(String telephone1) {
        this.telephone1 = new CrmString(TELEPHONE1_COLUMN, telephone1);
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
     * @return the transactioncurrencyid
     */
    public EntityReference getTransactioncurrencyid() {
        return transactioncurrencyid;
    }

    /**
     * @param transactioncurrencyid the transactioncurrencyid to set
     */
    public void setTransactioncurrencyid(String transactioncurrencyid) {
        this.transactioncurrencyid = new EntityReference(EntityName.Currency, TRANSACTIONCURRENCYID_COLUMN, transactioncurrencyid);
    }

    /**
     * @return the ownerid
     */
    public EntityReference getOwnerid() {
        return ownerid;
    }

    /**
     * @param ownerid the ownerid to set
     */
    public void setOwnerid(String ownerid) {
        this.ownerid = new EntityReference("systemuser", OWNERID_COLUMN, ownerid);
    }

    /**
     * @return the donotemail
     */
    public CrmBoolean isDonotemail() {
        return donotemail;
    }

    /**
     * @param donotemail the donotemail to set
     */
    public void setDonotemail(boolean donotemail) {
        this.donotemail = new CrmBoolean(DONOTEMAIL_COLUMN, donotemail);
    }

    /**
     * @return the donotbulkemail
     */
    public CrmBoolean isDonotbulkemail() {
        return donotbulkemail;
    }

    /**
     * @param donotbulkemail the donotbulkemail to set
     */
    public void setDonotbulkemail(boolean donotbulkemail) {
        this.donotbulkemail = new CrmBoolean(DONOTBULKEMAIL_COLUMN, donotbulkemail);
    }

    /**
     * @return the donotphone
     */
    public CrmBoolean isDonotphone() {
        return donotphone;
    }

    /**
     * @param donotphone the donotphone to set
     */
    public void setDonotphone(boolean donotphone) {
        this.donotphone = new CrmBoolean(DONOTPHONE_COLUMN, donotphone);
    }

    /**
     * @return the donotfax
     */
    public CrmBoolean isDonotfax() {
        return donotfax;
    }

    /**
     * @param donotfax the donotfax to set
     */
    public void setDonotfax(boolean donotfax) {
        this.donotfax = new CrmBoolean(DONOTFAX_COLUMN, donotfax);
    }

    /**
     * @return the donotpostalmail
     */
    public CrmBoolean isDonotpostalmail() {
        return donotpostalmail;
    }

    /**
     * @param donotpostalmail the donotpostalmail to set
     */
    public void setDonotpostalmail(boolean donotpostalmail) {
        this.donotpostalmail = new CrmBoolean(DONOTPOSTALEMAIL_COLUMN, donotpostalmail);
    }

    /**
     * @return the preferredcontactmethodcode
     */
    public OptionSetValue getPreferredcontactmethodcode() {
        return preferredcontactmethodcode;
    }

    /**
     * @param preferredcontactmethodcode the preferredcontactmethodcode to set
     */
    public void setPreferredcontactmethodcode(OptionSetValue preferredcontactmethodcode) {
        this.preferredcontactmethodcode = preferredcontactmethodcode;
    }

    /**
     * @param preferredcontactmethodcode the preferredcontactmethodcode to set
     */
    public void setPreferredcontactmethodcode(int preferredcontactmethodcode) {
        OptionSetValue optionSet = new OptionSetValue(PREFEREDCONTACTMETHOD_COLUMN, preferredcontactmethodcode);
        this.preferredcontactmethodcode = optionSet;
    }

    public StatusAndStatusReason getStatusOpenAndNew() {
        return new StatusAndStatusReason(Enums.LeadState.Open, Enums.LeadStatus.New);
    }

    public StatusAndStatusReason getStatusOpenAndContacted() {
        return new StatusAndStatusReason(Enums.LeadState.Open, Enums.LeadStatus.Contacted);
    }

    public StatusAndStatusReason getStatusQualified() {
        return new StatusAndStatusReason(Enums.LeadState.Qualified, Enums.LeadStatus.Qualified);
    }

    public StatusAndStatusReason getStatusDisqualifiedAndLost() {
        return new StatusAndStatusReason(Enums.LeadState.Disqualified, Enums.LeadStatus.Lost);
    }

    public StatusAndStatusReason getStatusDisqualifiedAndCannotContact() {
        return new StatusAndStatusReason(Enums.LeadState.Disqualified, Enums.LeadStatus.CannotContact);
    }

    public StatusAndStatusReason getStatusDisqualifiedAndNoLongerInterested() {
        return new StatusAndStatusReason(Enums.LeadState.Disqualified, Enums.LeadStatus.NoLongerInterested);
    }

    public StatusAndStatusReason getStatusDisqualifiedAndCanceled() {
        return new StatusAndStatusReason(Enums.LeadState.Disqualified, Enums.LeadStatus.Canceled);
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
        this.createdon = new DateTime(CREATEON_COLUMN, createdon);
    }

    /**
     * @param createdon the createdon to set
     */
    public void setCreatedon(DateTime createdon) {
        this.createdon = createdon;
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
     * @return the websiteurl
     */
    public CrmString getWebsiteurl() {
        return websiteurl;
    }

    /**
     * @param websiteurl the websiteurl to set
     */
    public void setWebsiteurl(String websiteurl) {
        this.websiteurl = new CrmString(WEBSITEURL_COLUMN, websiteurl);
    }

    /**
     * @return the companyname
     */
    public CrmString getCompanyname() {
        return companyname;
    }

    /**
     * @param companyname the companyname to set
     */
    public void setCompanyname(String companyname) {
        this.companyname = new CrmString(COMPANYNAME_COLUMN, companyname);
    }

    /**
     * @return the sic
     */
    public CrmString getSic() {
        return sic;
    }

    /**
     * @param sic the sic to set
     */
    public void setSic(String sic) {
        this.sic = new CrmString(SIC_COLUMN, sic);
    }

    /**
     * @return the campaignid
     */
    public EntityReference getCampaignid() {
        return campaignid;
    }

    /**
     * @param campaignid the campaignid to set
     */
    public void setCampaignid(String campaignid) {
        this.parentaccountid = new EntityReference(EntityName.Campaign, CAMPAIGNID_COLUMN, campaignid);
    }

    /**
     * @return the parentcontactid
     */
    public EntityReference getParentcontactid() {
        return parentcontactid;
    }

    /**
     * @param parentcontactid the parentcontactid to set
     */
    public void setParentcontactid(String parentcontactid) {
        this.parentcontactid = new EntityReference(EntityName.Contact, PARENTCONTACTID_COLUMN, parentcontactid);
    }

    /**
     * @return the parentaccountid
     */
    public EntityReference getParentaccountid() {
        return parentaccountid;
    }

    /**
     * @param parentaccountid the parentaccountid to set
     */
    public void setParentaccountid(String parentaccountid) {
        this.parentaccountid = new EntityReference(EntityName.Account, PARENTACCOUNTID_COLUMN, parentaccountid);
    }

    /**
     * @return the industrycode
     */
    public OptionSetValue getIndustrycode() {
        return industrycode;
    }

    /**
     * @param industrycode the industrycode to set
     */
    public void setIndustrycode(OptionSetValue industrycode) {
        this.industrycode = industrycode;
    }

    /**
     * @param industrycode the industrycode to set
     */
    public void setIndustrycode(int industrycode) {
        this.industrycode = new OptionSetValue(INDUSCTRYCODE_COLUMN, industrycode);
    }

    /**
     * @return the revenue
     */
    public Money getRevenue() {
        return revenue;
    }

    /**
     * @param revenue the revenue to set
     */
    public void setRevenue(BigDecimal revenue) {
        this.revenue = new Money(REVENUE_COLUMN, revenue);
    }

    /**
     * @return the numberofemployees
     */
    public CrmInt getNumberofemployees() {
        return numberofemployees;
    }

    /**
     * @param numberofemployees the numberofemployees to set
     */
    public void setNumberofemployees(int numberofemployees) {
        this.numberofemployees = new CrmInt(NUMBEROFEMPLOYEES_COLUMN, numberofemployees);
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
        this.emailaddress1 = new CrmString(EMAIL_COLUMN, emailaddress1);
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
     * @return the address1_line1
     */
    public CrmString getAddress1_line1() {
        return address1_line1;
    }

    /**
     * @param address1_line1 the address1_line1 to set
     */
    public void setAddress1_line1(String value) {
        this.address1_line1 = new CrmString(ADDRESS1_LINE1_COLUMN, value);
    }

    /**
     * @return the address1_line2
     */
    public CrmString getAddress1_line2() {
        return address1_line2;
    }

    /**
     * @param address1_line2 the address1_line2 to set
     */
    public void setAddress1_line2(String value) {
        this.address1_line2 = new CrmString(ADDRESS1_LINE2_COLUMN, value);
    }

    /**
     * @return the address1_city
     */
    public CrmString getAddress1_city() {
        return address1_city;
    }

    /**
     * @param address1_city the address1_city to set
     */
    public void setAddress1_city(String address1_city) {
        this.address1_city = new CrmString(ADDRESS1_CITY_COLUMN, address1_city);
    }

    /**
     * @return the address1_country
     */
    public CrmString getAddress1_country() {
        return address1_country;
    }

    /**
     * @param address1_country the address1_country to set
     */
    public void setAddress1_country(String address1_country) {
        this.address1_country = new CrmString(ADDRESS1_COUNTRY_COLUMN, address1_country);
    }

    /**
     * @return the address1_telephone1
     */
    public CrmString getAddress1_telephone1() {
        return address1_telephone1;
    }

    /**
     * @param address1_telephone1 the address1_telephone1 to set
     */
    public void setAddress1_telephone1(String address1_telephone1) {
        this.address1_telephone1 = new CrmString(ADDRESS1_TELEPHONE_COLUMN, address1_telephone1);
    }

    /**
     * @return the address1_stateorprovince
     */
    public CrmString getAddress1_stateorprovince() {
        return address1_stateorprovince;
    }

    /**
     * @param address1_stateorprovince the address1_stateorprovince to set
     */
    public void setAddress1_stateorprovince(String address1_stateorprovince) {
        this.address1_stateorprovince = new CrmString(ADDRESS1_STATEORPROVINCE_COLUMN, address1_stateorprovince);
    }

    /**
     * @return the address1_postalcode
     */
    public CrmString getAddress1_postalcode() {
        return address1_postalcode;
    }

    /**
     * @param address1_postalcode the address1_postalcode to set
     */
    public void setAddress1_postalcode(String address1_postalcode) {
        this.address1_postalcode = new CrmString(ADDRESS1_POSTALCODE_COLUMN, address1_postalcode);
    }

    /**
     * @return the address1_latitude
     */
    public CrmDouble getAddress1_latitude() {
        return address1_latitude;
    }

    /**
     * @param address1_latitude the address1_latitude to set
     */
    public void setAddress1_latitude(Double address1_latitude) {
        this.address1_latitude = new CrmDouble(ADDRESS1_LATITUDE_COLUMN, address1_latitude);
    }

    /**
     * @return the address1_longitude
     */
    public CrmDouble getAddress1_longitude() {
        return address1_longitude;
    }

    /**
     * @param address1_longitude the address1_longitude to set
     */
    public void setAddress1_longitude(Double address1_longitude) {
        this.address1_longitude = new CrmDouble(ADDRESS1_LONGITUDE_COLUMN, address1_longitude);
    }

    /**
     * @return the confirminterest
     */
    public CrmBoolean getConfirminterest() {
        return confirminterest;
    }

    /**
     * @param confirminterest the confirminterest to set
     */
    public void setConfirminterest(boolean confirminterest) {
        this.confirminterest = new CrmBoolean(CONFIRMINTEREST_COLUMN, confirminterest);
    }

    /**
     * @return the purchasetimeframe
     */
    public OptionSetValue getPurchasetimeframe() {
        return purchasetimeframe;
    }

    /**
     * @param purchasetimeframe the purchasetimeframe to set
     */
    public void setPurchasetimeframe(int purchasetimeframe) {
        this.purchasetimeframe = new OptionSetValue(PURCHASETIMEFRAME_COLUMN, purchasetimeframe);
    }

    /**
     * @return the purchaseprocess
     */
    public OptionSetValue getPurchaseprocess() {
        return purchaseprocess;
    }

    /**
     * @param purchaseprocess the purchaseprocess to set
     */
    public void setPurchaseprocess(int purchaseprocess) {
        this.purchaseprocess = new OptionSetValue(PURCHASEPROCESS_COLUMN, purchaseprocess);
    }

    /**
     * @return the decisionmaker
     */
    public CrmBoolean getDecisionmaker() {
        return decisionmaker;
    }

    /**
     * @param decisionmaker the decisionmaker to set
     */
    public void setDecisionmaker(boolean decisionmaker) {
        this.decisionmaker = new CrmBoolean(DECISIONMAKER_COLUMN, decisionmaker);
    }

    /**
     * @return the budgetamount
     */
    public Money getBudgetamount() {
        return budgetamount;
    }

    /**
     * @param budgetamount the budgetamount to set
     */
    public void setBudgetamount(BigDecimal budgetamount) {
        this.budgetamount = new Money(BUDGETAMOUNT_COLUMN, budgetamount);
    }

}

package com.xrm.msdynamics.entities;

import com.xrm.msdynamics.Enums;
import com.xrm.msdynamics.Enums.EntityName;
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
public class Opportunity extends BaseEntity {

    private Entity entity;

    private ArrayList<OpportunityProduct> opportunityProducts;

    public void addOpportunityProduct(OpportunityProduct opportunityProduct) {
        int idx = opportunityProducts.indexOf(opportunityProduct);

        if (idx < 0) {
            opportunityProducts.add(opportunityProduct);
        }
    }

    public ArrayList<OpportunityProduct> getOpportunityProducts() {

        for (OpportunityProduct opportunityProduct : opportunityProducts) {
            opportunityProduct.setOpportunityId(getId());
        }

        return opportunityProducts;
    }

    public boolean removeOpportunityProduct(OpportunityProduct opportunityProduct) {
        int idx = opportunityProducts.indexOf(opportunityProduct);

        if (idx >= 0) {
            opportunityProducts.remove(opportunityProduct);

            return true;
        }

        return false;
    }

    private DateTime createdon;
    private DateTime modifiedon;
    private DateTime estimatedclosedate;

    private CrmString name;
    private CrmString description;
    private CrmString currentsituation;
    private CrmString customerneed;
    private CrmString proposedsolution;

    private OptionSetValue purchasetimeframe;
    private OptionSetValue purchaseprocess;

    private EntityReference transactioncurrencyid;
    private EntityReference customerid;
    private EntityReference parentcontactid;
    private EntityReference parentaccountid;
    private EntityReference ownerid;

    private Money totallineitemdiscountamount;
    private Money totallineitemamount;
    private Money totalamount;
    private Money totaltax;
    private Money budgetamount;

    private static final String ID_COLUMN = "opportunityid";
    private static final String CREATEDON_COLUMN = "createdon";
    private static final String TRANSACTIONCURRENCYID_COLUMN = "transactioncurrencyid";
    private static final String NAME_COLUMN = "name";
    private static final String DESCRIPTION_COLUMN = "description";
    private static final String TOTALLINEITEMDISCOUNTAMOUNT_COLUMN = "totallineitemdiscountamount";
    private static final String TOTALLINEITEMAMOUNT_COLUMN = "totallineitemamount";
    private static final String TOTALAMOUN_COLUMN = "totalamount";
    private static final String TOTALTAX_COLUMN = "totaltax";
    private static final String MODIFIED_COLUMN = "modifiedon";
    private static final String CUSTOMERID_COLUMN = "customerid";
    private static final String PARENTCONTACTID = "parentcontactid";
    private static final String PARENTACCOUNTID_COLUMN = "parentaccountid";
    private static final String PURCHASETIMEFRAME_COLUMN = "purchasetimeframe";
    private static final String BUDGETAMOUNT_COLUMN = "budgetamount";
    private static final String PURCHASEPROCESS_COLUMN = "purchaseprocess";
    private static final String CURRENTSITUATION_COLUMN = "currentsituation";
    private static final String CUSTOMERNEED_COLUMN = "customerneed";
    private static final String PROPORSEDSOLUTION_COLUMN = "proposedsolution";
    private static final String ESTIMATEDCLOSEDATE_COLUMN = "estimatedclosedate";
    private static final String OWENERID_COLUMN = "ownerid";

    public static final String[] OpportunityColumns = new String[]{
        ID_COLUMN,
        CREATEDON_COLUMN,
        TRANSACTIONCURRENCYID_COLUMN,
        NAME_COLUMN,
        DESCRIPTION_COLUMN,
        TOTALLINEITEMDISCOUNTAMOUNT_COLUMN,
        TOTALLINEITEMAMOUNT_COLUMN,
        TOTALAMOUN_COLUMN,
        TOTALTAX_COLUMN,
        MODIFIED_COLUMN,
        CUSTOMERID_COLUMN,
        PARENTCONTACTID,
        PARENTACCOUNTID_COLUMN,
        PURCHASETIMEFRAME_COLUMN,
        BUDGETAMOUNT_COLUMN,
        PURCHASEPROCESS_COLUMN,
        CURRENTSITUATION_COLUMN,
        CUSTOMERNEED_COLUMN,
        PROPORSEDSOLUTION_COLUMN,
        ESTIMATEDCLOSEDATE_COLUMN,
        OWENERID_COLUMN
    };

    public Opportunity() {
        this.opportunityProducts = new ArrayList<>();

    }

    public Opportunity(NodeList documentAttributes) {
        super(documentAttributes);
        this.opportunityProducts = new ArrayList<>();
    }

    @Override
    public String[] getDefaultColumns() {
        return OpportunityColumns;
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
                    break;
                } catch (ParseException ex) {
                    Logger.getLogger(Opportunity.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            case DESCRIPTION_COLUMN: {
                this.setDescription(value);
                break;
            }

            case NAME_COLUMN: {
                this.setName(value);
                break;
            }

            case TOTALLINEITEMDISCOUNTAMOUNT_COLUMN: {
                this.setTotallineitemdiscountamount(new BigDecimal(value));
                break;
            }

            case TOTALLINEITEMAMOUNT_COLUMN: {
                this.setTotallineitemamount(new BigDecimal(value));
                break;
            }
            case TOTALAMOUN_COLUMN: {
                this.setTotalamount(new BigDecimal(value));
                break;
            }
            case TOTALTAX_COLUMN: {
                this.setTotaltax(new BigDecimal(value));
                break;
            }
            case MODIFIED_COLUMN: {
                try {
                    this.setModifiedon(DATE_FORMAT.parse(value));
                    break;
                } catch (ParseException ex) {
                    Logger.getLogger(Opportunity.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            case CUSTOMERID_COLUMN: {
//                this.setCustomerid(value);
//                break;
//            }
            case PARENTCONTACTID: {
                this.setParentcontactid(value);
                break;
            }

            case PARENTACCOUNTID_COLUMN: {
                this.setParentaccountid(value);
                break;
            }
            case PURCHASETIMEFRAME_COLUMN: {
                this.setPurchasetimeframe(Integer.parseInt(value));
                break;
            }
            case BUDGETAMOUNT_COLUMN: {
                this.setBudgetamount(new BigDecimal(value));
                break;
            }
            case PURCHASEPROCESS_COLUMN: {
                this.setPurchaseprocess(Integer.parseInt(value));
                break;
            }
            case CURRENTSITUATION_COLUMN: {
                this.setCurrentsituation(value);
                break;
            }
            case CUSTOMERNEED_COLUMN: {
                this.setCustomerneed(value);
                break;
            }
            case PROPORSEDSOLUTION_COLUMN: {
                this.setProposedsolution(value);
                break;
            }
            case ESTIMATEDCLOSEDATE_COLUMN: {
                try {
                    this.setEstimatedclosedate(DATE_FORMAT.parse(value));
                    break;
                } catch (ParseException ex) {
                    Logger.getLogger(Opportunity.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case OWENERID_COLUMN: {
                this.setOwnerid(value);
                break;
            }

        }
    }

    @Override
    protected ArrayList<XmlSerializable> getAttributeCollection() {
        ArrayList<XmlSerializable> options = new ArrayList<>();

        if (getCreatedon() != null) {
            options.add(getCreatedon());
        }

        if (getModifiedon() != null) {
            options.add(getModifiedon());
        }

        if (getEstimatedclosedate() != null) {
            options.add(getEstimatedclosedate());
        }

        if (getName() != null) {
            options.add(getName());
        }

        if (getDescription() != null) {
            options.add(getDescription());
        }

        if (getCurrentsituation() != null) {
            options.add(getCurrentsituation());
        }

        if (getCustomerneed() != null) {
            options.add(getCustomerneed());
        }

        if (getProposedsolution() != null) {
            options.add(getProposedsolution());
        }

        if (getPurchasetimeframe() != null) {
            options.add(getPurchasetimeframe());
        }

        if (getPurchaseprocess() != null) {
            options.add(getPurchaseprocess());
        }

        if (getTransactioncurrencyid() != null) {
            options.add(getTransactioncurrencyid());
        }
        if (getCustomerid() != null) {
            options.add(getCustomerid());
        }

        if (getParentcontactid() != null) {
            options.add(getParentcontactid());
        }

        if (getParentaccountid() != null) {
            options.add(getParentaccountid());
        }

        if (getOwnerid() != null) {
            options.add(getOwnerid());
        }
        if (getTotallineitemdiscountamount() != null) {
            options.add(getTotallineitemdiscountamount());
        }
        if (getTotallineitemamount() != null) {
            options.add(getTotallineitemamount());
        }
        if (getTotalamount() != null) {
            options.add(getTotalamount());
        }
        if (getTotaltax() != null) {
            options.add(getTotaltax());
        }
        if (getBudgetamount() != null) {
            options.add(getBudgetamount());
        }

        return options;
    }

    @Override
    protected Entity getEntity() {

        if (entity == null) {
            entity = new Entity(EntityName.Opportunity);
        }

        return entity;
    }

    @Override
    public String getLogicalName() {
        return Enums.EntityName.Opportunity;
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
        this.modifiedon = new DateTime(MODIFIED_COLUMN, modifiedon);
    }

    /**
     * @return the estimatedclosedate
     */
    public DateTime getEstimatedclosedate() {
        return estimatedclosedate;
    }

    /**
     * @param estimatedclosedate the estimatedclosedate to set
     */
    public void setEstimatedclosedate(Date estimatedclosedate) {
        this.estimatedclosedate = new DateTime(ESTIMATEDCLOSEDATE_COLUMN, estimatedclosedate);
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
     * @return the currentsituation
     */
    public CrmString getCurrentsituation() {
        return currentsituation;
    }

    /**
     * @param currentsituation the currentsituation to set
     */
    public void setCurrentsituation(String currentsituation) {
        this.currentsituation = new CrmString(CURRENTSITUATION_COLUMN, currentsituation);
    }

    /**
     * @return the customerneed
     */
    public CrmString getCustomerneed() {
        return customerneed;
    }

    /**
     * @param customerneed the customerneed to set
     */
    public void setCustomerneed(String customerneed) {
        this.customerneed = new CrmString(CUSTOMERNEED_COLUMN, customerneed);
    }

    /**
     * @return the proposedsolution
     */
    public CrmString getProposedsolution() {
        return proposedsolution;
    }

    /**
     * @param proposedsolution the proposedsolution to set
     */
    public void setProposedsolution(String proposedsolution) {
        this.proposedsolution = new CrmString(PROPORSEDSOLUTION_COLUMN, proposedsolution);
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
     * @return the customerid
     */
    public EntityReference getCustomerid() {
        return customerid;
    }

    /**
     * @param customerid the customerid to set
     */
    public void setCustomerid(EntityReference customerid) {
        this.customerid = customerid;
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
        this.parentcontactid = new EntityReference(EntityName.Contact, PARENTCONTACTID, parentcontactid);
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
     * @return the ownerid
     */
    public EntityReference getOwnerid() {
        return ownerid;
    }

    /**
     * @param ownerid the ownerid to set
     */
    public void setOwnerid(String ownerid) {
        this.ownerid = new EntityReference(EntityName.SystemUser, OWENERID_COLUMN, ownerid);
    }

    /**
     * @return the totallineitemdiscountamount
     */
    public Money getTotallineitemdiscountamount() {
        return totallineitemdiscountamount;
    }

    /**
     * @param totallineitemdiscountamount the totallineitemdiscountamount to set
     */
    public void setTotallineitemdiscountamount(BigDecimal totallineitemdiscountamount) {
        this.totallineitemdiscountamount = new Money(TOTALLINEITEMDISCOUNTAMOUNT_COLUMN, totallineitemdiscountamount);
    }

    /**
     * @return the totallineitemamount
     */
    public Money getTotallineitemamount() {
        return totallineitemamount;
    }

    /**
     * @param totallineitemamount the totallineitemamount to set
     */
    public void setTotallineitemamount(BigDecimal totallineitemamount) {
        this.totallineitemamount = new Money(TOTALAMOUN_COLUMN, totallineitemamount);
    }

    /**
     * @return the totalamount
     */
    public Money getTotalamount() {
        return totalamount;
    }

    /**
     * @param totalamount the totalamount to set
     */
    public void setTotalamount(BigDecimal totalamount) {
        this.totalamount = new Money(TOTALAMOUN_COLUMN, totalamount);
    }

    /**
     * @return the totaltax
     */
    public Money getTotaltax() {
        return totaltax;
    }

    /**
     * @param totaltax the totaltax to set
     */
    public void setTotaltax(BigDecimal totaltax) {
        this.totaltax = new Money(TOTALTAX_COLUMN, totaltax);
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

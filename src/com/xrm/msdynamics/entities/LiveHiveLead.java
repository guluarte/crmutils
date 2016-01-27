/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.entities;

import com.xrm.msdynamics.crmtypes.CrmDouble;
import com.xrm.msdynamics.crmtypes.CrmInt;
import com.xrm.msdynamics.crmtypes.CrmString;
import com.xrm.msdynamics.crmtypes.DateTime;
import com.xrm.msdynamics.crmtypes.XmlSerializable;
import java.text.ParseException;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.NodeList;

/**
 *
 * @author rodolfo@xrm.com
 */
public class LiveHiveLead extends Lead {

    private CrmInt xrmLiveHiveChangeInMetric;
    private CrmInt xrmLiveHiveLastView;
    private CrmInt xrmLiveHiveClosingMetric;
    private CrmInt xrmLiveHiveLastShare;
    private CrmInt xrmLiveHiveMetric;

    private CrmString xrmLiveHiveState;

    private CrmString engagementDisplayOnly;
    private CrmString recentChangeDisplayOnly;
    private CrmString xrmLeadSource;

    private CrmDouble xrmLiveHiveClosingScore;
    private CrmDouble xrmLiveHiveScore;
    private CrmDouble xrmLiveHiveChangeInScore;

    private DateTime xrmLiveHiveLastViewOn;
    private DateTime xrmLiveHiveLastShareOn;

    //Custom fields
    public static final String XRM_LIVEHIVE_CHANGE_IN_METRIC = "xrm_livehivechangeinmetric";
    public static final String XRM_LIVEHIVE_LAST_VIEW = "xrm_livehivelastview";
    public static final String XRM_LIVEHIVE_CLOSINGMETRIC = "xrm_livehiveclosingmetric";
    public static final String XRM_LIVEHIVE_LASTSHARE = "xrm_livehivelastshare";
    public static final String XRM_LIVEHIVE_METRIC = "xrm_livehivemetric";
    public static final String XRM_LIVEHIVE_STATE = "xrm_livehivestate";
    public static final String XRM_LIVEHIVE_CLOSING_SCORE = "xrm_livehiveclosingscore";
    public static final String XRM_LIVEHIVE_SCORE = "xrm_livehivescore";
    public static final String XRM_LIVEHIVE_CHANGE_IN_SCORE = "xrm_livehivechangeinscore";
    public static final String XRM_LIVEHIVE_LAST_VIEW_ON = "xrm_livehivelastviewon";
    public static final String XRM_LIVEHIVE_LAST_SHARE_ON = "xrm_livehivelastshareon";

    public static final String ENGAGEMENT_DISPLAY = "xrm_engagementdisplayonly";
    public static final String RECENT_CHANGE_DISPLAY = "xrm_recentchangedisplayonly";
    public static final String XRM_LEAD_SOURCE = "xrm_leadsource";

    public static String[] LiveHiveLeadColumns = new String[]{
        ID_COLUMN,
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
        DECISIONMAKER_COLUMN,
        XRM_LIVEHIVE_CHANGE_IN_METRIC,
        XRM_LIVEHIVE_LAST_VIEW,
        XRM_LIVEHIVE_CLOSINGMETRIC,
        XRM_LIVEHIVE_LASTSHARE,
        XRM_LIVEHIVE_METRIC,
        XRM_LIVEHIVE_STATE,
        XRM_LIVEHIVE_CLOSING_SCORE,
        XRM_LIVEHIVE_SCORE,
        XRM_LIVEHIVE_CHANGE_IN_SCORE,
        XRM_LIVEHIVE_LAST_VIEW_ON,
        XRM_LIVEHIVE_LAST_SHARE_ON,
        ENGAGEMENT_DISPLAY,
        RECENT_CHANGE_DISPLAY,
        XRM_LEAD_SOURCE
    };

    public LiveHiveLead(NodeList nodes) {
        super(nodes);
    }

    public LiveHiveLead() {
        super();
    }

    @Override
    public String[] getDefaultColumns() {
        return LiveHiveLeadColumns;
    }

    @Override
    public void setAttribute(String key, String value, String logicalName) {

        super.setAttribute(key, value, logicalName);

        switch (key) {

            case XRM_LIVEHIVE_CHANGE_IN_METRIC: {

                setXrmLiveHiveChangeInMetric(Integer.parseInt(value));
                break;
            }

            case XRM_LIVEHIVE_CLOSINGMETRIC: {
                setXrmLiveHiveClosingMetric(Integer.parseInt(value));
                break;
            }

            case XRM_LIVEHIVE_LASTSHARE: {
                setXrmLiveHiveLastShare(Integer.parseInt(value));
                break;
            }

            case XRM_LIVEHIVE_METRIC: {
                setXrmLiveHiveMetric(Integer.parseInt(value));
                break;
            }

            case XRM_LIVEHIVE_STATE: {
                setXrmLiveHiveState(value);
                break;
            }

            case ENGAGEMENT_DISPLAY: {
                setEngagementDisplayOnly(value);
                break;
            }

            case RECENT_CHANGE_DISPLAY: {
                setRecentChangeDisplayOnly(value);
                break;
            }

            case XRM_LIVEHIVE_CLOSING_SCORE: {
                setXrmLiveHiveClosingScore(Double.parseDouble(value));
                break;
            }

            case XRM_LIVEHIVE_SCORE: {
                setXrmLiveHiveScore(Double.parseDouble(value));
                break;
            }

            case XRM_LIVEHIVE_CHANGE_IN_SCORE: {
                setXrmLiveHiveChangeInScore(Double.parseDouble(value));
                break;
            }

            case XRM_LIVEHIVE_LAST_VIEW_ON: {
                try {
                    setXrmLiveHiveLastViewOn(DATE_FORMAT.parse(value));
                    break;
                } catch (ParseException ex) {
                    Logger.getLogger(LiveHiveLead.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }

            case XRM_LIVEHIVE_LAST_SHARE_ON: {
                try {
                    setXrmLiveHiveLastShareOn(DATE_FORMAT.parse(value));
                    break;
                } catch (ParseException ex) {
                    Logger.getLogger(LiveHiveLead.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }

            case XRM_LIVEHIVE_LAST_VIEW: {
                setXrmLiveHiveLastView(Integer.parseInt(value));
                break;
            }

            case XRM_LEAD_SOURCE: {
                setXrmLeadSource(value);
                break;
            }

        }

    }

    @Override
    protected ArrayList<XmlSerializable> getAttributeCollection() {

        ArrayList<XmlSerializable> parameters = super.getAttributeCollection();

        if (getXrmLiveHiveChangeInMetric() != null) {
            parameters.add(getXrmLiveHiveChangeInMetric());
        }

        if (getXrmLiveHiveClosingMetric() != null) {
            parameters.add(getXrmLiveHiveClosingMetric());
        }

        if (getXrmLiveHiveLastShare() != null) {
            parameters.add(getXrmLiveHiveLastShare());
        }

        if (getXrmLiveHiveMetric() != null) {
            parameters.add(getXrmLiveHiveMetric());
        }

        if (getXrmLiveHiveState() != null) {
            parameters.add(getXrmLiveHiveState());
        }

        if (getXrmLiveHiveClosingScore() != null) {
            parameters.add(getXrmLiveHiveClosingScore());
        }

        if (getXrmLiveHiveScore() != null) {
            parameters.add(getXrmLiveHiveScore());
        }

        if (getXrmLiveHiveChangeInScore() != null) {
            parameters.add(getXrmLiveHiveChangeInScore());
        }

        if (getXrmLiveHiveLastViewOn() != null) {
            parameters.add(getXrmLiveHiveLastViewOn());
        }

        if (getXrmLiveHiveLastShareOn() != null) {
            parameters.add(getXrmLiveHiveLastShareOn());
        }

        if (getXrmLiveHiveLastView() != null) {
            parameters.add(getXrmLiveHiveLastView());
        }

        if (getRecentChangeDisplayOnly() != null) {
            parameters.add(getRecentChangeDisplayOnly());
        }

        if (getEngagementDisplayOnly() != null) {
            parameters.add(getEngagementDisplayOnly());
        }

        if (getXrmLeadSource() != null) {
            parameters.add(getXrmLeadSource());
        }

        return parameters;
    }

    /**
     * @return the xrmLiveHiveChangeInMetric
     */
    public CrmInt getXrmLiveHiveChangeInMetric() {
        return xrmLiveHiveChangeInMetric;
    }

    /**
     * @param xrmLiveHiveChangeInMetric the xrmLiveHiveChangeInMetric to set
     */
    public void setXrmLiveHiveChangeInMetric(int xrmLiveHiveChangeInMetric) {
        this.xrmLiveHiveChangeInMetric = new CrmInt(XRM_LIVEHIVE_CHANGE_IN_METRIC, xrmLiveHiveChangeInMetric);
    }

    /**
     * @return the xrmLiveHiveClosingMetric
     */
    public CrmInt getXrmLiveHiveClosingMetric() {
        return xrmLiveHiveClosingMetric;
    }

    /**
     * @param xrmLiveHiveClosingMetric the xrmLiveHiveClosingMetric to set
     */
    public void setXrmLiveHiveClosingMetric(int xrmLiveHiveClosingMetric) {
        this.xrmLiveHiveClosingMetric = new CrmInt(XRM_LIVEHIVE_CLOSINGMETRIC, xrmLiveHiveClosingMetric);
    }

    /**
     * @return the xrmLiveHiveLastShare
     */
    public CrmInt getXrmLiveHiveLastShare() {
        return xrmLiveHiveLastShare;
    }

    /**
     * @param xrmLiveHiveLastShare the xrmLiveHiveLastShare to set
     */
    public void setXrmLiveHiveLastShare(int xrmLiveHiveLastShare) {
        this.xrmLiveHiveLastShare = new CrmInt(XRM_LIVEHIVE_LASTSHARE, xrmLiveHiveLastShare);
    }

    /**
     * @return the xrmLiveHiveMetric
     */
    public CrmInt getXrmLiveHiveMetric() {
        return xrmLiveHiveMetric;
    }

    /**
     * @param xrmLiveHiveMetric the xrmLiveHiveMetric to set
     */
    public void setXrmLiveHiveMetric(int xrmLiveHiveMetric) {
        this.xrmLiveHiveMetric = new CrmInt(XRM_LIVEHIVE_METRIC, xrmLiveHiveMetric);
    }

    /**
     * @return the xrmLiveHiveState
     */
    public CrmString getXrmLiveHiveState() {
        return xrmLiveHiveState;
    }

    /**
     * @param xrmLiveHiveState the xrmLiveHiveState to set
     */
    public void setXrmLiveHiveState(String xrmLiveHiveState) {
        this.xrmLiveHiveState = new CrmString(XRM_LIVEHIVE_STATE, xrmLiveHiveState);
    }

    /**
     * @return the xrmLiveHiveClosingScore
     */
    public CrmDouble getXrmLiveHiveClosingScore() {
        return xrmLiveHiveClosingScore;
    }

    /**
     * @param xrmLiveHiveClosingScore the xrmLiveHiveClosingScore to set
     */
    public void setXrmLiveHiveClosingScore(Double xrmLiveHiveClosingScore) {
        this.xrmLiveHiveClosingScore = new CrmDouble(XRM_LIVEHIVE_CLOSING_SCORE, xrmLiveHiveClosingScore);
    }

    /**
     * @return the xrmLiveHiveScore
     */
    public CrmDouble getXrmLiveHiveScore() {
        return xrmLiveHiveScore;
    }

    /**
     * @param xrmLiveHiveScore the xrmLiveHiveScore to set
     */
    public void setXrmLiveHiveScore(Double xrmLiveHiveScore) {
        this.xrmLiveHiveScore = new CrmDouble(XRM_LIVEHIVE_SCORE, xrmLiveHiveScore);
    }

    /**
     * @return the xrmLiveHiveChangeInScore
     */
    public CrmDouble getXrmLiveHiveChangeInScore() {
        return xrmLiveHiveChangeInScore;
    }

    /**
     * @param xrmLiveHiveChangeInScore the xrmLiveHiveChangeInScore to set
     */
    public void setXrmLiveHiveChangeInScore(Double xrmLiveHiveChangeInScore) {
        this.xrmLiveHiveChangeInScore = new CrmDouble(XRM_LIVEHIVE_CHANGE_IN_SCORE, xrmLiveHiveChangeInScore);
    }

    /**
     * @return the xrmLiveHiveLastViewOn
     */
    public DateTime getXrmLiveHiveLastViewOn() {
        return xrmLiveHiveLastViewOn;
    }

    /**
     * @param xrmLiveHiveLastViewOn the xrmLiveHiveLastViewOn to set
     */
    public void setXrmLiveHiveLastViewOn(Date xrmLiveHiveLastViewOn) {
        this.xrmLiveHiveLastViewOn = new DateTime(XRM_LIVEHIVE_LAST_VIEW_ON, xrmLiveHiveLastViewOn);
    }

    /**
     * @return the xrmLiveHiveLastShareOn
     */
    public DateTime getXrmLiveHiveLastShareOn() {
        return xrmLiveHiveLastShareOn;
    }

    /**
     * @param xrmLiveHiveLastShareOn the xrmLiveHiveLastShareOn to set
     */
    public void setXrmLiveHiveLastShareOn(Date xrmLiveHiveLastShareOn) {
        this.xrmLiveHiveLastShareOn = new DateTime(XRM_LIVEHIVE_LAST_SHARE_ON, xrmLiveHiveLastShareOn);
    }

    /**
     * @return the xrmLiveHiveLastView
     */
    public CrmInt getXrmLiveHiveLastView() {
        return xrmLiveHiveLastView;
    }

    /**
     * @param xrmLiveHiveLastView the xrmLiveHiveLastView to set
     */
    public void setXrmLiveHiveLastView(int xrmLiveHiveLastView) {
        this.xrmLiveHiveLastView = new CrmInt(XRM_LIVEHIVE_LAST_VIEW, xrmLiveHiveLastView);
    }

    /**
     * @return the engagementDisplayOnly
     */
    public CrmString getEngagementDisplayOnly() {
        return engagementDisplayOnly;
    }

    /**
     * @param engagementDisplayOnly the engagementDisplayOnly to set
     */
    public void setEngagementDisplayOnly(String engagementDisplayOnly) {
        this.engagementDisplayOnly = new CrmString(ENGAGEMENT_DISPLAY, engagementDisplayOnly);
    }

    /**
     * @return the recentChangeDisplayOnly
     */
    public CrmString getRecentChangeDisplayOnly() {
        return recentChangeDisplayOnly;
    }

    /**
     * @param recentChangeDisplayOnly the recentChangeDisplayOnly to set
     */
    public void setRecentChangeDisplayOnly(String recentChangeDisplayOnly) {
        this.recentChangeDisplayOnly = new CrmString(RECENT_CHANGE_DISPLAY, recentChangeDisplayOnly);
    }

    /**
     * @return the xrmLeadSource
     */
    public CrmString getXrmLeadSource() {
        return xrmLeadSource;
    }

    /**
     * @param xrmLeadSource the xrmLeadSource to set
     */
    public void setXrmLeadSource(String xrmLeadSource) {
        this.xrmLeadSource = new CrmString(XRM_LEAD_SOURCE, xrmLeadSource);
    }
}

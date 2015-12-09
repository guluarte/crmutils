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
public class LiveHiveAction extends BaseEntity {

    Entity entity = new Entity(EntityName.LiveHiveAction);

    private CrmString name;
    private CrmString listOfRecipientEmailAddresses;
    private CrmString emailBody;
    private CrmString emailSubject;
    private CrmString emailSender;
    private CrmString location;
    private CrmString attachmentList;

    private DateTime actionDate;

    private OptionSetValue actionType;

    private CrmInt attachmentPageViews;

    private EntityReference leadId;
    private EntityReference contactId;
    private EntityReference opportunityId;

    public static final String ID = "xrm_livehiveactionid";
    public static final String NAME = "xrm_name";
    public static final String LIST_OF_RECIPIENT_EMAIL_ADDRESSES = "xrm_listofrecipientemailaddresses";
    public static final String EMAIL_BODY = "xrm_emailbody";
    public static final String EMAIL_SUBJECT = "xrm_emailsubject";
    public static final String EMAIL_SENDER = "xrm_emailsender";
    public static final String ACTION_TYPE = "xrm_actiontype";
    public static final String ACTION_DATE = "xrm_actiondate";
    public static final String LOCATION = "xrm_location";
    public static final String LEAD_ID = "xrm_leadid";
    public static final String OPPORTUNITY_ID = "xrm_opportunityid";
    public static final String CONTACT_ID = "xrm_contactid";
    public static final String ATTACHMENT_PAGE_VIEWS = "xrm_attachmentpageviews";
    public static final String ATTACHMENTS_LIST = "xrm_attachmentlist";

    public static String[] DefaultColumns = new String[]{
        NAME,
        LIST_OF_RECIPIENT_EMAIL_ADDRESSES,
        EMAIL_BODY,
        EMAIL_SUBJECT,
        EMAIL_SENDER,
        ACTION_TYPE,
        ACTION_DATE,
        LOCATION,
        LEAD_ID,
        OPPORTUNITY_ID,
        CONTACT_ID,
        ATTACHMENT_PAGE_VIEWS,
        ATTACHMENTS_LIST

    };
    
    private static final int RECIPIENT_MAX_LENGTH = 100;
    private static final int BODY_MAX_LENGTH = 7000;
    private static final int SUBJECT_MAX_LENGTH = 50;

    public class ActionType {

        public static final String NAME = "xrm_emailactiontype";
        public static final int Email = 163650000;
        public static final int Link = 163650001;
        public static final int Open = 163650002;
        public static final int Reply = 163650003;
        public static final int Share = 163650004;

    }

    public LiveHiveAction(NodeList nodes) {
        super(nodes);
    }

    public LiveHiveAction() {
        super();
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
            case LIST_OF_RECIPIENT_EMAIL_ADDRESSES: {
                setListOfRecipientEmailAddresses(value);
                break;
            }
            case EMAIL_BODY: {
                setEmailBody(value);
                break;
            }
            case EMAIL_SUBJECT: {
                setEmailSubject(value);
                break;
            }
            case EMAIL_SENDER: {
                setEmailSender(value);
                break;
            }
            case ACTION_TYPE: {
                setActionType(Integer.parseInt(value));
                break;
            }
            case ACTION_DATE: {
                try {
                    setActionDate(DATE_FORMAT.parse(value));
                } catch (ParseException ex) {
                    Logger.getLogger(LiveHiveAction.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case LOCATION: {
                setLocation(value);
                break;
            }
            case LEAD_ID: {
                setLeadId(value);
                break;
            }
            case OPPORTUNITY_ID: {
                setOpportunityId(value);
                break;
            }
            case CONTACT_ID: {
                setContactId(value);
                break;
            }
            case ATTACHMENT_PAGE_VIEWS: {
                setAttachmentPageViews(Integer.parseInt(value));
                break;
            }

            case ATTACHMENTS_LIST: {
                setAttachmentList(value);
                break;
            }
        }
    }

    @Override
    public String[] getDefaultColumns() {
        return DefaultColumns;
    }

    @Override
    protected ArrayList<XmlSerializable> getAttributeCollection() {

        ArrayList<XmlSerializable> parameters = new ArrayList<>();

        if (getName() != null && !getName().isEmpty()) {
            parameters.add(getName());
        }

        if (getListOfRecipientEmailAddresses() != null && !getListOfRecipientEmailAddresses().isEmpty()) {
            parameters.add(getListOfRecipientEmailAddresses());
        }

        if (getEmailBody() != null && !getEmailBody().isEmpty()) {
            parameters.add(getEmailBody());
        }

        if (getEmailSubject() != null && !getEmailSubject().isEmpty()) {
            parameters.add(getEmailSubject());
        }

        if (getEmailSender() != null && !getEmailSender().isEmpty()) {
            parameters.add(getEmailSender());
        }

        if (getLocation() != null && !getLocation().isEmpty()) {
            parameters.add(getLocation());
        }

        if (getActionDate() != null) {
            parameters.add(getActionDate());
        }

        if (getActionType() != null) {
            parameters.add(getActionType());
        }

        if (getAttachmentPageViews() != null) {
            parameters.add(getAttachmentPageViews());
        }

        if (getLeadId() != null) {
            parameters.add(getLeadId());
        }

        if (getContactId() != null) {
            parameters.add(getContactId());
        }

        if (getOpportunityId() != null) {
            parameters.add(getOpportunityId());
        }

        if (getAttachmentList() != null) {
            parameters.add(getAttachmentList());
        }

        return parameters;
    }

    @Override
    protected Entity getEntity() {
        return entity;
    }

    @Override
    public String getLogicalName() {
        return EntityName.LiveHiveAction;
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
     * @return the listOfRecipientEmailAddresses
     */
    public CrmString getListOfRecipientEmailAddresses() {
        return listOfRecipientEmailAddresses;
    }

    /**
     * Comma separated list of recipient email addresses. If the string length
     * is >100, truncate and append "..." at the end
     *
     * @param listOfRecipientEmailAddresses the listOfRecipientEmailAddresses to
     * set
     */
    public void setListOfRecipientEmailAddresses(String listOfRecipientEmailAddresses) {

        if (listOfRecipientEmailAddresses.length() > RECIPIENT_MAX_LENGTH) {
            listOfRecipientEmailAddresses = listOfRecipientEmailAddresses.substring(0, Math.min(listOfRecipientEmailAddresses.length(), RECIPIENT_MAX_LENGTH)) + "...";
        }

        this.listOfRecipientEmailAddresses = new CrmString(LIST_OF_RECIPIENT_EMAIL_ADDRESSES, listOfRecipientEmailAddresses);
    }
    

    /**
     * @return the emailBody
     */
    public CrmString getEmailBody() {
        return emailBody;
    }

    /**
     * Body of the email. Length>7000 chars? append "..."
     * @param emailBody the emailBody to set
     */
    public void setEmailBody(String emailBody) {
        
        if (emailBody.length() > BODY_MAX_LENGTH) {
            emailBody = emailBody.substring(0, Math.min(emailBody.length(), BODY_MAX_LENGTH)) + "...";
        }
        
        this.emailBody = new CrmString(EMAIL_BODY, emailBody);
    }


    /**
     * @return the emailSubject
     */
    public CrmString getEmailSubject() {
        return emailSubject;
    }

    /**
     * Subject line. Truncate after 50 chars
     * @param emailSubject the emailSubject to set
     */
    public void setEmailSubject(String emailSubject) {
        
        if (emailSubject.length() > SUBJECT_MAX_LENGTH) {
            emailSubject = emailSubject.substring(0, Math.min(emailSubject.length(), SUBJECT_MAX_LENGTH)) + "...";
        }

        this.emailSubject = new CrmString(EMAIL_SUBJECT, emailSubject);
    }
    

    /**
     * @return the emailSender
     */
    public CrmString getEmailSender() {
        return emailSender;
    }

    /**
     * @param emailSender the emailSender to set
     */
    public void setEmailSender(String emailSender) {
        this.emailSender = new CrmString(EMAIL_SENDER, emailSender);
    }

    /**
     * @return the location
     */
    public CrmString getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = new CrmString(LOCATION, location);
    }

    /**
     * @return the actionDate
     */
    public DateTime getActionDate() {
        return actionDate;
    }

    /**
     * @param actionDate the actionDate to set
     */
    public void setActionDate(Date actionDate) {
        this.actionDate = new DateTime(ACTION_DATE, actionDate);
    }

    /**
     * @return the actionType
     */
    public OptionSetValue getActionType() {
        return actionType;
    }

    /**
     * @param actionType the actionType to set
     */
    public void setActionType(int actionType) {
        this.actionType = new OptionSetValue(ACTION_TYPE, actionType);
    }

    /**
     * @return the attachmentPageViews
     */
    public CrmInt getAttachmentPageViews() {
        return attachmentPageViews;
    }

    /**
     * @param attachmentPageViews the attachmentPageViews to set
     */
    public void setAttachmentPageViews(int attachmentPageViews) {
        this.attachmentPageViews = new CrmInt(ATTACHMENT_PAGE_VIEWS, attachmentPageViews);
    }

    /**
     * @return the leadId
     */
    public EntityReference getLeadId() {
        return leadId;
    }

    /**
     * @param leadId the leadId to set
     */
    public void setLeadId(String leadId) {
        this.leadId = new EntityReference(EntityName.Lead, LEAD_ID, leadId);
    }

    /**
     * @return the contactId
     */
    public EntityReference getContactId() {
        return contactId;
    }

    /**
     * @param contactId the contactId to set
     */
    public void setContactId(String contactId) {
        this.contactId = new EntityReference(EntityName.Contact, CONTACT_ID, contactId);
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
        this.opportunityId = new EntityReference(EntityName.Opportunity, OPPORTUNITY_ID, opportunityId);
    }

    /**
     * @return the attachmentList
     */
    public CrmString getAttachmentList() {
        return attachmentList;
    }

    /**
     * @param attachmentList the attachmentList to set
     */
    public void setAttachmentList(String attachmentList) {
        this.attachmentList = new CrmString(ATTACHMENTS_LIST, attachmentList);
    }

}

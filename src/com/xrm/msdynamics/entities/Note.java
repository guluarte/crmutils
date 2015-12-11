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
public class Note extends BaseEntity {

    private Entity entity;

    private EntityReference createdBy;
    private EntityReference objectId;
    private EntityReference ownerId;

    private CrmString documentBody;
    private CrmString subject;
    private CrmString fileName;
    private CrmString mimeType;
    private CrmString noteText;

    private CrmInt fileSize;
    private CrmBoolean isDocument;

    private OptionSetValue objectTypeCode;
    private DateTime createdOn;
    private DateTime modifiedOn;

    public static final String ID = "annotationid";
    public static final String CREATEDBY = "createdby";
    public static final String OBJECTID = "objectid";
    public static final String OWNERID = "ownerid";
    public static final String DOCUMENTBODY = "documentbody";
    public static final String SUBJECT = "subject";
    public static final String FILENAME = "filename";
    public static final String MIMETYPE = "mimetype";
    public static final String NOTETEXT = "notetext";
    public static final String FILESIZE = "filesize";
    public static final String ISDOCUMENT = "isdocument";
    public static final String OBJECTTYPECODE = "objecttypecode";
    public static final String CREATEDON = "createdon";
    public static final String MODIFIEDON = "modifiedon";

    public static final String[] NoteColumns = new String[]{
        ID,
        CREATEDBY,
        OBJECTID,
        OWNERID,
        DOCUMENTBODY,
        SUBJECT,
        FILENAME,
        MIMETYPE,
        NOTETEXT,
        FILESIZE,
        ISDOCUMENT,
        OBJECTTYPECODE,
        CREATEDON,
        MODIFIEDON
    };

    public Note() {
    }

    public Note(NodeList noteNodes) {
        super(noteNodes);
    }

    public class ObjectTypeCodes {

        public static final int Account = 1;
        public static final int Appointment = 4201;
        public static final int BulkImport = 4407;
        public static final int Calendar = 4003;
        public static final int Campaign = 4400;
        public static final int CampaignActivity = 4402;
        public static final int CampaignResponse = 4401;
        public static final int Case = 112;
        public static final int CaseResolution = 4206;
        public static final int Commitment = 4215;
        public static final int Competitor = 123;
        public static final int Contact = 2;
        public static final int Contract = 1010;
        public static final int ContractLine = 1011;
        public static final int Email = 4202;
        public static final int FacilityEquipment = 4000;
        public static final int Fax = 4204;
        public static final int Invoice = 1090;
        public static final int Lead = 4;
        public static final int Letter = 4007;
        public static final int MarketingList = 4300;
        public static final int Opportunity = 3;
        public static final int OpportunityClose = 4208;
        public static final int Order = 1088;
        public static final int OrderClose = 4209;
        public static final int PhoneCall = 4210;
        public static final int Product = 1024;
        public static final int Quote = 1084;
        public static final int QuoteClose = 4211;
        public static final int ResourceSpecification = 4006;
        public static final int Service = 4001;
        public static final int ServiceActivity = 4214;
        public static final int Task = 4212;
        public static final int RoutingRule = 8181;
        public static final int RoutingRuleItem = 8199;
    }

    @Override
    public void setAttribute(String key, String value, String logicalName) {
        switch (key) {
            case ID: {
                setId(value);
                break;
            }
            case OWNERID: {
                setOwnerId(logicalName, value);
                break;
            }
            case CREATEDBY: {
                setCreatedBy(logicalName, value);
                break;
            }
            case OBJECTID: {
                setObjectId(logicalName, value);
                break;
            }
            case DOCUMENTBODY: {
                setDocumentBody(value);
                break;
            }
            case SUBJECT: {
                setSubject(value);
                break;
            }
            case FILENAME: {
                setFileName(value);
                break;
            }
            case MIMETYPE: {
                setMimeType(value);
                break;
            }
            case NOTETEXT: {
                setNoteText(value);
                break;
            }
            case FILESIZE: {
                setFileSize(Integer.parseInt(value));
                break;
            }
            case ISDOCUMENT: {
                setIsDocument(Boolean.parseBoolean(value));
                break;
            }
            case OBJECTTYPECODE: {
                setObjectTypeCode(Integer.parseInt(value));
                break;
            }
            case CREATEDON: {
                try {
                    setCreatedOn(DATE_FORMAT.parse(value));
                    break;
                } catch (ParseException ex) {
                    Logger.getLogger(Note.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case MODIFIEDON: {
                try {
                    setModifiedOn(DATE_FORMAT.parse(value));
                    break;
                } catch (ParseException ex) {
                    Logger.getLogger(Note.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    @Override
    public String[] getDefaultColumns() {
        return NoteColumns;
    }

    @Override
    protected ArrayList<XmlSerializable> getAttributeCollection() {
        ArrayList<XmlSerializable> options = new ArrayList<>();

        if (getCreatedBy() != null) {
            options.add(getCreatedBy());
        }
        if (getObjectId() != null) {
            options.add(getObjectId());
        }
        if (getOwnerId() != null) {
            options.add(getOwnerId());
        }
        if (getDocumentBody() != null) {
            options.add(getDocumentBody());
        }
        if (getSubject() != null) {
            options.add(getSubject());
        }
        if (getFileName() != null) {
            options.add(getFileName());
        }
        if (getMimeType() != null) {
            options.add(getMimeType());
        }
        if (getNoteText() != null) {
            options.add(getNoteText());
        }
        if (getFileSize() != null) {
            options.add(getFileSize());
        }
        if (getIsDocument() != null) {
            options.add(getIsDocument());
        }
        if (getObjectTypeCode() != null) {
            options.add(getObjectTypeCode());
        }
        if (getCreatedOn() != null) {
            options.add(getCreatedOn());
        }
        if (getModifiedOn() != null) {
            options.add(getModifiedOn());
        }

        return options;
    }

    @Override
    protected Entity getEntity() {
        
        if (entity == null) {
            entity = new Entity(EntityName.Annotation);
        }
        return entity;
    }

    @Override
    public String getLogicalName() {
        return EntityName.Annotation;
    }

    /**
     * @return the createdBy
     */
    public EntityReference getCreatedBy() {
        return createdBy;
    }

    /**
     * @param logicalName
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String logicalName, String createdBy) {
        this.createdBy = new EntityReference(logicalName, CREATEDBY, createdBy);
    }

    /**
     * @return the objectId
     */
    public EntityReference getObjectId() {
        return objectId;
    }

    /**
     * @param logicalName
     * @param objectId the objectId to set
     */
    public void setObjectId(String logicalName, String objectId) {
        this.objectId = new EntityReference(logicalName, OBJECTID, objectId);
    }

    /**
     * @return the ownerId
     */
    public EntityReference getOwnerId() {
        return ownerId;
    }

    /**
     * @param logicalName
     * @param ownerId the ownerId to set
     */
    public void setOwnerId(String logicalName, String ownerId) {
        this.ownerId = new EntityReference(logicalName, OWNERID, ownerId);
    }

    /**
     * @return the documentBody
     */
    public CrmString getDocumentBody() {
        return documentBody;
    }

    /**
     * @param documentBody the documentBody to set
     */
    public void setDocumentBody(String documentBody) {
        this.documentBody = new CrmString(DOCUMENTBODY, documentBody);
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
        this.subject = new CrmString(SUBJECT, subject);
    }

    /**
     * @return the fileName
     */
    public CrmString getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = new CrmString(FILENAME, fileName);
    }

    /**
     * @return the mimeType
     */
    public CrmString getMimeType() {
        return mimeType;
    }

    /**
     * @param mimeType the mimeType to set
     */
    public void setMimeType(String mimeType) {
        this.mimeType = new CrmString(MIMETYPE, mimeType);
    }

    /**
     * @return the noteText
     */
    public CrmString getNoteText() {
        return noteText;
    }

    /**
     * @param noteText the noteText to set
     */
    public void setNoteText(String noteText) {
        this.noteText = new CrmString(NOTETEXT, noteText);
    }

    /**
     * @return the fileSize
     */
    public CrmInt getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize the fileSize to set
     */
    public void setFileSize(int fileSize) {
        this.fileSize = new CrmInt(FILESIZE, fileSize);
    }

    /**
     * @return the isDocument
     */
    public CrmBoolean getIsDocument() {
        return isDocument;
    }

    /**
     * @param isDocument the isDocument to set
     */
    public void setIsDocument(boolean isDocument) {
        this.isDocument = new CrmBoolean(ISDOCUMENT, isDocument);
    }

    /**
     * @return the objectTypeCode
     */
    public OptionSetValue getObjectTypeCode() {
        return objectTypeCode;
    }

    /**
     * @param objectTypeCode the objectTypeCode to set
     */
    public void setObjectTypeCode(int objectTypeCode) {
        this.objectTypeCode = new OptionSetValue(OBJECTTYPECODE, objectTypeCode);
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

}

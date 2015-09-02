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
public class SharePointDocument extends BaseEntity {

    private final Entity entity = new Entity(EntityName.SharePointDocument);

    private CrmString absoluteUrl;
    private CrmString author;
    private CrmString editUrl;
    private CrmString readUrl;
    private CrmString fileType;
    private CrmString relativeLocation;
    private CrmString fullName;
    private CrmString sharePointModifiedBy;
    private CrmString title;
    private CrmString version;
    private CrmString contentType;

    private EntityReference businessUnitId;
    private EntityReference createdBy;
    private EntityReference createdOnBehalfBy;
    private EntityReference organizationId;
    private EntityReference ownerId;
    private EntityReference owningBusinessUnit;
    private EntityReference owningTeam;
    private EntityReference owningUser;
    private EntityReference regardingObjectId;
    private EntityReference transactionCurrencyId;

    private DateTime createdOn;
    private DateTime modifiedOn;
    private DateTime sharePointCreatedOn;

    private CrmInt documentId;
    private CrmInt childFolderCount;
    private CrmInt childItemCount;
    private CrmInt fileSize;

    private CrmBoolean isFolder;

    private static final String ID = "sharepointdocumentid";
    private static final String ABSOLUTEURL = "absoluteurl";
    private static final String AUTHOR = "author";
    private static final String EDITURL = "editurl";
    private static final String READURL = "readurl";
    private static final String FILETYPE = "filetype";
    private static final String RELATIVELOCATION = "relativelocation";
    private static final String FULLNAME = "fullname";
    private static final String SHAREPOINTMODIFIEDBY = "sharepointmodifiedby";
    private static final String TITLE = "title";
    private static final String VERSION = "version";
    private static final String BUSINESSUNITID = "businessunitid";
    private static final String CREATEDBY = "createdby";
    private static final String CREATEDONBEHALFBY = "createdonbehalfby";
    private static final String ORGANIZATIONID = "organizationid";
    private static final String OWNERID = "ownerid";
    private static final String OWNINGBUSINESSUNIT = "owningbusinessunit";
    private static final String OWNINGTEAM = "owningteam";
    private static final String OWNINGUSER = "owninguser";
    private static final String REGARDINGOBJECTID = "regardingobjectid";
    private static final String TRANSACTIONCURRENCYID = "transactioncurrencyid";
    private static final String CONTENTTYPE = "contenttype";
    private static final String CREATEDON = "createdon";
    private static final String MODIFIEDON = "modifiedon";
    private static final String SHAREPOINTCREATEDON = "sharepointcreatedon";
    private static final String DOCUMENTID = "documentid";
    private static final String CHILDFOLDERCOUNT = "childfoldercount";
    private static final String CHILDITEMCOUNT = "childitemcount";
    private static final String FILESIZE = "filesize";
    private static final String ISFOLDER = "isfolder";

    public static final String[] SharePointDocumentColumns = new String[]{
        ID,
        ABSOLUTEURL,
        AUTHOR,
        EDITURL,
        READURL,
        FILETYPE,
        RELATIVELOCATION,
        FULLNAME,
        SHAREPOINTMODIFIEDBY,
        TITLE,
        VERSION,
        BUSINESSUNITID,
        CREATEDBY,
        CREATEDONBEHALFBY,
        ORGANIZATIONID,
        OWNERID,
        OWNINGBUSINESSUNIT,
        OWNINGTEAM,
        OWNINGUSER,
        REGARDINGOBJECTID,
        TRANSACTIONCURRENCYID,
        CONTENTTYPE,
        CREATEDON,
        MODIFIEDON,
        SHAREPOINTCREATEDON,
        DOCUMENTID,
        CHILDFOLDERCOUNT,
        CHILDITEMCOUNT,
        FILESIZE,
        ISFOLDER
    };

    public SharePointDocument() {

    }

    public SharePointDocument(NodeList documentAttributes) {
        super(documentAttributes);
    }

    @Override
    public void setAttribute(String key, String value, String logicalName) {
        switch (key) {
            case ID: {
                setId(value);
                break;
            }
            case ABSOLUTEURL: {
                setAbsoluteUrl(value);
                break;
            }
            case AUTHOR: {
                setAuthor(value);
                break;
            }
            case EDITURL: {
                setEditUrl(value);
                break;
            }
            case READURL: {
                setReadUrl(value);
                break;
            }
            case FILETYPE: {
                setFileType(value);
                break;
            }
            case RELATIVELOCATION: {
                setRelativeLocation(value);
                break;
            }
            case FULLNAME: {
                setFullName(value);
                break;
            }
            case SHAREPOINTMODIFIEDBY: {
                setSharePointModifiedBy(value);
                break;
            }
            case TITLE: {
                setTitle(value);
                break;
            }
            case VERSION: {
                setVersion(value);
                break;
            }
            case BUSINESSUNITID: {
                setBusinessUnitId(value);
                break;
            }
            case CREATEDBY: {
                setCreatedBy(value);
                break;
            }
            case CREATEDONBEHALFBY: {
                setCreatedOnBehalfBy(value);
                break;
            }
            case ORGANIZATIONID: {
                setOrganizationId(value);
                break;
            }
            case OWNERID: {
                setOwnerId(value);
                break;
            }
            case OWNINGBUSINESSUNIT: {
                setOwningBusinessUnit(value);
                break;
            }
            case OWNINGTEAM: {
                setOwningTeam(value);
                break;
            }
            case OWNINGUSER: {
                setOwningUser(value);
                break;
            }
            case REGARDINGOBJECTID: {
                setRegardingObjectId(value);
                break;
            }
            case TRANSACTIONCURRENCYID: {
                setTransactionCurrencyId(value);
                break;
            }
            case CONTENTTYPE: {
                setContentType(value);
                break;
            }
            case CREATEDON: {
                try {
                    setCreatedOn(DATE_FORMAT.parse(value));
                    break;
                } catch (ParseException ex) {
                    Logger.getLogger(SharePointDocument.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case MODIFIEDON: {
                try {
                    setModifiedOn(DATE_FORMAT.parse(value));
                    break;
                } catch (ParseException ex) {
                    Logger.getLogger(SharePointDocument.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            case SHAREPOINTCREATEDON: {
                try {
                    setSharePointCreatedOn(DATE_FORMAT.parse(value));
                    break;
                } catch (ParseException ex) {
                    Logger.getLogger(SharePointDocument.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            case DOCUMENTID: {
                setDocumentId(Integer.parseInt(value));
                break;
            }

            case CHILDFOLDERCOUNT: {
                setChildFolderCount(Integer.parseInt(value));
                break;
            }

            case CHILDITEMCOUNT: {
                setChildItemCount(Integer.parseInt(value));
                break;
            }

            case FILESIZE: {
                setFileSize(Integer.parseInt(value));
                break;
            }

            case ISFOLDER: {
                setIsFolder(Boolean.parseBoolean(value));
                break;
            }

        }
    }

    @Override
    public String[] getDefaultColumns() {
        return SharePointDocumentColumns;
    }

    @Override
    protected ArrayList<XmlSerializable> getAttributeCollection() {
        ArrayList<XmlSerializable> options = new ArrayList<>();

        if (getAbsoluteUrl() != null) {
            options.add(getAbsoluteUrl());
        }
        if (getAuthor() != null) {
            options.add(getAuthor());
        }
        if (getEditUrl() != null) {
            options.add(getEditUrl());
        }
        if (getReadUrl() != null) {
            options.add(getReadUrl());
        }
        if (getFileType() != null) {
            options.add(getFileType());
        }
        if (getRelativeLocation() != null) {
            options.add(getRelativeLocation());
        }
        if (getFullName() != null) {
            options.add(getFullName());
        }
        if (getSharePointModifiedBy() != null) {
            options.add(getSharePointModifiedBy());
        }
        if (getTitle() != null) {
            options.add(getTitle());
        }
        if (getVersion() != null) {
            options.add(getVersion());
        }
        if (getBusinessUnitId() != null) {
            options.add(getBusinessUnitId());
        }
        if (getCreatedBy() != null) {
            options.add(getCreatedBy());
        }
        if (getCreatedOnBehalfBy() != null) {
            options.add(getCreatedOnBehalfBy());
        }
        if (getOrganizationId() != null) {
            options.add(getOrganizationId());
        }
        if (getOwnerId() != null) {
            options.add(getOwnerId());
        }
        if (getOwningBusinessUnit() != null) {
            options.add(getOwningBusinessUnit());
        }
        if (getOwningTeam() != null) {
            options.add(getOwningTeam());
        }
        if (getOwningUser() != null) {
            options.add(getOwningUser());
        }
        if (getRegardingObjectId() != null) {
            options.add(getRegardingObjectId());
        }
        if (getTransactionCurrencyId() != null) {
            options.add(getTransactionCurrencyId());
        }
        if (getContentType() != null) {
            options.add(getContentType());
        }
        if (getCreatedOn() != null) {
            options.add(getCreatedOn());
        }
        if (getModifiedOn() != null) {
            options.add(getModifiedOn());
        }
        if (getSharePointCreatedOn() != null) {
            options.add(getSharePointCreatedOn());
        }
        if (getDocumentId() != null) {
            options.add(getDocumentId());
        }
        if (getChildFolderCount() != null) {
            options.add(getChildFolderCount());
        }
        if (getChildItemCount() != null) {
            options.add(getChildItemCount());
        }
        if (getFileSize() != null) {
            options.add(getFileSize());
        }
        if (getIsFolder() != null) {
            options.add(getIsFolder());
        }

        return options;
    }

    @Override
    protected Entity getEntity() {
        return entity;
    }

    @Override
    public String getLogicalName() {
        return EntityName.SharePointDocument;
    }

    /**
     * @return the absoluteUrl
     */
    public CrmString getAbsoluteUrl() {
        return absoluteUrl;
    }

    /**
     * @param absoluteUrl the absoluteUrl to set
     */
    public void setAbsoluteUrl(String absoluteUrl) {
        this.absoluteUrl = new CrmString(ABSOLUTEURL, absoluteUrl);
    }

    /**
     * @return the author
     */
    public CrmString getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = new CrmString(AUTHOR, author);
    }

    /**
     * @return the editUrl
     */
    public CrmString getEditUrl() {
        return editUrl;
    }

    /**
     * @param editUrl the editUrl to set
     */
    public void setEditUrl(String editUrl) {
        this.editUrl = new CrmString(EDITURL, editUrl);
    }

    /**
     * @return the readUrl
     */
    public CrmString getReadUrl() {
        return readUrl;
    }

    /**
     * @param readUrl the readUrl to set
     */
    public void setReadUrl(String readUrl) {
        this.readUrl = new CrmString(READURL, readUrl);
    }

    /**
     * @return the fileType
     */
    public CrmString getFileType() {
        return fileType;
    }

    /**
     * @param fileType the fileType to set
     */
    public void setFileType(String fileType) {
        this.fileType = new CrmString(FILETYPE, fileType);
    }

    /**
     * @return the relativeLocation
     */
    public CrmString getRelativeLocation() {
        return relativeLocation;
    }

    /**
     * @param relativeLocation the relativeLocation to set
     */
    public void setRelativeLocation(String relativeLocation) {
        this.relativeLocation = new CrmString(RELATIVELOCATION, relativeLocation);
    }

    /**
     * @return the fullName
     */
    public CrmString getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = new CrmString(FULLNAME, fullName);
    }

    /**
     * @return the sharePointModifiedBy
     */
    public CrmString getSharePointModifiedBy() {
        return sharePointModifiedBy;
    }

    /**
     * @param sharePointModifiedBy the sharePointModifiedBy to set
     */
    public void setSharePointModifiedBy(String sharePointModifiedBy) {
        this.sharePointModifiedBy = new CrmString(SHAREPOINTMODIFIEDBY, sharePointModifiedBy);
    }

    /**
     * @return the title
     */
    public CrmString getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = new CrmString(TITLE, title);
    }

    /**
     * @return the version
     */
    public CrmString getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = new CrmString(VERSION, version);
    }

    /**
     * @return the contentType
     */
    public CrmString getContentType() {
        return contentType;
    }

    /**
     * @param contentType the contentType to set
     */
    public void setContentType(String contentType) {
        this.contentType = new CrmString(CONTENTTYPE, contentType);
    }

    /**
     * @return the businessUnitId
     */
    public EntityReference getBusinessUnitId() {
        return businessUnitId;
    }

    /**
     * @param businessUnitId the businessUnitId to set
     */
    public void setBusinessUnitId(String businessUnitId) {
        this.businessUnitId = new EntityReference(BUSINESSUNITID, BUSINESSUNITID, businessUnitId);
    }

    /**
     * @return the createdBy
     */
    public EntityReference getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = new EntityReference(EntityName.SystemUser, CREATEDBY, createdBy);
    }

    /**
     * @return the createdOnBehalfBy
     */
    public EntityReference getCreatedOnBehalfBy() {
        return createdOnBehalfBy;
    }

    /**
     * @param createdOnBehalfBy the createdOnBehalfBy to set
     */
    public void setCreatedOnBehalfBy(String createdOnBehalfBy) {
        this.createdOnBehalfBy = new EntityReference(EntityName.SystemUser, CREATEDONBEHALFBY, createdOnBehalfBy);
    }

    /**
     * @return the organizationId
     */
    public EntityReference getOrganizationId() {
        return organizationId;
    }

    /**
     * @param organizationId the organizationId to set
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = new EntityReference(EntityName.Account, ORGANIZATIONID, organizationId);
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
        this.ownerId = new EntityReference(EntityName.SystemUser, OWNERID, ownerId);
    }

    /**
     * @return the owningBusinessUnit
     */
    public EntityReference getOwningBusinessUnit() {
        return owningBusinessUnit;
    }

    /**
     * @param owningBusinessUnit the owningBusinessUnit to set
     */
    public void setOwningBusinessUnit(String owningBusinessUnit) {
        this.owningBusinessUnit = new EntityReference(EntityName.BusinnesUnit, OWNINGBUSINESSUNIT, owningBusinessUnit);
    }

    /**
     * @return the owningTeam
     */
    public EntityReference getOwningTeam() {
        return owningTeam;
    }

    /**
     * @param owningTeam the owningTeam to set
     */
    public void setOwningTeam(String owningTeam) {
        this.owningTeam = new EntityReference(EntityName.Account, OWNINGTEAM, owningTeam);
    }

    /**
     * @return the owningUser
     */
    public EntityReference getOwningUser() {
        return owningUser;
    }

    /**
     * @param owningUser the owningUser to set
     */
    public void setOwningUser(String owningUser) {
        this.owningUser = new EntityReference(EntityName.Account, OWNINGUSER, owningUser);
    }

    /**
     * @return the regardingObjectId
     */
    public EntityReference getRegardingObjectId() {
        return regardingObjectId;
    }

    /**
     * @param regardingObjectId the regardingObjectId to set
     */
    public void setRegardingObjectId(String regardingObjectId) {
        this.regardingObjectId = new EntityReference(EntityName.Article, REGARDINGOBJECTID, regardingObjectId);
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
     * @return the sharePointCreatedOn
     */
    public DateTime getSharePointCreatedOn() {
        return sharePointCreatedOn;
    }

    /**
     * @param sharePointCreatedOn the sharePointCreatedOn to set
     */
    public void setSharePointCreatedOn(Date sharePointCreatedOn) {
        this.sharePointCreatedOn = new DateTime(SHAREPOINTCREATEDON, sharePointCreatedOn);
    }

    /**
     * @return the documentId
     */
    public CrmInt getDocumentId() {
        return documentId;
    }

    /**
     * @param documentId the documentId to set
     */
    public void setDocumentId(int documentId) {
        this.documentId = new CrmInt(DOCUMENTID, documentId);
    }

    /**
     * @return the childFolderCount
     */
    public CrmInt getChildFolderCount() {
        return childFolderCount;
    }

    /**
     * @param childFolderCount the childFolderCount to set
     */
    public void setChildFolderCount(int childFolderCount) {
        this.childFolderCount = new CrmInt(CHILDFOLDERCOUNT, childFolderCount);
    }

    /**
     * @return the childItemCount
     */
    public CrmInt getChildItemCount() {
        return childItemCount;
    }

    /**
     * @param childItemCount the childItemCount to set
     */
    public void setChildItemCount(int childItemCount) {
        this.childItemCount = new CrmInt(CHILDITEMCOUNT, childItemCount);
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
     * @return the isFolder
     */
    public CrmBoolean getIsFolder() {
        return isFolder;
    }

    /**
     * @param isFolder the isFolder to set
     */
    public void setIsFolder(boolean isFolder) {
        this.isFolder = new CrmBoolean(ISFOLDER, isFolder);
    }

}

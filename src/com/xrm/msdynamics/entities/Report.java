/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.entities;

import com.xrm.msdynamics.Enums.EntityName;
import com.xrm.msdynamics.crmtypes.CrmBoolean;
import com.xrm.msdynamics.crmtypes.CrmInt;
import com.xrm.msdynamics.crmtypes.CrmString;
import com.xrm.msdynamics.crmtypes.Entity;
import com.xrm.msdynamics.crmtypes.XmlSerializable;

import java.util.ArrayList;
import org.w3c.dom.NodeList;

/**
 *
 * @author rguluarte
 */
public class Report extends BaseEntity {

    Entity entity;

    private CrmString name;
    private CrmString reportNameOnSrs;

    private CrmString scheduleXml;

    private CrmString customReportXml;

    private CrmString defaultFilter;
    private CrmString description;
    private CrmString fileName;

    private CrmString queryInfo;

    private CrmInt fileSize;
    private CrmInt reportTypeCode;

    private CrmBoolean isManaged;

    public static final String ID = "reportid";

    public static final String NAME = "name";
    public static final String REPORTNAMEONSRS = "reportnameonsrs";
    public static final String REPORTTYPECODE = "reporttypecode";
    public static final String SCHEDULEXML = "schedulexml";

    public static final String CUSTOMREPORTXML = "customreportxml";

    public static final String DEFAULTFILTER = "defaultfilter";
    public static final String DESCRIPTION = "description";
    public static final String FILENAME = "filename";
    public static final String FILESIZE = "filesize";

    public static final String ISMANAGED = "ismanaged";

    public static final String QUERYINFO = "queryinfo";

    public static String[] Columns = new String[]{
        ID,
        NAME,
        REPORTNAMEONSRS,
        REPORTTYPECODE,
        SCHEDULEXML,
        CUSTOMREPORTXML,
        DEFAULTFILTER,
        DESCRIPTION,
        FILENAME,
        FILESIZE,
        ISMANAGED,
        QUERYINFO
    };

    public Report(NodeList nodes) {
        super(nodes);
    }

    public Report() {
        super();
    }

    @Override
    public void setAttribute(String key, String value, String logicalName) {
        switch (key) {

            case ID: {
                this.setId(value);
                break;
            }

            case NAME: {
                this.setName(value);
                break;
            }
            case REPORTNAMEONSRS: {
                this.setReportNameOnSrs(value);
                break;
            }
            case REPORTTYPECODE: {
                this.setReportTypeCode(Integer.parseInt(value));
                break;
            }
            case SCHEDULEXML: {
                this.setScheduleXml(value);
                break;
            }
            case CUSTOMREPORTXML: {
                this.setCustomReportXml(value);
                break;
            }
            case DEFAULTFILTER: {
                this.setDefaultFilter(value);
                break;
            }
            case DESCRIPTION: {
                this.setDescription(value);
                break;
            }
            case FILENAME: {
                this.setFileName(value);
                break;
            }
            case FILESIZE: {
                this.setFileSize(Integer.parseInt(value));
                break;
            }
            case ISMANAGED: {
                this.setIsManaged(Boolean.parseBoolean(value));
                break;
            }
            case QUERYINFO: {
                this.setQueryInfo(value);
                break;
            }

        }
    }

    @Override
    public String[] getDefaultColumns() {
        return Columns;
    }

    @Override
    protected ArrayList<XmlSerializable> getAttributeCollection() {
        ArrayList<XmlSerializable> parameters = new ArrayList<>();

        if (getName() != null) {
            parameters.add(getName());
        }

        if (getReportNameOnSrs() != null) {
            parameters.add(getReportNameOnSrs());
        }

        if (getScheduleXml() != null) {
            parameters.add(getScheduleXml());
        }
        if (getCustomReportXml() != null) {
            parameters.add(getCustomReportXml());
        }
        if (getDefaultFilter() != null) {
            parameters.add(getDefaultFilter());
        }
        if (getDescription() != null) {
            parameters.add(getDescription());
        }
        if (getFileName() != null) {
            parameters.add(getFileName());
        }
        if (getQueryInfo() != null) {
            parameters.add(getQueryInfo());
        }
        if (getFileSize() != null) {
            parameters.add(getFileSize());
        }
        if (getReportTypeCode() != null) {
            parameters.add(getReportTypeCode());
        }
        if (getIsManaged() != null) {
            parameters.add(getIsManaged());
        }

        return parameters;
    }

    @Override
    protected Entity getEntity() {
        if (entity == null) {
            entity = new Entity(EntityName.Report);
        }

        return entity;
    }

    @Override
    public String getLogicalName() {
        return EntityName.Report;
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
     * @return the reportNameOnSrs
     */
    public CrmString getReportNameOnSrs() {
        return reportNameOnSrs;
    }

    /**
     * @param reportNameOnSrs the reportNameOnSrs to set
     */
    public void setReportNameOnSrs(String reportNameOnSrs) {
        this.reportNameOnSrs = new CrmString(REPORTNAMEONSRS, reportNameOnSrs);
    }

    /**
     * @return the reportTypeCode
     */
    public CrmInt getReportTypeCode() {
        return reportTypeCode;
    }

    /**
     * @param reportTypeCode the reportTypeCode to set
     */
    public void setReportTypeCode(int reportTypeCode) {
        this.reportTypeCode = new CrmInt(REPORTTYPECODE, reportTypeCode);
    }

    /**
     * @return the scheduleXml
     */
    public CrmString getScheduleXml() {
        return scheduleXml;
    }

    /**
     * @param scheduleXml the scheduleXml to set
     */
    public void setScheduleXml(String scheduleXml) {
        this.scheduleXml = new CrmString(SCHEDULEXML, scheduleXml);
    }

    /**
     * @return the customReportXml
     */
    public CrmString getCustomReportXml() {
        return customReportXml;
    }

    /**
     * @param customReportXml the customReportXml to set
     */
    public void setCustomReportXml(String customReportXml) {
        this.customReportXml = new CrmString(CUSTOMREPORTXML, customReportXml);
    }

    /**
     * @return the defaultFilter
     */
    public CrmString getDefaultFilter() {
        return defaultFilter;
    }

    /**
     * @param defaultFilter the defaultFilter to set
     */
    public void setDefaultFilter(String defaultFilter) {
        this.defaultFilter = new CrmString(DEFAULTFILTER, defaultFilter);
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
     * @return the queryInfo
     */
    public CrmString getQueryInfo() {
        return queryInfo;
    }

    /**
     * @param queryInfo the queryInfo to set
     */
    public void setQueryInfo(String queryInfo) {
        this.queryInfo = new CrmString(QUERYINFO, queryInfo);
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
     * @return the isManaged
     */
    public CrmBoolean getIsManaged() {
        return isManaged;
    }

    /**
     * @param isManaged the isManaged to set
     */
    public void setIsManaged(boolean isManaged) {
        this.isManaged = new CrmBoolean(ISMANAGED, isManaged);
    }

}

/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.entities;

import com.xrm.msdynamics.Enums;

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
public class Task extends BaseEntity {

    Entity entity;

    private CrmString subject;
    private CrmString description;

    private EntityReference regardingObjectId;
    private EntityReference ownerId;

    private DateTime scheduledEnd;
    private DateTime scheduledStart;

    private OptionSetValue priorityCode;
    private OptionSetValue stateCode;
    private OptionSetValue statusCode;

    private CrmInt actualDurationMinutes;

    public static final String ID_COLUMN = "activityid";
    public static final String SUBJECT_COLUMN = "subject";
    public static final String DESCRIPTION_COLUMN = "description";
    public static final String REGARDINGOBJECTID_COLUMN = "regardingobjectid";
    public static final String OWNERID_COLUMN = "ownerid";
    public static final String ACTUALDURATIONMINUTES_COLUMN = "actualdurationminutes";
    public static final String SCHEDULEDEND_COLUMN = "scheduledend";
    public static final String SCHEDULEDSTART_COLUMN = "scheduledstart";
    public static final String STATECODE_COLUMN = "statecode";
    public static final String STATUSCODE_COLUMN = "statuscode";
    public static final String PRIORITYCODE_COLUMN = "prioritycode";

    public class PriorityCodes {

        public static final int Low = 0;
        public static final int Normal = 1;
        public static final int High = 2;
    }

    public static String[] Columns = new String[]{
        ID_COLUMN,
        SUBJECT_COLUMN,
        DESCRIPTION_COLUMN,
        REGARDINGOBJECTID_COLUMN,
        OWNERID_COLUMN,
        ACTUALDURATIONMINUTES_COLUMN,
        SCHEDULEDEND_COLUMN,
        SCHEDULEDSTART_COLUMN,
        STATECODE_COLUMN,
        STATUSCODE_COLUMN,
        PRIORITYCODE_COLUMN
    };

    /**
     * Status reason and status codes
     */
    public class TaskState {

        public static final int Open = 0;
        public static final int Completed = 1;
        public static final int Canceled = 2;
    }

    public class TaskStatus {

        public static final int NotStarted = 2;
        public static final int InProgess = 3;
        public static final int WaitingOnSomeoneElse = 4;
        public static final int Deferred = 7;
        public static final int Completed = 5;
        public static final int Canceled = 6;
    }

    public Task(NodeList nodes) {
        super(nodes);
    }

    public Task() {
        super();
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

            case DESCRIPTION_COLUMN: {
                this.setDescription(value);
                break;
            }

            case REGARDINGOBJECTID_COLUMN: {
                this.setRegardingObjectId(logicalName, value);
                break;
            }

            case OWNERID_COLUMN: {
                this.setOwnerId(value);
                break;
            }

            case ACTUALDURATIONMINUTES_COLUMN: {
                this.setActualDurationMinutes(Integer.parseInt(value));
                break;
            }

            case SCHEDULEDEND_COLUMN: {
                try {
                    this.setScheduledEnd(DATE_FORMAT.parse(value));
                } catch (ParseException ex) {
                    Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }

            case SCHEDULEDSTART_COLUMN: {
                try {
                    this.setScheduledStart(DATE_FORMAT.parse(value));
                } catch (ParseException ex) {
                    Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }

            case PRIORITYCODE_COLUMN: {
                this.setPriorityCode(Integer.parseInt(value));
                break;
            }

            case STATECODE_COLUMN: {
                this.setStateCode(Integer.parseInt(value));
                break;
            }

            case STATUSCODE_COLUMN: {
                this.setStatusCode(Integer.parseInt(value));
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

        if (getSubject() != null) {
            parameters.add(getSubject());
        }

        if (getDescription() != null) {
            parameters.add(getDescription());
        }

        if (getRegardingObjectId() != null) {
            parameters.add(getRegardingObjectId());
        }

        if (getOwnerId() != null) {
            parameters.add(getOwnerId());
        }

        if (getActualDurationMinutes() != null) {
            parameters.add(getActualDurationMinutes());
        }

        if (getScheduledEnd() != null) {
            parameters.add(getScheduledEnd());
        }

        if (getScheduledStart() != null) {
            parameters.add(getScheduledStart());
        }

        if (getPriorityCode() != null) {
            parameters.add(getPriorityCode());
        }

        return parameters;
    }

    @Override
    protected Entity getEntity() {

        if (entity == null) {
            entity = new Entity(Enums.EntityName.Task);
        }
        return entity;
    }

    @Override
    public String getLogicalName() {
        return Enums.EntityName.Task;
    }

    public StatusAndStatusReason getStatusOpenAndNotStarted() {
        return new StatusAndStatusReason(TaskState.Open, TaskStatus.NotStarted);
    }

    public StatusAndStatusReason getStatusOpenAndInProgress() {
        return new StatusAndStatusReason(TaskState.Open, TaskStatus.InProgess);
    }

    public StatusAndStatusReason getStatusOpenAndWaitingOnSomeoneElse() {
        return new StatusAndStatusReason(TaskState.Open, TaskStatus.WaitingOnSomeoneElse);
    }

    public StatusAndStatusReason getStatusOpenAndDeferred() {
        return new StatusAndStatusReason(TaskState.Open, TaskStatus.Deferred);
    }

    public StatusAndStatusReason getStatusCompleted() {
        return new StatusAndStatusReason(TaskState.Completed, TaskStatus.Completed);
    }

    public StatusAndStatusReason getStatusCanceled() {
        return new StatusAndStatusReason(TaskState.Canceled, TaskStatus.Canceled);
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
     * @return the regardingObjectId
     */
    public EntityReference getRegardingObjectId() {
        return regardingObjectId;
    }

    /**
     * @param entityName
     * @param regardingObjectId the regardingObjectId to set
     */
    public void setRegardingObjectId(String entityName, String regardingObjectId) {
        this.regardingObjectId = new EntityReference(entityName, REGARDINGOBJECTID_COLUMN, regardingObjectId);
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
        this.ownerId = new EntityReference(Enums.EntityName.SystemUser, OWNERID_COLUMN, ownerId);
    }

    /**
     * @return the scheduledEnd
     */
    public DateTime getScheduledEnd() {
        return scheduledEnd;
    }

    /**
     * @param scheduledEnd the scheduledEnd to set
     */
    public void setScheduledEnd(Date scheduledEnd) {
        this.scheduledEnd = new DateTime(SCHEDULEDEND_COLUMN, scheduledEnd);
    }

    /**
     * @return the scheduledStart
     */
    public DateTime getScheduledStart() {
        return scheduledStart;
    }

    /**
     * @param scheduledStart the scheduledStart to set
     */
    public void setScheduledStart(Date scheduledStart) {
        this.scheduledStart = new DateTime(SCHEDULEDSTART_COLUMN, scheduledStart);
    }

    /**
     * @return the priorityCode
     */
    public OptionSetValue getPriorityCode() {
        return priorityCode;
    }

    /**
     * @param priorityCode the priorityCode to set
     */
    public void setPriorityCode(int priorityCode) {
        this.priorityCode = new OptionSetValue(PRIORITYCODE_COLUMN, priorityCode);
    }

    /**
     * @return the actualDurationMinutes
     */
    public CrmInt getActualDurationMinutes() {
        return actualDurationMinutes;
    }

    /**
     * @param actualDurationMinutes the actualDurationMinutes to set
     */
    public void setActualDurationMinutes(int actualDurationMinutes) {
        this.actualDurationMinutes = new CrmInt(ACTUALDURATIONMINUTES_COLUMN, actualDurationMinutes);
    }

    /**
     * @return the stateCode
     */
    public OptionSetValue getStateCode() {
        return stateCode;
    }

    /**
     * @param stateCode the stateCode to set
     */
    public void setStateCode(int stateCode) {
        this.stateCode = new OptionSetValue(STATECODE_COLUMN, stateCode);
    }

    /**
     * @return the statusCode
     */
    public OptionSetValue getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = new OptionSetValue(STATUSCODE_COLUMN, statusCode);
    }

}

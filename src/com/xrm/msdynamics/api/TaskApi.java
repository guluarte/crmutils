/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.api;

import com.xrm.msdynamics.Enums.EntityName;
import com.xrm.msdynamics.OrganizationService;
import com.xrm.msdynamics.entities.Contact;
import com.xrm.msdynamics.entities.EntityFactory;
import com.xrm.msdynamics.entities.Lead;

import com.xrm.msdynamics.entities.LiveHiveTask;

import java.util.ArrayList;

import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

/**
 *
 * @author rguluarte
 */
public class TaskApi {

    private final OrganizationService service;

    /**
     * List of valid Task status
     */
    public class TaskStatus {

        public static final String OpenAndNotStarted = "OpenAndNotStarted";
        public static final String OpenAndInProgress = "OpenAndInProgress";
        public static final String OpenAndWaitingOnSomeoneElse = "OpenAndWaitingOnSomeoneElse";
        public static final String OpenAndDeferred = "OpenAndDeferred";
        public static final String Completed = "Completed";
        public static final String Canceled = "Canceled";
    }

    public TaskApi(OrganizationService service) {
        this.service = service;
    }

    /**
     * Creates a new LiveHive Task
     *
     * @param task
     * @return the task Id
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public String create(LiveHiveTask task) throws SAXException, ParserConfigurationException, Exception {
        return service.Create(task.toEntity());
    }

    /**
     * Creates a new LiveHiveTask
     *
     * @param subject
     * @param time
     * @param status
     * @param prospectId
     * @param prospectEntityName
     * @param taskType
     * @return The Task Id
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public String create(String subject, Date time, String status, String prospectId, String prospectEntityName, int taskType) throws SAXException, ParserConfigurationException, Exception {

        LiveHiveTask task = new LiveHiveTask();

        if (subject != null) {
            task.setSubject(subject);
        }

        if (time != null) {
            task.setScheduledStart(time);
        }

        if (prospectId != null && prospectEntityName != null) {
            task.setRegardingObjectId(prospectEntityName, prospectId);
        }

        if (taskType > 0) {
            task.setXrmTaskType(taskType);
        }

        task.setId(service.Create(task.toEntity()));

        if (status != null) {
            setTaskStatus(status, task);
        }

        return task.getId();
    }

    /**
     * Sets the status of the task Note: Task marked as completed or canceled
     * cannot be updated.
     *
     * @param status
     * @param task
     * @throws Exception
     */
    public void setTaskStatus(String status, LiveHiveTask task) throws Exception {
        // set status
        switch (status) {
            case TaskStatus.OpenAndNotStarted: {
                service.SetStateRequest(task.toEntityReference(), task.getStatusOpenAndNotStarted());
                break;
            }
            case TaskStatus.OpenAndInProgress: {
                service.SetStateRequest(task.toEntityReference(), task.getStatusOpenAndInProgress());
                break;
            }
            case TaskStatus.OpenAndWaitingOnSomeoneElse: {
                service.SetStateRequest(task.toEntityReference(), task.getStatusOpenAndWaitingOnSomeoneElse());
                break;
            }
            case TaskStatus.OpenAndDeferred: {
                service.SetStateRequest(task.toEntityReference(), task.getStatusOpenAndDeferred());
                break;
            }
            case TaskStatus.Completed: {
                service.SetStateRequest(task.toEntityReference(), task.getStatusCompleted());
                break;
            }
            case TaskStatus.Canceled: {
                service.SetStateRequest(task.toEntityReference(), task.getStatusCanceled());
                break;
            }
        }
    }

    /**
     * Updates a LiveHiveTask Note: Task marked as completed or canceled cannot
     * be updated.
     *
     * @param task
     * @return The Task Id
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public String update(LiveHiveTask task) throws SAXException, ParserConfigurationException, Exception {
        service.Update(task.toEntity());
        return task.getId();
    }

    /**
     * Updates a LiveHiveTask Note: Task marked as completed or canceled cannot
     * be updated.
     *
     * @param taskId
     * @param subject
     * @param time
     * @param status
     * @param prospectId
     * @param prospectEntityName
     * @return The TaskId updated
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public String update(String taskId, String subject, Date time, String status, String prospectId, String prospectEntityName) throws SAXException, ParserConfigurationException, Exception {

        LiveHiveTask task = new LiveHiveTask();

        task.setId(taskId);

        if (subject != null) {
            task.setSubject(subject);
        }

        if (time != null) {
            task.setScheduledStart(time);
        }

        if (prospectId != null && prospectEntityName != null) {
            task.setRegardingObjectId(prospectEntityName, prospectId);
        }

        service.Update(task.toEntity());

        if (status != null) {
            setTaskStatus(status, task);
        }

        return task.getId();
    }

    /**
     * Deletes a list of task separated by a comma
     *
     * @param taskIds
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public void delete(String taskIds) throws SAXException, ParserConfigurationException, Exception {

        if (taskIds == null) {
            return;
        }

        String[] taskArray = taskIds.split(",");

        for (String taskId : taskArray) {
            taskId = taskId.trim();
            service.Delete(taskId, EntityName.Task);
        }

    }

    /**
     * Deletes a livehive task
     *
     * @param task
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public void delete(LiveHiveTask task) throws SAXException, ParserConfigurationException, Exception {
        service.Delete(task.getId(), task.getLogicalName());
    }

    /**
     * Deletes an arraylist of LiveHiveTasks
     *
     * @param taskIds
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public void delete(ArrayList<String> taskIds) throws ParserConfigurationException, Exception {

        if (taskIds == null) {
            return;
        }

        for (String taskId : taskIds) {
            delete(taskId);
        }
    }

    /**
     * Grabs all the task on the CRM
     * @return an ArrayList of LiveHiveTasks
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public ArrayList<LiveHiveTask> retrieveAll() throws SAXException, ParserConfigurationException, Exception {
        LiveHiveTask task = new LiveHiveTask();
        String[] columns = task.getDefaultColumns();
        Document responseDocument = service.RetrieveAll(EntityName.Task, columns, null);
        EntityFactory entityFactory = new EntityFactory<>(LiveHiveTask.class);
        return entityFactory.Build(responseDocument);
    }
    
    /**
     * Retrieves a single Task
     * @param taskId
     * @return a LiveHiveTask
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public LiveHiveTask retrieveSingle(String taskId) throws SAXException, ParserConfigurationException, Exception {
        
        LiveHiveTask task = new LiveHiveTask();
        String[] columns = task.getDefaultColumns();
        
        NodeList nodes = service.Retrieve(EntityName.Task, taskId, columns);
        
        return new LiveHiveTask(nodes);
    }
    
    /**
     * Retrieves all Task associated with the RegardingObjectId (lead or contact id)
     * @param regardingId
     * @return an ArrayList of LiveHiveTasks
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public ArrayList<LiveHiveTask> retrieveByRegardingObjectId(String regardingId) throws SAXException, ParserConfigurationException, Exception {
        
        Document responseDocument = service.RetrieveEntityByNameAndColumnString(EntityName.Task, LiveHiveTask.REGARDINGOBJECTID_COLUMN, regardingId);
        EntityFactory entityFactory = new EntityFactory<>(LiveHiveTask.class);
        
        return entityFactory.Build(responseDocument);
    }
    
    /**
     * Retrieves all task associated with the lead email
     * @param email
     * @return An ArrayList con LiveHiveTasks
     * @throws IllegalAccessException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public ArrayList<LiveHiveTask> retrieveByLeadEmail(String email) throws IllegalAccessException, ParserConfigurationException, Exception {

        ArrayList<Lead> leads = service.RetrieveLeadByEmail(email);

         if(leads.isEmpty()) {
             return new ArrayList<>();
         }
         
         Lead lead = leads.get(0);
         
         return retrieveByRegardingObjectId(lead.getId());  
    }
    
    /**
     * Retrieves all task associated with the contact email
     * @param email
     * @return An ArrayList con LiveHiveTasks
     * @throws IllegalAccessException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public ArrayList<LiveHiveTask> retrieveByContactEmail(String email) throws IllegalAccessException, ParserConfigurationException, Exception {

        ArrayList<Contact> contacts = service.RetrieveContactByEmail(email);

         if(contacts.isEmpty()) {
             return new ArrayList<>();
         }
         
         Contact lead = contacts.get(0);
         
         return retrieveByRegardingObjectId(lead.getId());  
    }
    
}
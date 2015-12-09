CrmUtils for MS Dynamics using JAVA

CRM On-premises

        String url = "https://server";
        String username = "domain\\user";
        String password = "password";

        OrganizationService service = new OrganizationService(url, username, password, true);
		
Crm OnLine

        String url = "https://server";
        String username = "user@domain.com";
        String password = "password";

        OrganizationService service = new OrganizationService(url, username, password, false);
		
Getting the current User Id

        String userId = service.WhoAmIRequest();
		
 Creating a LiveHive Contact (for leads use LiveHiveLead)
 
        LiveHiveContact liveHiveContact = new LiveHiveContact();
        
        liveHiveContact.setFirstname("Test");
        liveHiveContact.setLastname("Last");
        
        liveHiveContact.setXrmLiveHiveScore(1.3);
        liveHiveContact.setXrmLiveHiveClosingScore(3.6);
        liveHiveContact.setXrmLiveHiveChangeInMetric(3);
        liveHiveContact.setXrmLiveHiveLastView(1000);
        liveHiveContact.setXrmLiveHiveClosingMetric(6);
        liveHiveContact.setXrmLiveHiveLastShare(10002);
        liveHiveContact.setXrmLiveHiveMetric(4);
        liveHiveContact.setXrmLiveHiveState("STATE");
        liveHiveContact.setXrmLiveHiveChangeInScore(3.5);
        liveHiveContact.setEngagementDisplayOnly("4.0 out of 5"); - This should be calculated from the previous values
        liveHiveContact.setRecentChangeDisplayOnly("890.0%"); - This should be calculated from the previous values
        liveHiveContact.setLeadsourcecode(Lead.LeadSourceCode.LiveHive);
        
        Calendar cal = Calendar.getInstance();
        
        liveHiveContact.setXrmLiveHiveLastViewOn(cal.getTime());
        liveHiveContact.setXrmLiveHiveLastShareOn(cal.getTime());
        
        service.Create(liveHiveContact.toEntity());
		
Check if a solution has been installed

        boolean solutionExists = service.hasSolutionInstalled("xLiveHive");
		
		
Retrieve leads by email

        List<Lead> leadsByName = service.RetrieveLeadByEmail("guluarte@gmail.com");
		
Retrieving entities

        NodeList nodes = service.Retrieve(EntityName.Annotation, "85c7d950-4c36-e511-80c5-00155dfe6a4d", Note.NoteColumns);
        Note test = new Note(nodes);
		
Setting status reason and state code

        BaseEntity.StatusAndStatusReason contacted = lead.getStatusOpenAndContacted();
        service.SetStateRequest(lead.toEntityReference(), contacted.State, contacted.Status);
		
Retrieving Notes

        ArrayList<Note> noteDocumnets = service.RetrieveAllNoteDocuments();
		
Creating notes with attachments

        File file = new File("Test.xlsx");
        String fileBytes = encodeFileToBase64Binary(file.getAbsolutePath());

        MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
        String mimeType = mimeTypesMap.getContentType(file);

        Note note = new Note();
        note.setFileName("xShopify Resources-CRM.xlsx");
        note.setMimeType(mimeType);
        note.setDocumentBody(fileBytes);
        note.setSubject("xShopify Resources");
        note.setObjectId(EntityName.Account, liveHiveAccount.getId());
        note.setId(service.Create(note.toEntity()));

        Document NoteDocument = service.RetrieveAll(EntityName.Annotation, Note.NoteColumns, null);

        NodeList noteNodes = service.Retrieve(EntityName.Annotation, note.getId(), Note.NoteColumns);

        Note createdNote = new Note(noteNodes);
		
Sample creating an opportunity

        Opportunity op = new Opportunity();
        op.setName("Test from java");
        op.setBudgetamount(new BigDecimal(50));
        op.setPurchasetimeframe(Enums.PurshaseTimeframe.NextQuarter);
        op.setPurchaseprocess(Enums.PurchaseProcess.Committee);
        op.setDescription("Testing Soap");
        op.setCurrentsituation("Situation is good");
        op.setEstimatedclosedate(Calendar.getInstance().getTime());

        op.setId(service.Create(op.toEntity()));
		
Updating an entity

        service.Update(op.toEntity());
		
And deleting an entity

        service.Delete(id, entityName);
		
Retrieving all entities and building them

        Document NoteDocument = service.RetrieveAll(EntityName.Annotation, Note.NoteColumns, null);
        EntityFactory entityFactory = new EntityFactory<>(Note.class);
        ArrayList<Note> notes = entityFactory.Build(NoteDocument);
		
Creating LiveHive Actions

        LiveHiveAction liveHiveAction = new LiveHiveAction();
        liveHiveAction.setName("Test Action complete");
        liveHiveAction.setListOfRecipientEmailAddresses("email1@xrm.com, email1@xrm.com, email1@xrm.com, email1@xrm.com");
        liveHiveAction.setEmailBody("body");
        liveHiveAction.setEmailSubject("Email Subject");
        liveHiveAction.setEmailSender("rodolfo@xrm.com");
        liveHiveAction.setActionType(LiveHiveAction.ActionType.Email);
        liveHiveAction.setActionDate(cal.getTime());
        liveHiveAction.setLocation("California");
        liveHiveAction.setAttachmentList("document.docx, errorlist.xlsx");
        liveHiveAction.setAttachmentPageViews(950);
        liveHiveAction.setLeadId(lead.getId());
        liveHiveAction.setId(service.Create(liveHiveAction.toEntity()));
        
        System.out.println("liveHiveAction " + liveHiveAction.getId());
		
Updating LiveHive Actions
Updating LiveHive Actions is the same as the other entities.
		
        LiveHiveAction liveHiveAction = new LiveHiveAction();
        liveHiveAction.setId("b17cbea7-4395-e511-80d4-00155d026854");
        liveHiveAction.setName("Test Action Update");
        service.Update(liveHiveAction.toEntity());
		
TaskApi
        Document leadResponseDocument = service.RetrieveAll(EntityName.Lead, Lead.LeadColumns, null);
        EntityFactory entityFactory = new EntityFactory<>(Lead.class);
        ArrayList<Lead> leads = entityFactory.Build(leadResponseDocument);
        
        String leadId = leads.get(0).getId();
        String leadEmail = leads.get(0).getEmailaddress1().toString();
        
        //We pass the OrganizationService to the TaskApi
        TaskApi taskApi = new TaskApi(service);
        
        // If we want to retrieve all the taks on the CRM
        ArrayList<LiveHiveTask> tasks = taskApi.retrieveAll();

        // Retrieving a single task by Id
        LiveHiveTask singleTask = taskApi.retrieveSingle(tasks.get(0).getId());
        
        //Retrieving a task by a lead or contact id
        ArrayList<LiveHiveTask> taskRegardingLeadId = taskApi.retrieveByRegardingObjectId(leadId);
        
        //Retrieving a task by a lead email
        ArrayList<LiveHiveTask> taskByLeadEmail = taskApi.retrieveByLeadEmail(leadEmail);
                
        Calendar cal = Calendar.getInstance();
        //Creating a task
        String taskId = taskApi.create("Test Subject 2", cal.getTime(), TaskApi.TaskStatus.OpenAndInProgress, leadId, EntityName.Lead, LiveHiveTask.TaskTypes.Email);
        
        // Updating a task - Note a task marked as completed or canceled cannot be updated.
        taskApi.update(taskId, "New Subject 2", cal.getTime(), null, leadId, EntityName.Lead);
        
        // Deleting task, we can use a comma delimited list or just pass a single id
        taskApi.delete(taskId);
		
Task
        We can also create/update/delete tasks as we do with the other entities.
        
        //Sample creating a new Task
        Task task = new Task();
        
        task.setSubject("Test subject 2");
        task.setDescription("Test description 2");
        task.setOwnerId(userId);
        task.setActualDurationMinutes(30);
        task.setScheduledEnd(cal.getTime());
        task.setPriorityCode(Task.PriorityCodes.High);
        task.setRegardingObjectId(leads.get(0).getLogicalName(), leads.get(0).getId());
        task.setId(service.Create(task.toEntity()));
        
        //Changing the task status
        service.SetStateRequest(task.toEntityReference(), task.getStatusCompleted());
#ChangeLog
	
Added the following methods to the entities LiveHIveContact and LiveHiveLead

        liveHiveContact.setEngagementDisplayOnly("4.0 out of 5"); - This should be calculated from the previous values
        liveHiveContact.setRecentChangeDisplayOnly("890.0%"); - This should be calculated from the previous values
		
Added LiveHiveActions
Added Task, LiveHiveTask and TaskApi
Added setAttachmentPageviewsDisplay() and getAttachmentPageviewsDisplay() to LiveHiveAction
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
		
For Impersonation add the LiveHive login after the password:

	 String url = "https://livehive.xrmlive.com/";
        String username = "ENTERPRISE\\livehivetest";
        String password = "";

        OrganizationService service = new OrganizationService(url, username, password, "usertest1", true);
		
Getting the current User Id

        String userId = service.WhoAmIRequest();
		
 Creating a LiveHive Contact (for leads use LiveHiveLead and for Accounts LiveHiveAccount)
 
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

        // For Associating a LiveHiveAction with a lead 
        liveHiveAction.setLeadId(lead.getId());

        // For Associating a LiveHiveAction with a contact 
        //liveHiveAction.setContactId();

        // For Associating a LiveHiveAction with an account 
        //liveHiveAction.setAccountId();
        
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
		
LeadApi for subscribing/unsubscribing

	public interface ILeadApi {
		void setSubscribeValue(Lead lead, boolean subscribe) throws SAXException, ParserConfigurationException, Exception; 
		void setSubscribeValue(String leadId, boolean subscribe) throws SAXException, ParserConfigurationException, Exception;
		void subscribe(Lead lead) throws SAXException, ParserConfigurationException, Exception; 
		void subscribe(String leadId) throws SAXException, ParserConfigurationException, Exception;
		void unsubscribe(String leadId) throws SAXException, ParserConfigurationException, Exception;
		void unsubscribe(Lead lead) throws SAXException, ParserConfigurationException, Exception;
		boolean toogleSubscribe(String leadId) throws SAXException, ParserConfigurationException, Exception;    
		boolean toogleSubscribe(Lead lead) throws SAXException, ParserConfigurationException, Exception; 
		Lead retrieve(String leadId) throws SAXException, ParserConfigurationException, Exception;
		ArrayList<Lead> retrieveAll() throws SAXException, ParserConfigurationException, Exception;
		boolean getSubscriptionValue(String leadId) throws SAXException, ParserConfigurationException, Exception;
		boolean getSubscriptionValue(Lead lead, boolean refreshEntity) throws SAXException, ParserConfigurationException, Exception;
	}


    public static void testLeadApi(OrganizationService service) throws ParserConfigurationException, Exception {
        
        String leadId = "ac7cbea7-4395-e511-80d4-00155d026854";
        
        // We init the class passing the service
        LeadApi leadApi = new LeadApi(service);
        
        // Retrieving all leads
        ArrayList<Lead> leads = leadApi.retrieveAll();
        
        // Retrieving a single lead by id
        Lead lead = leadApi.retrieve(leadId);
        
        // Getting the current subscription value, true if we want to refresh the entity
        boolean startingSubscriptionValueByLead = leadApi.getSubscriptionValue(lead, true);
       
        // Setting the subsciption value
        leadApi.setSubscribeValue(lead, true);
        leadApi.setSubscribeValue(lead, false);
        
        // Setting the subscription value to true
        // by the lead entity
        leadApi.subscribe(lead);
        
        // by leadId
        //leadApi.subscribe(leadId);
        
        // Setting the subscription value to false
        // by the lead entity
        leadApi.unsubscribe(lead);
        // by the lead id
        //leadApi.unsubscribe(leadId);
        
        // Toogling the subscription value by lead
        leadApi.toogleSubscribe(lead);
        // by the leadId
        //leadApi.toogleSubscribe(leadId);
        
        // Retrieving the current subscription value by lead or by leadId
        boolean endingSubscriptionValueByLead = leadApi.getSubscriptionValue(lead, true);
        boolean curretnSubscriptionValueById = leadApi.getSubscriptionValue(leadId);
        
    }

Retrieving Contacts And Leads WithIn a List

    public static void testCriteriaForLeads(OrganizationService service) throws ParserConfigurationException, Exception {

        LeadApi leadApi = new LeadApi(service);
        ArrayList<Lead> leadsById = leadApi.retrieveIn(new String[]{
            "ac7cbea7-4395-e511-80d4-00155d026854", "a12748b0-d9a8-e511-80d5-00155d02686f"
        });
        
        System.out.println("Leads By Id.");
        
        for (Lead leadById : leadsById) {
            System.out.println("Id: " + leadById.getId() + " Topic: " + leadById.getSubject());
        }
        
        ArrayList<Lead> leadsByEmail = leadApi.retrieveInEmailList(new String[]{
            "rodolfo@xrm.com", "test@test.com"
        });
        
        System.out.println("Leads By Email.");
        
        for (Lead leadByEmail1 : leadsByEmail) {
            System.out.println("Id: " + leadByEmail1.getId() + " Topic: " + leadByEmail1.getSubject());
        }
        
         ArrayList<Lead> allLeads = leadApi.retrieveAll();
         
         System.out.println("All Leads.");
         
         for (Lead lead : allLeads) {
            System.out.println("Id: " + lead.getId() + " Topic: " + lead.getSubject());
        }
    }
	
Retrieving Lead Views

        LeadApi leadApi = new LeadApi(service);

        ArrayList<SavedView> leadViews = leadApi.retrieveLeadSavedViews();

        for (View leadView : leadViews) {
            ArrayList<Lead> leadsInView = leadApi.leadsInSavedView(leadView.getId());
            System.out.println("Leads in view =" + leadView.getName() + " Id:" + leadView.getId());
            for (Lead leadInView : leadsInView) {
                System.out.println("\tleadId =" + leadInView.getId());
            }
        }

        ArrayList<View> leadSystemViews = leadApi.retrievePublicSystemViews();

        for (View leadView : leadSystemViews) {
            ArrayList<Lead> leadsInView = leadApi.leadsInView(leadView.getId());
            System.out.println("Leads in view =" + leadView.getName());
            System.out.println("Type =" + leadView.getQueryType().getValue());
            for (Lead leadInView : leadsInView) {
                System.out.println("\tleadId =" + leadInView.getId());
            }
        }


Contacts
	
    public static void testCriteriaForContacts(OrganizationService service) throws ParserConfigurationException, Exception {
        ContactApi contactApi = new ContactApi(service);
        ArrayList<Contact> contacts = contactApi.retrieveAll();

        ArrayList<Contact> contactsById = contactApi.retrieveIn(new String[]{
            "dd5b2959-e392-e511-80d4-00155d026854", "0036b826-3595-e511-80d4-00155d026854"
        });
        
        System.out.println("Contacts by Id.");
        
        for (Contact contactById : contactsById) {
            System.out.println("Id: " + contactById.getId() + " Name: " + contactById.getFirstname());
        }

        ArrayList<Contact> contactsByEmail = contactApi.retrieveInEmailList(new String[]{
            "test@test.com"
        });
        System.out.println("Contacts by Email.");
        
        for (Contact contactByEmail : contactsByEmail) {
            System.out.println("Id: " + contactByEmail.getId() + " Name: " + contactByEmail.getFirstname());
        }
        
        ArrayList<Contact> allContacts = contactApi.retrieveAll();
        
        System.out.println("All Contacts.");
        
        for (Contact contact : allContacts) {
            System.out.println("Id: " + contact.getId() + " Name: " + contact.getFirstname());
        }
    }
	

Retrieving Contacts Views

ContactApi contactApi = new ContactApi(service);

        ArrayList<SavedView> contactSavedViews = contactApi.retrieveSavedViews();

        for (View contactSavedView : contactSavedViews) {
            ArrayList<Contact> contactsInView = contactApi.contactsInSavedView(contactSavedView.getId());
            System.out.println("Contacts in view =" + contactSavedView.getName() + " Id:" + contactSavedView.getId());
            for (Contact contactInView : contactsInView) {
                System.out.println("\t contactId =" + contactInView.getId());
            }
        }

        ArrayList<View> contactSystemViews = contactApi.retrievePublicSystemViews();

        for (View contactView : contactSystemViews) {
            ArrayList<Contact> contactsInView = contactApi.contactsInView(contactView.getId());
            System.out.println("Contacts in view =" + contactView.getName());
            System.out.println("Type =" + contactView.getQueryType().getValue());
            for (Contact contactInView : contactsInView) {
                System.out.println("\t contactId =" + contactInView.getId());
                Account contactAccount = contactApi.getAccount(contactInView);
                if(contactAccount != null) {
                    System.out.println("\t\t account =" + contactAccount.getName());
                }
                
            }
        }
		
Getting contacts' account

		Account contactAccount = contactApi.getAccount(contactInView);
                if(contactAccount != null) {
                    System.out.println("\t\t account =" + contactAccount.getName());
                }

                
	
Accounts

Creating Email and Appointments

        AccountApi accoutnApi = new AccountApi(service);

        // Retrieving all contacts
        ArrayList<Account> accounts = accoutnApi.retrieveAll();

        // Retrieving a single contact by id
        Account account = accounts.get(0);

        String emailId = service.CreateCustomEmailActivity(account.getLogicalName(), account.getId(), userId, "Test subject for Account", "Test body", Enums.EmailPriority.High, Calendar.getInstance().getTime());

        System.out.println("Created Custom Email activity for account: " + emailId);

        service.CreateAppointment("Test appointment for account", "Description", Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), "House", userId, account.getLogicalName(), account.getId(), Enums.EmailPriority.High);
		

Search or Create an Account by Email

    public static void testSearchAccountByEmailOrCreateDefault(OrganizationService service) throws Exception {
        AccountApi accountApi = new AccountApi(service);

        Account defaultAccountSettings = new Account();

        defaultAccountSettings.setName("Test Search");

        Account createdOrRetrievedAccount = accountApi.retrieveByEmailOrCreate("testseachemail@xrm.com", defaultAccountSettings);

        System.out.println("Account Retrieved or Created: " + createdOrRetrievedAccount.getId());

    }
	

	
Create and Update a LiveHiveAccount (Account with the extra LiveHive Fields)

    public static void testLiveHiveAccount(OrganizationService service) throws Exception {

        LiveHiveAccount account = new LiveHiveAccount();

        account.setName("Test");

        account.setEmailAddress("testest@xrm.com");

        account.setXrmLiveHiveScore(1.5);
        account.setXrmLiveHiveClosingScore(2.6);
        account.setXrmLiveHiveChangeInMetric(5);
        account.setXrmLiveHiveLastView(2000);
        account.setXrmLiveHiveClosingMetric(5);
        account.setXrmLiveHiveLastShare(10003);
        account.setXrmLiveHiveMetric(5);
        account.setXrmLiveHiveState("STATE");
        account.setXrmLiveHiveChangeInScore(3.6);
        
        account.setEngagementDisplayOnly("3.0 out of 5");
        account.setRecentChangeDisplayOnly("150.0%");

        Calendar cal = Calendar.getInstance();

        account.setXrmLiveHiveLastViewOn(cal.getTime());
        account.setXrmLiveHiveLastShareOn(cal.getTime());

        account.setId(service.Create(account.toEntity()));

        System.out.println("Created LiveHive Account: " + account.getId());

        NodeList nodes = service.Retrieve(EntityName.Account, account.getId(), LiveHiveAccount.AccountColumns);
        LiveHiveAccount retrievedAccount = new LiveHiveAccount(nodes);

        /* Update account */
        retrievedAccount.setName("Test Updated account");

        service.Update(retrievedAccount.toEntity());

        System.out.println("Updated LiveHive Account: " + retrievedAccount.getId());

    }
	

LiveHive Actions for Accounts

        public static void testLiveHiveActionForAccount(OrganizationService service) throws ParserConfigurationException, IllegalAccessException, Exception, InstantiationException {
        Calendar cal = Calendar.getInstance();

        LiveHiveAccount liveHiveAccount = new LiveHiveAccount();
        liveHiveAccount.setName("Test LiveHive account With metrics");

        liveHiveAccount.setXrmLiveHiveScore(1.3);
        liveHiveAccount.setXrmLiveHiveClosingScore(3.6);
        liveHiveAccount.setXrmLiveHiveChangeInMetric(3);
        liveHiveAccount.setXrmLiveHiveLastView(1000);
        liveHiveAccount.setXrmLiveHiveClosingMetric(6);
        liveHiveAccount.setXrmLiveHiveLastShare(10002);
        liveHiveAccount.setXrmLiveHiveMetric(4);
        liveHiveAccount.setXrmLiveHiveState("STATE");
        liveHiveAccount.setXrmLiveHiveChangeInScore(3.5);
        liveHiveAccount.setXrmLiveHiveLastViewOn(cal.getTime());
        liveHiveAccount.setXrmLiveHiveLastShareOn(cal.getTime());

        liveHiveAccount.setEngagementDisplayOnly("4.0 out of 5");
        liveHiveAccount.setRecentChangeDisplayOnly("890.0%");

        liveHiveAccount.setId(service.Create(liveHiveAccount.toEntity()));

        System.out.println("account " + liveHiveAccount.getId());

        Document livehiveActionResponseDocument = service.RetrieveAll(EntityName.LiveHiveAction, LiveHiveAction.DefaultColumns, null);
        EntityFactory entityFactory = new EntityFactory<>(LiveHiveAction.class);
        ArrayList<LiveHiveAction> liveHiveActions = entityFactory.Build(livehiveActionResponseDocument);
        
        LiveHiveAction liveHiveAction = new LiveHiveAction();
        //liveHiveAction.setName("Test Action 2 complete");
        liveHiveAction.setListOfRecipientEmailAddresses("email1@xrm.com, email1@xrm.com, email1@xrm.com, email1@xrm.com");
        liveHiveAction.setEmailBody("body 2");
        liveHiveAction.setName("Action Name");
        liveHiveAction.setEmailSubject("Email Subject 2");
        liveHiveAction.setEmailSender("rodolfo@xrm.com");
        liveHiveAction.setActionType(LiveHiveAction.ActionType.Email);
        liveHiveAction.setActionDate(cal.getTime());
        liveHiveAction.setLocation("California");
        liveHiveAction.setAttachmentList("document.docx, errorlist.xlsx");
        liveHiveAction.setAttachmentPageViews(950);
        liveHiveAction.setActionName("Test action name");
        liveHiveAction.setAccountId(liveHiveAccount.getId());
        liveHiveAction.setId(service.Create(liveHiveAction.toEntity()));
        liveHiveAction.setAttachmentPageviewsDisplay("950 pageviews");

        System.out.println("liveHiveAction for account " + liveHiveAction.getId());
    }
	

LiveHiveAccounts Retrieval

    private static void testLiveHiveAccountRetrieval(OrganizationService service) throws SAXException, ParserConfigurationException, Exception {
        
        Document livehiveActionResponseDocument = service.RetrieveAll(EntityName.Account, LiveHiveAccount.LiveHiveAccountColumns, null);
        EntityFactory entityFactory = new EntityFactory<>(LiveHiveAccount.class);
        ArrayList<LiveHiveAccount> liveHiveAccounts = entityFactory.Build(livehiveActionResponseDocument);
        
        System.out.println("LiveHiveAccounts retrieved");
        
        for (LiveHiveAccount liveHiveAccount : liveHiveAccounts) {
            System.out.println("LiveHiveAccount for account " + liveHiveAccount.getName());
        }
    }


	
Retrieving Reports

        ReportApi reportApi = new ReportApi(service);
        ArrayList<Report> reports = reportApi.getAll();
        
        for (Report report : reports) {
            System.out.println("\tReportId =" + report.getId());
            System.out.println("\t\tName =" + report.getName());
            System.out.println("\t\tDescription =" + report.getDescription());
            //System.out.println("\t\tQuery Info =" + report.getQueryInfo());
            System.out.println("\t\tSSRS ID =" + report.getReportNameOnSrs());
        }


Sample Output

	ReportId =3a9e7c0f-dc8f-e511-80d0-00155dff7902
			Name =Neglected Cases
			Description =Identify cases that have not been contacted recently. 
			SSRS ID =1033{7358F4D6-605C-4D25-9CA2-0583FC2E683F}
		ReportId =429e7c0f-dc8f-e511-80d0-00155dff7902
			Name =Top Knowledge Base Articles
			Description =Identify the most frequently used knowledge base articles. 
			SSRS ID =1033{BD8A98C4-8DA0-4505-99C2-22FB9D0FC52A}


	
#ChangeLog
	
Added the following methods to the entities LiveHIveContact and LiveHiveLead

        liveHiveContact.setEngagementDisplayOnly("4.0 out of 5"); - This should be calculated from the previous values
        liveHiveContact.setRecentChangeDisplayOnly("890.0%"); - This should be calculated from the previous values
		
Added LiveHiveActions

Added Task, LiveHiveTask and TaskApi

Added setAttachmentPageviewsDisplay() and getAttachmentPageviewsDisplay() to LiveHiveAction

Added LeadApi

Added ContactApi

Added Criteria, FilterExpression and Filters

Added Reports and SavedViews

        CRM On-premises
        String url = "https://server";
        String username = "domain\\user";
        String password = "password";

        OrganizationService service = new OrganizationService(url, username, password, true);
		
		Crm OnLine
		CRM On-premises
        String url = "https://server";
        String username = "user@domain.com";
        String password = "password";

        OrganizationService service = new OrganizationService(url, username, password, false);
		
		
        Creating a LiveHive Contact
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
		
/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.api;

import com.xrm.msdynamics.Enums;
import com.xrm.msdynamics.Enums.EntityName;
import com.xrm.msdynamics.OrganizationService;
import com.xrm.msdynamics.api.interfaces.IContactApi;
import com.xrm.msdynamics.crmtypes.ConditionExpression;
import com.xrm.msdynamics.crmtypes.Criteria;
import com.xrm.msdynamics.crmtypes.FilterExpression;
import com.xrm.msdynamics.entities.Account;
import com.xrm.msdynamics.entities.Contact;
import com.xrm.msdynamics.entities.EntityFactory;
import com.xrm.msdynamics.entities.SavedView;
import com.xrm.msdynamics.entities.View;
import com.xrm.msdynamics.exceptions.ServiceFaultException;

import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author rguluarte
 */
public class ContactApi implements IContactApi {

    private final OrganizationService service;

    public ContactApi(OrganizationService service) {
        this.service = service;
    }

    @Override
    public void setSubscribeValue(Contact contact, boolean subscribe) throws SAXException, ParserConfigurationException, Exception {
        subscribe = !subscribe; /// MS Dynamics is donotemail = true means do not send email.
        contact.setDoNotEmail(subscribe);
        contact.setDoNotBulkEmail(subscribe);
        contact.setDonotsendmm(subscribe);
        service.Update(contact.toEntity());
    }

    @Override
    public void setSubscribeValue(String contactId, boolean subscribe) throws SAXException, ParserConfigurationException, Exception {
        Contact contact = retrieve(contactId);
        setSubscribeValue(contact, subscribe);
    }

    @Override
    public void subscribe(Contact contact) throws SAXException, ParserConfigurationException, Exception {
        setSubscribeValue(contact, true);
    }

    @Override
    public void subscribe(String contactId) throws SAXException, ParserConfigurationException, Exception {
        setSubscribeValue(contactId, true);
    }

    @Override
    public void unsubscribe(String contactId) throws SAXException, ParserConfigurationException, Exception {
        setSubscribeValue(contactId, false);
    }

    @Override
    public void unsubscribe(Contact contact) throws SAXException, ParserConfigurationException, Exception {
        setSubscribeValue(contact, false);
    }

    @Override
    public boolean toogleSubscribe(String contactId) throws SAXException, ParserConfigurationException, Exception {
        boolean subscribe = !getSubscriptionValue(contactId);
        setSubscribeValue(contactId, subscribe);

        return subscribe;
    }

    @Override
    public boolean toogleSubscribe(Contact contact) throws SAXException, ParserConfigurationException, Exception {

        boolean subscribe = !getSubscriptionValue(contact, false);
        setSubscribeValue(contact, subscribe);

        return subscribe;
    }

    @Override
    public Contact retrieve(String contactId) throws SAXException, ParserConfigurationException, Exception {
        NodeList nodes = service.Retrieve(Enums.EntityName.Contact, contactId, Contact.ContactColumns);
        return new Contact(nodes);
    }

    @Override
    public ArrayList<Contact> retrieveAll() throws SAXException, ParserConfigurationException, Exception {
        Document contactResponseDocument = service.RetrieveAll(Enums.EntityName.Contact, Contact.ContactColumns, null);
        EntityFactory entityFactory = new EntityFactory<>(Contact.class);
        return entityFactory.Build(contactResponseDocument);
    }

    @Override
    public boolean getSubscriptionValue(String contactId) throws SAXException, ParserConfigurationException, Exception {
        return getSubscriptionValue(retrieve(contactId), false);
    }

    @Override
    public boolean getSubscriptionValue(Contact contact, boolean refreshEntity) throws SAXException, ParserConfigurationException, Exception {

        if (refreshEntity) {
            contact = retrieve(contact.getId());
        }

        if (contact.getDoNotEmail() != null) {
            return !((boolean) contact.getDoNotEmail().getValue()); // MS dynamics is do not email
        }

        return false;
    }

    public ArrayList<Contact> retrieveWithCriteria(Criteria criteria) throws ParserConfigurationException, Exception {

        Document doc = service.RetrieveMultiple(EntityName.Contact, Contact.ContactColumns, criteria);

        EntityFactory entityFactory = new EntityFactory<>(Contact.class);
        return entityFactory.Build(doc);

    }

    public ArrayList<Contact> retrieveIn(String[] contactsId, String column) throws ParserConfigurationException, Exception {

        if (contactsId.length < 1) {
            return new ArrayList<>();
        }

        ConditionExpression conditionExpression = new ConditionExpression(ConditionExpression.Operator.Equal, column, contactsId[0]);
        FilterExpression filter = new FilterExpression(Criteria.FilterOperator.Or, conditionExpression);

        for (String contactId : contactsId) {
            filter.addCondition(new ConditionExpression(ConditionExpression.Operator.Equal, column, contactId));
        }

        Criteria criteria = new Criteria(Criteria.FilterOperator.And, filter);

        return retrieveWithCriteria(criteria);
    }

    public ArrayList<Contact> retrieveIn(String[] contactsId) throws Exception {
        return retrieveIn(contactsId, Contact.ID_COLUMN);
    }

    public ArrayList<Contact> retrieveInEmailList(String[] emails) throws Exception {
        return retrieveIn(emails, Contact.EMAILADDRESS1_COLUMN);
    }

    public ArrayList<View> retrieveViews() throws ParserConfigurationException, Exception {

        ConditionExpression conditionExpression = new ConditionExpression(ConditionExpression.Operator.Equal, View.RETURNEDTYPECODE, EntityName.Contact);
        FilterExpression filter = new FilterExpression(Criteria.FilterOperator.Or, conditionExpression);
        Criteria criteria = new Criteria(Criteria.FilterOperator.And, filter);

        Document doc = service.RetrieveMultiple(EntityName.View, View.Columns, criteria);

        EntityFactory entityFactory = new EntityFactory<>(View.class);

        return entityFactory.Build(doc);
    }

    public ArrayList<View> retrievePublicSystemViews() throws ParserConfigurationException, Exception {

        return retrieveViewByTypeAndReturnType(View.ViewType.MainApplicationView, EntityName.Contact, "0");
    }

    public Account getAccount(Contact contact) throws SAXException, ParserConfigurationException, Exception {

        String accountId = null;

        if (contact.getParentCustomerId() != null && contact.getParentCustomerId().getValue() != null && contact.getParentCustomerId().getValue() != "null") {
            accountId = (String) contact.getParentCustomerId().getValue();
        } else {
            return null;
        }
        NodeList nodes = service.Retrieve(EntityName.Account, accountId, Account.AccountColumns);
        Account retrievedAccount = new Account(nodes);

        return retrievedAccount;
    }

    public ArrayList<SavedView> retrieveSavedViews() throws ParserConfigurationException, Exception {

        ConditionExpression conditionExpression = new ConditionExpression(ConditionExpression.Operator.Equal, SavedView.RETURNEDTYPECODE, EntityName.Contact);
        FilterExpression filter = new FilterExpression(Criteria.FilterOperator.Or, conditionExpression);
        Criteria criteria = new Criteria(Criteria.FilterOperator.And, filter);

        Document doc = service.RetrieveMultiple(EntityName.SavedView, SavedView.Columns, criteria);

        EntityFactory entityFactory = new EntityFactory<>(SavedView.class);

        return entityFactory.Build(doc);
    }

    public ArrayList<Contact> contactsInView(View view) throws InstantiationException, IllegalAccessException, ParserConfigurationException, Exception {

        if (view.getFetchxml() == null || "".equals(view.getFetchxml().toString()) || view.getFetchxml().toString().contains("{0}")) {
            return new ArrayList<>();
        }

        Document doc = service.runFetchXmlQuery(view.getFetchxml().toString());

        EntityFactory entityFactory = new EntityFactory<>(Contact.class);

        return entityFactory.Build(doc);

    }
    
    public ArrayList<Contact> contactsInSavedView(SavedView view) throws InstantiationException, IllegalAccessException, ParserConfigurationException, Exception {

        if (view.getFetchxml() == null || "".equals(view.getFetchxml().toString()) || view.getFetchxml().toString().contains("{0}")) {
            return new ArrayList<>();
        }

        Document doc = service.runFetchXmlQuery(view.getFetchxml().toString());

        EntityFactory entityFactory = new EntityFactory<>(Contact.class);

        return entityFactory.Build(doc);

    }

    public ArrayList<Contact> contactsInView(String viewId) throws InstantiationException, IllegalAccessException, ParserConfigurationException, Exception {

        NodeList nodes = service.Retrieve(EntityName.View, viewId, View.Columns);
        View retrievedView = new View(nodes);

        return contactsInView(retrievedView);

    }
    
    public ArrayList<Contact> contactsInSavedView(String viewId) throws InstantiationException, IllegalAccessException, ParserConfigurationException, Exception {

        NodeList nodes = service.Retrieve(EntityName.SavedView, viewId, SavedView.Columns);
        SavedView retrievedView = new SavedView(nodes);

        return contactsInSavedView(retrievedView);

    }

    public ArrayList<View> retrieveViewByTypeAndReturnType(String type, String returnType, String isPrivate) throws ParserConfigurationException, Exception {

        // Entity type filter
        ConditionExpression conditionExpression = new ConditionExpression(ConditionExpression.Operator.Equal, View.RETURNEDTYPECODE, returnType);
        FilterExpression filter = new FilterExpression(Criteria.FilterOperator.Or, conditionExpression);

        // Is private filter
        ConditionExpression isPrivateConditionExpression = new ConditionExpression(ConditionExpression.Operator.Equal, View.ISPRIVATE, isPrivate);
        ConditionExpression isQuickFindQuery = new ConditionExpression(ConditionExpression.Operator.Equal, View.QUERYTYPE, type);

        FilterExpression filterIsPrivate = new FilterExpression(Criteria.FilterOperator.And, isPrivateConditionExpression);
        filterIsPrivate.addCondition(isQuickFindQuery);

        Criteria criteria = new Criteria(Criteria.FilterOperator.And, filter);
        criteria.addFilter(filterIsPrivate);

        Document doc = service.RetrieveMultiple(EntityName.View, View.Columns, criteria);

        EntityFactory entityFactory = new EntityFactory<>(View.class);

        return entityFactory.Build(doc);
    }

    public ArrayList<Contact> leadsInView(String viewId) throws InstantiationException, IllegalAccessException, ParserConfigurationException, Exception {

        NodeList nodes = service.Retrieve(EntityName.View, viewId, View.Columns);
        View retrievedView = new View(nodes);

        return contactsInView(retrievedView);

    }
}

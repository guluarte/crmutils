/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.api;

import com.xrm.msdynamics.Enums;
import com.xrm.msdynamics.OrganizationService;
import com.xrm.msdynamics.api.interfaces.IContactApi;
import com.xrm.msdynamics.entities.Contact;
import com.xrm.msdynamics.entities.EntityFactory;

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

        if (contact.getDoNotEmail()!= null) {
            return !((boolean) contact.getDoNotEmail().getValue()); // MS dynamics is do not email
        }

        return false;
    }
}

/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.api.interfaces;

import com.xrm.msdynamics.entities.Contact;

import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author rguluarte
 */
public interface IContactApi {
    public void setSubscribeValue(Contact contact, boolean subscribe) throws SAXException, ParserConfigurationException, Exception; 
    public void setSubscribeValue(String contactId, boolean subscribe) throws SAXException, ParserConfigurationException, Exception;
    public void subscribe(Contact contact) throws SAXException, ParserConfigurationException, Exception; 
    public void subscribe(String contactId) throws SAXException, ParserConfigurationException, Exception;
    public void unsubscribe(String contactId) throws SAXException, ParserConfigurationException, Exception;
    public void unsubscribe(Contact contact) throws SAXException, ParserConfigurationException, Exception;
    public boolean toogleSubscribe(String contactId) throws SAXException, ParserConfigurationException, Exception;    
    public boolean toogleSubscribe(Contact contact) throws SAXException, ParserConfigurationException, Exception; 
    public Contact retrieve(String contactId) throws SAXException, ParserConfigurationException, Exception;
    public ArrayList<Contact> retrieveAll() throws SAXException, ParserConfigurationException, Exception;
    public boolean getSubscriptionValue(String contactId) throws SAXException, ParserConfigurationException, Exception;
    public boolean getSubscriptionValue(Contact contact, boolean refreshEntity) throws SAXException, ParserConfigurationException, Exception;
}

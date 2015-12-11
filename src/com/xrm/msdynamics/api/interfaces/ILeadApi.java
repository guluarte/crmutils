/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.api.interfaces;

import com.xrm.msdynamics.entities.Lead;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 *
 * @author rguluarte
 */
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

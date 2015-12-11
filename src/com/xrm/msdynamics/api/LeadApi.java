/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.api;

import com.xrm.msdynamics.Enums.EntityName;
import com.xrm.msdynamics.OrganizationService;
import com.xrm.msdynamics.api.interfaces.ILeadApi;
import com.xrm.msdynamics.entities.EntityFactory;
import com.xrm.msdynamics.entities.Lead;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author rguluarte
 */
public class LeadApi implements ILeadApi {

    private final OrganizationService service;

    public LeadApi(OrganizationService service) {
        this.service = service;
    }

    @Override
    public void setSubscribeValue(Lead lead, boolean subscribe) throws SAXException, ParserConfigurationException, Exception {
        subscribe = !subscribe; /// MS Dynamics is donotemail = true means do not send email.
        lead.setDonotemail(subscribe);
        lead.setDonotbulkemail(subscribe);
        lead.setDonotsendmm(subscribe);
        service.Update(lead.toEntity());
    }

    @Override
    public void setSubscribeValue(String leadId, boolean subscribe) throws SAXException, ParserConfigurationException, Exception {
        Lead lead = retrieve(leadId);
        setSubscribeValue(lead, subscribe);
    }

    @Override
    public void subscribe(Lead lead) throws SAXException, ParserConfigurationException, Exception {
        setSubscribeValue(lead, true);
    }

    @Override
    public void subscribe(String leadId) throws SAXException, ParserConfigurationException, Exception {
        setSubscribeValue(leadId, true);
    }

    @Override
    public void unsubscribe(String leadId) throws SAXException, ParserConfigurationException, Exception {
        setSubscribeValue(leadId, false);
    }

    @Override
    public void unsubscribe(Lead lead) throws SAXException, ParserConfigurationException, Exception {
        setSubscribeValue(lead, false);
    }

    @Override
    public boolean toogleSubscribe(String leadId) throws SAXException, ParserConfigurationException, Exception {
        boolean subscribe = !getSubscriptionValue(leadId);
        setSubscribeValue(leadId, subscribe);

        return subscribe;
    }

    @Override
    public boolean toogleSubscribe(Lead lead) throws SAXException, ParserConfigurationException, Exception {

        boolean subscribe = !getSubscriptionValue(lead, false);
        setSubscribeValue(lead, subscribe);

        return subscribe;
    }

    @Override
    public Lead retrieve(String leadId) throws SAXException, ParserConfigurationException, Exception {
        NodeList nodes = service.Retrieve(EntityName.Lead, leadId, Lead.LeadColumns);
        return new Lead(nodes);
    }

    @Override
    public ArrayList<Lead> retrieveAll() throws SAXException, ParserConfigurationException, Exception {
        Document leadResponseDocument = service.RetrieveAll(EntityName.Lead, Lead.LeadColumns, null);
        EntityFactory entityFactory = new EntityFactory<>(Lead.class);
        return entityFactory.Build(leadResponseDocument);
    }

    @Override
    public boolean getSubscriptionValue(String leadId) throws SAXException, ParserConfigurationException, Exception {
        return getSubscriptionValue(retrieve(leadId), false);
    }

    @Override
    public boolean getSubscriptionValue(Lead lead, boolean refreshEntity) throws SAXException, ParserConfigurationException, Exception {

        if (refreshEntity) {
            lead = retrieve(lead.getId());
        }

        if (lead.isDonotemail() != null) {
            return !((boolean) lead.isDonotemail().getValue()); // MS dynamics is do not email
        }

        return false;
    }
}

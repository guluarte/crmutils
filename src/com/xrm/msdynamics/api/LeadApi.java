/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.api;

import com.xrm.msdynamics.Enums.EntityName;
import com.xrm.msdynamics.OrganizationService;
import com.xrm.msdynamics.api.interfaces.ILeadApi;
import com.xrm.msdynamics.crmtypes.ConditionExpression;
import com.xrm.msdynamics.crmtypes.Criteria;
import com.xrm.msdynamics.crmtypes.FilterExpression;
import com.xrm.msdynamics.entities.EntityFactory;
import com.xrm.msdynamics.entities.Lead;
import com.xrm.msdynamics.entities.SavedView;
import com.xrm.msdynamics.entities.View;
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

    public ArrayList<Lead> retrieveWithCriteria(Criteria criteria) throws ParserConfigurationException, Exception {

        Document doc = service.RetrieveMultiple(EntityName.Lead, Lead.LeadColumns, criteria);

        EntityFactory entityFactory = new EntityFactory<>(Lead.class);
        return entityFactory.Build(doc);

    }

    public ArrayList<Lead> retrieveIn(String[] leadsId, String column) throws ParserConfigurationException, Exception {

        if (leadsId.length < 1) {
            return new ArrayList<>();
        }

        ConditionExpression conditionExpression = new ConditionExpression(ConditionExpression.Operator.Equal, column, leadsId[0]);
        FilterExpression filter = new FilterExpression(Criteria.FilterOperator.Or, conditionExpression);

        for (String leadId : leadsId) {
            filter.addCondition(new ConditionExpression(ConditionExpression.Operator.Equal, column, leadId));
        }

        Criteria criteria = new Criteria(Criteria.FilterOperator.And, filter);

        return retrieveWithCriteria(criteria);
    }

    public ArrayList<Lead> retrieveIn(String[] leadsId) throws Exception {
        return retrieveIn(leadsId, Lead.ID_COLUMN);
    }

    public ArrayList<Lead> retrieveInEmailList(String[] emails) throws Exception {
        return retrieveIn(emails, Lead.EMAIL_COLUMN);
    }

    // Returns all views that returns leads
    public ArrayList<View> retrieveLeadViews() throws ParserConfigurationException, Exception {
        
        ConditionExpression conditionExpression = new ConditionExpression(ConditionExpression.Operator.Equal, View.RETURNEDTYPECODE, EntityName.Lead);
        FilterExpression filter = new FilterExpression(Criteria.FilterOperator.Or, conditionExpression);
        Criteria criteria = new Criteria(Criteria.FilterOperator.And, filter);
        
        Document doc = service.RetrieveMultiple(EntityName.View, View.Columns, criteria);

        EntityFactory entityFactory = new EntityFactory<>(View.class);
        
        return entityFactory.Build(doc);
    }
    
    public ArrayList<View> retrieveLeadSavedViews() throws ParserConfigurationException, Exception {
        
        ConditionExpression conditionExpression = new ConditionExpression(ConditionExpression.Operator.Equal, SavedView.RETURNEDTYPECODE, EntityName.Lead);
        FilterExpression filter = new FilterExpression(Criteria.FilterOperator.Or, conditionExpression);
        Criteria criteria = new Criteria(Criteria.FilterOperator.And, filter);
        
        Document doc = service.RetrieveMultiple(EntityName.SavedView, SavedView.Columns, criteria);

        EntityFactory entityFactory = new EntityFactory<>(SavedView.class);
        
        return entityFactory.Build(doc);
    }
    
    public ArrayList<Lead> leadsInView(View view) throws InstantiationException, IllegalAccessException, ParserConfigurationException, Exception {
        
        if(view.getFetchxml() == null || "".equals(view.getFetchxml().toString()) || view.getFetchxml().toString().contains("{0}") ) {
            return new ArrayList<>();
        }
        
        Document doc = service.runFetchXmlQuery(view.getFetchxml().toString());

        EntityFactory entityFactory = new EntityFactory<>(Lead.class);
        
        return entityFactory.Build(doc);
        
    }

}

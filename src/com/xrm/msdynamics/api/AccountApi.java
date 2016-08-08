/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.api;

import com.xrm.msdynamics.Enums;
import com.xrm.msdynamics.Enums.EntityName;
import com.xrm.msdynamics.OrganizationService;
import com.xrm.msdynamics.crmtypes.ConditionExpression;
import com.xrm.msdynamics.crmtypes.Criteria;
import com.xrm.msdynamics.crmtypes.FilterExpression;
import com.xrm.msdynamics.entities.Account;
import com.xrm.msdynamics.entities.EntityFactory;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author rguluarte
 */
public class AccountApi {

    private final OrganizationService service;

    public AccountApi(OrganizationService service) {
        this.service = service;
    }
    
    public ArrayList<Account> retrieveAll() throws SAXException, ParserConfigurationException, Exception {
        Document contactResponseDocument = service.RetrieveAll(Enums.EntityName.Account, Account.AccountColumns, null);
        EntityFactory entityFactory = new EntityFactory<>(Account.class);
        return entityFactory.Build(contactResponseDocument);
    }

    public ArrayList<Account> retrieveWithCriteria(Criteria criteria) throws ParserConfigurationException, Exception {

        Document doc = service.RetrieveMultiple(EntityName.Account, Account.AccountColumns, criteria);

        EntityFactory entityFactory = new EntityFactory<>(Account.class);
        return entityFactory.Build(doc);

    }

    public ArrayList<Account> retrieveIn(String[] accountsId, String column) throws ParserConfigurationException, Exception {

        if (accountsId.length < 1) {
            return new ArrayList<>();
        }

        ConditionExpression conditionExpression = new ConditionExpression(ConditionExpression.Operator.Equal, column, accountsId[0]);
        FilterExpression filter = new FilterExpression(Criteria.FilterOperator.Or, conditionExpression);

        for (String contactId : accountsId) {
            filter.addCondition(new ConditionExpression(ConditionExpression.Operator.Equal, column, contactId));
        }

        Criteria criteria = new Criteria(Criteria.FilterOperator.And, filter);

        return retrieveWithCriteria(criteria);
    }

    public ArrayList<Account> retrieveIn(String[] accountsId) throws Exception {
        return retrieveIn(accountsId, Account.ID_COLUMN);
    }

    public ArrayList<Account> retrieveInEmailList(String[] emails) throws Exception {
        return retrieveIn(emails, Account.EMAIL_ADDRESS);
    }
    
    public Account retrieveByEmailOrCreate(String email, Account defaultAccountValues) throws Exception {
        ArrayList<Account> matchedAccounts =  retrieveIn(new String[] { email} , Account.EMAIL_ADDRESS);
        
        if(matchedAccounts.isEmpty()) {
            
            defaultAccountValues.setEmailAddress(email);
            
            defaultAccountValues.setId(service.Create(defaultAccountValues.toEntity()));
            
            return defaultAccountValues;
            
        } else {
            return matchedAccounts.get(0);
        }
    }
}

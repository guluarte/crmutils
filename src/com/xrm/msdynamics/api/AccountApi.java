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
import com.xrm.msdynamics.entities.Contact;
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

    /**
     * Retrieves all accounts Use with caution
     *
     * @return
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
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

    public Account retreiveAccountByContactEmailAddressOrCreateContact(String email, Contact defaultContactSettings, Account defaultAccountSettings) throws ParserConfigurationException, Exception {
        ArrayList<Account> accounts = retreiveAccountsByContactEmailAddressOrCreateContact(email, defaultContactSettings, defaultAccountSettings);

        return accounts.get(0);
    }

    public ArrayList<Account> retreiveAccountsByContactEmailAddressOrCreateContact(String email, Contact defaultContactSettings, Account defaultAccountSettings) throws ParserConfigurationException, Exception {

        ArrayList<Account> accounts = retreiveAccountsByContactEmailAddresses(new String[]{email});

        if (!accounts.isEmpty()) {
            return accounts;
        }

        // Creates a new account if none found
        defaultAccountSettings.setId(service.Create(defaultAccountSettings.toEntity()));

        defaultContactSettings.setEmailaddress1(email);
        defaultContactSettings.setParentCustomerId(defaultAccountSettings.getId());

        defaultContactSettings.setId(service.Create(defaultContactSettings.toEntity()));

        accounts.add(defaultAccountSettings);

        return accounts;

    }

    public ArrayList<Account> retreiveAccountsByContactEmailAddress(String email) throws ParserConfigurationException, Exception {
        return retreiveAccountsByContactEmailAddresses(new String[]{email});
    }

    /**
     * Retrieves first account that matches the email address in the primary contact
     * or in the account's contacts.
     * @param email
     * @return
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public Account retreiveAccountByContactEmailAddress(String email) throws ParserConfigurationException, Exception {

        ArrayList<Account> accounts = retreiveAccountsByContactEmailAddresses(new String[]{email});

        if (!accounts.isEmpty()) {
            return accounts.get(0);
        }

        return null;
    }

    /**
     * Retrieves all accounts that match the primary contact email address or
     *  the account's contacts email address
     * @param emails
     * @return
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public ArrayList<Account> retreiveAccountsByContactEmailAddresses(String[] emails) throws ParserConfigurationException, Exception {

        ContactApi contactsApi = new ContactApi(service);

        ArrayList<Contact> matchedContacts = contactsApi.retrieveInEmailList(emails);

        ArrayList<String> accountsId = new ArrayList<>();

        for (Contact matchedContact : matchedContacts) {
            if (matchedContact.getParentCustomerId() != null) {
                String accountId = matchedContact.getParentCustomerId().getValue().toString();

                if (!accountsId.contains(accountId)) {
                    accountsId.add(matchedContact.getParentCustomerId().getValue().toString());
                }

            }

            ArrayList<Account> primaryAccounts = retrieveIn(new String[]{matchedContact.getId()}, Account.PRIMARYCONTACT_COLUMN);

            for (Account primaryAccount : primaryAccounts) {
                String accountId = primaryAccount.getId();

                if (!accountsId.contains(accountId)) {
                    accountsId.add(primaryAccount.getId());
                }

            }
        }

        // account primary contacts
        return retrieveIn(accountsId.toArray(new String[accountsId.size()]), Account.ID_COLUMN);

    }

    /**
     * Retrieves all account that match the ids provided
     *
     * @param accountsId
     * @return
     * @throws Exception
     */
    public ArrayList<Account> retrieveIn(String[] accountsId) throws Exception {
        return retrieveIn(accountsId, Account.ID_COLUMN);
    }

    /**
     * Retrieves all accounts that match the email address provided
     *
     * @param emails
     * @return
     * @throws Exception
     */
    public ArrayList<Account> retrieveInEmailList(String[] emails) throws Exception {
        return retrieveIn(emails, Account.EMAIL_ADDRESS);
    }

    /**
     * Retrieves an account by email
     *
     * @param email
     * @return
     * @throws Exception
     */
    public Account retrieveByEmail(String email) throws Exception {
        ArrayList<Account> matchedAccounts = retrieveIn(new String[]{email}, Account.EMAIL_ADDRESS);

        if (!matchedAccounts.isEmpty()) {
            return matchedAccounts.get(0);
        }

        return null;
    }

    /**
     * Retrieves the matched account or creates a new one using the default
     * values
     *
     * @param email
     * @param defaultAccountValues
     * @return
     * @throws Exception
     */
    public Account retrieveByEmailOrCreate(String email, Account defaultAccountValues) throws Exception {
        ArrayList<Account> matchedAccounts = retrieveIn(new String[]{email}, Account.EMAIL_ADDRESS);

        if (matchedAccounts.isEmpty()) {

            defaultAccountValues.setEmailAddress(email);

            defaultAccountValues.setId(service.Create(defaultAccountValues.toEntity()));

            return defaultAccountValues;

        } else {
            return matchedAccounts.get(0);
        }
    }
}

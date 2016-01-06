package com.xrm.msdynamics;

import com.xrm.msdynamics.Enums.EntityName;

import com.xrm.msdynamics.Enums.ParameterName;
import com.xrm.msdynamics.crmtypes.ColumnSet;
import com.xrm.msdynamics.crmtypes.Criteria;
import com.xrm.msdynamics.crmtypes.Entity;
import com.xrm.msdynamics.crmtypes.EntityReference;
import com.xrm.msdynamics.entities.Account;
import com.xrm.msdynamics.entities.BaseEntity;
import com.xrm.msdynamics.entities.Contact;
import com.xrm.msdynamics.entities.EmailTemplate;
import com.xrm.msdynamics.entities.EntityFactory;
import com.xrm.msdynamics.entities.Lead;
import com.xrm.msdynamics.entities.Note;
import com.xrm.msdynamics.entities.SharePointDocument;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author rguluarte
 */
public class OrganizationService {

    private final String crmServerUrl;
    private final CrmAuthenticationHeader authHeader;

    /**
     * Format the date for MS Dynamics
     */
    protected SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private final CrmAuth auth = new CrmAuth();

    /**
     * Instantiates the service with the params passed.
     *
     * @param crmServerUrl
     * @param username - domain\\user for on-premises or user@email.com if it is
     * online
     * @param userPassword
     * @param isOnPremises
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws XPathExpressionException
     * @throws Exception
     */
    public OrganizationService(String crmServerUrl, String username, String userPassword, boolean isOnPremises) throws IOException, SAXException, ParserConfigurationException, XPathExpressionException, Exception {

        this.crmServerUrl = crmServerUrl;

        if (isOnPremises) {
            authHeader = auth.GetHeaderOnPremise(username,
                    userPassword, crmServerUrl);
        } else {
            authHeader = auth.GetHeaderOnline(username,
                    userPassword, crmServerUrl);
        }

    }

    /**
     * Creates an entity in the CRM
     *
     * @param entity
     * @return The id of the created entity
     * @throws IOException
     * @throws MalformedURLException
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public String Create(Entity entity) throws IOException, MalformedURLException, SAXException, ParserConfigurationException, Exception {

        String request = "<s:Body>\n"
                + "   <Execute xmlns=\"http://schemas.microsoft.com/xrm/2011/Contracts/Services\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
                + "     <request i:type=\"a:CreateRequest\" xmlns:a=\"http://schemas.microsoft.com/xrm/2011/Contracts\">\n"
                + "       <a:Parameters xmlns:b=\"http://schemas.datacontract.org/2004/07/System.Collections.Generic\">\n"
                + "\n" + entity.ToXML() + "</a:Parameters>\n"
                + "       <a:RequestId i:nil=\"true\" />\n"
                + "       <a:RequestName>Create</a:RequestName>\n"
                + "     </request>\n"
                + "   </Execute>\n"
                + " </s:Body>";

        Document xDoc = CrmExecuteSoap.ExecuteSoapRequest(authHeader, request, crmServerUrl);

        return getAttributeValue(xDoc, "id");
    }

    /**
     * Retrieves the entity, use service.Retrieve(Account.getLogicalName(),
     * "id", Account.getDefaultColumns())
     *
     * @param entityName
     * @param id
     * @param columns
     * @return Returns a NodeList for creating an entity, use the entity load
     * method to parse.
     * @throws IOException
     * @throws MalformedURLException
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public NodeList Retrieve(String entityName, String id, String[] columns) throws IOException, MalformedURLException, SAXException, ParserConfigurationException, Exception {
        ColumnSet columnSet = new ColumnSet(columns);
        String request = "<s:Body>\n"
                + "                <Execute xmlns=\"http://schemas.microsoft.com/xrm/2011/Contracts/Services\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
                + "                  <request i:type=\"a:RetrieveRequest\" xmlns:a=\"http://schemas.microsoft.com/xrm/2011/Contracts\">\n"
                + "                    <a:Parameters xmlns:b=\"http://schemas.datacontract.org/2004/07/System.Collections.Generic\">\n"
                + "                      <a:KeyValuePairOfstringanyType>\n"
                + "                        <b:key>Target</b:key>\n"
                + "                        <b:value i:type=\"a:EntityReference\">\n"
                + "                          <a:Id>" + id + "</a:Id>\n"
                + "                          <a:LogicalName>" + entityName + "</a:LogicalName>\n"
                + "                          <a:Name i:nil=\"true\" />\n"
                + "                        </b:value>\n"
                + "                      </a:KeyValuePairOfstringanyType>\n"
                + columnSet.ToXML()
                + "                    </a:Parameters>\n"
                + "                    <a:RequestId i:nil=\"true\" />\n"
                + "                    <a:RequestName>Retrieve</a:RequestName>\n"
                + "                  </request>\n"
                + "                </Execute>\n"
                + "              </s:Body>";

        Document xDoc = CrmExecuteSoap.ExecuteSoapRequest(authHeader, request, crmServerUrl);

        return getDocumentAttributes(xDoc);
    }

    /**
     * Updates the entity in the CRM
     *
     * @param entity
     * @throws IOException
     * @throws MalformedURLException
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public void Update(Entity entity) throws IOException, MalformedURLException, SAXException, ParserConfigurationException, Exception {

        String request = "<s:Body>\n"
                + "         <Execute xmlns=\"http://schemas.microsoft.com/xrm/2011/Contracts/Services\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
                + "           <request i:type=\"a:UpdateRequest\" xmlns:a=\"http://schemas.microsoft.com/xrm/2011/Contracts\">\n"
                + "             <a:Parameters xmlns:b=\"http://schemas.datacontract.org/2004/07/System.Collections.Generic\">\n"
                + entity.ToXML()
                + "             </a:Parameters>\n"
                + "             <a:RequestId i:nil=\"true\" />\n"
                + "             <a:RequestName>Update</a:RequestName>\n"
                + "           </request>\n"
                + "         </Execute>\n"
                + "       </s:Body>";

        CrmExecuteSoap.ExecuteSoapRequest(authHeader, request, crmServerUrl);
    }

    /**
     * Deletes the enity in the CRM
     *
     * @param id the Entity Guid
     * @param entityName
     * @throws IOException
     * @throws MalformedURLException
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public void Delete(String id, String entityName) throws IOException, MalformedURLException, SAXException, ParserConfigurationException, Exception {
        String request = "<s:Body>\n"
                + "          <Execute xmlns=\"http://schemas.microsoft.com/xrm/2011/Contracts/Services\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
                + "            <request i:type=\"a:DeleteRequest\" xmlns:a=\"http://schemas.microsoft.com/xrm/2011/Contracts\">\n"
                + "              <a:Parameters xmlns:b=\"http://schemas.datacontract.org/2004/07/System.Collections.Generic\">\n"
                + "                <a:KeyValuePairOfstringanyType>\n"
                + "                  <b:key>Target</b:key>\n"
                + "                  <b:value i:type=\"a:EntityReference\">\n"
                + "                    <a:Id>" + id + "</a:Id>\n"
                + "                    <a:LogicalName>" + entityName + "</a:LogicalName>\n"
                + "                    <a:Name i:nil=\"true\" />\n"
                + "                  </b:value>\n"
                + "                </a:KeyValuePairOfstringanyType>\n"
                + "              </a:Parameters>\n"
                + "              <a:RequestId i:nil=\"true\" />\n"
                + "              <a:RequestName>Delete</a:RequestName>\n"
                + "            </request>\n"
                + "          </Execute>\n"
                + "        </s:Body>";

        CrmExecuteSoap.ExecuteSoapRequest(authHeader, request, crmServerUrl);

    }

    /**
     * Changes the entity state
     *
     * @param Entity reference
     * @param statecode
     * @param statuscode
     * @throws IOException
     * @throws MalformedURLException
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public void SetStateRequest(EntityReference ref, int state, int status) throws IOException, MalformedURLException, SAXException, ParserConfigurationException, Exception {
        String request = "  <s:Body>\n"
                + "    <Execute xmlns=\"http://schemas.microsoft.com/xrm/2011/Contracts/Services\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
                + "      <request i:type=\"b:SetStateRequest\" xmlns:a=\"http://schemas.microsoft.com/xrm/2011/Contracts\" xmlns:b=\"http://schemas.microsoft.com/crm/2011/Contracts\">\n"
                + "        <a:Parameters xmlns:c=\"http://schemas.datacontract.org/2004/07/System.Collections.Generic\">\n"
                + "          <a:KeyValuePairOfstringanyType>\n"
                + "            <c:key>" + ParameterName.EntityMoniker + "</c:key>\n"
                + "            <c:value i:type=\"a:EntityReference\">\n"
                + "              <a:Id>" + ref.getValue() + "</a:Id>\n"
                + "              <a:LogicalName>" + ref.getLogicalName() + "</a:LogicalName>\n"
                + "              <a:Name i:nil=\"true\" />\n"
                + "            </c:value>\n"
                + "          </a:KeyValuePairOfstringanyType>\n"
                + "          <a:KeyValuePairOfstringanyType>\n"
                + "            <c:key>State</c:key>\n"
                + "            <c:value i:type=\"a:OptionSetValue\">\n"
                + "              <a:Value>" + state + "</a:Value>\n"
                + "            </c:value>\n"
                + "          </a:KeyValuePairOfstringanyType>\n"
                + "          <a:KeyValuePairOfstringanyType>\n"
                + "            <c:key>Status</c:key>\n"
                + "            <c:value i:type=\"a:OptionSetValue\">\n"
                + "              <a:Value>" + status + "</a:Value>\n"
                + "            </c:value>\n"
                + "          </a:KeyValuePairOfstringanyType>\n"
                + "        </a:Parameters>\n"
                + "        <a:RequestId i:nil=\"true\" />\n"
                + "        <a:RequestName>SetState</a:RequestName>\n"
                + "      </request>\n"
                + "    </Execute>\n"
                + "  </s:Body>";

        CrmExecuteSoap.ExecuteSoapRequest(authHeader, request, crmServerUrl);
    }

    public Document RetrieveMultiple(String entityName, String[] columns, Criteria criteria) throws SAXException, ParserConfigurationException, Exception {

        StringBuilder columnsString = new StringBuilder();
        for (String column : columns) {
            columnsString.append("<c:string>").append(column).append("</c:string>\n");
        }

        String request = "<s:Body>\n"
                + "    <Execute xmlns=\"http://schemas.microsoft.com/xrm/2011/Contracts/Services\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
                + "      <request i:type=\"a:RetrieveMultipleRequest\" xmlns:a=\"http://schemas.microsoft.com/xrm/2011/Contracts\">\n"
                + "        <a:Parameters xmlns:b=\"http://schemas.datacontract.org/2004/07/System.Collections.Generic\">\n"
                + "          <a:KeyValuePairOfstringanyType>\n"
                + "            <b:key>Query</b:key>\n"
                + "            <b:value i:type=\"a:QueryExpression\">\n"
                + "              <a:ColumnSet>\n"
                + "                <a:AllColumns>false</a:AllColumns>\n"
                + "                <a:Columns xmlns:c=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\">\n"
                + columnsString.toString()
                + "                </a:Columns>\n"
                + "              </a:ColumnSet>\n"
                + criteria.ToXML()
                + "              <a:Distinct>false</a:Distinct>\n"
                + "              <a:EntityName>" + entityName + "</a:EntityName>\n"
                + "              <a:LinkEntities />\n"
                + "              <a:Orders />\n"
                + "              <a:PageInfo>\n"
                + "                <a:Count>0</a:Count>\n"
                + "                <a:PageNumber>0</a:PageNumber>\n"
                + "                <a:PagingCookie i:nil=\"true\" />\n"
                + "                <a:ReturnTotalRecordCount>false</a:ReturnTotalRecordCount>\n"
                + "              </a:PageInfo>\n"
                + "              <a:NoLock>false</a:NoLock>\n"
                + "            </b:value>\n"
                + "          </a:KeyValuePairOfstringanyType>\n"
                + "        </a:Parameters>\n"
                + "        <a:RequestId i:nil=\"true\" />\n"
                + "        <a:RequestName>RetrieveMultiple</a:RequestName>\n"
                + "      </request>\n"
                + "    </Execute>\n"
                + "  </s:Body>";

        return CrmExecuteSoap.ExecuteSoapRequest(authHeader, request, crmServerUrl);
    }

    /**
     * Set the status and state for the entity
     *
     * @param ref
     * @param statusAndState
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public void SetStateRequest(EntityReference ref, BaseEntity.StatusAndStatusReason statusAndState) throws SAXException, ParserConfigurationException, Exception {
        SetStateRequest(ref, statusAndState.State, statusAndState.Status);
    }

    /**
     * Retrieves an account by his name
     *
     * @param name
     * @return
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public Account RetrieveAccountByName(String name) throws SAXException, ParserConfigurationException, Exception {
        String request = "  <s:Body>\n"
                + "    <Execute xmlns=\"http://schemas.microsoft.com/xrm/2011/Contracts/Services\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
                + "      <request i:type=\"a:RetrieveMultipleRequest\" xmlns:a=\"http://schemas.microsoft.com/xrm/2011/Contracts\">\n"
                + "        <a:Parameters xmlns:b=\"http://schemas.datacontract.org/2004/07/System.Collections.Generic\">\n"
                + "          <a:KeyValuePairOfstringanyType>\n"
                + "            <b:key>Query</b:key>\n"
                + "            <b:value i:type=\"a:QueryExpression\">\n"
                + "              <a:ColumnSet>\n"
                + "                <a:AllColumns>true</a:AllColumns>\n"
                + "                <a:Columns xmlns:c=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\" />\n"
                + "              </a:ColumnSet>\n"
                + "              <a:Criteria>\n"
                + "                <a:Conditions>\n"
                + "                  <a:ConditionExpression>\n"
                + "                    <a:AttributeName>name</a:AttributeName>\n"
                + "                    <a:Operator>Equal</a:Operator>\n"
                + "                    <a:Values xmlns:c=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\">\n"
                + "                      <c:anyType i:type=\"d:string\" xmlns:d=\"http://www.w3.org/2001/XMLSchema\">" + name + "</c:anyType>\n"
                + "                    </a:Values>\n"
                + "                  </a:ConditionExpression>\n"
                + "                </a:Conditions>\n"
                + "                <a:FilterOperator>And</a:FilterOperator>\n"
                + "                <a:Filters />\n"
                + "              </a:Criteria>\n"
                + "              <a:Distinct>false</a:Distinct>\n"
                + "              <a:EntityName>account</a:EntityName>\n"
                + "              <a:LinkEntities />\n"
                + "              <a:Orders />\n"
                + "              <a:PageInfo>\n"
                + "                <a:Count>0</a:Count>\n"
                + "                <a:PageNumber>0</a:PageNumber>\n"
                + "                <a:PagingCookie i:nil=\"true\" />\n"
                + "                <a:ReturnTotalRecordCount>false</a:ReturnTotalRecordCount>\n"
                + "              </a:PageInfo>\n"
                + "              <a:NoLock>false</a:NoLock>\n"
                + "            </b:value>\n"
                + "          </a:KeyValuePairOfstringanyType>\n"
                + "        </a:Parameters>\n"
                + "        <a:RequestId i:nil=\"true\" />\n"
                + "        <a:RequestName>RetrieveMultiple</a:RequestName>\n"
                + "      </request>\n"
                + "    </Execute>\n"
                + "  </s:Body>";

        EntityFactory entityFactory = new EntityFactory<>(Account.class);
        Document xDoc = CrmExecuteSoap.ExecuteSoapRequest(authHeader, request, crmServerUrl);
        ArrayList<Account> accounts = entityFactory.Build(xDoc);

        if (accounts.isEmpty()) {
            return null;
        }
        return accounts.get(0);
    }

    /**
     * Retrieves an entity by his name
     *
     * @param name
     * @param column Entity columns to retrieve
     * @param value The name value
     * @return a Document to parse with the entity factory,
     * entityFactory.Build(xDoc);
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public Document RetrieveEntityByNameAndColumnString(String name, String column, String value) throws SAXException, ParserConfigurationException, Exception {
        String request = "  <s:Body>\n"
                + "    <Execute xmlns=\"http://schemas.microsoft.com/xrm/2011/Contracts/Services\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
                + "      <request i:type=\"a:RetrieveMultipleRequest\" xmlns:a=\"http://schemas.microsoft.com/xrm/2011/Contracts\">\n"
                + "        <a:Parameters xmlns:b=\"http://schemas.datacontract.org/2004/07/System.Collections.Generic\">\n"
                + "          <a:KeyValuePairOfstringanyType>\n"
                + "            <b:key>Query</b:key>\n"
                + "            <b:value i:type=\"a:QueryExpression\">\n"
                + "              <a:ColumnSet>\n"
                + "                <a:AllColumns>true</a:AllColumns>\n"
                + "                <a:Columns xmlns:c=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\" />\n"
                + "              </a:ColumnSet>\n"
                + "              <a:Criteria>\n"
                + "                <a:Conditions>\n"
                + "                  <a:ConditionExpression>\n"
                + "                    <a:AttributeName>" + column + "</a:AttributeName>\n"
                + "                    <a:Operator>Equal</a:Operator>\n"
                + "                    <a:Values xmlns:c=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\">\n"
                + "                      <c:anyType i:type=\"d:string\" xmlns:d=\"http://www.w3.org/2001/XMLSchema\">" + value + "</c:anyType>\n"
                + "                    </a:Values>\n"
                + "                  </a:ConditionExpression>\n"
                + "                </a:Conditions>\n"
                + "                <a:FilterOperator>And</a:FilterOperator>\n"
                + "                <a:Filters />\n"
                + "              </a:Criteria>\n"
                + "              <a:Distinct>false</a:Distinct>\n"
                + "              <a:EntityName>" + name + "</a:EntityName>\n"
                + "              <a:LinkEntities />\n"
                + "              <a:Orders />\n"
                + "              <a:PageInfo>\n"
                + "                <a:Count>0</a:Count>\n"
                + "                <a:PageNumber>0</a:PageNumber>\n"
                + "                <a:PagingCookie i:nil=\"true\" />\n"
                + "                <a:ReturnTotalRecordCount>false</a:ReturnTotalRecordCount>\n"
                + "              </a:PageInfo>\n"
                + "              <a:NoLock>false</a:NoLock>\n"
                + "            </b:value>\n"
                + "          </a:KeyValuePairOfstringanyType>\n"
                + "        </a:Parameters>\n"
                + "        <a:RequestId i:nil=\"true\" />\n"
                + "        <a:RequestName>RetrieveMultiple</a:RequestName>\n"
                + "      </request>\n"
                + "    </Execute>\n"
                + "  </s:Body>";

        return CrmExecuteSoap.ExecuteSoapRequest(authHeader, request, crmServerUrl);
    }

    /**
     * Search a contact by their email address
     *
     * @param contactName
     * @return A list of contacts
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public ArrayList<Contact> RetrieveContactByEmail(String contactName) throws InstantiationException, IllegalAccessException, ParserConfigurationException, Exception {
        EntityFactory entityFactory = new EntityFactory<>(Contact.class);
        Document xDoc = RetrieveEntityByNameAndColumnString(EntityName.Contact, Contact.EMAILADDRESS1_COLUMN, contactName);
        ArrayList<Contact> contacts = entityFactory.Build(xDoc);

        if (contacts.isEmpty()) {
            return null;
        }
        return contacts;
    }

    /**
     * Retrieves the contact that match the full name
     *
     * @param contactName
     * @return A list of contacts
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public ArrayList<Contact> RetrieveContactByFullName(String contactName) throws InstantiationException, IllegalAccessException, ParserConfigurationException, Exception {
        EntityFactory entityFactory = new EntityFactory<>(Contact.class);
        Document xDoc = RetrieveEntityByNameAndColumnString(EntityName.Contact, "fullname", contactName);
        ArrayList<Contact> contacts = entityFactory.Build(xDoc);

        if (contacts.isEmpty()) {
            return null;
        }
        return contacts;
    }

    /**
     * Retrieves a list of contacts by their first name
     *
     * @param contactName
     * @return A list of contacts
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public ArrayList<Contact> RetrieveContactByFirstName(String contactName) throws InstantiationException, IllegalAccessException, ParserConfigurationException, Exception {
        EntityFactory entityFactory = new EntityFactory<>(Contact.class);
        Document xDoc = RetrieveEntityByNameAndColumnString(EntityName.Contact, Contact.FIRSTNAME_COLUMN, contactName);
        ArrayList<Contact> contacts = entityFactory.Build(xDoc);

        if (contacts.isEmpty()) {
            return null;
        }
        return contacts;
    }

    /**
     * Retrieves contacts by their last name
     *
     * @param contactName
     * @return List of contacts
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public ArrayList<Contact> RetrieveContactByLastName(String contactName) throws InstantiationException, IllegalAccessException, ParserConfigurationException, Exception {
        EntityFactory entityFactory = new EntityFactory<>(Contact.class);
        Document xDoc = RetrieveEntityByNameAndColumnString(EntityName.Contact, Contact.LASTNAME_COLUMN, contactName);
        ArrayList<Contact> contacts = entityFactory.Build(xDoc);

        if (contacts.isEmpty()) {
            return null;
        }
        return contacts;
    }

    /**
     * Retrieves the lead by his email
     *
     * @param contactName
     * @return A list of leads or null
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public ArrayList<Lead> RetrieveLeadByEmail(String contactName) throws InstantiationException, IllegalAccessException, ParserConfigurationException, Exception {
        EntityFactory entityFactory = new EntityFactory<>(Lead.class);
        Document xDoc = RetrieveEntityByNameAndColumnString(EntityName.Lead, Lead.EMAIL_COLUMN, contactName);
        ArrayList<Lead> contacts = entityFactory.Build(xDoc);

        if (contacts.isEmpty()) {
            return null;
        }
        return contacts;
    }

    /**
     * Retrieves the leads by their full name
     *
     * @param contactName
     * @return A list of leads of null
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public ArrayList<Lead> RetrieveLeadByFullName(String contactName) throws InstantiationException, IllegalAccessException, ParserConfigurationException, Exception {
        EntityFactory entityFactory = new EntityFactory<>(Lead.class);
        Document xDoc = RetrieveEntityByNameAndColumnString(EntityName.Lead, "fullname", contactName);
        ArrayList<Lead> contacts = entityFactory.Build(xDoc);

        if (contacts.isEmpty()) {
            return null;
        }
        return contacts;
    }

    /**
     * Retrieves a list of leads by their first name
     *
     * @param contactName
     * @return A list of leads or null
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public ArrayList<Lead> RetrieveLeadByFirstName(String contactName) throws InstantiationException, IllegalAccessException, ParserConfigurationException, Exception {
        EntityFactory entityFactory = new EntityFactory<>(Lead.class);
        Document xDoc = RetrieveEntityByNameAndColumnString(EntityName.Lead, Lead.FIRSTNAME_COLUMN, contactName);
        ArrayList<Lead> contacts = entityFactory.Build(xDoc);

        if (contacts.isEmpty()) {
            return null;
        }
        return contacts;
    }

    /**
     * Retrieves a list of leads by their last name
     *
     * @param contactName
     * @return A list of leads or null
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public ArrayList<Lead> RetrieveLeadByLastName(String contactName) throws InstantiationException, IllegalAccessException, ParserConfigurationException, Exception {
        EntityFactory entityFactory = new EntityFactory<>(Lead.class);
        Document xDoc = RetrieveEntityByNameAndColumnString(EntityName.Lead, Lead.LASTNAME_COLUMN, contactName);
        ArrayList<Lead> contacts = entityFactory.Build(xDoc);

        if (contacts.isEmpty()) {
            return null;
        }
        return contacts;
    }

    /**
     * Check if an appointment with the same subject and object id exists
     *
     * @param subject
     * @param objectId
     * @return true or false
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public boolean existAppointmentBySubjectAndRegardingObjectId(String subject, String objectId) throws ParserConfigurationException, Exception {
        Document xDoc = SearchActivyBySubjectAndRegardingObjectId(EntityName.Appointment, subject, objectId);
        Node node = getValueOfNodeWithName(":Entities", xDoc.getFirstChild());

        if (node == null) {
            return false;
        }

        if (node.getChildNodes().getLength() > 0) {
            return true;
        }

        return false;
    }

    /**
     * Checks if an email activity with the same subject and object id exists
     *
     * @param subject
     * @param objectId
     * @return true or false
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public boolean existEmailActivyBySubjectAndRegardingObjectId(String subject, String objectId) throws ParserConfigurationException, Exception {
        Document xDoc = SearchActivyBySubjectAndRegardingObjectId(EntityName.Email, subject, objectId);
        Node node = getValueOfNodeWithName(":Entities", xDoc.getFirstChild());

        if (node == null) {
            return false;
        }

        if (node.getChildNodes().getLength() > 0) {
            return true;
        }

        return false;
    }

    /**
     * Search and activity by their subject, entity name and object id
     *
     * @param entityName
     * @param subject
     * @param objectId
     * @return Returns the xml response
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public Document SearchActivyBySubjectAndRegardingObjectId(String entityName, String subject, String objectId) throws SAXException, ParserConfigurationException, Exception {
        String request = "  <s:Body>\n"
                + "    <Execute xmlns=\"http://schemas.microsoft.com/xrm/2011/Contracts/Services\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
                + "      <request i:type=\"a:RetrieveMultipleRequest\" xmlns:a=\"http://schemas.microsoft.com/xrm/2011/Contracts\">\n"
                + "        <a:Parameters xmlns:b=\"http://schemas.datacontract.org/2004/07/System.Collections.Generic\">\n"
                + "          <a:KeyValuePairOfstringanyType>\n"
                + "            <b:key>Query</b:key>\n"
                + "            <b:value i:type=\"a:QueryExpression\">\n"
                + "              <a:ColumnSet>\n"
                + "                <a:AllColumns>true</a:AllColumns>\n"
                + "                <a:Columns xmlns:c=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\" />\n"
                + "              </a:ColumnSet>\n"
                + "              <a:Criteria>\n"
                + "                <a:Conditions>\n"
                + "                  <a:ConditionExpression>\n"
                + "                    <a:AttributeName>subject</a:AttributeName>\n"
                + "                    <a:Operator>Equal</a:Operator>\n"
                + "                    <a:Values xmlns:c=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\">\n"
                + "                      <c:anyType i:type=\"d:string\" xmlns:d=\"http://www.w3.org/2001/XMLSchema\">" + subject + "</c:anyType>\n"
                + "                    </a:Values>\n"
                + "                  </a:ConditionExpression>\n"
                + "                  <a:ConditionExpression>\n"
                + "                    <a:AttributeName>regardingobjectid</a:AttributeName>\n"
                + "                    <a:Operator>Equal</a:Operator>\n"
                + "                    <a:Values xmlns:c=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\">\n"
                + "                      <c:anyType i:type=\"d:guid\" xmlns:d=\"http://schemas.microsoft.com/2003/10/Serialization/\">" + objectId + "</c:anyType>\n"
                + "                    </a:Values>\n"
                + "                  </a:ConditionExpression>\n"
                + "                </a:Conditions>\n"
                + "                <a:FilterOperator>And</a:FilterOperator>\n"
                + "                <a:Filters />\n"
                + "              </a:Criteria>\n"
                + "              <a:Distinct>false</a:Distinct>\n"
                + "              <a:EntityName>" + entityName + "</a:EntityName>\n"
                + "              <a:LinkEntities />\n"
                + "              <a:Orders />\n"
                + "              <a:PageInfo>\n"
                + "                <a:Count>0</a:Count>\n"
                + "                <a:PageNumber>0</a:PageNumber>\n"
                + "                <a:PagingCookie i:nil=\"true\" />\n"
                + "                <a:ReturnTotalRecordCount>false</a:ReturnTotalRecordCount>\n"
                + "              </a:PageInfo>\n"
                + "              <a:NoLock>false</a:NoLock>\n"
                + "            </b:value>\n"
                + "          </a:KeyValuePairOfstringanyType>\n"
                + "        </a:Parameters>\n"
                + "        <a:RequestId i:nil=\"true\" />\n"
                + "        <a:RequestName>RetrieveMultiple</a:RequestName>\n"
                + "      </request>\n"
                + "    </Execute>\n"
                + "  </s:Body>";

        return CrmExecuteSoap.ExecuteSoapRequest(authHeader, request, crmServerUrl);
    }

    /**
     * Creates an appointment with the specified details
     *
     * @param subject
     * @param description
     * @param dateStart
     * @param dateEnd
     * @param location
     * @param organizer
     * @param antendeeLogicalName - eg contact, lead
     * @param antendee
     * @param priority see Enums.EmailPriority
     * @return Returns the document with the xml result
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public Document CreateAppointment(String subject, String description, Date dateStart, Date dateEnd, String location, String organizer, String antendeeLogicalName, String antendee, int priority) throws SAXException, ParserConfigurationException, Exception {
        String request = "  <s:Body>\n"
                + "    <Execute xmlns=\"http://schemas.microsoft.com/xrm/2011/Contracts/Services\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
                + "      <request i:type=\"b:BookRequest\" xmlns:a=\"http://schemas.microsoft.com/xrm/2011/Contracts\" xmlns:b=\"http://schemas.microsoft.com/crm/2011/Contracts\">\n"
                + "        <a:Parameters xmlns:c=\"http://schemas.datacontract.org/2004/07/System.Collections.Generic\">\n"
                + "          <a:KeyValuePairOfstringanyType>\n"
                + "            <c:key>Target</c:key>\n"
                + "            <c:value i:type=\"a:Entity\">\n"
                + "              <a:Attributes>\n"
                + "                <a:KeyValuePairOfstringanyType>\n"
                + "                  <c:key>subject</c:key>\n"
                + "                  <c:value i:type=\"d:string\" xmlns:d=\"http://www.w3.org/2001/XMLSchema\">" + subject + "</c:value>\n"
                + "                </a:KeyValuePairOfstringanyType>\n"
                + "                <a:KeyValuePairOfstringanyType>\n"
                + "                  <c:key>description</c:key>\n"
                + "                  <c:value i:type=\"d:string\" xmlns:d=\"http://www.w3.org/2001/XMLSchema\">" + description + "</c:value>\n"
                + "                </a:KeyValuePairOfstringanyType>\n"
                + "                <a:KeyValuePairOfstringanyType>\n"
                + "                  <c:key>scheduledstart</c:key>\n"
                + "                  <c:value i:type=\"d:dateTime\" xmlns:d=\"http://www.w3.org/2001/XMLSchema\">" + DATE_FORMAT.format(dateStart) + "</c:value>\n"
                + "                </a:KeyValuePairOfstringanyType>\n"
                + "                <a:KeyValuePairOfstringanyType>\n"
                + "                  <c:key>scheduledend</c:key>\n"
                + "                  <c:value i:type=\"d:dateTime\" xmlns:d=\"http://www.w3.org/2001/XMLSchema\">" + DATE_FORMAT.format(dateEnd) + "</c:value>\n"
                + "                </a:KeyValuePairOfstringanyType>\n"
                + "                <a:KeyValuePairOfstringanyType>\n"
                + "                  <c:key>location</c:key>\n"
                + "                  <c:value i:type=\"d:string\" xmlns:d=\"http://www.w3.org/2001/XMLSchema\">" + location + "</c:value>\n"
                + "                </a:KeyValuePairOfstringanyType>\n"
                + "                <a:KeyValuePairOfstringanyType>\n"
                + "                  <c:key>requiredattendees</c:key>\n"
                + "                  <c:value i:type=\"a:EntityCollection\">\n"
                + "                    <a:Entities>\n"
                + "                      <a:Entity>\n"
                + "                        <a:Attributes>\n"
                + "                          <a:KeyValuePairOfstringanyType>\n"
                + "                            <c:key>partyid</c:key>\n"
                + "                            <c:value i:type=\"a:EntityReference\">\n"
                + "                              <a:Id>" + organizer + "</a:Id>\n"
                + "                              <a:LogicalName>systemuser</a:LogicalName>\n"
                + "                              <a:Name i:nil=\"true\" />\n"
                + "                            </c:value>\n"
                + "                          </a:KeyValuePairOfstringanyType>\n"
                + "                        </a:Attributes>\n"
                + "                        <a:EntityState i:nil=\"true\" />\n"
                + "                        <a:FormattedValues />\n"
                + "                        <a:Id>00000000-0000-0000-0000-000000000000</a:Id>\n"
                + "                        <a:LogicalName>activityparty</a:LogicalName>\n"
                + "                        <a:RelatedEntities />\n"
                + "                      </a:Entity>\n"
                + "                      <a:Entity>\n"
                + "                        <a:Attributes>\n"
                + "                          <a:KeyValuePairOfstringanyType>\n"
                + "                            <c:key>partyid</c:key>\n"
                + "                            <c:value i:type=\"a:EntityReference\">\n"
                + "                              <a:Id>" + antendee + "</a:Id>\n"
                + "                              <a:LogicalName>" + antendeeLogicalName + "</a:LogicalName>\n"
                + "                              <a:Name i:nil=\"true\" />\n"
                + "                            </c:value>\n"
                + "                          </a:KeyValuePairOfstringanyType>\n"
                + "                        </a:Attributes>\n"
                + "                        <a:EntityState i:nil=\"true\" />\n"
                + "                        <a:FormattedValues />\n"
                + "                        <a:Id>00000000-0000-0000-0000-000000000000</a:Id>\n"
                + "                        <a:LogicalName>activityparty</a:LogicalName>\n"
                + "                        <a:RelatedEntities />\n"
                + "                      </a:Entity>\n"
                + "                    </a:Entities>\n"
                + "                    <a:EntityName i:nil=\"true\" />\n"
                + "                    <a:MinActiveRowVersion i:nil=\"true\" />\n"
                + "                    <a:MoreRecords>false</a:MoreRecords>\n"
                + "                    <a:PagingCookie i:nil=\"true\" />\n"
                + "                    <a:TotalRecordCount>0</a:TotalRecordCount>\n"
                + "                    <a:TotalRecordCountLimitExceeded>false</a:TotalRecordCountLimitExceeded>\n"
                + "                  </c:value>\n"
                + "                </a:KeyValuePairOfstringanyType>\n"
                + "                <a:KeyValuePairOfstringanyType>\n"
                + "                  <c:key>organizer</c:key>\n"
                + "                  <c:value i:type=\"a:EntityCollection\">\n"
                + "                    <a:Entities>\n"
                + "                      <a:Entity>\n"
                + "                        <a:Attributes>\n"
                + "                          <a:KeyValuePairOfstringanyType>\n"
                + "                            <c:key>partyid</c:key>\n"
                + "                            <c:value i:type=\"a:EntityReference\">\n"
                + "                              <a:Id>" + organizer + "</a:Id>\n"
                + "                              <a:LogicalName>systemuser</a:LogicalName>\n"
                + "                              <a:Name i:nil=\"true\" />\n"
                + "                            </c:value>\n"
                + "                          </a:KeyValuePairOfstringanyType>\n"
                + "                        </a:Attributes>\n"
                + "                        <a:EntityState i:nil=\"true\" />\n"
                + "                        <a:FormattedValues />\n"
                + "                        <a:Id>00000000-0000-0000-0000-000000000000</a:Id>\n"
                + "                        <a:LogicalName>activityparty</a:LogicalName>\n"
                + "                        <a:RelatedEntities />\n"
                + "                      </a:Entity>\n"
                + "                    </a:Entities>\n"
                + "                    <a:EntityName i:nil=\"true\" />\n"
                + "                    <a:MinActiveRowVersion i:nil=\"true\" />\n"
                + "                    <a:MoreRecords>false</a:MoreRecords>\n"
                + "                    <a:PagingCookie i:nil=\"true\" />\n"
                + "                    <a:TotalRecordCount>0</a:TotalRecordCount>\n"
                + "                    <a:TotalRecordCountLimitExceeded>false</a:TotalRecordCountLimitExceeded>\n"
                + "                  </c:value>\n"
                + "                </a:KeyValuePairOfstringanyType>\n"
                + "                <a:KeyValuePairOfstringanyType>\n"
                + "                  <c:key>regardingobjectid</c:key>\n"
                + "                  <c:value i:type=\"a:EntityReference\">\n"
                + "                    <a:Id>" + antendee + "</a:Id>\n"
                + "                    <a:LogicalName>" + antendeeLogicalName + "</a:LogicalName>\n"
                + "                    <a:Name i:nil=\"true\" />\n"
                + "                  </c:value>\n"
                + "                </a:KeyValuePairOfstringanyType>\n"
                + "                <a:KeyValuePairOfstringanyType>\n"
                + "                  <c:key>prioritycode</c:key>\n"
                + "                  <c:value i:type=\"a:OptionSetValue\">\n"
                + "                    <a:Value>" + priority + "</a:Value>\n"
                + "                  </c:value>\n"
                + "                </a:KeyValuePairOfstringanyType>\n"
                + "              </a:Attributes>\n"
                + "              <a:EntityState i:nil=\"true\" />\n"
                + "              <a:FormattedValues />\n"
                + "              <a:Id>00000000-0000-0000-0000-000000000000</a:Id>\n"
                + "              <a:LogicalName>appointment</a:LogicalName>\n"
                + "              <a:RelatedEntities />\n"
                + "            </c:value>\n"
                + "          </a:KeyValuePairOfstringanyType>\n"
                + "        </a:Parameters>\n"
                + "        <a:RequestId i:nil=\"true\" />\n"
                + "        <a:RequestName>Book</a:RequestName>\n"
                + "      </request>\n"
                + "    </Execute>\n"
                + "  </s:Body>";

        return CrmExecuteSoap.ExecuteSoapRequest(authHeader, request, crmServerUrl);
    }

    /**
     * Creates an appointment
     *
     * @param entityName - ig contact or lead
     * @param toId
     * @param fromId
     * @param subject
     * @param body
     * @param priority - see Enums.EmailPriority
     * @return
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public String CreateEmailActivity(String entityName, String toId, String fromId, String subject, String body, int priority) throws SAXException, ParserConfigurationException, Exception, SAXException, SAXException, SAXException, SAXException, SAXException, SAXException, SAXException, ParserConfigurationException {
        String request = "  <s:Body>\n"
                + "    <Execute xmlns=\"http://schemas.microsoft.com/xrm/2011/Contracts/Services\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
                + "      <request i:type=\"a:CreateRequest\" xmlns:a=\"http://schemas.microsoft.com/xrm/2011/Contracts\">\n"
                + "        <a:Parameters xmlns:b=\"http://schemas.datacontract.org/2004/07/System.Collections.Generic\">\n"
                + "          <a:KeyValuePairOfstringanyType>\n"
                + "            <b:key>Target</b:key>\n"
                + "            <b:value i:type=\"a:Entity\">\n"
                + "              <a:Attributes>\n"
                + "                <a:KeyValuePairOfstringanyType>\n"
                + "                  <b:key>to</b:key>\n"
                + "                  <b:value i:type=\"a:EntityCollection\">\n"
                + "                    <a:Entities>\n"
                + "                      <a:Entity>\n"
                + "                        <a:Attributes>\n"
                + "                          <a:KeyValuePairOfstringanyType>\n"
                + "                            <b:key>partyid</b:key>\n"
                + "                            <b:value i:type=\"a:EntityReference\">\n"
                + "                              <a:Id>" + toId + "</a:Id>\n"
                + "                              <a:LogicalName>" + entityName + "</a:LogicalName>\n"
                + "                              <a:Name i:nil=\"true\" />\n"
                + "                            </b:value>\n"
                + "                          </a:KeyValuePairOfstringanyType>\n"
                + "                        </a:Attributes>\n"
                + "                        <a:EntityState i:nil=\"true\" />\n"
                + "                        <a:FormattedValues />\n"
                + "                        <a:Id>00000000-0000-0000-0000-000000000000</a:Id>\n"
                + "                        <a:LogicalName>activityparty</a:LogicalName>\n"
                + "                        <a:RelatedEntities />\n"
                + "                      </a:Entity>\n"
                + "                    </a:Entities>\n"
                + "                    <a:EntityName i:nil=\"true\" />\n"
                + "                    <a:MinActiveRowVersion i:nil=\"true\" />\n"
                + "                    <a:MoreRecords>false</a:MoreRecords>\n"
                + "                    <a:PagingCookie i:nil=\"true\" />\n"
                + "                    <a:TotalRecordCount>0</a:TotalRecordCount>\n"
                + "                    <a:TotalRecordCountLimitExceeded>false</a:TotalRecordCountLimitExceeded>\n"
                + "                  </b:value>\n"
                + "                </a:KeyValuePairOfstringanyType>\n"
                + "                <a:KeyValuePairOfstringanyType>\n"
                + "                  <b:key>from</b:key>\n"
                + "                  <b:value i:type=\"a:EntityCollection\">\n"
                + "                    <a:Entities>\n"
                + "                      <a:Entity>\n"
                + "                        <a:Attributes>\n"
                + "                          <a:KeyValuePairOfstringanyType>\n"
                + "                            <b:key>partyid</b:key>\n"
                + "                            <b:value i:type=\"a:EntityReference\">\n"
                + "                              <a:Id>" + fromId + "</a:Id>\n"
                + "                              <a:LogicalName>systemuser</a:LogicalName>\n"
                + "                              <a:Name i:nil=\"true\" />\n"
                + "                            </b:value>\n"
                + "                          </a:KeyValuePairOfstringanyType>\n"
                + "                        </a:Attributes>\n"
                + "                        <a:EntityState i:nil=\"true\" />\n"
                + "                        <a:FormattedValues />\n"
                + "                        <a:Id>00000000-0000-0000-0000-000000000000</a:Id>\n"
                + "                        <a:LogicalName>activityparty</a:LogicalName>\n"
                + "                        <a:RelatedEntities />\n"
                + "                      </a:Entity>\n"
                + "                    </a:Entities>\n"
                + "                    <a:EntityName i:nil=\"true\" />\n"
                + "                    <a:MinActiveRowVersion i:nil=\"true\" />\n"
                + "                    <a:MoreRecords>false</a:MoreRecords>\n"
                + "                    <a:PagingCookie i:nil=\"true\" />\n"
                + "                    <a:TotalRecordCount>0</a:TotalRecordCount>\n"
                + "                    <a:TotalRecordCountLimitExceeded>false</a:TotalRecordCountLimitExceeded>\n"
                + "                  </b:value>\n"
                + "                </a:KeyValuePairOfstringanyType>\n"
                + "                <a:KeyValuePairOfstringanyType>\n"
                + "                  <b:key>subject</b:key>\n"
                + "                  <b:value i:type=\"c:string\" xmlns:c=\"http://www.w3.org/2001/XMLSchema\">" + subject + "</b:value>\n"
                + "                </a:KeyValuePairOfstringanyType>\n"
                + "                <a:KeyValuePairOfstringanyType>\n"
                + "                  <b:key>description</b:key>\n"
                + "                  <b:value i:type=\"c:string\" xmlns:c=\"http://www.w3.org/2001/XMLSchema\">" + body + "</b:value>\n"
                + "                </a:KeyValuePairOfstringanyType>\n"
                + "                <a:KeyValuePairOfstringanyType>\n"
                + "                  <b:key>directioncode</b:key>\n"
                + "                  <b:value i:type=\"c:boolean\" xmlns:c=\"http://www.w3.org/2001/XMLSchema\">true</b:value>\n"
                + "                </a:KeyValuePairOfstringanyType>\n"
                + "                <a:KeyValuePairOfstringanyType>\n"
                + "                  <b:key>prioritycode</b:key>\n"
                + "                  <b:value i:type=\"a:OptionSetValue\">\n"
                + "                    <a:Value>" + priority + "</a:Value>\n"
                + "                  </b:value>\n"
                + "                </a:KeyValuePairOfstringanyType>\n"
                + "                <a:KeyValuePairOfstringanyType>\n"
                + "                  <b:key>regardingobjectid</b:key>\n"
                + "                  <b:value i:type=\"a:EntityReference\">\n"
                + "                    <a:Id>" + toId + "</a:Id>\n"
                + "                    <a:LogicalName>" + entityName + "</a:LogicalName>\n"
                + "                    <a:Name i:nil=\"true\" />\n"
                + "                  </b:value>\n"
                + "                </a:KeyValuePairOfstringanyType>\n"
                + "              </a:Attributes>\n"
                + "              <a:EntityState i:nil=\"true\" />\n"
                + "              <a:FormattedValues />\n"
                + "              <a:Id>00000000-0000-0000-0000-000000000000</a:Id>\n"
                + "              <a:LogicalName>email</a:LogicalName>\n"
                + "              <a:RelatedEntities />\n"
                + "            </b:value>\n"
                + "          </a:KeyValuePairOfstringanyType>\n"
                + "        </a:Parameters>\n"
                + "        <a:RequestId i:nil=\"true\" />\n"
                + "        <a:RequestName>Create</a:RequestName>\n"
                + "      </request>\n"
                + "    </Execute>\n"
                + "  </s:Body>";

        Document xDoc = CrmExecuteSoap.ExecuteSoapRequest(authHeader, request, crmServerUrl);

        if (xDoc == null) {
            return null;
        }

        return getAttributeValue(xDoc, "id");
    }

    /**
     * Creates a custom email activity with a timestamp NOTE This need the
     * custom xLiveHive solution installed in the CRM to work.
     *
     * @param entityName
     * @param toId
     * @param fromId
     * @param subject
     * @param body
     * @param priority
     * @param timestamp
     * @return
     */
    public String CreateCustomEmailActivity(String entityName, String toId, String fromId, String subject, String body, int priority, Date timestamp) throws SAXException, ParserConfigurationException, Exception {

        String request = "<s:Body>\n"
                + "    <Execute xmlns=\"http://schemas.microsoft.com/xrm/2011/Contracts/Services\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
                + "      <request i:type=\"a:CreateRequest\" xmlns:a=\"http://schemas.microsoft.com/xrm/2011/Contracts\">\n"
                + "        <a:Parameters xmlns:b=\"http://schemas.datacontract.org/2004/07/System.Collections.Generic\">\n"
                + "          <a:KeyValuePairOfstringanyType>\n"
                + "            <b:key>Target</b:key>\n"
                + "            <b:value i:type=\"a:Entity\">\n"
                + "              <a:Attributes>\n"
                + "                <a:KeyValuePairOfstringanyType>\n"
                + "                  <b:key>to</b:key>\n"
                + "                  <b:value i:type=\"a:EntityCollection\">\n"
                + "                    <a:Entities>\n"
                + "                      <a:Entity>\n"
                + "                        <a:Attributes>\n"
                + "                          <a:KeyValuePairOfstringanyType>\n"
                + "                            <b:key>partyid</b:key>\n"
                + "                            <b:value i:type=\"a:EntityReference\">\n"
                + "                              <a:Id>" + toId + "</a:Id>\n"
                + "                              <a:LogicalName>" + entityName + "</a:LogicalName>\n"
                + "                              <a:Name i:nil=\"true\" />\n"
                + "                            </b:value>\n"
                + "                          </a:KeyValuePairOfstringanyType>\n"
                + "                        </a:Attributes>\n"
                + "                        <a:EntityState i:nil=\"true\" />\n"
                + "                        <a:FormattedValues />\n"
                + "                        <a:Id>00000000-0000-0000-0000-000000000000</a:Id>\n"
                + "                        <a:LogicalName>activityparty</a:LogicalName>\n"
                + "                        <a:RelatedEntities />\n"
                + "                      </a:Entity>\n"
                + "                    </a:Entities>\n"
                + "                    <a:EntityName i:nil=\"true\" />\n"
                + "                    <a:MinActiveRowVersion i:nil=\"true\" />\n"
                + "                    <a:MoreRecords>false</a:MoreRecords>\n"
                + "                    <a:PagingCookie i:nil=\"true\" />\n"
                + "                    <a:TotalRecordCount>0</a:TotalRecordCount>\n"
                + "                    <a:TotalRecordCountLimitExceeded>false</a:TotalRecordCountLimitExceeded>\n"
                + "                  </b:value>\n"
                + "                </a:KeyValuePairOfstringanyType>\n"
                + "                <a:KeyValuePairOfstringanyType>\n"
                + "                  <b:key>from</b:key>\n"
                + "                  <b:value i:type=\"a:EntityCollection\">\n"
                + "                    <a:Entities>\n"
                + "                      <a:Entity>\n"
                + "                        <a:Attributes>\n"
                + "                          <a:KeyValuePairOfstringanyType>\n"
                + "                            <b:key>partyid</b:key>\n"
                + "                            <b:value i:type=\"a:EntityReference\">\n"
                + "                              <a:Id>" + fromId + "</a:Id>\n"
                + "                              <a:LogicalName>systemuser</a:LogicalName>\n"
                + "                              <a:Name i:nil=\"true\" />\n"
                + "                            </b:value>\n"
                + "                          </a:KeyValuePairOfstringanyType>\n"
                + "                        </a:Attributes>\n"
                + "                        <a:EntityState i:nil=\"true\" />\n"
                + "                        <a:FormattedValues />\n"
                + "                        <a:Id>00000000-0000-0000-0000-000000000000</a:Id>\n"
                + "                        <a:LogicalName>activityparty</a:LogicalName>\n"
                + "                        <a:RelatedEntities />\n"
                + "                      </a:Entity>\n"
                + "                    </a:Entities>\n"
                + "                    <a:EntityName i:nil=\"true\" />\n"
                + "                    <a:MinActiveRowVersion i:nil=\"true\" />\n"
                + "                    <a:MoreRecords>false</a:MoreRecords>\n"
                + "                    <a:PagingCookie i:nil=\"true\" />\n"
                + "                    <a:TotalRecordCount>0</a:TotalRecordCount>\n"
                + "                    <a:TotalRecordCountLimitExceeded>false</a:TotalRecordCountLimitExceeded>\n"
                + "                  </b:value>\n"
                + "                </a:KeyValuePairOfstringanyType>\n"
                + "                <a:KeyValuePairOfstringanyType>\n"
                + "                  <b:key>subject</b:key>\n"
                + "                  <b:value i:type=\"c:string\" xmlns:c=\"http://www.w3.org/2001/XMLSchema\">" + subject + "</b:value>\n"
                + "                </a:KeyValuePairOfstringanyType>\n"
                + "                <a:KeyValuePairOfstringanyType>\n"
                + "                  <b:key>description</b:key>\n"
                + "                  <b:value i:type=\"c:string\" xmlns:c=\"http://www.w3.org/2001/XMLSchema\">" + body + "</b:value>\n"
                + "                </a:KeyValuePairOfstringanyType>\n"
                + "                <a:KeyValuePairOfstringanyType>\n"
                + "                  <b:key>directioncode</b:key>\n"
                + "                  <b:value i:type=\"c:boolean\" xmlns:c=\"http://www.w3.org/2001/XMLSchema\">true</b:value>\n"
                + "                </a:KeyValuePairOfstringanyType>\n"
                + "                <a:KeyValuePairOfstringanyType>\n"
                + "                  <b:key>prioritycode</b:key>\n"
                + "                  <b:value i:type=\"a:OptionSetValue\">\n"
                + "                    <a:Value>" + priority + "</a:Value>\n"
                + "                  </b:value>\n"
                + "                </a:KeyValuePairOfstringanyType>\n"
                + "                <a:KeyValuePairOfstringanyType>\n"
                + "                  <b:key>regardingobjectid</b:key>\n"
                + "                  <b:value i:type=\"a:EntityReference\">\n"
                + "                    <a:Id>" + toId + "</a:Id>\n"
                + "                    <a:LogicalName>" + entityName + "</a:LogicalName>\n"
                + "                    <a:Name i:nil=\"true\" />\n"
                + "                  </b:value>\n"
                + "                </a:KeyValuePairOfstringanyType>\n"
                + "                <a:KeyValuePairOfstringanyType>\n"
                + "                  <b:key>xrm_timestamp</b:key>\n"
                + "                  <b:value i:type=\"c:dateTime\" xmlns:c=\"http://www.w3.org/2001/XMLSchema\">" + DATE_FORMAT.format(timestamp) + "</b:value>\n"
                + "                </a:KeyValuePairOfstringanyType>\n"
                + "              </a:Attributes>\n"
                + "              <a:EntityState i:nil=\"true\" />\n"
                + "              <a:FormattedValues />\n"
                + "              <a:Id>00000000-0000-0000-0000-000000000000</a:Id>\n"
                + "              <a:LogicalName>email</a:LogicalName>\n"
                + "              <a:RelatedEntities />\n"
                + "            </b:value>\n"
                + "          </a:KeyValuePairOfstringanyType>\n"
                + "        </a:Parameters>\n"
                + "        <a:RequestId i:nil=\"true\" />\n"
                + "        <a:RequestName>Create</a:RequestName>\n"
                + "      </request>\n"
                + "    </Execute>\n"
                + "  </s:Body>";

        Document xDoc = CrmExecuteSoap.ExecuteSoapRequest(authHeader, request, crmServerUrl);

        if (xDoc == null) {
            return null;
        }

        return getAttributeValue(xDoc, "id");
    }

    /**
     * Returns all SharePointDocuments avaliable to the user
     *
     * @param userId
     * @return A list of SharePointDocuments or null
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public ArrayList<SharePointDocument> RetrieveDocumentsByUserId(String userId) throws SAXException, ParserConfigurationException, Exception {
        String request = "  <s:Body>\n"
                + "    <Execute xmlns=\"http://schemas.microsoft.com/xrm/2011/Contracts/Services\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
                + "      <request i:type=\"a:RetrieveMultipleRequest\" xmlns:a=\"http://schemas.microsoft.com/xrm/2011/Contracts\">\n"
                + "        <a:Parameters xmlns:b=\"http://schemas.datacontract.org/2004/07/System.Collections.Generic\">\n"
                + "          <a:KeyValuePairOfstringanyType>\n"
                + "            <b:key>Query</b:key>\n"
                + "            <b:value i:type=\"a:QueryExpression\">\n"
                + "              <a:ColumnSet>\n"
                + "                <a:AllColumns>true</a:AllColumns>\n"
                + "                <a:Columns xmlns:c=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\" />\n"
                + "              </a:ColumnSet>\n"
                + "              <a:Criteria>\n"
                + "                <a:Conditions>\n"
                + "                  <a:ConditionExpression>\n"
                + "                    <a:AttributeName>owninguser</a:AttributeName>\n"
                + "                    <a:Operator>Equal</a:Operator>\n"
                + "                    <a:Values xmlns:c=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\">\n"
                + "                      <c:anyType i:type=\"d:guid\" xmlns:d=\"http://schemas.microsoft.com/2003/10/Serialization/\">" + userId + "</c:anyType>\n"
                + "                    </a:Values>\n"
                + "                  </a:ConditionExpression>\n"
                + "                </a:Conditions>\n"
                + "                <a:FilterOperator>And</a:FilterOperator>\n"
                + "                <a:Filters />\n"
                + "              </a:Criteria>\n"
                + "              <a:Distinct>false</a:Distinct>\n"
                + "              <a:EntityName>sharepointdocument</a:EntityName>\n"
                + "              <a:LinkEntities />\n"
                + "              <a:Orders />\n"
                + "              <a:PageInfo>\n"
                + "                <a:Count>0</a:Count>\n"
                + "                <a:PageNumber>0</a:PageNumber>\n"
                + "                <a:PagingCookie i:nil=\"true\" />\n"
                + "                <a:ReturnTotalRecordCount>false</a:ReturnTotalRecordCount>\n"
                + "              </a:PageInfo>\n"
                + "              <a:NoLock>false</a:NoLock>\n"
                + "            </b:value>\n"
                + "          </a:KeyValuePairOfstringanyType>\n"
                + "        </a:Parameters>\n"
                + "        <a:RequestId i:nil=\"true\" />\n"
                + "        <a:RequestName>RetrieveMultiple</a:RequestName>\n"
                + "      </request>\n"
                + "    </Execute>\n"
                + "  </s:Body>";

        EntityFactory entityFactory = new EntityFactory<>(SharePointDocument.class);
        Document xDoc = CrmExecuteSoap.ExecuteSoapRequest(authHeader, request, crmServerUrl);
        return entityFactory.Build(xDoc);
    }

    /**
     * Retrieves all email templates avaliable to the user id
     *
     * @param userId
     * @return A list of EmailTemplates or null
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public ArrayList<EmailTemplate> RetrieveTemplatesOwnedByUserId(String userId) throws SAXException, ParserConfigurationException, Exception {
        String request = "  <s:Body>\n"
                + "    <Execute xmlns=\"http://schemas.microsoft.com/xrm/2011/Contracts/Services\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
                + "      <request i:type=\"a:RetrieveMultipleRequest\" xmlns:a=\"http://schemas.microsoft.com/xrm/2011/Contracts\">\n"
                + "        <a:Parameters xmlns:b=\"http://schemas.datacontract.org/2004/07/System.Collections.Generic\">\n"
                + "          <a:KeyValuePairOfstringanyType>\n"
                + "            <b:key>Query</b:key>\n"
                + "            <b:value i:type=\"a:QueryExpression\">\n"
                + "              <a:ColumnSet>\n"
                + "                <a:AllColumns>true</a:AllColumns>\n"
                + "                <a:Columns xmlns:c=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\" />\n"
                + "              </a:ColumnSet>\n"
                + "              <a:Criteria>\n"
                + "                <a:Conditions>\n"
                + "                  <a:ConditionExpression>\n"
                + "                    <a:AttributeName>ownerid</a:AttributeName>\n"
                + "                    <a:Operator>Equal</a:Operator>\n"
                + "                    <a:Values xmlns:c=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\">\n"
                + "                      <c:anyType i:type=\"d:guid\" xmlns:d=\"http://schemas.microsoft.com/2003/10/Serialization/\">" + userId + "</c:anyType>\n"
                + "                    </a:Values>\n"
                + "                  </a:ConditionExpression>\n"
                + "                </a:Conditions>\n"
                + "                <a:FilterOperator>And</a:FilterOperator>\n"
                + "                <a:Filters />\n"
                + "              </a:Criteria>\n"
                + "              <a:Distinct>false</a:Distinct>\n"
                + "              <a:EntityName>template</a:EntityName>\n"
                + "              <a:LinkEntities />\n"
                + "              <a:Orders />\n"
                + "              <a:PageInfo>\n"
                + "                <a:Count>0</a:Count>\n"
                + "                <a:PageNumber>0</a:PageNumber>\n"
                + "                <a:PagingCookie i:nil=\"true\" />\n"
                + "                <a:ReturnTotalRecordCount>false</a:ReturnTotalRecordCount>\n"
                + "              </a:PageInfo>\n"
                + "              <a:NoLock>false</a:NoLock>\n"
                + "            </b:value>\n"
                + "          </a:KeyValuePairOfstringanyType>\n"
                + "        </a:Parameters>\n"
                + "        <a:RequestId i:nil=\"true\" />\n"
                + "        <a:RequestName>RetrieveMultiple</a:RequestName>\n"
                + "      </request>\n"
                + "    </Execute>\n"
                + "  </s:Body>";

        EntityFactory entityFactory = new EntityFactory<>(EmailTemplate.class);
        Document xDoc = CrmExecuteSoap.ExecuteSoapRequest(authHeader, request, crmServerUrl);
        return entityFactory.Build(xDoc);
    }

    /**
     * Returns all notes avaliable to the user executing the request
     *
     * @return A list of notes
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public ArrayList<Note> RetrieveAllNoteDocuments() throws SAXException, ParserConfigurationException, Exception {
        String request = "  <s:Body>\n"
                + "    <Execute xmlns=\"http://schemas.microsoft.com/xrm/2011/Contracts/Services\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
                + "      <request i:type=\"a:RetrieveMultipleRequest\" xmlns:a=\"http://schemas.microsoft.com/xrm/2011/Contracts\">\n"
                + "        <a:Parameters xmlns:b=\"http://schemas.datacontract.org/2004/07/System.Collections.Generic\">\n"
                + "          <a:KeyValuePairOfstringanyType>\n"
                + "            <b:key>Query</b:key>\n"
                + "            <b:value i:type=\"a:QueryExpression\">\n"
                + "              <a:ColumnSet>\n"
                + "                <a:AllColumns>true</a:AllColumns>\n"
                + "                <a:Columns xmlns:c=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\" />\n"
                + "              </a:ColumnSet>\n"
                + "              <a:Criteria>\n"
                + "                <a:Conditions>\n"
                + "                  <a:ConditionExpression>\n"
                + "                    <a:AttributeName>isdocument</a:AttributeName>\n"
                + "                    <a:Operator>Equal</a:Operator>\n"
                + "                    <a:Values xmlns:c=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\">\n"
                + "                      <c:anyType i:type=\"d:boolean\" xmlns:d=\"http://www.w3.org/2001/XMLSchema\">true</c:anyType>\n"
                + "                    </a:Values>\n"
                + "                  </a:ConditionExpression>\n"
                + "                </a:Conditions>\n"
                + "                <a:FilterOperator>And</a:FilterOperator>\n"
                + "                <a:Filters />\n"
                + "              </a:Criteria>\n"
                + "              <a:Distinct>false</a:Distinct>\n"
                + "              <a:EntityName>annotation</a:EntityName>\n"
                + "              <a:LinkEntities />\n"
                + "              <a:Orders />\n"
                + "              <a:PageInfo>\n"
                + "                <a:Count>0</a:Count>\n"
                + "                <a:PageNumber>0</a:PageNumber>\n"
                + "                <a:PagingCookie i:nil=\"true\" />\n"
                + "                <a:ReturnTotalRecordCount>false</a:ReturnTotalRecordCount>\n"
                + "              </a:PageInfo>\n"
                + "              <a:NoLock>false</a:NoLock>\n"
                + "            </b:value>\n"
                + "          </a:KeyValuePairOfstringanyType>\n"
                + "        </a:Parameters>\n"
                + "        <a:RequestId i:nil=\"true\" />\n"
                + "        <a:RequestName>RetrieveMultiple</a:RequestName>\n"
                + "      </request>\n"
                + "    </Execute>\n"
                + "  </s:Body>";
        EntityFactory entityFactory = new EntityFactory<>(Note.class);
        Document xDoc = CrmExecuteSoap.ExecuteSoapRequest(authHeader, request, crmServerUrl);
        return entityFactory.Build(xDoc);
    }

    /**
     * Retrieves all email templates avaliable to the user executing the request
     *
     * @return A list of emailtemplates or null
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public ArrayList<EmailTemplate> RetrieveTemplatesAccesible() throws SAXException, ParserConfigurationException, Exception {
        String request = "  <s:Body>\n"
                + "    <Execute xmlns=\"http://schemas.microsoft.com/xrm/2011/Contracts/Services\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
                + "      <request i:type=\"a:RetrieveMultipleRequest\" xmlns:a=\"http://schemas.microsoft.com/xrm/2011/Contracts\">\n"
                + "        <a:Parameters xmlns:b=\"http://schemas.datacontract.org/2004/07/System.Collections.Generic\">\n"
                + "          <a:KeyValuePairOfstringanyType>\n"
                + "            <b:key>Query</b:key>\n"
                + "            <b:value i:type=\"a:QueryExpression\">\n"
                + "              <a:ColumnSet>\n"
                + "                <a:AllColumns>true</a:AllColumns>\n"
                + "                <a:Columns xmlns:c=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\" />\n"
                + "              </a:ColumnSet>\n"
                + "              <a:Criteria>\n"
                + "                <a:Conditions>\n"
                + "                  <a:ConditionExpression>\n"
                + "                    <a:AttributeName>ispersonal</a:AttributeName>\n"
                + "                    <a:Operator>Equal</a:Operator>\n"
                + "                    <a:Values xmlns:c=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\">\n"
                + "                      <c:anyType i:type=\"d:boolean\" xmlns:d=\"http://www.w3.org/2001/XMLSchema\">false</c:anyType>\n"
                + "                    </a:Values>\n"
                + "                  </a:ConditionExpression>\n"
                + "                </a:Conditions>\n"
                + "                <a:FilterOperator>And</a:FilterOperator>\n"
                + "                <a:Filters />\n"
                + "              </a:Criteria>\n"
                + "              <a:Distinct>false</a:Distinct>\n"
                + "              <a:EntityName>template</a:EntityName>\n"
                + "              <a:LinkEntities />\n"
                + "              <a:Orders />\n"
                + "              <a:PageInfo>\n"
                + "                <a:Count>0</a:Count>\n"
                + "                <a:PageNumber>0</a:PageNumber>\n"
                + "                <a:PagingCookie i:nil=\"true\" />\n"
                + "                <a:ReturnTotalRecordCount>false</a:ReturnTotalRecordCount>\n"
                + "              </a:PageInfo>\n"
                + "              <a:NoLock>false</a:NoLock>\n"
                + "            </b:value>\n"
                + "          </a:KeyValuePairOfstringanyType>\n"
                + "        </a:Parameters>\n"
                + "        <a:RequestId i:nil=\"true\" />\n"
                + "        <a:RequestName>RetrieveMultiple</a:RequestName>\n"
                + "      </request>\n"
                + "    </Execute>\n"
                + "  </s:Body>";

        EntityFactory entityFactory = new EntityFactory<>(EmailTemplate.class);
        Document xDoc = CrmExecuteSoap.ExecuteSoapRequest(authHeader, request, crmServerUrl);
        return entityFactory.Build(xDoc);
    }

    /**
     * Retrieves all entities that match the criteria
     *
     * @param entityName
     * @param columns
     * @param criteria
     * @return The xml document
     * @throws IOException
     * @throws MalformedURLException
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public Document RetrieveAll(String entityName, String[] columns, Criteria criteria) throws IOException, MalformedURLException, SAXException, ParserConfigurationException, Exception {

        StringBuilder columnsString = new StringBuilder();
        for (String column : columns) {
            columnsString.append("<c:string>").append(column).append("</c:string>\n");
        }
        String request = "<s:Body>\n"
                + "    <Execute xmlns=\"http://schemas.microsoft.com/xrm/2011/Contracts/Services\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
                + "      <request i:type=\"a:RetrieveMultipleRequest\" xmlns:a=\"http://schemas.microsoft.com/xrm/2011/Contracts\">\n"
                + "        <a:Parameters xmlns:b=\"http://schemas.datacontract.org/2004/07/System.Collections.Generic\">\n"
                + "          <a:KeyValuePairOfstringanyType>\n"
                + "            <b:key>Query</b:key>\n"
                + "            <b:value i:type=\"a:QueryExpression\">\n"
                + "              <a:ColumnSet>\n"
                + "                <a:AllColumns>false</a:AllColumns>\n"
                + "                <a:Columns xmlns:c=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\">\n"
                + columnsString.toString()
                + "                </a:Columns>\n"
                + "              </a:ColumnSet>\n"
                + "              <a:Distinct>false</a:Distinct>\n"
                + "              <a:EntityName>" + entityName + "</a:EntityName>\n"
                + "              <a:LinkEntities />\n"
                + "              <a:Orders />\n"
                + "              <a:PageInfo>\n"
                + "                <a:Count>0</a:Count>\n"
                + "                <a:PageNumber>0</a:PageNumber>\n"
                + "                <a:PagingCookie i:nil=\"true\" />\n"
                + "                <a:ReturnTotalRecordCount>false</a:ReturnTotalRecordCount>\n"
                + "              </a:PageInfo>\n"
                + "              <a:NoLock>false</a:NoLock>\n"
                + "            </b:value>\n"
                + "          </a:KeyValuePairOfstringanyType>\n"
                + "        </a:Parameters>\n"
                + "        <a:RequestId i:nil=\"true\" />\n"
                + "        <a:RequestName>RetrieveMultiple</a:RequestName>\n"
                + "      </request>\n"
                + "    </Execute>\n"
                + "  </s:Body>";

        Document xDoc = CrmExecuteSoap.ExecuteSoapRequest(authHeader, request, crmServerUrl);

        return xDoc;
    }

    /**
     * Retrieves the userId of the user executing the request
     *
     * @return The user guid
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public String WhoAmIRequest() throws IOException, SAXException,
            ParserConfigurationException, Exception {
        StringBuilder xml = new StringBuilder();
        xml.append("<s:Body>");
        xml.append("<Execute xmlns=\"http://schemas.microsoft.com/xrm/2011/Contracts/Services\">");
        xml.append("<request i:type=\"c:WhoAmIRequest\" xmlns:b=\"http://schemas.microsoft.com/xrm/2011/Contracts\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:c=\"http://schemas.microsoft.com/crm/2011/Contracts\">");
        xml.append("<b:Parameters xmlns:d=\"http://schemas.datacontract.org/2004/07/System.Collections.Generic\"/>");
        xml.append("<b:RequestId i:nil=\"true\"/>");
        xml.append("<b:RequestName>WhoAmI</b:RequestName>");
        xml.append("</request>");
        xml.append("</Execute>");
        xml.append("</s:Body>");

        Document xDoc = CrmExecuteSoap.ExecuteSoapRequest(authHeader,
                xml.toString(), crmServerUrl);
        if (xDoc == null) {
            return null;
        }

        return getAttributeValue(xDoc, "UserId");
    }

    private NodeList getDocumentAttributes(Document xDoc) {
        return xDoc.getElementsByTagName("b:KeyValuePairOfstringanyType");
    }

    private String getAttributeValue(Document xDoc, String key) {
        NodeList nodes = getDocumentAttributes(xDoc);
        for (int i = 0; i < nodes.getLength(); i++) {
            if ((nodes.item(i).getFirstChild().getTextContent())
                    .equals(key)) {
                return nodes.item(i).getLastChild().getTextContent();
            }
        }

        return null;
    }

    /**
     * Walks through the node and his children until it finds the node with the
     * specified name otherwise returns null
     *
     * @param name
     * @param node
     * @return node
     */
    protected Node getValueOfNodeWithName(String name, Node node) {

        if (node.getNodeName() != null && node.getNodeName().contains(name)) {

            return node;
        }

        NodeList childNodes = node.getChildNodes();

        if (childNodes == null) {
            return null;
        }

        for (int j = 0; j < childNodes.getLength(); j++) {
            Node childNode = childNodes.item(j);

            Node returnNode = getValueOfNodeWithName(name, childNode);

            if (returnNode != null) {
                return returnNode;
            }
        }

        return null;
    }

    /**
     * Returns true if the solution with the given name exists or false
     * otherwise
     *
     * @param solutionName
     * @return true or false
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public boolean hasSolutionInstalled(String solutionName) throws SAXException, ParserConfigurationException, Exception {

        String request = "  <s:Body>\n"
                + "    <Execute xmlns=\"http://schemas.microsoft.com/xrm/2011/Contracts/Services\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
                + "      <request i:type=\"a:RetrieveMultipleRequest\" xmlns:a=\"http://schemas.microsoft.com/xrm/2011/Contracts\">\n"
                + "        <a:Parameters xmlns:b=\"http://schemas.datacontract.org/2004/07/System.Collections.Generic\">\n"
                + "          <a:KeyValuePairOfstringanyType>\n"
                + "            <b:key>Query</b:key>\n"
                + "            <b:value i:type=\"a:QueryExpression\">\n"
                + "              <a:ColumnSet>\n"
                + "                <a:AllColumns>true</a:AllColumns>\n"
                + "                <a:Columns xmlns:c=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\" />\n"
                + "              </a:ColumnSet>\n"
                + "              <a:Criteria>\n"
                + "                <a:Conditions>\n"
                + "                  <a:ConditionExpression>\n"
                + "                    <a:AttributeName>uniquename</a:AttributeName>\n"
                + "                    <a:Operator>Equal</a:Operator>\n"
                + "                    <a:Values xmlns:c=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\">\n"
                + "                      <c:anyType i:type=\"d:string\" xmlns:d=\"http://www.w3.org/2001/XMLSchema\">" + solutionName + "</c:anyType>\n"
                + "                    </a:Values>\n"
                + "                  </a:ConditionExpression>\n"
                + "                </a:Conditions>\n"
                + "                <a:FilterOperator>And</a:FilterOperator>\n"
                + "                <a:Filters />\n"
                + "              </a:Criteria>\n"
                + "              <a:Distinct>false</a:Distinct>\n"
                + "              <a:EntityName>solution</a:EntityName>\n"
                + "              <a:LinkEntities />\n"
                + "              <a:Orders />\n"
                + "              <a:PageInfo>\n"
                + "                <a:Count>0</a:Count>\n"
                + "                <a:PageNumber>0</a:PageNumber>\n"
                + "                <a:PagingCookie i:nil=\"true\" />\n"
                + "                <a:ReturnTotalRecordCount>false</a:ReturnTotalRecordCount>\n"
                + "              </a:PageInfo>\n"
                + "              <a:NoLock>false</a:NoLock>\n"
                + "            </b:value>\n"
                + "          </a:KeyValuePairOfstringanyType>\n"
                + "        </a:Parameters>\n"
                + "        <a:RequestId i:nil=\"true\" />\n"
                + "        <a:RequestName>RetrieveMultiple</a:RequestName>\n"
                + "      </request>\n"
                + "    </Execute>\n"
                + "  </s:Body>";

        Document xDoc = executeRequest(request);

        Node node = getValueOfNodeWithName(":Entities", xDoc.getFirstChild());

        if (node == null) {
            return false;
        }

        return node.getChildNodes().getLength() > 0;
    }

    /**
     * Executes a SOAP request sending the request and the authHeader.
     *
     * @param request
     * @return an XML Document with the response
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public Document executeRequest(String request) throws SAXException, ParserConfigurationException, Exception {
        return CrmExecuteSoap.ExecuteSoapRequest(authHeader, request, crmServerUrl);
    }

    public Document runFetchXmlQuery(String fetchXml) throws ParserConfigurationException, Exception {

        fetchXml = fetchXml.replaceAll("<", "&lt;");
        fetchXml = fetchXml.replaceAll(">", "&gt;");

        String request = "  <s:Body>\n"
                + "    <Execute xmlns=\"http://schemas.microsoft.com/xrm/2011/Contracts/Services\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
                + "      <request i:type=\"a:RetrieveMultipleRequest\" xmlns:a=\"http://schemas.microsoft.com/xrm/2011/Contracts\">\n"
                + "        <a:Parameters xmlns:b=\"http://schemas.datacontract.org/2004/07/System.Collections.Generic\">\n"
                + "          <a:KeyValuePairOfstringanyType>\n"
                + "            <b:key>Query</b:key>\n"
                + "            <b:value i:type=\"a:FetchExpression\">\n"
                + "              <a:Query>" + fetchXml + "</a:Query>\n"
                + "            </b:value>\n"
                + "          </a:KeyValuePairOfstringanyType>\n"
                + "        </a:Parameters>\n"
                + "        <a:RequestId i:nil=\"true\" />\n"
                + "        <a:RequestName>RetrieveMultiple</a:RequestName>\n"
                + "      </request>\n"
                + "    </Execute>\n"
                + "  </s:Body>";

        return executeRequest(request);
    }

}

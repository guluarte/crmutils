package com.xrm.msdynamics.entities;

import com.xrm.msdynamics.Enums;

import com.xrm.msdynamics.crmtypes.CrmBoolean;
import com.xrm.msdynamics.crmtypes.CrmString;
import com.xrm.msdynamics.crmtypes.Entity;
import com.xrm.msdynamics.crmtypes.EntityReference;
import com.xrm.msdynamics.crmtypes.OptionSetValue;
import com.xrm.msdynamics.crmtypes.XmlSerializable;
import java.text.ParseException;
import java.util.ArrayList;
import org.w3c.dom.NodeList;

/**
 *
 * @author rguluarte
 */
public class EmailTemplate extends BaseEntity {

    Entity entity = new Entity(Enums.EntityName.EmailTemplate);

    private CrmString title;
    private CrmString body;
    private CrmString description;
    private CrmString mimetype;

    private OptionSetValue templatetypecode;

    private CrmBoolean ispersonal;

    private EntityReference createdby;

    public static final String ID_COLUMN = "templateid";
    public static final String TITLE_COLUMN = "title";
    public static final String BODY_COLUMN = "body";
    public static final String CREATEDBY_COLUMN = "createdby";
    public static final String DESCRIPTION_COLUMN = "description";
    public static final String ISPERSONAL_COLUMN = "ispersonal";
    public static final String MIMETYPE_COLUMN = "mimetype";
    public static final String TEMPLATETYPECODE_COLUMN = "templatetypecode";

    public static final String[] EmailTemplateColumns = new String[]{
        ID_COLUMN,
        TITLE_COLUMN,
        BODY_COLUMN,
        CREATEDBY_COLUMN,
        DESCRIPTION_COLUMN,
        ISPERSONAL_COLUMN,
        MIMETYPE_COLUMN,
        TEMPLATETYPECODE_COLUMN
    };

    public EmailTemplate() {

    }

    public EmailTemplate(NodeList atr) {
        super(atr);
    }

    public class TemplateTypeCode {

        public static final int Global = 8;
        public static final int Lead = 4;
        public static final int Opportunity = 3;
        public static final int Account = 1;
        public static final int Contact = 2;
        public static final int Quote = 1084;
        public static final int Order = 1088;
        public static final int Invoice = 1090;
        public static final int Case = 112;
        public static final int Contract = 1010;
        public static final int CampaignActivity = 4402;
        public static final int ServiceActivity = 4214;
        public static final int SystemJob = 4700;
    }

    @Override
    public String[] getDefaultColumns() {
        return EmailTemplateColumns;
    }

    @Override
    public void setAttribute(String key, String value, String logicalName) {

        switch (key) {

            case ID_COLUMN: {
                this.setId(value);
                break;
            }

            case TITLE_COLUMN: {
                this.setTitle(value);
                break;
            }

            case BODY_COLUMN: {
                this.setBody(value);
                break;
            }

            case CREATEDBY_COLUMN: {
                this.setCreatedby(value);
                break;
            }

            case DESCRIPTION_COLUMN: {
                this.setDescription(value);
                break;
            }

            case ISPERSONAL_COLUMN: {
                this.setIspersonal(Boolean.parseBoolean(value));
                break;
            }

            case MIMETYPE_COLUMN: {
                this.setMimetype(value);
                break;
            }

            case TEMPLATETYPECODE_COLUMN: {
                this.setTemplatetypecode(Integer.parseInt(value));

                break;
            }
        }
    }

    @Override
    protected ArrayList<XmlSerializable> getAttributeCollection() {
        ArrayList<XmlSerializable> parameters = new ArrayList<>();

        if (getTitle() != null) {
            parameters.add(getTitle());
        }

        if (getBody() != null) {
            parameters.add(getBody());
        }

        if (getCreatedby() != null) {
            parameters.add(getCreatedby());
        }

        if (getDescription() != null) {
            parameters.add(getDescription());
        }

        if (getIspersonal() != null) {
            parameters.add(getIspersonal());
        }

        if (getMimetype() != null) {
            parameters.add(getMimetype());
        }
        if (getTemplatetypecode() != null) {
            parameters.add(getTemplatetypecode());
        }

        return parameters;
    }

    @Override
    protected Entity getEntity() {
        return entity;
    }

    @Override
    public String getLogicalName() {
        return Enums.EntityName.EmailTemplate;
    }

    /**
     * @return the title
     */
    public CrmString getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = new CrmString(TITLE_COLUMN, title);
    }

    /**
     * @return the body
     */
    public CrmString getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = new CrmString(BODY_COLUMN, body);
    }

    /**
     * @return the description
     */
    public CrmString getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = new CrmString(DESCRIPTION_COLUMN, description);
    }

    /**
     * @return the mimetype
     */
    public CrmString getMimetype() {
        return mimetype;
    }

    /**
     * @param mimetype the mimetype to set
     */
    public void setMimetype(String mimetype) {
        this.mimetype = new CrmString(MIMETYPE_COLUMN, mimetype);
    }

    /**
     * @return the ispersonal
     */
    public CrmBoolean getIspersonal() {
        return ispersonal;
    }

    /**
     * @param ispersonal the ispersonal to set
     */
    public void setIspersonal(boolean ispersonal) {
        this.ispersonal = new CrmBoolean(ISPERSONAL_COLUMN, ispersonal);
    }

    /**
     * @return the createdby
     */
    public EntityReference getCreatedby() {
        return createdby;
    }

    /**
     * @param createdby the createdby to set
     */
    public void setCreatedby(String createdby) {
        this.createdby = new EntityReference(Enums.EntityName.SystemUser, CREATEDBY_COLUMN, createdby);
    }

    /**
     * @return the templatetypecode
     */
    public OptionSetValue getTemplatetypecode() {
        return templatetypecode;
    }

    /**
     * @param templatetypecode the templatetypecode to set
     */
    public void setTemplatetypecode(int templatetypecode) {
        this.templatetypecode = new OptionSetValue(TEMPLATETYPECODE_COLUMN, templatetypecode);
    }

}

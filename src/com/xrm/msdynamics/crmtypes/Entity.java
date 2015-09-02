package com.xrm.msdynamics.crmtypes;

import java.util.ArrayList;

/**
 *
 * @author rguluarte
 * @param <T>
 */
public class Entity<T> implements XmlSerializable {

    private ArrayList<XmlSerializable> attributes = new ArrayList<XmlSerializable>() {
    };
    private String logicalName;
    private String id = Guid.EMPTY_GUID;

    public Entity(String entityName, String id) {
        this.logicalName = entityName;
        this.id = id;
    }

    public Entity(String entityName) {
        this.logicalName = entityName;
    }

    public void addAttribute(XmlSerializable attribute) {
        this.attributes.add(attribute);
    }

    public boolean removeAttribute(XmlSerializable attribute) {

        int index = this.attributes.indexOf(attribute);
        if (index > -1) {
            this.attributes.remove(attribute);
            return true;
        }

        return false;
    }

    private String getAttributesXml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<a:Attributes>\n");

        for (XmlSerializable attribute : attributes) {
            sb.append(attribute.ToXML());
        }

        sb.append("</a:Attributes>\n");
        return sb.toString();
    }

    @Override
    public String ToXML() {
        return "\n<a:KeyValuePairOfstringanyType>\n"
                + "           <b:key>Target</b:key>\n"
                + "           <b:value i:type=\"a:Entity\">\n" + getAttributesXml()
                + "             <a:EntityState i:nil=\"true\" />\n"
                + "             <a:FormattedValues />\n"
                + "             <a:Id>" + this.id + "</a:Id>\n"
                + "             <a:LogicalName>" + this.logicalName + "</a:LogicalName>\n"
                + "             <a:RelatedEntities />\n"
                + "           </b:value>\n"
                + "         </a:KeyValuePairOfstringanyType>\n";
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLogicalName(String logicalName) {
        this.logicalName = logicalName;
    }

    public void setAttributes(ArrayList<XmlSerializable> attributeCollection) {
        this.attributes = attributeCollection;
    }

    public ArrayList<XmlSerializable> getAttributes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getId() {
        return id;
    }

    @Override
    public String getKey() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

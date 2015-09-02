package com.xrm.msdynamics.entities;

import com.xrm.msdynamics.crmtypes.Entity;
import com.xrm.msdynamics.crmtypes.EntityReference;
import com.xrm.msdynamics.crmtypes.XmlSerializable;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author rguluarte
 */
public abstract class BaseEntity {

    /**
     * Simple class for holding the statuscode and statecode
     */
    public class StatusAndStatusReason {

        public final int State;
        public final int Status;

        public StatusAndStatusReason(int state, int status) {
            this.State = state;
            this.Status = status;
        }
    }

    public BaseEntity() {

    }

    /**
     * Builds the entity with the NodeList provided
     * @param atributes
     */
    public BaseEntity(NodeList atributes) {
        Load(atributes);
    }

    /**
     * Formats the date for MS Dynamics
     */
    protected SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    /**
     * Sets the attribute
     * @param key
     * @param value
     * @param logicalName
     */
    public abstract void setAttribute(String key, String value, String logicalName);

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

            NamedNodeMap nodeAttributes = node.getAttributes();

            if (nodeAttributes != null) {

                Node nodeType = nodeAttributes.getNamedItem("i:type");

                if (nodeType != null && nodeType.getNodeValue() != null) {
                    if (nodeType.getNodeValue().contains(":EntityReference")) {
                        Node idNode = getValueOfNodeWithName(":Id", node);
                        return idNode;
                    }
                }
            }

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
     * Loads the entity with the attributes found in the NodeList
     * @param nodes
     */
    public void Load(NodeList nodes) {
        for (int i = 0; i < nodes.getLength(); i++) {

            Node node = nodes.item(i);

            Node keyNode = getValueOfNodeWithName(":key", node);
            Node valueNode = getValueOfNodeWithName(":value", node);

            if (keyNode == null || valueNode == null) {
                continue;
            }

            String key = keyNode.getTextContent();
            String value = valueNode.getTextContent();

            Node logicalNameNode = getValueOfNodeWithName(":LogicalName", valueNode.getParentNode());
            String logicalName = "";

            if (logicalNameNode != null) {
                logicalName = logicalNameNode.getTextContent();
            }

            if (key != null && !key.isEmpty() && value != null && !value.isEmpty()) {
                try {
                    this.setAttribute(key, value, logicalName);
                } catch (Throwable e) {

                }
            }

        }

    }

    /**
     * Returns the entity default columns
     * @return A list of the default columns
     */
    public abstract String[] getDefaultColumns();

    /**
     * Retrieves all the attributes set
     * @return A list of XmlSerializable attributes
     */
    protected abstract ArrayList<XmlSerializable> getAttributeCollection();

    /**
     * Gets the entity object
     * @return The entity object
     */
    protected abstract Entity getEntity();

    /**
     * Returns the entity for being used in the Soap request
     * @return the enity xml
     */
    public Entity toEntity() {
        Entity entity = this.getEntity();

        entity.setAttributes(this.getAttributeCollection());
        entity.setLogicalName(this.getLogicalName());

        return entity;
    }

    /**
     * Converts the entity to an entity reference
     * @return EntityReference
     */
    public EntityReference toEntityReference() {
        return new EntityReference(getLogicalName(), "", getId());
    }

    /**
     * Returns the entity logical name
     * @return the entity logial name
     */
    public abstract String getLogicalName();

    /**
     * @return the id
     */
    public String getId() {
        return this.getEntity().getId();
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.getEntity().setId(id);
    }
}

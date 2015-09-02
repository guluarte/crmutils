/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.entities;

import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author rguluarte
 * @param <T>
 */
public class EntityFactory<T extends BaseEntity> {

    private final Class<T> clazz;

    public EntityFactory(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T Build(NodeList nodes) throws InstantiationException, IllegalAccessException {
        T entity = clazz.newInstance();
        entity.Load(nodes);
        return entity;

    }

    public ArrayList<T> Build(Document doc) throws InstantiationException, IllegalAccessException, ParserConfigurationException {

        ArrayList<T> entityList = new ArrayList<>();
        NodeList entities = doc.getElementsByTagName("b:Entity");

        for (int i = 0; i < entities.getLength(); i++) {
            Node node = entities.item(i);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document newDocument = builder.newDocument();
            Node importedNode = newDocument.importNode(node, true);
            newDocument.appendChild(importedNode);
            NodeList nodes = newDocument.getElementsByTagName("b:KeyValuePairOfstringanyType");

            entityList.add(Build(nodes));

        }
        return entityList;
    }
}

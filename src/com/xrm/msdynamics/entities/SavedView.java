/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.entities;

import com.xrm.msdynamics.Enums;
import com.xrm.msdynamics.crmtypes.Entity;

import org.w3c.dom.NodeList;

/**
 *
 * @author rguluarte
 */
public class SavedView extends View {

    public static final String ID = "userqueryid";

    public static String[] Columns = new String[]{
        ID,
        NAME,
        FETCHXML,
        RETURNEDTYPECODE
    };

    public SavedView(NodeList nodes) {
        super(nodes);
    }

    public SavedView() {
        super();
    }

    @Override
    public void setAttribute(String key, String value, String logicalName) {

        super.setAttribute(key, value, logicalName);

        switch (key) {

            case ID: {
                this.setId(value);
                break;
            }
        }

    }

    @Override
    public String[] getDefaultColumns() {
        return Columns;
    }

    @Override
    protected Entity getEntity() {
        if (entity == null) {
            entity = new Entity(Enums.EntityName.SavedView);
        }

        return entity;
    }

    @Override
    public String getLogicalName() {
        return Enums.EntityName.SavedView;
    }

}

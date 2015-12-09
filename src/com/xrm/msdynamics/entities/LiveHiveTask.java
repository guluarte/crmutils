/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.entities;

import com.xrm.msdynamics.crmtypes.OptionSetValue;
import com.xrm.msdynamics.crmtypes.XmlSerializable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.w3c.dom.NodeList;

/**
 *
 * @author rguluarte
 */
public class LiveHiveTask extends Task {

    private OptionSetValue xrmTaskType;
    
    public static final String XRM_TASKTYPE = "xrm_tasktype";
    
    public static String[] ColumnsLiveHiveTask = new String[]{
        XRM_TASKTYPE
    };
    
    public class TaskTypes {
        public static final int Email = 163650000;
        public static final int Phone = 163650001;
    }

    public LiveHiveTask(NodeList nodes) {
        super(nodes);
    }

    public LiveHiveTask() {
        super();
    }

    @Override
    public void setAttribute(String key, String value, String logicalName) {

        super.setAttribute(key, value, logicalName);
        
        switch(key) {
            case XRM_TASKTYPE: {
                setXrmTaskType(Integer.parseInt(value));
            }
        }
    
    }

    @Override
    public String[] getDefaultColumns() {
        List<String> l1 = Arrays.asList(super.getDefaultColumns());
        //l1.addAll(Arrays.asList(ColumnsLiveHiveTask));
        return (String[]) l1.toArray();
    }

    @Override
    protected ArrayList<XmlSerializable> getAttributeCollection() {

        ArrayList<XmlSerializable> parameters = super.getAttributeCollection();
        
        if(getXrmTaskType() != null) {
            parameters.add(getXrmTaskType());
        }
        return parameters;
    }

    /**
     * @return the xrmTaskType
     */
    public OptionSetValue getXrmTaskType() {
        return xrmTaskType;
    }

    /**
     * @param xrmTaskType the xrmTaskType to set
     */
    public void setXrmTaskType(int xrmTaskType) {
        this.xrmTaskType = new OptionSetValue(XRM_TASKTYPE, xrmTaskType);
    }
}
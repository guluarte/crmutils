package com.xrm.msdynamics.crmtypes;

/**
 *
 * @author rguluarte
 */
public interface XmlSerializable {
    public String ToXML();
    public String getKey();
    public Object getValue();
}

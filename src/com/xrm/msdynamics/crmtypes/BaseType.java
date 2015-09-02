package com.xrm.msdynamics.crmtypes;

/**
 *
 * @author rguluarte
 */
public abstract class BaseType implements XmlSerializable {
    @Override
    public abstract String ToXML();
}
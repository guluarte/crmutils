/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.api;

import com.xrm.msdynamics.Enums;
import com.xrm.msdynamics.OrganizationService;
import com.xrm.msdynamics.entities.EntityFactory;
import com.xrm.msdynamics.entities.Report;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author rguluarte
 */
public class ReportApi {

    private final OrganizationService service;

    public ReportApi(OrganizationService service) {
        this.service = service;
    }

    public ArrayList<Report> getAll() throws InstantiationException, IllegalAccessException, ParserConfigurationException, SAXException, Exception {
        Document reportsResponseDocument = service.RetrieveAll(Enums.EntityName.Report, Report.Columns, null);
        EntityFactory entityFactory = new EntityFactory<>(Report.class);
        return entityFactory.Build(reportsResponseDocument);
    }

}

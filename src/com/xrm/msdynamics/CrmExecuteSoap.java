package com.xrm.msdynamics;

import com.xrm.msdynamics.entities.LiveHiveLead;
import com.xrm.msdynamics.exceptions.ServiceFaultException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class CrmExecuteSoap {

    public static int TimeOut = 4;

    public static void SetTimeOut(int minutes) {
        CrmExecuteSoap.TimeOut = minutes;
    }

    /**
     * Executes the SOAP request.
     *
     * @return Document SOAP response.
     * @param authHeader CrmAuthenticationHeader.
     * @param requestBody The SOAP request body.
     * @param url The CRM URL.
     * @throws java.net.MalformedURLException
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public static Document ExecuteSoapRequest(
            CrmAuthenticationHeader authHeader, String requestBody, String url) throws MalformedURLException, IOException, SAXException, ParserConfigurationException, Exception {
        if (!url.endsWith("/")) {
            url += "/";
        }

        StringBuilder xml = new StringBuilder();
        xml.append("<s:Envelope xmlns:s=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:a=\"http://www.w3.org/2005/08/addressing\">");
        xml.append(authHeader.Header);
        xml.append(requestBody);
        xml.append("</s:Envelope>");

        URL SoapURL = new URL(url + "XRMServices/2011/Organization.svc");
        HttpURLConnection rc = (HttpURLConnection) SoapURL.openConnection();
        int timeout = 1000 * (60 * CrmExecuteSoap.TimeOut); // four minutes 
        rc.setConnectTimeout(timeout);
        rc.setRequestMethod("POST");
        rc.setDoOutput(true);
        rc.setDoInput(true);
        rc.setRequestProperty("Content-Type",
                "application/soap+xml; charset=UTF-8");
        String reqStr = xml.toString();
        int len = reqStr.length();
        rc.setRequestProperty("Content-Length", Integer.toString(len));
        rc.connect();
        OutputStreamWriter out = new OutputStreamWriter(rc.getOutputStream());
        out.write(reqStr, 0, len);
        out.flush();
        
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(reqStr);

        if (rc.getResponseCode() != 200) {
            try (InputStreamReader read = new InputStreamReader(rc.getErrorStream())) {
                StringBuilder sb = new StringBuilder();
                int ch = read.read();
                while (ch != -1) {
                    sb.append((char) ch);
                    ch = read.read();
                }
                String response = sb.toString();

                ServiceFaultException serviceFault = buildServiceFaultException(reqStr, response, rc);

                Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(serviceFault.getMessage());

                throw serviceFault;
            }

        }

        String response;
        try (InputStreamReader read = new InputStreamReader(rc.getInputStream())) {
            StringBuilder sb = new StringBuilder();
            int ch = read.read();
            while (ch != -1) {
                sb.append((char) ch);
                ch = read.read();
            }
            response = sb.toString();
        }

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document doc = builder
                .parse(new ByteArrayInputStream(response.getBytes()));

        return doc;
    }

    private static ServiceFaultException buildServiceFaultException(String reqStr, String response, HttpURLConnection rc) throws IOException {
        StringBuilder exceptionMessage = new StringBuilder();

        exceptionMessage.append("Status Code: ");
        exceptionMessage.append("[");

        if (rc == null) {
            exceptionMessage.append("Empty HttpURLConnection object");
        } else {
            exceptionMessage.append(rc.getResponseCode());
        }
        
        exceptionMessage.append("]");
        exceptionMessage.append(System.lineSeparator());
        exceptionMessage.append("Request: ");
        exceptionMessage.append(System.lineSeparator());
        exceptionMessage.append("[");
        exceptionMessage.append(reqStr);
        exceptionMessage.append("]");
        exceptionMessage.append(System.lineSeparator());
        exceptionMessage.append("Response: ");
        exceptionMessage.append(System.lineSeparator());

        exceptionMessage.append("[");

        if (response == null || response.isEmpty()) {
            exceptionMessage.append("Empty Response");
        } else {
            exceptionMessage.append(response);
        }

        exceptionMessage.append("]");

        ServiceFaultException serviceFault = new ServiceFaultException(exceptionMessage.toString());
        serviceFault.setRequest(reqStr);
        
        if (rc != null) {
            serviceFault.setStatusCode(rc.getResponseCode());
        }
        
        serviceFault.setResponse(response);
        serviceFault.setHttpURLConnection(rc);
        return serviceFault;
    }
}

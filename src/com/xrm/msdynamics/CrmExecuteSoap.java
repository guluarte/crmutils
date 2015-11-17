package com.xrm.msdynamics;

import com.xrm.msdynamics.exceptions.ServiceFaultException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class CrmExecuteSoap {
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
        int timeout = 1000 * (120); //two minnutes
        rc.setConnectTimeout( timeout );
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
        
        if (rc.getResponseCode() != 200) {
            try (InputStreamReader read = new InputStreamReader(rc.getErrorStream())) {
                StringBuilder sb = new StringBuilder();
                int ch = read.read();
                while (ch != -1) {
                    sb.append((char) ch);
                    ch = read.read();
                }
                String response = sb.toString();
                
                throw new ServiceFaultException(response);
            }
            
        }

        String response;
         try (InputStreamReader read = new InputStreamReader(rc.getInputStream())) {
             StringBuilder sb = new StringBuilder();
             int ch = read.read();
             while (ch != -1) {
                 sb.append((char) ch);
                 ch = read.read();
             }    response = sb.toString();
         }

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document doc = builder
                .parse(new ByteArrayInputStream(response.getBytes()));

        return doc;
    }
}

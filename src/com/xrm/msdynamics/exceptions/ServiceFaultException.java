/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics.exceptions;

import java.net.HttpURLConnection;

/**
 *
 * @author rguluarte
 */
public class ServiceFaultException extends Exception {
    
    private String response;
    private String request;
    private int statusCode;
    private HttpURLConnection httpURLConnection;
    
    public ServiceFaultException(String message) {
        super(message);
    }

    /**
     * @return the response
     */
    public String getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * @return the request
     */
    public String getRequest() {
        return request;
    }

    /**
     * @param request the request to set
     */
    public void setRequest(String request) {
        this.request = request;
    }

    /**
     * @return the statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return the httpURLConnection
     */
    public HttpURLConnection getHttpURLConnection() {
        return httpURLConnection;
    }

    /**
     * @param httpURLConnection the httpURLConnection to set
     */
    public void setHttpURLConnection(HttpURLConnection httpURLConnection) {
        this.httpURLConnection = httpURLConnection;
    }
    
}

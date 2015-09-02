/*
 *  Copyright  2007-2015 xRM
 */
package com.xrm.msdynamics;


/**
 *
 * @author rguluarte
 */
public class OAuth {

    private final String clientId;
    private final String redirectUri;
    private final String orgName;

    public OAuth(String orgName, String clientId, String redirectUri) {
        this.clientId = clientId;
        this.redirectUri = redirectUri;
        this.orgName = orgName;
    }

    /*
     First, an auth endpoint discovery request is sent. Note that specifying 
     the SDK version is required:
     ET https://ORG_NAME.crm.dynamics.com/XRMServices/2011/Organization.svc/web?SdkClientVersion=6.1.0.533 HTTP/1.1
    
     This returns the auth endpoint on the response header:
     HTTP/1.1 401 Unauthorized
     WWW-Authenticate: Bearer authorization_uri=https://login.windows.net/TENTAND_ID/oauth2/authorize

     HTTP Error 401 - Unauthorized: Access is denied
     */
    public String getAuthorizationEndPoint(String orgEndPoint) {
        return "";
    }

    /*
     Next, a request to the auth endpoint is made with resource, client id, 
     response type (= “code”), redirect uri and prompt (= “login”) parameters 
     on the query string:
     */
    public String getRedirectUrl(String authEndPoint, String resourceUi) {
        return authEndPoint + "?resource=" + resourceUi + "&client_id=" + this.clientId + "&response_type=code&redirect_uri=" + this.redirectUri + "&prompt=login";
    }

    public String getToken(String code) {
        String tokenEndPoint = "https://login.windows.net/TENANT_ID/oauth2/token";
        String postPayLoad = "resource=" + this.orgName + ".crm.dynamics.com&client_id=" + this.clientId + "&grant_type=authorization_code&code=" + code + "&redirect_uri=" + this.redirectUri;

        return "";
    }
}

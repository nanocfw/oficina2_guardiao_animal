/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.client.implementations;

import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;

/**
 *
 * @author Marciano
 */
public abstract class Service
{

    private static String serverURL;
    private static String userName;
    private static String password;

    public static String getServerURL()
    {
        return serverURL;
    }

    public static void setServerURL(String serverURL)
    {
        Service.serverURL = serverURL;
    }

    public Service()
    {
        Service.serverURL = "http://localhost:8090/ga/";
        userName = "admin";
        password = "admin";
    }

    public HttpHeaders getAuthHeader()
    {
        return new HttpHeaders()
        {
            {
                String auth = userName + ":" + password;
                byte[] encodedAuth;
                
                encodedAuth = Base64.encodeBase64(
                        auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodedAuth);
                set("Authorization", authHeader);
            }
        };
    }
}

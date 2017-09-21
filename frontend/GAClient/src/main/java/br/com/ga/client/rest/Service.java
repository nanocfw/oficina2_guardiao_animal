/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.client.rest;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

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
        Service.serverURL = "http://localhost:8090/";
        userName = "admin";
        password = "admin";
    }

    public BasicAuthRestTemplate getNewRestTemplate()
    {
        BasicAuthRestTemplate restTemplate = new BasicAuthRestTemplate(userName, password);
        MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonHttpMessageConverter.getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        restTemplate.getMessageConverters().add(jsonHttpMessageConverter);
        return restTemplate;
    }
}

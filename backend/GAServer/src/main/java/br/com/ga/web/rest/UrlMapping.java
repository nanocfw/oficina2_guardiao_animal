/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.web.rest;

/**
 * @author Marciano
 */
public class UrlMapping {

    public static final String PERSON = "ga/person/";
    public static final String PERSON_GET = "fetch/{personId}";
    public static final String PERSON_GET_LIST = "fetch/{listClients}/{rowsReturn}/{rowsIgnore}";
    public static final String PERSON_GET_SERVICE_PROVIDER_LIST = "fetchserviceprovider/{country}/{city}/{rowsReturn}/{rowsIgnore}";
    public static final String PERSON_GET_SERVICE_PROVIDER_LIST_BY_LAT_LNG = "fetchserviceprovider/{lat}/{lng}/{ray}/{rowsReturn}/{rowsIgnore}";
    public static final String PERSON_CREATE_UPDATE = "save/";
    public static final String PERSON_UPDATE_AUTH_TOKEN = "updatetoken/";
    public static final String PERSON_LOGIN = "fetch/";
    public static final String PERSON_LOGIN_BY_VALID_TOKEN = "fetchtoken/";
    public static final String PERSON_EMAIL_IN_USE = "fetch/{email}/{currentId}";
    public static final String PERSON_DELETE = "delete/";
    public static final String PERSON_VALID_TOKEN = "fetchtoken/{token}";

    public static final String SERVICE_TYPE = "ga/servicetype/";
    public static final String SERVICE_TYPE_GET = "fetch/{serviceId}";
    public static final String SERVICE_TYPE_GET_BY_DESCRIPTION = "fetch/{serviceDescription}";
    public static final String SERVICE_TYPE_GET_LIST = "fetch/";
    public static final String SERVICE_TYPE_DELETE = "delete/";
    public static final String SERVICE_TYPE_CREATE_UPDATE = "save/";

    public static final String PICTURE = "ga/picture/";
    public static final String PICTURE_GET = "fetch/{pictureId}";
    public static final String PICTURE_CREATE_UPDATE = "save/";
    public static final String PICTURE_DELETE = "delete/";
}

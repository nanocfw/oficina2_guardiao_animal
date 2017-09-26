/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.web.rest;

/**
 *
 * @author Marciano
 */
public class UrlMapping
{

    public static final String PERSON = "ga/person/";
    public static final String PERSON_GET = "fetch/{personId}";
    public static final String PERSON_GET_LIST = "fetch/{listClients}/{rowsReturn}/{rowsIgnore}";
    public static final String PERSON_CREATE_UPDATE = "save/";
    public static final String PERSON_LOGIN = "fetch/";
    public static final String PERSON_EMAIL_IN_USE = "fetch/{email}/{currentId}";
    public static final String PERSON_DELETE = "delete/";
}
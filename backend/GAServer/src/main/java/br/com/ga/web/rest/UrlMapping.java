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
    public static final String PERSON_GET_SERVICE_PROVIDER_LIST = "fetchserviceprovider/{currentId}/{country}/{city}/{rowsReturn}/{rowsIgnore}";
    public static final String PERSON_GET_SERVICE_PROVIDER_LIST_BY_LAT_LNG = "fetchserviceprovider/{currentId}/{lat}/{lng}/{ray}/{rowsReturn}/{rowsIgnore}";
    public static final String PERSON_CREATE_UPDATE = "save/";
    public static final String PERSON_UPDATE_AUTH_TOKEN = "updatetoken/";
    public static final String PERSON_LOGIN = "fetch/";
    public static final String PERSON_LOGIN_BY_VALID_TOKEN = "fetchtoken/";
    public static final String PERSON_EMAIL_IN_USE = "fetch/{email}/{currentId}";
    public static final String PERSON_DELETE = "delete/{personId}";
    public static final String PERSON_VALID_TOKEN = "fetchtoken/{token}";

    public static final String SERVICE_TYPE = "ga/servicetype/";
    public static final String SERVICE_TYPE_GET = "fetch/{serviceTypeId}";
    public static final String SERVICE_TYPE_GET_BY_DESCRIPTION = "fetch/{serviceDescription}";
    public static final String SERVICE_TYPE_GET_LIST = "fetch/";
    public static final String SERVICE_TYPE_DELETE = "delete/{serviceTypeId}";
    public static final String SERVICE_TYPE_CREATE_UPDATE = "save/";

    public static final String PICTURE = "ga/picture/";
    public static final String PICTURE_GET = "fetch/{pictureId}";
    public static final String PICTURE_CREATE_UPDATE = "save/";
    public static final String PICTURE_DELETE = "delete/{pictureId}";

    public static final String ANIMAL = "ga/animal/";
    public static final String ANIMAL_GET = "fetch/{animalId}";
    public static final String ANIMAL_GET_LIST = "fetch/{ownerId}/{rowsReturn}/{rowsIgnore}";
    public static final String ANIMAL_CREATE_UPDATE = "save/";
    public static final String ANIMAL_DELETE = "delete/{animalId}";

    public static final String APPOINTMENT = "ga/appointment";
    public static final String APPOINTMENT_GET = "fetch/{appointmentId}";
    public static final String APPOINTMENT_GET_AVAILABLE_SCHEDULE = "fetch/{currentId}/{serviceProviderId}/{start}/{end}";
    public static final String APPOINTMENT_GET_LIST_BY_CLIENT = "fetchclient/{clientId}/{rowsReturn}/{rowsIgnore}";
    public static final String APPOINTMENT_GET_LIST_BY_SERVICE_PROVIDER = "fetchserviceprovider/{serviceProviderId}/{rowsReturn}/{rowsIgnore}";
    public static final String APPOINTMENT_CREATE_UPDATE = "save/";
    public static final String APPOINTMENT_DELETE = "delete/{appointmentId}";

    public static final String ANIMAL_TYPE = "ga/animaltype/";
    public static final String ANIMAL_TYPE_CREATE_UPDATE = "save/";
    public static final String ANIMAL_TYPE_GET = "fetch/{animalTypeId}";
    public static final String ANIMAL_TYPE_GET_BY_DESCRIPTION = "fetch/{description}";
    public static final String ANIMAL_TYPE_GET_LIST = "fetch/{rowsReturn}/{rowsIgnore}";
    public static final String ANIMAL_TYPE_DELETE = "delete/{animalTypeId}";

    public static final String SERVICE_PROVIDER_ANIMAL_TYPE = "ga/serviceprovideranimaltype/";
    public static final String SERVICE_PROVIDER_ANIMAL_TYPE_CREATE_UPDATE = "save/";
    public static final String SERVICE_PROVIDER_ANIMAL_TYPE_GET = "fetch/{serviceProviderAnimalTypeId}";
    public static final String SERVICE_PROVIDER_ANIMAL_TYPE_GET_LIST_BY_PROVIDER = "fetch/p/{serviceProviderId}/{rowsReturn}/{rowsIgnore}";
    public static final String SERVICE_PROVIDER_ANIMAL_TYPE_GET_LIST_BY_SERVICE_TYPE = "fetch/s/{serviceTypeId}/{rowsReturn}/{rowsIgnore}";
    public static final String SERVICE_PROVIDER_ANIMAL_TYPE_GET_LIST_BY_ANIMAL_TYPE = "fetchp/a/{AnimalTypeId}/{rowsReturn}/{rowsIgnore}";
    public static final String SERVICE_PROVIDER_ANIMAL_TYPE_DELETE = "delete/{serviceProviderAnimalTypeId}";
    public static final String SERVICE_PROVIDER_ANIMAL_TYPE_ALREADY_REGISTERED = "fetch/{currentId}/{serviceProviderId}/{serviceTypeId}/{animalTypeId}/{animalSize}/{billingType}";
}

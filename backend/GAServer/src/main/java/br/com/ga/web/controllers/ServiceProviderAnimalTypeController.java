package br.com.ga.web.controllers;

import br.com.ga.entity.ServiceProviderAnimalType;
import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.exceptions.InvalidEntity;
import br.com.ga.service.intf.IServiceProviderAnimalTypeService;
import br.com.ga.web.rest.ResponseCode;
import br.com.ga.web.rest.ResponseData;
import br.com.ga.web.rest.UrlMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlMapping.SERVICE_PROVIDER_ANIMAL_TYPE)
public class ServiceProviderAnimalTypeController {

    @Autowired
    IServiceProviderAnimalTypeService serviceProviderAnimalTypeService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.SERVICE_PROVIDER_ANIMAL_TYPE_CREATE_UPDATE,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<ServiceProviderAnimalType> createUpdate(@RequestBody ServiceProviderAnimalType serviceProviderAnimalType) throws Exception {
        try {
            ServiceProviderAnimalType s = serviceProviderAnimalTypeService.createUpdate(serviceProviderAnimalType);
            return new ResponseData<>(ServiceProviderAnimalType.class, s, ResponseCode.CREATED);
        } catch (InvalidEntity e) {
            return new ResponseData<>(ServiceProviderAnimalType.class, ResponseCode.ERROR, InvalidEntity.class, e.getMessage());
        } catch (Exception ex) {
            return new ResponseData<>(ServiceProviderAnimalType.class, ResponseCode.ERROR, ex.getClass(), ex.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.SERVICE_PROVIDER_ANIMAL_TYPE_GET,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<ServiceProviderAnimalType> findById(@PathVariable(value = "serviceProviderAnimalTypeId") long serviceProviderAnimalTypeId) throws Exception {
        try {
            ServiceProviderAnimalType serviceProviderAnimalType = serviceProviderAnimalTypeService.findById(serviceProviderAnimalTypeId);
            return new ResponseData<>(ServiceProviderAnimalType.class, serviceProviderAnimalType, ResponseCode.FOUND);
        } catch (EntityNotFound e) {
            return new ResponseData<>(ServiceProviderAnimalType.class, ResponseCode.NOT_FOUND, EntityNotFound.class, e.getMessage());
        } catch (Exception e) {
            return new ResponseData<>(ServiceProviderAnimalType.class, ResponseCode.ERROR, e.getClass(), e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.SERVICE_PROVIDER_ANIMAL_TYPE_GET_LIST_BY_PROVIDER,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ServiceProviderAnimalType> findListByProvider(
            @PathVariable(value = "serviceProviderId") long serviceProviderId,
            @PathVariable(value = "rowsReturn") int rowsReturn,
            @PathVariable(value = "rowsIgnore") int rowsIgnore) {
        return serviceProviderAnimalTypeService.findListByProvider(serviceProviderId, rowsReturn, rowsIgnore);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.SERVICE_PROVIDER_ANIMAL_TYPE_GET_LIST_BY_SERVICE_TYPE,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ServiceProviderAnimalType> findListByServiceType(
            @PathVariable(value = "serviceTypeId") long serviceTypeId,
            @PathVariable(value = "rowsReturn") int rowsReturn,
            @PathVariable(value = "rowsIgnore") int rowsIgnore) {
        return serviceProviderAnimalTypeService.findListByServiceType(serviceTypeId, rowsReturn, rowsIgnore);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.SERVICE_PROVIDER_ANIMAL_TYPE_GET_LIST_BY_ANIMAL_TYPE,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ServiceProviderAnimalType> findListByAnimalType(
            @PathVariable(value = "animalTypeId") long animalTypeId,
            @PathVariable(value = "rowsReturn") int rowsReturn,
            @PathVariable(value = "rowsIgnore") int rowsIgnore) {
        return serviceProviderAnimalTypeService.findListByAnimalType(animalTypeId, rowsReturn, rowsIgnore);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.SERVICE_PROVIDER_ANIMAL_TYPE_DELETE,
            method = RequestMethod.DELETE)
    public ResponseData<Integer> deleteById(long serviceProviderAnimalTypeId) throws Exception {
        try {
            int deletedRows = serviceProviderAnimalTypeService.deleteById(serviceProviderAnimalTypeId);
            return new ResponseData<>(Integer.class, deletedRows, ResponseCode.DELETED);
        } catch (Exception e) {
            return new ResponseData<>(Integer.class, ResponseCode.ERROR, e.getClass(), e.getMessage());
        }
    }
}

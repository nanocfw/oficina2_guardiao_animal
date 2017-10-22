package br.com.ga.web.controllers;

import br.com.ga.entity.ServiceType;
import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.exceptions.InvalidEntity;
import br.com.ga.service.intf.IServiceTypeService;
import br.com.ga.web.rest.ResponseCode;
import br.com.ga.web.rest.ResponseData;
import br.com.ga.web.rest.UrlMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlMapping.SERVICE_TYPE)
public class ServiceTypeController {

    @Autowired
    IServiceTypeService serviceTypeService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.SERVICE_TYPE_GET,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<ServiceType> get(@PathVariable(value = "serviceId") int serviceId) {
        try {
            ServiceType s = serviceTypeService.findById(serviceId);
            return new ResponseData<>(ServiceType.class, s, ResponseCode.FOUND);
        } catch (EntityNotFound e) {
            return new ResponseData<>(ServiceType.class, ResponseCode.NOT_FOUND, EntityNotFound.class, e.getMessage());
        } catch (Exception e) {
            return new ResponseData<>(ServiceType.class, ResponseCode.ERROR, e.getClass(), e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.SERVICE_TYPE_GET_BY_DESCRIPTION,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<ServiceType> get(@PathVariable(value = "serviceDescription") String serviceDescription) {
        try {
            ServiceType s = serviceTypeService.findByDescription(serviceDescription);
            return new ResponseData<>(ServiceType.class, s, ResponseCode.FOUND);
        } catch (EntityNotFound e) {
            return new ResponseData<>(ServiceType.class, ResponseCode.NOT_FOUND, EntityNotFound.class, e.getMessage());
        } catch (Exception e) {
            return new ResponseData<>(ServiceType.class, ResponseCode.ERROR, e.getClass(), e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.SERVICE_TYPE_GET_LIST,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ServiceType> getList() {
        return serviceTypeService.findList();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.SERVICE_TYPE_CREATE_UPDATE,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<ServiceType> createUpdate(@RequestBody ServiceType serviceType) {
        try {
            ServiceType s = serviceTypeService.createUpdate(serviceType);
            return new ResponseData<>(ServiceType.class, s, ResponseCode.CREATED);
        } catch (InvalidEntity e) {
            return new ResponseData<>(ServiceType.class, ResponseCode.ERROR, InvalidEntity.class, e.getMessage());
        } catch (Exception ex) {
            return new ResponseData<>(ServiceType.class, ResponseCode.ERROR, ex.getClass(), ex.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.SERVICE_TYPE_DELETE,
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(ServiceType serviceType) throws Exception {
        serviceTypeService.delete(serviceType);
    }
}

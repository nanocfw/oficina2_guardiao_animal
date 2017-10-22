package br.com.ga.client.implementations;

import br.com.ga.client.rest.BasicAuthRestTemplate;
import br.com.ga.client.rest.Service;
import br.com.ga.entity.ServiceType;
import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.exceptions.InvalidEntity;
import br.com.ga.service.intf.IServiceTypeService;
import br.com.ga.web.rest.ResponseCode;
import br.com.ga.web.rest.ResponseData;
import br.com.ga.web.rest.UrlMapping;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceTypeServiceImpl extends Service implements IServiceTypeService {
    @Override
    public ServiceType createUpdate(ServiceType service) throws Exception {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<ResponseData<ServiceType>> response;
        HttpEntity<ServiceType> httpService = new HttpEntity<>(service);

        response = rest.exchange(
                getServerURL() + UrlMapping.SERVICE_TYPE + UrlMapping.SERVICE_TYPE_CREATE_UPDATE,
                HttpMethod.POST,
                httpService,
                new ParameterizedTypeReference<ResponseData<ServiceType>>() {
                });

        if (response.getBody().getStatus() == ResponseCode.CREATED)
            return response.getBody().getValue();

        if (response.getBody().getExceptionType() == InvalidEntity.class)
            throw new InvalidEntity(response.getBody().getExceptionMessage());

        throw new Exception(
                "ExceptionClass: " + response.getBody().getExceptionType().toString()
                        + " Message: " + response.getBody().getExceptionMessage());
    }

    @Override
    public ServiceType findById(int id) throws Exception {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<ResponseData<ServiceType>> response;
        Map<String, Integer> params = new HashMap<>();
        params.put("serviceId", id);

        response = rest.exchange(
                getServerURL() + UrlMapping.SERVICE_TYPE + UrlMapping.SERVICE_TYPE_GET,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseData<ServiceType>>() {
                },
                params
        );

        if (response.getBody().getStatus() == ResponseCode.FOUND)
            return response.getBody().getValue();

        if (response.getBody().getExceptionType() == EntityNotFound.class)
            throw new EntityNotFound(response.getBody().getExceptionMessage());

        throw new Exception(
                "ExceptionClass: " + response.getBody().getExceptionType().toString()
                        + " Message: " + response.getBody().getExceptionMessage());
    }

    @Override
    public ServiceType findByDescription(String description) throws Exception {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<ResponseData<ServiceType>> response;
        Map<String, String> params = new HashMap<>();
        params.put("serviceDescription", description);

        response = rest.exchange(
                getServerURL() + UrlMapping.SERVICE_TYPE + UrlMapping.SERVICE_TYPE_GETBYDESCRIPTION,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseData<ServiceType>>() {
                },
                params
        );

        if (response.getBody().getStatus() == ResponseCode.FOUND)
            return response.getBody().getValue();

        if (response.getBody().getExceptionType() == EntityNotFound.class)
            throw new EntityNotFound(response.getBody().getExceptionMessage());

        throw new Exception(
                "ExceptionClass: " + response.getBody().getExceptionType().toString()
                        + " Message: " + response.getBody().getExceptionMessage());
    }

    @Override
    public List<ServiceType> findList() {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<List<ServiceType>> response;

        response = rest.exchange(
                getServerURL() + UrlMapping.SERVICE_TYPE + UrlMapping.SERVICE_TYPE_GET_LIST,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ServiceType>>() {
                }
        );

        return response.getBody();
    }

    @Override
    public void delete(ServiceType service) throws Exception {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        HttpEntity<ServiceType> httpService = new HttpEntity<>(service);

        rest.exchange(
                getServerURL() + UrlMapping.SERVICE_TYPE + UrlMapping.SERVICE_TYPE_DELETE,
                HttpMethod.DELETE,
                httpService,
                void.class
        );
    }
}

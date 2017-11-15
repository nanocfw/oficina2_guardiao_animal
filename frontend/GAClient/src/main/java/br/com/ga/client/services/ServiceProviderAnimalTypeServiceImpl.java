package br.com.ga.client.services;

import br.com.ga.client.rest.BasicAuthRestTemplate;
import br.com.ga.client.rest.Service;
import br.com.ga.entity.ServiceProviderAnimalType;
import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.exceptions.InvalidEntity;
import br.com.ga.service.intf.IServiceProviderAnimalTypeService;
import br.com.ga.web.rest.ResponseCode;
import br.com.ga.web.rest.ResponseData;
import br.com.ga.web.rest.UrlMapping;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class ServiceProviderAnimalTypeServiceImpl extends Service implements IServiceProviderAnimalTypeService {

    @Override
    public ServiceProviderAnimalType createUpdate(ServiceProviderAnimalType serviceProviderAnimalType) throws Exception {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<ResponseData<ServiceProviderAnimalType>> response;
        HttpEntity<ServiceProviderAnimalType> httpServiceProviderAnimalType = new HttpEntity<>(serviceProviderAnimalType);

        response = rest.exchange(
                getServerURL() + UrlMapping.SERVICE_PROVIDER_ANIMAL_TYPE + UrlMapping.SERVICE_PROVIDER_ANIMAL_TYPE_CREATE_UPDATE,
                HttpMethod.POST,
                httpServiceProviderAnimalType,
                new ParameterizedTypeReference<ResponseData<ServiceProviderAnimalType>>() {
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
    public ServiceProviderAnimalType findById(long serviceProviderAnimalTypeId) throws Exception {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<ResponseData<ServiceProviderAnimalType>> response;
        Map<String, Long> params = new HashMap<>();
        params.put("serviceProviderAnimalTypeId", serviceProviderAnimalTypeId);

        response = rest.exchange(
                getServerURL() + UrlMapping.SERVICE_PROVIDER_ANIMAL_TYPE + UrlMapping.SERVICE_PROVIDER_ANIMAL_TYPE_GET,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseData<ServiceProviderAnimalType>>() {
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
    public List<ServiceProviderAnimalType> findListByProvider(long serviceProviderId, int rowsReturn, int rowsIgnore) {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<List<ServiceProviderAnimalType>> response;
        Map<String, Long> params = new HashMap<>();
        params.put("serviceProviderId", serviceProviderId);
        params.put("rowsReturn", Long.valueOf(rowsReturn));
        params.put("rowsIgnore", Long.valueOf(rowsIgnore));

        response = rest.exchange(
                getServerURL() + UrlMapping.SERVICE_PROVIDER_ANIMAL_TYPE + UrlMapping.SERVICE_PROVIDER_ANIMAL_TYPE_GET_LIST_BY_PROVIDER,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ServiceProviderAnimalType>>() {
                },
                params
        );

        return response.getBody();
    }

    @Override
    public List<ServiceProviderAnimalType> findListByServiceType(long serviceTypeId, int rowsReturn, int rowsIgnore) {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<List<ServiceProviderAnimalType>> response;
        Map<String, Long> params = new HashMap<>();
        params.put("serviceTypeId", serviceTypeId);
        params.put("rowsReturn", Long.valueOf(rowsReturn));
        params.put("rowsIgnore", Long.valueOf(rowsIgnore));

        response = rest.exchange(
                getServerURL() + UrlMapping.SERVICE_PROVIDER_ANIMAL_TYPE + UrlMapping.SERVICE_PROVIDER_ANIMAL_TYPE_GET_LIST_BY_SERVICE_TYPE,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ServiceProviderAnimalType>>() {
                },
                params
        );

        return response.getBody();
    }

    @Override
    public List<ServiceProviderAnimalType> findListByAnimalType(long animalTypeId, int rowsReturn, int rowsIgnore) {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<List<ServiceProviderAnimalType>> response;
        Map<String, Long> params = new HashMap<>();
        params.put("animalTypeId", animalTypeId);
        params.put("rowsReturn", Long.valueOf(rowsReturn));
        params.put("rowsIgnore", Long.valueOf(rowsIgnore));

        response = rest.exchange(
                getServerURL() + UrlMapping.SERVICE_PROVIDER_ANIMAL_TYPE + UrlMapping.SERVICE_PROVIDER_ANIMAL_TYPE_GET_LIST_BY_ANIMAL_TYPE,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ServiceProviderAnimalType>>() {
                },
                params
        );

        return response.getBody();
    }

    @Override
    public void delete(ServiceProviderAnimalType serviceProviderAnimalType) throws Exception {
        deleteById(serviceProviderAnimalType.getId());
    }

    @Override
    public int deleteById(long serviceProviderAnimalTypeId) throws Exception {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<ResponseData<Integer>> response;
        Map<String, Long> params = new HashMap<>();
        params.put("serviceProviderAnimalTypeId", serviceProviderAnimalTypeId);

        response = rest.exchange(
                getServerURL() + UrlMapping.SERVICE_PROVIDER_ANIMAL_TYPE + UrlMapping.SERVICE_PROVIDER_ANIMAL_TYPE_DELETE,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<ResponseData<Integer>>() {
                },
                params
        );

        if (response.getBody().getStatus() == ResponseCode.DELETED)
            return response.getBody().getValue();

        throw new Exception(
                "ExceptionClass: " + response.getBody().getExceptionType().toString()
                        + " Message: " + response.getBody().getExceptionMessage());
    }
}

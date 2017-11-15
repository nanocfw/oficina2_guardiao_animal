package br.com.ga.client.services;

import br.com.ga.client.rest.BasicAuthRestTemplate;
import br.com.ga.client.rest.Service;
import br.com.ga.entity.AnimalType;
import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.exceptions.InvalidEntity;
import br.com.ga.service.intf.IAnimalTypeService;
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
public class AnimalTypeServiceImpl extends Service implements IAnimalTypeService {
    @Override
    public AnimalType createUpdate(AnimalType animalType) throws Exception {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<ResponseData<AnimalType>> response;
        HttpEntity<AnimalType> httpAnimalType = new HttpEntity<>(animalType);

        response = rest.exchange(
                getServerURL() + UrlMapping.ANIMAL_TYPE + UrlMapping.ANIMAL_TYPE_CREATE_UPDATE,
                HttpMethod.POST,
                httpAnimalType,
                new ParameterizedTypeReference<ResponseData<AnimalType>>() {
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
    public AnimalType findById(long animalTypeId) throws Exception {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<ResponseData<AnimalType>> response;
        Map<String, Long> params = new HashMap<>();
        params.put("animalTypeId", animalTypeId);

        response = rest.exchange(
                getServerURL() + UrlMapping.ANIMAL_TYPE + UrlMapping.ANIMAL_TYPE_GET,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseData<AnimalType>>() {
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
    public AnimalType findByDescription(String description) throws Exception {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<ResponseData<AnimalType>> response;
        Map<String, String> params = new HashMap<>();
        params.put("description", description);

        response = rest.exchange(
                getServerURL() + UrlMapping.ANIMAL_TYPE + UrlMapping.ANIMAL_TYPE_GET_BY_DESCRIPTION,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseData<AnimalType>>() {
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
    public List<AnimalType> findList(int rowsReturn, int rowsIgnore) {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<List<AnimalType>> response;
        Map<String, Integer> params = new HashMap<>();
        params.put("rowsReturn", rowsReturn);
        params.put("rowsIgnore", rowsIgnore);

        response = rest.exchange(
                getServerURL() + UrlMapping.ANIMAL_TYPE + UrlMapping.ANIMAL_TYPE_GET_LIST,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AnimalType>>() {
                },
                params
        );

        return response.getBody();
    }

    @Override
    public void delete(AnimalType animalType) throws Exception {
        deleteById(animalType.getId());
    }

    @Override
    public int deleteById(long animalTypeId) throws Exception {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<ResponseData<Integer>> response;
        Map<String, Long> params = new HashMap<>();
        params.put("animalTypeId", animalTypeId);

        response = rest.exchange(
                getServerURL() + UrlMapping.ANIMAL_TYPE + UrlMapping.ANIMAL_TYPE_DELETE,
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

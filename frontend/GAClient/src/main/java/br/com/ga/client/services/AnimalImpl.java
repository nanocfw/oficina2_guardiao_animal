package br.com.ga.client.services;

import br.com.ga.client.rest.BasicAuthRestTemplate;
import br.com.ga.client.rest.Service;
import br.com.ga.entity.Animal;
import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.exceptions.InvalidEntity;
import br.com.ga.service.intf.IAnimalService;
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
public class AnimalImpl extends Service implements IAnimalService {
    @Override
    public Animal createUpdate(Animal animal) throws Exception {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<ResponseData<Animal>> response;
        HttpEntity<Animal> httpAnimal = new HttpEntity<>(animal);

        response = rest.exchange(
                getServerURL() + UrlMapping.ANIMAL + UrlMapping.ANIMAL_CREATE_UPDATE,
                HttpMethod.POST,
                httpAnimal,
                new ParameterizedTypeReference<ResponseData<Animal>>() {
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
    public Animal findById(long id) throws Exception {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<ResponseData<Animal>> response;
        Map<String, Long> params = new HashMap<>();
        params.put("animalId", id);

        response = rest.exchange(
                getServerURL() + UrlMapping.ANIMAL + UrlMapping.ANIMAL_GET,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseData<Animal>>() {
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
    public List<Animal> findList(long ownerId, int rowsReturn, int rowsIgnore) {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<List<Animal>> response;
        Map<String, String> params = new HashMap<>();
        params.put("ownerId", String.valueOf(ownerId));
        params.put("rowsReturn", String.valueOf(rowsReturn));
        params.put("rowsIgnore", String.valueOf(rowsIgnore));

        response = rest.exchange(
                getServerURL() + UrlMapping.ANIMAL + UrlMapping.ANIMAL_GET_LIST,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Animal>>() {
                },
                params
        );

        return response.getBody();
    }

    @Override
    public void delete(Animal animal) throws Exception {
        deleteById(animal.getId());
    }

    @Override
    public int deleteById(long animalId) throws Exception {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<ResponseData<Integer>> response;
        Map<String, Long> params = new HashMap<>();
        params.put("animalId", animalId);

        response = rest.exchange(
                getServerURL() + UrlMapping.ANIMAL + UrlMapping.ANIMAL_DELETE,
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

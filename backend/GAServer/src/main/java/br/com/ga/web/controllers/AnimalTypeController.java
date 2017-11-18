package br.com.ga.web.controllers;

import br.com.ga.entity.AnimalType;
import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.exceptions.InvalidEntity;
import br.com.ga.service.intf.IAnimalTypeService;
import br.com.ga.web.rest.ResponseCode;
import br.com.ga.web.rest.ResponseData;
import br.com.ga.web.rest.UrlMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlMapping.ANIMAL_TYPE)
public class AnimalTypeController {

    @Autowired
    private IAnimalTypeService animalTypeService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.ANIMAL_TYPE_CREATE_UPDATE,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<AnimalType> createUpdate(@RequestBody AnimalType animalType) throws Exception {
        try {
            AnimalType a = animalTypeService.createUpdate(animalType);
            return new ResponseData<>(AnimalType.class, a, ResponseCode.CREATED);
        } catch (InvalidEntity e) {
            return new ResponseData<>(AnimalType.class, ResponseCode.ERROR, InvalidEntity.class, e.getMessage());
        } catch (Exception ex) {
            return new ResponseData<>(AnimalType.class, ResponseCode.ERROR, ex.getClass(), ex.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.ANIMAL_TYPE_GET,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<AnimalType> findById(@PathVariable(value = "animalTypeId") int animalTypeId) throws Exception {
        try {
            AnimalType animalType = animalTypeService.findById(animalTypeId);
            return new ResponseData<>(AnimalType.class, animalType, ResponseCode.FOUND);
        } catch (EntityNotFound e) {
            return new ResponseData<>(AnimalType.class, ResponseCode.NOT_FOUND, EntityNotFound.class, e.getMessage());
        } catch (Exception e) {
            return new ResponseData<>(AnimalType.class, ResponseCode.ERROR, e.getClass(), e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.ANIMAL_TYPE_GET_BY_DESCRIPTION,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<AnimalType> findByDescription(@PathVariable(value = "description") String description) throws Exception {
        try {
            AnimalType animalType = animalTypeService.findByDescription(description);
            return new ResponseData<>(AnimalType.class, animalType, ResponseCode.FOUND);
        } catch (EntityNotFound e) {
            return new ResponseData<>(AnimalType.class, ResponseCode.NOT_FOUND, EntityNotFound.class, e.getMessage());
        } catch (Exception e) {
            return new ResponseData<>(AnimalType.class, ResponseCode.ERROR, e.getClass(), e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.ANIMAL_TYPE_GET_LIST,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AnimalType> findList(
            @PathVariable(value = "rowsReturn") int rowsReturn,
            @PathVariable(value = "rowsIgnore") int rowsIgnore) {
        return animalTypeService.findList(rowsReturn, rowsIgnore);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.ANIMAL_TYPE_DELETE,
            method = RequestMethod.DELETE)
    public ResponseData<Integer> deleteById(@PathVariable(value = "animalTypeId") int animalTypeId) throws Exception {
        try {
            int deletedRows = animalTypeService.deleteById(animalTypeId);
            return new ResponseData<>(Integer.class, deletedRows, ResponseCode.DELETED);
        } catch (Exception e) {
            return new ResponseData<>(Integer.class, ResponseCode.ERROR, e.getClass(), e.getMessage());
        }
    }
}

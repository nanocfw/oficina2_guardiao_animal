/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.web.controllers;

import br.com.ga.entity.Animal;

import java.util.List;

import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.exceptions.InvalidEntity;
import br.com.ga.service.intf.IAnimalService;
import br.com.ga.web.rest.ResponseCode;
import br.com.ga.web.rest.ResponseData;
import br.com.ga.web.rest.UrlMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Marciano
 */
@RestController
@RequestMapping(UrlMapping.ANIMAL)
public class AnimalController {

    @Autowired
    private IAnimalService animalService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.ANIMAL_GET,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Animal> get(@PathVariable(value = "animalId") long animalId) {
        try {
            Animal animal = animalService.findById(animalId);
            return new ResponseData<>(Animal.class, animal, ResponseCode.FOUND);
        } catch (EntityNotFound e) {
            return new ResponseData<>(Animal.class, ResponseCode.NOT_FOUND, EntityNotFound.class, e.getMessage());
        } catch (Exception e) {
            return new ResponseData<>(Animal.class, ResponseCode.ERROR, e.getClass(), e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.ANIMAL_GET_LIST,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Animal> getList(
            @PathVariable(value = "ownerId") long ownerId,
            @PathVariable(value = "rowsReturn") int rowsReturn,
            @PathVariable(value = "rowsIgnore") int rowsIgnore) {
        return animalService.findList(ownerId, rowsReturn, rowsIgnore);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.ANIMAL_CREATE_UPDATE,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Animal> createUpdate(@RequestBody Animal a) {
        try {
            Animal animal = animalService.createUpdate(a);
            return new ResponseData<>(Animal.class, animal, ResponseCode.CREATED);
        } catch (InvalidEntity e) {
            return new ResponseData<>(Animal.class, ResponseCode.ERROR, InvalidEntity.class, e.getMessage());
        } catch (Exception ex) {
            return new ResponseData<>(Animal.class, ResponseCode.ERROR, ex.getClass(), ex.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.ANIMAL_DELETE,
            method = RequestMethod.DELETE)
    public ResponseData<Integer> delete(@PathVariable("animalId") long animalId) throws Exception {
        try {
            int deletedRows = animalService.deleteById(animalId);
            return new ResponseData<>(Integer.class, deletedRows, ResponseCode.DELETED);
        } catch (Exception e) {
            return new ResponseData<>(Integer.class, ResponseCode.ERROR, e.getClass(), e.getMessage());
        }
    }
}

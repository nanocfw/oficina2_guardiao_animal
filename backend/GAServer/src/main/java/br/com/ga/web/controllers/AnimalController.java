/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.web.controllers;

import br.com.ga.entity.Animal;
import br.com.ga.service.IAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marciano
 */
@RestController
//Mapeia as requisições de localhost:8080/person/
@RequestMapping("/animal/")
public class AnimalController {

    @Autowired
    private IAnimalService animalService;

    @ResponseStatus(HttpStatus.OK) //Por padrão responde com o status code 200 success
    @RequestMapping(value = "/{animalId}",
            //Mapeia as requisições GET para localhost:8080/person/
            //recebendo um ID como @PathVariable
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    // Produz JSON como retorno
    public Animal get(@PathVariable(value = "animalId") int animalId) {
        return animalService.findById(animalId);
    }
}

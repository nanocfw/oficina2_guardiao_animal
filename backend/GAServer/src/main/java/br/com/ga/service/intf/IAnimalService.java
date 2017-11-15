/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.service.intf;

import br.com.ga.entity.Animal;

import java.util.List;

/**
 * @author Marciano
 */
public interface IAnimalService {

    Animal createUpdate(final Animal animal) throws Exception;

    Animal findById(final long animalId) throws Exception;

    List<Animal> findList(final long ownerId, final int rowsReturn, int rowsIgnore);

    void delete(final Animal animal) throws Exception;

    int deleteById(long animalId) throws Exception;
}

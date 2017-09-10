/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.service;

import br.com.ga.entity.Animal;
import java.util.List;

/**
 *
 * @author Marciano
 */
public interface IAnimalService {

    Animal create(final Animal animal);

    Animal findById(final int id);

    Animal update(Animal animal);

    List<Animal> findList(final int rowsReturn, int rowsIgnore);

    boolean delete(final int id);
}

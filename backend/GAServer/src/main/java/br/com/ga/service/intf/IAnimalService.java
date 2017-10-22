/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.service.intf;

import br.com.ga.entity.Animal;
import java.util.List;

/**
 *
 * @author Marciano
 */
public interface IAnimalService
{

    Animal create(final Animal animal) throws Exception;

    Animal findById(final int id) throws Exception;

    Animal update(Animal animal) throws Exception;

    List<Animal> findList(final int rowsReturn, int rowsIgnore);

    void delete(final Animal animal) throws Exception;
}

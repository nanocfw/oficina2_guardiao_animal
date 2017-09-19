/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.dao.intf;

import br.com.ga.entity.Animal;
import java.util.List;

/**
 *
 * @author Marciano
 */
public interface IAnimalDao
{

    Animal create(final Animal animal) throws Exception;

    Animal findById(final int id);

    Animal update(Animal animal) throws Exception;

    List<Animal> findList(final int rowsReturn, int rowsIgnore);

    void delete(final Animal animal) throws Exception;
}

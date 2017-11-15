/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.service.implementations;

import br.com.ga.entity.Animal;
import br.com.ga.exceptions.InvalidEntity;
import br.com.ga.service.intf.IAnimalService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ga.dao.intf.IAnimalDao;

/**
 * @author Marciano
 */
@Service
public class AnimalServiceImpl implements IAnimalService {

    @Autowired
    private IAnimalDao animalDao;

    @Override
    public Animal createUpdate(Animal animal) throws Exception {
        if (animal.getOwner_id() == 0)
            throw new InvalidEntity("Id do responsável pelo animal não foi informado.");

        if (animal.getName().isEmpty())
            throw new InvalidEntity("Nome do animal inválido.");

        if (animal.getAnimalType_id() == 0)
            throw new InvalidEntity("Tipo (gato/cachorro/pássaro/etc.) do animal inválido.");

        if (animal.getBreed().isEmpty())
            throw new InvalidEntity("Espécie (raça) do animal inválida.");

        if (animal.getWeight() <= 0)
            throw new InvalidEntity("Peso do animal inválido.");

        return animalDao.createUpdate(animal);
    }

    @Override
    public Animal findById(long animalId) throws Exception {
        return animalDao.findById(animalId);
    }

    @Override
    public List<Animal> findList(final long ownerId, final int rowsReturn, int rowsIgnore) {
        return animalDao.findList(ownerId, rowsReturn, rowsIgnore);
    }

    @Override
    public void delete(Animal animal) throws Exception {
        animalDao.delete(animal);
    }

    @Override
    public int deleteById(long animalId) throws Exception {
        return animalDao.deleteById(animalId);
    }

}

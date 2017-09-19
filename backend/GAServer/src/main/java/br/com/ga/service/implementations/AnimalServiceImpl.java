/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.service.implementations;

import br.com.ga.entity.Animal;
import br.com.ga.service.intf.IAnimalService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ga.dao.intf.IAnimalDao;

/**
 *
 * @author Marciano
 */
@Service
public class AnimalServiceImpl implements IAnimalService
{

    @Autowired
    private IAnimalDao animalDao;

    @Override
    public Animal create(Animal animal) throws Exception
    {
        return animalDao.create(animal);
    }

    @Override
    public Animal findById(int id)
    {
        return animalDao.findById(id);
    }

    @Override
    public Animal update(Animal animal) throws Exception
    {
        return animalDao.update(animal);
    }

    @Override
    public List<Animal> findList(int rowsReturn, int rowsIgnore)
    {
        return animalDao.findList(rowsReturn, rowsIgnore);
    }

    @Override
    public void delete(Animal animal) throws Exception
    {
        animalDao.delete(animal);
    }

}

package br.com.ga.client.services;

import br.com.ga.client.rest.Service;
import br.com.ga.entity.AnimalType;
import br.com.ga.service.intf.IAnimalTypeService;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class AnimalTypeServiceImpl extends Service implements IAnimalTypeService{
    @Override
    public AnimalType createUpdate(AnimalType animalType) throws Exception {
        return null;
    }

    @Override
    public AnimalType findById(long id) throws Exception {
        return null;
    }

    @Override
    public AnimalType findByDescription(String description) throws Exception {
        return null;
    }

    @Override
    public List<AnimalType> findList(int rowsReturn, int rowsIgnore) {
        return null;
    }

    @Override
    public void delete(AnimalType animalType) throws Exception {

    }

    @Override
    public int deleteById(long animalTypeId) throws Exception {
        return 0;
    }
}

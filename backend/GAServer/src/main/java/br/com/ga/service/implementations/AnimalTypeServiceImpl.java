package br.com.ga.service.implementations;

import br.com.ga.dao.intf.IAnimalTypeDao;
import br.com.ga.entity.AnimalType;
import br.com.ga.service.intf.IAnimalTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalTypeServiceImpl implements IAnimalTypeService {

    @Autowired
    IAnimalTypeDao animalTypeDao;

    @Override
    public AnimalType createUpdate(AnimalType animalType) throws Exception {
        return animalTypeDao.createUpdate(animalType);
    }

    @Override
    public AnimalType findById(int animalTypeId) throws Exception {
        return animalTypeDao.findById(animalTypeId);
    }

    @Override
    public AnimalType findByDescription(String description) throws Exception {
        return animalTypeDao.findByDescription(description);
    }

    @Override
    public List<AnimalType> findList(int rowsReturn, int rowsIgnore) {
        return animalTypeDao.findList(rowsReturn, rowsIgnore);
    }

    @Override
    public void delete(AnimalType animalType) throws Exception {
        animalTypeDao.delete(animalType);
    }

    @Override
    public int deleteById(int animalTypeId) throws Exception {
        return animalTypeDao.deleteById(animalTypeId);
    }
}

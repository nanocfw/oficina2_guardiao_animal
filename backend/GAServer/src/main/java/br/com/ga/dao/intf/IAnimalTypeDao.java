package br.com.ga.dao.intf;

import br.com.ga.entity.AnimalType;

import java.util.List;

public interface IAnimalTypeDao {
    AnimalType createUpdate(final AnimalType animalType) throws Exception;

    AnimalType findById(final long animalTypeId) throws Exception;

    AnimalType findByDescription(final String description) throws Exception;

    List<AnimalType> findList(final int rowsReturn, int rowsIgnore);

    void delete(final AnimalType animalType) throws Exception;

    int deleteById(long animalTypeId) throws Exception;
}

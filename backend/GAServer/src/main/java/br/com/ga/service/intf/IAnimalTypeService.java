package br.com.ga.service.intf;

import br.com.ga.entity.AnimalType;

import java.util.List;

public interface IAnimalTypeService {
    AnimalType createUpdate(final AnimalType animalType) throws Exception;

    AnimalType findById(final int animalTypeId) throws Exception;

    AnimalType findByDescription(final String description) throws Exception;

    List<AnimalType> findList(final int rowsReturn, int rowsIgnore);

    void delete(final AnimalType animalType) throws Exception;

    int deleteById(final int animalTypeId) throws Exception;
}

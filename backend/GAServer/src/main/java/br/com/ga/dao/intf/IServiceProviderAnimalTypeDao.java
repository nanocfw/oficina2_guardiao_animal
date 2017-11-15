package br.com.ga.dao.intf;

import br.com.ga.entity.ServiceProviderAnimalType;

import java.util.List;

public interface IServiceProviderAnimalTypeDao {
    ServiceProviderAnimalType createUpdate(final ServiceProviderAnimalType serviceProviderAnimalType) throws Exception;

    ServiceProviderAnimalType findById(final long id) throws Exception;

    List<ServiceProviderAnimalType> findListByProvider(final long serviceProviderId, final int rowsReturn, int rowsIgnore);

    List<ServiceProviderAnimalType> findListByServiceType(final long serviceTypeId, final int rowsReturn, int rowsIgnore);

    List<ServiceProviderAnimalType> findListByAnimalType(final long animalTypeId, final int rowsReturn, int rowsIgnore);

    void delete(final ServiceProviderAnimalType serviceProviderAnimalType) throws Exception;

    int deleteById(long serviceProviderAnimalTypeId) throws Exception;
}

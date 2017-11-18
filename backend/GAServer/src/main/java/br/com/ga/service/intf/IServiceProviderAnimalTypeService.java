package br.com.ga.service.intf;

import br.com.ga.entity.ServiceProviderAnimalType;
import br.com.ga.entity.enums.AnimalSize;
import br.com.ga.entity.enums.BillingType;

import java.util.List;

public interface IServiceProviderAnimalTypeService {
    ServiceProviderAnimalType createUpdate(final ServiceProviderAnimalType serviceProviderAnimalType) throws Exception;

    ServiceProviderAnimalType findById(final long serviceProviderAnimalTypeId) throws Exception;

    boolean alreadyRegistered(final long currentId, final long serviceProviderId, final int serviceTypeId, final int animalTypeId, final AnimalSize animalSize, BillingType billingType) throws Exception;

    List<ServiceProviderAnimalType> findListByProvider(final long serviceProviderId, final int rowsReturn, int rowsIgnore);

    List<ServiceProviderAnimalType> findListByServiceType(final long serviceTypeId, final int rowsReturn, int rowsIgnore);

    List<ServiceProviderAnimalType> findListByAnimalType(final long animalTypeId, final int rowsReturn, int rowsIgnore);

    void delete(final ServiceProviderAnimalType serviceProviderAnimalType) throws Exception;

    int deleteById(long serviceProviderAnimalTypeId) throws Exception;
}

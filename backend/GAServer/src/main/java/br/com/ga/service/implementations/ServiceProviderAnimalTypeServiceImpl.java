package br.com.ga.service.implementations;

import br.com.ga.dao.intf.IServiceProviderAnimalTypeDao;
import br.com.ga.entity.ServiceProviderAnimalType;
import br.com.ga.entity.enums.AnimalSize;
import br.com.ga.entity.enums.BillingType;
import br.com.ga.exceptions.InvalidEntity;
import br.com.ga.service.intf.IServiceProviderAnimalTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProviderAnimalTypeServiceImpl implements IServiceProviderAnimalTypeService {

    @Autowired
    IServiceProviderAnimalTypeDao serviceProviderAnimalTypeDao;

    @Override
    public ServiceProviderAnimalType createUpdate(ServiceProviderAnimalType serviceProviderAnimalType) throws Exception {
        if (alreadyRegistered(serviceProviderAnimalType.getId(),
                serviceProviderAnimalType.getServiceProvider_id(),
                serviceProviderAnimalType.getServiceType_id(),
                serviceProviderAnimalType.getAnimalType_id(),
                serviceProviderAnimalType.getAnimalSize(),
                serviceProviderAnimalType.getBillingType()))
            throw new InvalidEntity("Tipo de serviço já cadastrado.");
        return serviceProviderAnimalTypeDao.createUpdate(serviceProviderAnimalType);
    }

    @Override
    public ServiceProviderAnimalType findById(long serviceProviderAnimalTypeId) throws Exception {
        return serviceProviderAnimalTypeDao.findById(serviceProviderAnimalTypeId);
    }

    @Override
    public boolean alreadyRegistered(long currentId, long serviceProviderId, int serviceTypeId, int animalTypeId, AnimalSize animalSize, BillingType billingType) {
        return serviceProviderAnimalTypeDao.alreadyRegistered(currentId, serviceProviderId, serviceTypeId, animalTypeId, animalSize, billingType);
    }

    @Override
    public List<ServiceProviderAnimalType> findListByProvider(long serviceProviderId, int rowsReturn, int rowsIgnore) {
        return serviceProviderAnimalTypeDao.findListByProvider(serviceProviderId, rowsReturn, rowsIgnore);
    }

    @Override
    public List<ServiceProviderAnimalType> findListByServiceType(long serviceTypeId, int rowsReturn, int rowsIgnore) {
        return serviceProviderAnimalTypeDao.findListByServiceType(serviceTypeId, rowsReturn, rowsIgnore);
    }

    @Override
    public List<ServiceProviderAnimalType> findListByAnimalType(long animalTypeId, int rowsReturn, int rowsIgnore) {
        return serviceProviderAnimalTypeDao.findListByAnimalType(animalTypeId, rowsReturn, rowsIgnore);
    }

    @Override
    public void delete(ServiceProviderAnimalType serviceProviderAnimalType) throws Exception {
        serviceProviderAnimalTypeDao.delete(serviceProviderAnimalType);
    }

    @Override
    public int deleteById(long serviceProviderAnimalTypeId) throws Exception {
        return serviceProviderAnimalTypeDao.deleteById(serviceProviderAnimalTypeId);
    }
}

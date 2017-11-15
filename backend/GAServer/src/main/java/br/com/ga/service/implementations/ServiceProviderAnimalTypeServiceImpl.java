package br.com.ga.service.implementations;

import br.com.ga.dao.intf.IServiceProviderAnimalTypeDao;
import br.com.ga.entity.ServiceProviderAnimalType;
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
        return serviceProviderAnimalTypeDao.createUpdate(serviceProviderAnimalType);
    }

    @Override
    public ServiceProviderAnimalType findById(long serviceProviderAnimalTypeId) throws Exception {
        return serviceProviderAnimalTypeDao.findById(serviceProviderAnimalTypeId);
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

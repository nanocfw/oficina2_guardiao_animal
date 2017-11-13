package br.com.ga.service.implementations;

import br.com.ga.dao.intf.IServiceTypeDao;
import br.com.ga.entity.ServiceType;
import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.exceptions.InvalidEntity;
import br.com.ga.service.intf.IServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTypeServiceImpl implements IServiceTypeService {

    @Autowired
    IServiceTypeDao serviceTypeDao;

    @Override
    public ServiceType createUpdate(ServiceType service) throws Exception {
        if (service.getDescription().isEmpty())
            throw new InvalidEntity("Descrição do serviço inválida.");

        if (service.getValue() <= 0)
            throw new InvalidEntity("Valor cobrado pelo serviço inválido.");

        return serviceTypeDao.createUpdate(service);
    }

    @Override
    public ServiceType findById(int id) throws EntityNotFound {
        return serviceTypeDao.findById(id);
    }

    @Override
    public ServiceType findByDescription(String description) throws Exception {
        return serviceTypeDao.findByDescription(description);
    }

    @Override
    public List<ServiceType> findList() {
        return serviceTypeDao.findList();
    }

    @Override
    public void delete(ServiceType service) throws Exception {
        serviceTypeDao.delete(service);
    }

    @Override
    public int deleteById(long serviceTypeId) throws Exception {
        return serviceTypeDao.deleteById(serviceTypeId);
    }
}

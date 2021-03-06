package br.com.ga.service.intf;

import br.com.ga.entity.ServiceType;
import br.com.ga.exceptions.EntityNotFound;

import java.util.List;

public interface IServiceTypeService {
    ServiceType createUpdate(final ServiceType service) throws Exception;

    ServiceType findById(final int serviceTypeId) throws Exception;

    ServiceType findByDescription(final String description) throws Exception;

    List<ServiceType> findList();

    void delete(final ServiceType service) throws Exception;

    int deleteById(int serviceTypeId) throws Exception;
}

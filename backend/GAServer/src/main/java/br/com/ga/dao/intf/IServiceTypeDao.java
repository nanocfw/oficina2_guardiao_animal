package br.com.ga.dao.intf;

import br.com.ga.entity.ServiceType;
import br.com.ga.exceptions.EntityNotFound;

import java.util.List;

public interface IServiceTypeDao {
    ServiceType createUpdate(final ServiceType service) throws Exception;

    ServiceType findById(final int id) throws EntityNotFound;

    ServiceType findByDescription(final String description) throws Exception;

    List<ServiceType> findList();

    void delete(final ServiceType service) throws Exception;
}

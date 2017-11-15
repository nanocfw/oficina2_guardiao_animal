package br.com.ga.dao.implementations;

import br.com.ga.dao.intf.IServiceProviderAnimalTypeDao;
import br.com.ga.entity.ServiceProviderAnimalType;
import br.com.ga.exceptions.EntityNotFound;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository
public class ServiceProviderAnimalTypeDaoImpl implements IServiceProviderAnimalTypeDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public ServiceProviderAnimalType createUpdate(ServiceProviderAnimalType serviceProviderAnimalType) throws Exception {
        ServiceProviderAnimalType s = em.merge(serviceProviderAnimalType);
        em.flush();
        return s;
    }

    @Override
    public ServiceProviderAnimalType findById(long serviceProviderAnimalTypeId) throws Exception {
        return (ServiceProviderAnimalType) em
                .createQuery("SELECT s FROM ServiceProviderAnimalType s WHERE s.id = :serviceProviderAnimalTypeId")
                .setParameter("serviceProviderAnimalTypeId", serviceProviderAnimalTypeId)
                .getSingleResult();
    }

    @Override
    public List<ServiceProviderAnimalType> findListByProvider(long serviceProviderId, int rowsReturn, int rowsIgnore) {
        return em
                .createQuery("SELECT s FROM ServiceProviderAnimalType s WHERE s.serviceProvider_id = :serviceProvider_id")
                .setParameter("serviceProvider_id", serviceProviderId)
                .setFirstResult(rowsIgnore)
                .setMaxResults(rowsReturn)
                .getResultList();
    }

    @Override
    public List<ServiceProviderAnimalType> findListByServiceType(long serviceTypeId, int rowsReturn, int rowsIgnore) {
        return em
                .createQuery("SELECT s FROM ServiceProviderAnimalType s WHERE s.serviceType_id = :serviceType_id")
                .setParameter("serviceType_id", serviceTypeId)
                .setFirstResult(rowsIgnore)
                .setMaxResults(rowsReturn)
                .getResultList();
    }

    @Override
    public List<ServiceProviderAnimalType> findListByAnimalType(long animalTypeId, int rowsReturn, int rowsIgnore) {
        return em
                .createQuery("SELECT s FROM ServiceProviderAnimalType s WHERE s.animalType_id = :animalType_id")
                .setParameter("animalType_id", animalTypeId)
                .setFirstResult(rowsIgnore)
                .setMaxResults(rowsReturn)
                .getResultList();
    }

    @Override
    public void delete(ServiceProviderAnimalType serviceProviderAnimalType) throws Exception {
        if (serviceProviderAnimalType.getId() <= 0)
            throw new EntityNotFound("Entidade não possui Id");

        em.remove(serviceProviderAnimalType);
    }

    @Override
    public int deleteById(long serviceProviderAnimalTypeId) throws Exception {
        if (serviceProviderAnimalTypeId <= 0)
            throw new EntityNotFound("Id inválido");

        Query qry = em
                .createQuery("DELETE FROM ServiceProviderAnimalType s WHERE s.id = :serviceProviderAnimalTypeId")
                .setParameter("serviceProviderAnimalTypeId", serviceProviderAnimalTypeId);
        return qry.executeUpdate();
    }
}

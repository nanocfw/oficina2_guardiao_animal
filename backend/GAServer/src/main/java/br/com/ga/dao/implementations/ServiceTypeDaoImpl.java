package br.com.ga.dao.implementations;

import br.com.ga.dao.intf.IServiceTypeDao;
import br.com.ga.entity.ServiceType;
import br.com.ga.entity.enums.BillingType;
import br.com.ga.exceptions.EntityNotFound;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Transactional
@Repository
public class ServiceTypeDaoImpl implements IServiceTypeDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public ServiceType createUpdate(ServiceType service) throws Exception {
        ServiceType p = em.merge(service);
        em.flush();
        return p;
    }

    @Override
    public ServiceType findById(int serviceTypeId) throws EntityNotFound {
        TypedQuery<ServiceType> qry = em
                .createQuery("SELECT p FROM ServiceType p WHERE p.id = :serviceTypeId", ServiceType.class);
        try {
            return (ServiceType) qry
                    .setParameter("serviceTypeId", serviceTypeId)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFound();
        }
    }

    @Override
    public ServiceType findByDescription(String description) throws Exception {
        TypedQuery<ServiceType> qry = em
                .createQuery("SELECT p FROM ServiceType p WHERE p.description = :description", ServiceType.class);
        try {
            return (ServiceType) qry
                    .setParameter("description", description)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFound();
        }
    }

    @Override
    public List<ServiceType> findList() {
        return em
                .createQuery("SELECT p FROM ServiceType p")
                .getResultList();
    }

    @Override
    public void delete(ServiceType service) throws Exception {
        em.remove(service);
        em.flush();
    }

    @Override
    public int deleteById(long serviceTypeId) throws Exception {
        if (serviceTypeId <= 0)
            throw new EntityNotFound("Id inválido");

        Query qry = em
                .createQuery("DELETE FROM ServiceType s WHERE s.id = :serviceTypeId")
                .setParameter("serviceTypeId", serviceTypeId);
        return qry.executeUpdate();
    }

    public ServiceTypeDaoImpl() {
        super();
    }

    public void init() {
        ServiceType svc = null;
        try {
            svc = findByDescription("Cuidador");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (svc == null)
            try {
                createUpdate(new ServiceType("Cuidador"));
            } catch (Exception e) {
                e.printStackTrace();
            }

        svc = null;
        try {
            svc = findByDescription("Passeador");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (svc == null)
            try {
                createUpdate(new ServiceType("Passeador"));
            } catch (Exception e) {
                e.printStackTrace();
            }

        svc = null;
        try {
            svc = findByDescription("Tratador");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (svc == null)
            try {
                createUpdate(new ServiceType("Tratador"));
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}

package br.com.ga.dao.implementations;

import br.com.ga.dao.intf.IServiceTypeDao;
import br.com.ga.entity.ServiceType;
import br.com.ga.exceptions.EntityNotFound;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
    public ServiceType findById(int id) throws EntityNotFound {
        TypedQuery<ServiceType> qry = em
                .createQuery("SELECT p FROM ServiceType p WHERE p.id = :id", ServiceType.class);
        try {
            return (ServiceType) qry
                    .setParameter("id", id)
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

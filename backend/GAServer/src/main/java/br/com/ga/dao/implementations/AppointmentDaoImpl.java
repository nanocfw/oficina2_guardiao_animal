package br.com.ga.dao.implementations;

import br.com.ga.dao.intf.IAppointmentDao;
import br.com.ga.entity.Appointment;
import br.com.ga.exceptions.EntityNotFound;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public class AppointmentDaoImpl implements IAppointmentDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Appointment createUpdate(Appointment appointment) throws Exception {
        Appointment a = em.merge(appointment);
        em.flush();
        return a;
    }

    @Override
    public Appointment findById(long appointmentId) throws Exception {
        return (Appointment) em
                .createQuery("SELECT a FROM Appointment a WHERE a.id = :appointmentId")
                .setParameter("appointmentId", appointmentId)
                .getSingleResult();
    }

    @Override
    public List<Appointment> findListByClient(long clientId, int rowsReturn, int rowsIgnore) {
        return em
                .createQuery("SELECT a FROM Appointment a WHERE a.client_id = :clientId")
                .setParameter("clientId", clientId)
                .setFirstResult(rowsIgnore)
                .setMaxResults(rowsReturn)
                .getResultList();
    }

    @Override
    public List<Appointment> findListByServiceProvider(long serviceProviderId, int rowsReturn, int rowsIgnore) {
        return em
                .createQuery("SELECT a FROM Appointment a WHERE a.serviceProvider_id = :serviceProviderId")
                .setParameter("serviceProviderId", serviceProviderId)
                .setFirstResult(rowsIgnore)
                .setMaxResults(rowsReturn)
                .getResultList();
    }

    @Override
    public boolean availableSchedule(long currentId, long serviceProviderId, Date start, Date end) {
        Query qry = em
                .createQuery("SELECT a FROM Appointment a WHERE "
                        + "a.id <> :currentId AND "
                        + "a.serviceProvider_id = :serviceProviderId AND ("
                        + "(:start1 BETWEEN a.startService AND a.endService) OR "
                        + "(:end1 BETWEEN a.startService AND a.endService) OR "
                        + "(a.startService BETWEEN :start2 AND :end2) OR "
                        + "(a.endService BETWEEN :start3 AND :end3)"
                        + ")")
                .setParameter("currentId", currentId)
                .setParameter("serviceProviderId", serviceProviderId)
                .setParameter("start1", start)
                .setParameter("start2", start)
                .setParameter("start3", start)
                .setParameter("end1", end)
                .setParameter("end2", end)
                .setParameter("end3", end)
                .setMaxResults(1);

        return qry.getResultList().size() == 0;
    }

    @Override
    public void delete(Appointment appointment) throws Exception {
        if (appointment.getId() <= 0)
            throw new EntityNotFound("Entidade não possui Id");

        em.remove(appointment);
    }

    @Override
    public int deleteById(long appointmentId) throws Exception {
        if (appointmentId <= 0)
            throw new EntityNotFound("Id inválido");

        Query qry = em
                .createQuery("DELETE FROM Appointment a WHERE a.id = :appointmentId")
                .setParameter("appointmentId", appointmentId);
        return qry.executeUpdate();
    }
}

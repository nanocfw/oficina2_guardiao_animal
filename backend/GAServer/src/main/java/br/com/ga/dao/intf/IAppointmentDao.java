package br.com.ga.dao.intf;

import br.com.ga.entity.Appointment;

import java.util.Date;
import java.util.List;

public interface IAppointmentDao {
    Appointment createUpdate(final Appointment appointment) throws Exception;

    Appointment findById(final long id) throws Exception;

    List<Appointment> findListByClient(final long clientId, final int rowsReturn, int rowsIgnore);

    List<Appointment> findListByServiceProvider(final long serviceProviderId, final int rowsReturn, int rowsIgnore);

    boolean availableSchedule(final long currentId, final long serviceProviderId, final Date start, final Date end);

    void delete(final Appointment appointment) throws Exception;

    int deleteById(long appointmentId) throws Exception;
}

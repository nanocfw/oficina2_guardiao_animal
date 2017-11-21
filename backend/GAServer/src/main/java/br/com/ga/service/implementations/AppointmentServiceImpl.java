package br.com.ga.service.implementations;

import br.com.ga.dao.intf.IAppointmentDao;
import br.com.ga.entity.Appointment;
import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.exceptions.InvalidEntity;
import br.com.ga.service.intf.IAppointmentService;
import br.com.ga.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

    @Autowired
    private IAppointmentDao appointmentDao;

    @Override
    public Appointment createUpdate(Appointment appointment) throws Exception {
        if (appointment.getClient_id() == 0)
            throw new InvalidEntity("Id do cliente não foi informado.");

        if (appointment.getServiceProvider_id() == 0)
            throw new InvalidEntity("Id do prestador de serviços não foi informado");

        if (appointment.getId() == 0) {
            if (appointment.getEndService().before(appointment.getStartService()))
                throw new InvalidEntity("Hora de início do serviço deve ser anterior a hora final.");

            if (appointment.getStartService().before(Util.curDate()) || appointment.getEndService().before(Util.curDate()))
                throw new InvalidEntity("Horário de início e fim do serviço devem ser posteriores a hora atual");

            if (!availableSchedule(appointment.getId(), appointment.getServiceProvider_id(), appointment.getStartService(), appointment.getEndService()))
                throw new InvalidEntity("Horário não está disponível para agendamento.");
        }

        return appointmentDao.createUpdate(appointment);
    }

    @Override
    public Appointment findById(long appointmentId) throws Exception {
        return appointmentDao.findById(appointmentId);
    }

    @Override
    public List<Appointment> findListByClient(long clientId, int rowsReturn, int rowsIgnore) {
        return appointmentDao.findListByClient(clientId, rowsReturn, rowsIgnore);
    }

    @Override
    public List<Appointment> findListByServiceProvider(long serviceProviderId, int rowsReturn, int rowsIgnore) {
        return appointmentDao.findListByServiceProvider(serviceProviderId, rowsReturn, rowsIgnore);
    }

    @Override
    public boolean availableSchedule(long currentId, long serviceProviderId, Date start, Date end) throws Exception {
        return appointmentDao.availableSchedule(currentId, serviceProviderId, start, end);
    }

    @Override
    public void delete(Appointment appointment) throws Exception {
        if (appointment.getId() <= 0)
            throw new EntityNotFound("Entidade não possui Id");

        appointmentDao.delete(appointment);
    }

    @Override
    public int deleteById(long appointmentId) throws Exception {
        return appointmentDao.deleteById(appointmentId);
    }
}

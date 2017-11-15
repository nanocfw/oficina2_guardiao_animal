package br.com.ga.web.controllers;

import br.com.ga.entity.Appointment;
import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.exceptions.InvalidEntity;
import br.com.ga.service.intf.IAppointmentService;
import br.com.ga.web.rest.ResponseCode;
import br.com.ga.web.rest.ResponseData;
import br.com.ga.web.rest.UrlMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(UrlMapping.APPOINTMENT)
public class AppointmentController {

    @Autowired
    IAppointmentService appointmentService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.APPOINTMENT_GET,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Appointment> findById(@PathVariable(value = "appointmentId") long appointmentId) {
        try {
            Appointment appointment = appointmentService.findById(appointmentId);
            return new ResponseData<>(Appointment.class, appointment, ResponseCode.FOUND);
        } catch (EntityNotFound e) {
            return new ResponseData<>(Appointment.class, ResponseCode.NOT_FOUND, EntityNotFound.class, e.getMessage());
        } catch (Exception e) {
            return new ResponseData<>(Appointment.class, ResponseCode.ERROR, e.getClass(), e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.APPOINTMENT_CREATE_UPDATE,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Appointment> createUpdate(@RequestBody Appointment a) {
        try {
            Appointment appointment = appointmentService.createUpdate(a);
            return new ResponseData<>(Appointment.class, appointment, ResponseCode.CREATED);
        } catch (InvalidEntity e) {
            return new ResponseData<>(Appointment.class, ResponseCode.ERROR, InvalidEntity.class, e.getMessage());
        } catch (Exception ex) {
            return new ResponseData<>(Appointment.class, ResponseCode.ERROR, ex.getClass(), ex.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.APPOINTMENT_GET_LIST_BY_CLIENT,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Appointment> findListByClient(
            @PathVariable(value = "clientId") long clientId,
            @PathVariable(value = "rowsReturn") int rowsReturn,
            @PathVariable(value = "rowsIgnore") int rowsIgnore) {
        return appointmentService.findListByClient(clientId, rowsReturn, rowsIgnore);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.APPOINTMENT_GET_LIST_BY_SERVICE_PROVIDER,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Appointment> findListByServiceProvider(
            @PathVariable(value = "serviceProviderId") long serviceProviderId,
            @PathVariable(value = "rowsReturn") int rowsReturn,
            @PathVariable(value = "rowsIgnore") int rowsIgnore) {
        return appointmentService.findListByServiceProvider(serviceProviderId, rowsReturn, rowsIgnore);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.APPOINTMENT_GET_AVAILABLE_SCHEDULE,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Boolean> availableSchedule(
            @PathVariable(value = "currentId") long currentId,
            @PathVariable(value = "serviceProviderId") long serviceProviderId,
            @PathVariable(value = "start") String start,
            @PathVariable(value = "end") String end) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            Date dtStart = dateFormat.parse(start);
            Date dtEnd = dateFormat.parse(end);
            boolean available = appointmentService.availableSchedule(currentId, serviceProviderId, dtStart, dtEnd);
            return new ResponseData<>(Boolean.class, available, ResponseCode.FOUND);
        } catch (Exception e) {
            return new ResponseData<>(Boolean.class, ResponseCode.ERROR, e.getClass(), e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.APPOINTMENT_DELETE,
            method = RequestMethod.DELETE)
    public ResponseData<Integer> deleteById(@PathVariable(value = "appointmentId") long appointmentId) throws Exception {
        try {
            int deletedRows = appointmentService.deleteById(appointmentId);
            return new ResponseData<>(Integer.class, deletedRows, ResponseCode.DELETED);
        } catch (Exception e) {
            return new ResponseData<>(Integer.class, ResponseCode.ERROR, e.getClass(), e.getMessage());
        }
    }
}

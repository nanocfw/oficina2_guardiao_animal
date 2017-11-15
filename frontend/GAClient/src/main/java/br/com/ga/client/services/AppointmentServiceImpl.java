package br.com.ga.client.services;

import br.com.ga.client.rest.BasicAuthRestTemplate;
import br.com.ga.client.rest.Service;
import br.com.ga.entity.Appointment;
import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.exceptions.InvalidEntity;
import br.com.ga.service.intf.IAppointmentService;
import br.com.ga.web.rest.ResponseCode;
import br.com.ga.web.rest.ResponseData;
import br.com.ga.web.rest.UrlMapping;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import javax.ejb.Stateless;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class AppointmentServiceImpl extends Service implements IAppointmentService {
    @Override
    public Appointment createUpdate(Appointment appointment) throws Exception {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<ResponseData<Appointment>> response;
        HttpEntity<Appointment> httpAppointment = new HttpEntity<>(appointment);

        response = rest.exchange(
                getServerURL() + UrlMapping.APPOINTMENT + UrlMapping.APPOINTMENT_CREATE_UPDATE,
                HttpMethod.POST,
                httpAppointment,
                new ParameterizedTypeReference<ResponseData<Appointment>>() {
                });

        if (response.getBody().getStatus() == ResponseCode.CREATED)
            return response.getBody().getValue();

        if (response.getBody().getExceptionType() == InvalidEntity.class)
            throw new InvalidEntity(response.getBody().getExceptionMessage());

        throw new Exception(
                "ExceptionClass: " + response.getBody().getExceptionType().toString()
                        + " Message: " + response.getBody().getExceptionMessage());
    }

    @Override
    public Appointment findById(long appointmentId) throws Exception {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<ResponseData<Appointment>> response;
        Map<String, Long> params = new HashMap<>();
        params.put("appointmentId", appointmentId);

        response = rest.exchange(
                getServerURL() + UrlMapping.APPOINTMENT + UrlMapping.APPOINTMENT_GET,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseData<Appointment>>() {
                },
                params
        );

        if (response.getBody().getStatus() == ResponseCode.FOUND)
            return response.getBody().getValue();

        if (response.getBody().getExceptionType() == EntityNotFound.class)
            throw new EntityNotFound(response.getBody().getExceptionMessage());

        throw new Exception(
                "ExceptionClass: " + response.getBody().getExceptionType().toString()
                        + " Message: " + response.getBody().getExceptionMessage());
    }

    @Override
    public List<Appointment> findListByClient(long clientId, int rowsReturn, int rowsIgnore) {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<List<Appointment>> response;
        Map<String, String> params = new HashMap<>();
        params.put("clientId", String.valueOf(clientId));
        params.put("rowsReturn", String.valueOf(rowsReturn));
        params.put("rowsIgnore", String.valueOf(rowsIgnore));

        response = rest.exchange(
                getServerURL() + UrlMapping.APPOINTMENT + UrlMapping.APPOINTMENT_GET_LIST_BY_CLIENT,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Appointment>>() {
                },
                params
        );

        return response.getBody();
    }

    @Override
    public List<Appointment> findListByServiceProvider(long serviceProviderId, int rowsReturn, int rowsIgnore) {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<List<Appointment>> response;
        Map<String, String> params = new HashMap<>();
        params.put("serviceProviderId", String.valueOf(serviceProviderId));
        params.put("rowsReturn", String.valueOf(rowsReturn));
        params.put("rowsIgnore", String.valueOf(rowsIgnore));

        response = rest.exchange(
                getServerURL() + UrlMapping.APPOINTMENT + UrlMapping.APPOINTMENT_GET_LIST_BY_SERVICE_PROVIDER,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Appointment>>() {
                },
                params
        );

        return response.getBody();
    }

    @Override
    public boolean availableSchedule(long currentId, long serviceProviderId, Date start, Date end) throws Exception {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<ResponseData<Boolean>> response;
        Map<String, String> params = new HashMap<>();
        params.put("currentId", String.valueOf(currentId));
        params.put("serviceProviderId", String.valueOf(serviceProviderId));
        params.put("start", String.format("yyyy-MM-dd hh:mm", start));
        params.put("end", String.format("yyyy-MM-dd hh:mm", end));


        response = rest.exchange(
                getServerURL() + UrlMapping.APPOINTMENT + UrlMapping.APPOINTMENT_GET_AVAILABLE_SCHEDULE,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseData<Boolean>>() {
                },
                params
        );

        if (response.getBody().getStatus() == ResponseCode.FOUND)
            return response.getBody().getValue();

        throw new Exception(
                "ExceptionClass: " + response.getBody().getExceptionType().toString()
                        + " Message: " + response.getBody().getExceptionMessage());
    }

    @Override
    public void delete(Appointment appointment) throws Exception {
        deleteById(appointment.getId());
    }

    @Override
    public int deleteById(long appointmentId) throws Exception {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<ResponseData<Integer>> response;
        Map<String, Long> params = new HashMap<>();
        params.put("appointmentId", appointmentId);

        response = rest.exchange(
                getServerURL() + UrlMapping.APPOINTMENT + UrlMapping.APPOINTMENT_DELETE,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<ResponseData<Integer>>() {
                },
                params
        );

        if (response.getBody().getStatus() == ResponseCode.DELETED)
            return response.getBody().getValue();

        throw new Exception(
                "ExceptionClass: " + response.getBody().getExceptionType().toString()
                        + " Message: " + response.getBody().getExceptionMessage());
    }
}

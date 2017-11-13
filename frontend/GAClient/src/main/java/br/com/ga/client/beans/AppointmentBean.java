package br.com.ga.client.beans;

import br.com.ga.service.intf.IAppointmentService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class AppointmentBean extends DefaultBean {

    @EJB
    IAppointmentService appointmentService;
}

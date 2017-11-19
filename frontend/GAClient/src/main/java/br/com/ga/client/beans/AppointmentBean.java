package br.com.ga.client.beans;

import br.com.ga.entity.Appointment;
import br.com.ga.service.intf.IAppointmentService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class AppointmentBean extends DefaultBean {

    @EJB
    IAppointmentService appointmentService;

    @ManagedProperty(value = "#{loginBean}")
    LoginBean loginBean;

    private Appointment currentAppointment;

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public Appointment getCurrentAppointment() {
        return currentAppointment;
    }

    public void setCurrentAppointment(Appointment currentAppointment) {
        this.currentAppointment = currentAppointment;
    }

    public String createUpdate() {
        this.currentAppointment.setClient_id(loginBean.getAuthenticatedUser().getId());
        return "";
    }

    public AppointmentBean() {
        this.currentAppointment = new Appointment();
    }
}

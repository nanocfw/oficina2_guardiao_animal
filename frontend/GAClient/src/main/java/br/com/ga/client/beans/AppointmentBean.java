package br.com.ga.client.beans;

import br.com.ga.entity.Appointment;
import br.com.ga.service.intf.IAppointmentService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
public class AppointmentBean extends DefaultBean {

    @EJB
    IAppointmentService appointmentService;

    @ManagedProperty(value = "#{loginBean}")
    LoginBean loginBean;

    @ManagedProperty(value = "#{personBean}")
    PersonBean personBean;

    @ManagedProperty(value = "#{animalBean}")
    AnimalBean animalBean;

    @ManagedProperty(value = "#{serviceProviderAnimalTypeBean}")
    ServiceProviderAnimalTypeBean serviceProviderAnimalTypeBean;

    @ManagedProperty(value = "#{serviceTypeBean}")
    ServiceTypeBean serviceTypeBean;

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

    public PersonBean getPersonBean() {
        return personBean;
    }

    public void setPersonBean(PersonBean personBean) {
        this.personBean = personBean;
    }

    public AnimalBean getAnimalBean() {
        return animalBean;
    }

    public void setAnimalBean(AnimalBean animalBean) {
        this.animalBean = animalBean;
    }

    public ServiceTypeBean getServiceTypeBean() {
        return serviceTypeBean;
    }

    public void setServiceTypeBean(ServiceTypeBean serviceTypeBean) {
        this.serviceTypeBean = serviceTypeBean;
    }

    public ServiceProviderAnimalTypeBean getServiceProviderAnimalTypeBean() {
        return serviceProviderAnimalTypeBean;
    }

    public void setServiceProviderAnimalTypeBean(ServiceProviderAnimalTypeBean serviceProviderAnimalTypeBean) {
        this.serviceProviderAnimalTypeBean = serviceProviderAnimalTypeBean;
    }

    public String createUpdate() {
        this.currentAppointment.setClient_id(loginBean.getAuthenticatedUser().getId());
        return "";
    }

    public List<Appointment> getServicesListByProvider() {
        List<Appointment> aux;

        aux = appointmentService.findListByServiceProvider(loginBean.getAuthenticatedUser().getId(), 1000, 0);
        for (Appointment appointment : aux) {
            appointment.setServiceProviderName(loginBean.getAuthenticatedUser().getName());
            try {
                appointment.setClientName(personBean.findById(appointment.getClient_id()).getName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                appointment.setAnimalName(animalBean.findById(appointment.getAnimal_id()).getName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                appointment.setServiceTypeDescription(
                        serviceTypeBean.getTypeName(
                                serviceProviderAnimalTypeBean.findById(appointment.getServiceProviderAnimalType_id()).getServiceType_id()
                        ));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return aux;
    }

    public List<Appointment> getServicesListByClient() {
        List<Appointment> aux;

        aux = appointmentService.findListByClient(loginBean.getAuthenticatedUser().getId(), 1000, 0);
        for (Appointment appointment : aux) {
            appointment.setClientName(loginBean.getAuthenticatedUser().getName());
            appointment.setAnimalName(animalBean.getAnimalName(appointment.getAnimal_id()));
            try {
                appointment.setServiceProviderName(personBean.findById(appointment.getServiceProvider_id()).getName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                appointment.setServiceTypeDescription(
                        serviceTypeBean.getTypeName(
                                serviceProviderAnimalTypeBean.findById(appointment.getServiceProviderAnimalType_id()).getServiceType_id()
                        ));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return aux;
    }

    public AppointmentBean() {
        this.currentAppointment = new Appointment();
    }
}

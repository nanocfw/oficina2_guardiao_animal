package br.com.ga.client.beans;

import br.com.ga.entity.Animal;
import br.com.ga.entity.Appointment;
import br.com.ga.entity.ServiceProviderAnimalType;
import br.com.ga.entity.enums.BillingType;
import br.com.ga.exceptions.InvalidEntity;
import br.com.ga.service.intf.IAppointmentService;
import br.com.ga.util.FacesUtils;
import br.com.ga.util.Util;

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
        ServiceProviderAnimalType service = serviceProviderAnimalTypeBean.getServiceProviderAnimalTypeById(currentAppointment.getServiceProviderAnimalType_id());

        Animal animal;
        try {
            animal = animalBean.findById(currentAppointment.getAnimal_id());
        } catch (Exception ex) {
            FacesUtils.addErrorMessage("form:register", ex.getMessage());
            return "erro";
        }

        if (animal.getAnimalType_id() != service.getAnimalType_id()) {
            FacesUtils.addErrorMessage("form:register", "Tipo do animal diverge do tipo de animal para o serviço escolhido");
            return "cadastroInvalido";
        }

        if (animal.getSize() != service.getAnimalSize()) {
            FacesUtils.addErrorMessage("form:register", "Tamanho do animal diverge do tamanho de animal para o serviço escolhido");
            return "cadastroInvalido";
        }

        currentAppointment.setBillingType(service.getBillingType());

        if (currentAppointment.getBillingType() == BillingType.PER_HOUR)
            currentAppointment.setServiceDuration((int) Util.timeDiffInHours(currentAppointment.getEndService(), currentAppointment.getStartService()));
        else
            currentAppointment.setServiceDuration((int) Util.dateDiffInDays(currentAppointment.getEndService(), currentAppointment.getStartService()));

        currentAppointment.setServiceCost(service.getValue() * currentAppointment.getServiceDuration());

        this.currentAppointment.setServiceProvider_id(serviceProviderAnimalTypeBean.getCurrentServiceProviderId());
        this.currentAppointment.setClient_id(loginBean.getAuthenticatedUser().getId());
        try {
            appointmentService.createUpdate(this.currentAppointment);
            clear();
            return redirectToBuiedServices(loginBean.getAuthenticatedUser().isServiceProvider());
        } catch (InvalidEntity e) {
            FacesUtils.addErrorMessage("form:register", e.getMessage());
            return "cadastroInvalido";
        } catch (Exception e) {
            FacesUtils.addErrorMessage("form:register", e.getMessage());
            return "erro";
        }
    }

    public void clear() {
        currentAppointment = new Appointment();
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

    public void evaluate(Appointment appointment) {
        setCurrentAppointment(appointment);
    }

    public String evaluateServiceProvider() {
        if (currentAppointment.isServiceRated()) {
            FacesUtils.addErrorMessage("form:register", "Serviço já foi avaliado.");
            return "cadastroInvalido";
        }
        if (currentAppointment.getServiceRating() == 0) {
            FacesUtils.addErrorMessage("form:register", "Avaliação mínima deve ser 1 estrela.");
            return "cadastroInvalido";
        }
        currentAppointment.setServiceRated(true);
        currentAppointment.setServiceRatingDate(Util.curDate());
        try {
            appointmentService.createUpdate(this.currentAppointment);
            clear();
            return redirectToBuiedServices(loginBean.getAuthenticatedUser().isServiceProvider());
        } catch (InvalidEntity e) {
            FacesUtils.addErrorMessage("form:register", e.getMessage());
            return "cadastroInvalido";
        } catch (Exception e) {
            FacesUtils.addErrorMessage("form:register", e.getMessage());
            return "erro";
        }
    }

    public String evaluateClient() {
        if (currentAppointment.isClientRated()) {
            FacesUtils.addErrorMessage("form:register", "Serviço já foi avaliado.");
            return "cadastroInvalido";
        }
        if (currentAppointment.getClientRating() == 0) {
            FacesUtils.addErrorMessage("form:register", "Avaliação mínima deve ser 1 estrela.");
            return "cadastroInvalido";
        }
        currentAppointment.setClientRated(true);
        currentAppointment.setClientRatingDate(Util.curDate());
        try {
            appointmentService.createUpdate(this.currentAppointment);
            clear();
            return redirectToProvidedServices();
        } catch (InvalidEntity e) {
            FacesUtils.addErrorMessage("form:register", e.getMessage());
            return "cadastroInvalido";
        } catch (Exception e) {
            FacesUtils.addErrorMessage("form:register", e.getMessage());
            return "erro";
        }
    }

    public AppointmentBean() {
        this.currentAppointment = new Appointment();
    }
}

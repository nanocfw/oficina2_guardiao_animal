package br.com.ga.client.beans;

import br.com.ga.entity.ServiceProviderAnimalType;
import br.com.ga.entity.enums.AnimalSize;
import br.com.ga.entity.enums.BillingType;
import br.com.ga.exceptions.InvalidEntity;
import br.com.ga.service.intf.IServiceProviderAnimalTypeService;
import br.com.ga.util.FacesUtils;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
public class ServiceProviderAnimalTypeBean extends DefaultBean {
    @EJB
    IServiceProviderAnimalTypeService serviceProviderAnimalTypeService;

    @ManagedProperty(value = "#{loginBean}")
    LoginBean loginBean;

    @ManagedProperty(value = "#{serviceTypeBean}")
    ServiceTypeBean serviceTypeBean;

    @ManagedProperty(value = "#{animalTypeBean}")
    AnimalTypeBean animalTypeBean;

    private ServiceProviderAnimalType currentServiceProviderAnimalType;
    private int animalSize;
    private int billingType;

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public ServiceTypeBean getServiceTypeBean() {
        return serviceTypeBean;
    }

    public void setServiceTypeBean(ServiceTypeBean serviceTypeBean) {
        this.serviceTypeBean = serviceTypeBean;
    }

    public AnimalTypeBean getAnimalTypeBean() {
        return animalTypeBean;
    }

    public void setAnimalTypeBean(AnimalTypeBean animalTypeBean) {
        this.animalTypeBean = animalTypeBean;
    }

    public ServiceProviderAnimalType getCurrentServiceProviderAnimalType() {
        return currentServiceProviderAnimalType;
    }

    public void setCurrentServiceProviderAnimalType(ServiceProviderAnimalType currentServiceProviderAnimalType) {
        this.currentServiceProviderAnimalType = currentServiceProviderAnimalType;
        this.animalSize = currentServiceProviderAnimalType.getAnimalSize().ordinal();
        this.billingType = currentServiceProviderAnimalType.getBillingType().ordinal();
        animalTypeBean.setCurrentTypeName(currentServiceProviderAnimalType.getAnimalType_id());
        serviceTypeBean.setCurrentTypeName(currentServiceProviderAnimalType.getServiceType_id());
    }

    public int getAnimalSize() {
        return animalSize;
    }

    public void setAnimalSize(int animalSize) {
        this.animalSize = animalSize;
        this.currentServiceProviderAnimalType.setAnimalSize(AnimalSize.values()[animalSize]);
    }

    public int getBillingType() {
        return billingType;
    }

    public void setBillingType(int billingType) {
        this.billingType = billingType;
        this.currentServiceProviderAnimalType.setBillingType(BillingType.values()[billingType]);
    }

    public List<ServiceProviderAnimalType> getListByProvider() {
        List<ServiceProviderAnimalType> aux = serviceProviderAnimalTypeService.findListByProvider(loginBean.getAuthenticatedUser().getId(), 1000, 0);
        for (ServiceProviderAnimalType s : aux) {
            s.setServiceTypeDescription(serviceTypeBean.getTypeName(s.getServiceType_id()));
            s.setAnimalTypeDescription(animalTypeBean.getTypeName(s.getAnimalType_id()));
        }
        return aux;
    }

    public String createUpdate() throws Exception {
        this.currentServiceProviderAnimalType.setServiceProvider_id(loginBean.getAuthenticatedUser().getId());
        this.currentServiceProviderAnimalType.setAnimalType_id(animalTypeBean.getCurrentIdTypeName());
        this.currentServiceProviderAnimalType.setServiceType_id(serviceTypeBean.getCurrentIdTypeName());

        try {
            setCurrentServiceProviderAnimalType(serviceProviderAnimalTypeService.createUpdate(this.currentServiceProviderAnimalType));
            clear();
            return redirectToServices();
        } catch (InvalidEntity e) {
            FacesUtils.addErrorMessage("form:register", e.getMessage());
            return "cadastroInvalido";
        } catch (Exception e) {
            FacesUtils.addErrorMessage("form:register", e.getMessage());
            return "erro";
        }
    }

    public void clear() {
        this.currentServiceProviderAnimalType = new ServiceProviderAnimalType();
        this.animalSize = 0;
        this.billingType = 0;
        animalTypeBean.setCurrentTypeName(-1);
        serviceTypeBean.setCurrentTypeName(-1);
    }

    public ServiceProviderAnimalTypeBean() {
        super();
        this.currentServiceProviderAnimalType = new ServiceProviderAnimalType();
    }
}

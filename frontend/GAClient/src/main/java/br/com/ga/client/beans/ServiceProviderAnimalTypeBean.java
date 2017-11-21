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
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class ServiceProviderAnimalTypeBean extends DefaultBean {
    @EJB
    IServiceProviderAnimalTypeService serviceProviderAnimalTypeService;

    @ManagedProperty(value = "#{loginBean}")
    LoginBean loginBean;

    @ManagedProperty(value = "#{animalTypeBean}")
    AnimalTypeBean animalTypeBean;

    @ManagedProperty(value = "#{serviceTypeBean}")
    ServiceTypeBean serviceTypeBean;

    private ServiceProviderAnimalType currentServiceProviderAnimalType;
    private int animalSize;
    private int billingType;
    private long currentServiceProviderId;

    private List<SelectItem> serviceTypes;
    private List<ServiceProviderAnimalType> serviceProviderAnimalTypes;

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public ServiceProviderAnimalType getCurrentServiceProviderAnimalType() {
        return currentServiceProviderAnimalType;
    }

    public void setCurrentServiceProviderAnimalType(ServiceProviderAnimalType currentServiceProviderAnimalType) {
        this.currentServiceProviderAnimalType = currentServiceProviderAnimalType;
        this.animalSize = currentServiceProviderAnimalType.getAnimalSize().ordinal();
        this.billingType = currentServiceProviderAnimalType.getBillingType().ordinal();
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

    public List<SelectItem> getServiceTypes() {
        loadServicesForServiceProvider(1);//ajustar p pegar do guardião selecionado na página
        return serviceTypes;
    }

    public void setServiceTypes(List<SelectItem> serviceTypes) {
        this.serviceTypes = serviceTypes;
    }

    public List<ServiceProviderAnimalType> getListByProvider() {
        List<ServiceProviderAnimalType> aux = serviceProviderAnimalTypeService.findListByProvider(loginBean.getAuthenticatedUser().getId(), 1000, 0);
        for (ServiceProviderAnimalType s : aux) {
            s.setServiceTypeDescription(serviceTypeBean.getTypeName(s.getServiceType_id()));
            s.setAnimalTypeDescription(animalTypeBean.getTypeName(s.getAnimalType_id()));
        }
        return aux;
    }

    public AnimalTypeBean getAnimalTypeBean() {
        return animalTypeBean;
    }

    public void setAnimalTypeBean(AnimalTypeBean animalTypeBean) {
        this.animalTypeBean = animalTypeBean;
    }

    public ServiceTypeBean getServiceTypeBean() {
        return serviceTypeBean;
    }

    public void setServiceTypeBean(ServiceTypeBean serviceTypeBean) {
        this.serviceTypeBean = serviceTypeBean;
    }

    public long getCurrentServiceProviderId() {
        return currentServiceProviderId;
    }

    public void setCurrentServiceProviderId(long currentServiceProviderId) {
        this.currentServiceProviderId = currentServiceProviderId;
    }

    public String createUpdate() throws Exception {
        this.currentServiceProviderAnimalType.setServiceProvider_id(loginBean.getAuthenticatedUser().getId());

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

    public void loadServicesForServiceProvider(long serviceProviderId) {
        currentServiceProviderId = serviceProviderId;
        serviceTypes.clear();
        serviceProviderAnimalTypes = serviceProviderAnimalTypeService.findListByProvider(serviceProviderId, 1000, 0);
        for (ServiceProviderAnimalType s : serviceProviderAnimalTypes) {
            s.setDescription("Serviço: " + serviceTypeBean.getTypeName(s.getServiceType_id()) +
                    " Animal: " + animalTypeBean.getTypeName(s.getAnimalType_id()) +
                    " Tamanho: " + s.getAnimalSize().toString() +
                    " Valor: " + String.format("R$ %.2f", s.getValue()) +
                    " " + s.getBillingType().toString());
            serviceTypes.add(new SelectItem(s.getId(), s.getDescription()));
        }
    }

    public ServiceProviderAnimalType getServiceProviderAnimalTypeById(long id) {
        if (serviceProviderAnimalTypes == null || serviceProviderAnimalTypes.isEmpty())
            return null;

        for (ServiceProviderAnimalType s : serviceProviderAnimalTypes)
            if (s.getId() == id)
                return s;
        try {
            return findById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void clear() {
        this.currentServiceProviderAnimalType = new ServiceProviderAnimalType();
        this.animalSize = 0;
        this.billingType = 0;
    }

    public void edit(ServiceProviderAnimalType serviceProviderAnimalType) {
        setCurrentServiceProviderAnimalType(serviceProviderAnimalType);
    }

    public ServiceProviderAnimalType findById(long id) throws Exception {
        return serviceProviderAnimalTypeService.findById(id);
    }

    public ServiceProviderAnimalTypeBean() {
        super();
        this.currentServiceProviderAnimalType = new ServiceProviderAnimalType();
        this.serviceTypes = new ArrayList<>();
    }
}

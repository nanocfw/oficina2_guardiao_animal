package br.com.ga.client.beans;

import br.com.ga.entity.ServiceType;
import br.com.ga.service.intf.IServiceTypeService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class ServiceTypeBean implements Serializable {
    @EJB
    IServiceTypeService serviceTypeService;

    private String currentTypeName;
    private List<String> types;
    private List<ServiceType> serviceTypes;

    public String getCurrentTypeName() {
        return currentTypeName;
    }

    public void setCurrentTypeName(String currentTypeName) {
        this.currentTypeName = currentTypeName;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    @PostConstruct
    public void init() {
        types.clear();
        serviceTypes = serviceTypeService.findList();
        for (ServiceType s : serviceTypes)
            types.add(s.getDescription());
    }

    public void setCurrentTypeName(int id) {
        currentTypeName = "";
        for (ServiceType serviceType : serviceTypes)
            if (serviceType.getId() == id) {
                currentTypeName = serviceType.getDescription();
                break;
            }
    }

    public int getCurrentIdTypeName() {
        int id = 0;

        for (ServiceType serviceType : serviceTypes)
            if (serviceType.getDescription().equals(currentTypeName)) {
                id = serviceType.getId();
                break;
            }

        return id;
    }

    public String getTypeName(int id) {
        for (ServiceType serviceType : serviceTypes)
            if (serviceType.getId() == id) {
                return serviceType.getDescription();
            }
        return "";
    }

    public ServiceTypeBean() {
        super();
        this.types = new ArrayList<>();
        this.serviceTypes = new ArrayList<>();
    }
}

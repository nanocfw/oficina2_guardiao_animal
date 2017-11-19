package br.com.ga.client.beans;

import br.com.ga.entity.ServiceType;
import br.com.ga.service.intf.IServiceTypeService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class ServiceTypeBean implements Serializable {
    @EJB
    IServiceTypeService serviceTypeService;

    private List<SelectItem> serviceTypes;
    private List<ServiceType> types;

    public List<SelectItem> getServiceTypes() {
        return serviceTypes;
    }

    public void setServiceTypes(List<SelectItem> serviceTypes) {
        this.serviceTypes = serviceTypes;
    }

    public List<ServiceType> getTypes() {
        return types;
    }

    public void setTypes(List<ServiceType> types) {
        this.types = types;
    }

    public String getTypeName(int id) {
        for (ServiceType s : types)
            if (s.getId() == id)
                return s.getDescription();
        return "";
    }

    @PostConstruct
    public void init() {
        serviceTypes.clear();
        types = serviceTypeService.findList();
        for (ServiceType s : types)
            serviceTypes.add(new SelectItem(s.getId(), s.getDescription()));
    }

    public ServiceTypeBean() {
        super();
        this.types = new ArrayList<>();
        this.serviceTypes = new ArrayList<>();
    }
}

package br.com.ga.client.beans;

import br.com.ga.entity.ServiceType;
import br.com.ga.service.intf.IServiceTypeService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class ServiceTypeBean implements Serializable {
    @EJB
    IServiceTypeService serviceTypeService;

    private ServiceType currentServiceType;

    public ServiceTypeBean() {
        super();
        this.currentServiceType = new ServiceType();
    }

    public List<ServiceType> findList() {
        return serviceTypeService.findList();
    }
}

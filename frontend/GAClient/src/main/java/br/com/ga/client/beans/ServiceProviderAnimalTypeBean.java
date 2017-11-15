package br.com.ga.client.beans;

import br.com.ga.service.intf.IServiceProviderAnimalTypeService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ServiceProviderAnimalTypeBean {

    @EJB
    IServiceProviderAnimalTypeService serviceProviderAnimalTypeService;
}

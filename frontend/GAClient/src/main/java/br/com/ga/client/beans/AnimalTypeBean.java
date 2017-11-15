package br.com.ga.client.beans;

import br.com.ga.entity.AnimalType;
import br.com.ga.service.intf.IAnimalTypeService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
public class AnimalTypeBean {
    @EJB
    IAnimalTypeService animalTypeService;

    List<AnimalType> getList() {
        return animalTypeService.findList(1000, 0);
    }
}

package br.com.ga.client.beans;

import br.com.ga.entity.AnimalType;
import br.com.ga.service.intf.IAnimalTypeService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class AnimalTypeBean extends DefaultBean {

    @EJB
    IAnimalTypeService animalTypeService;

    private List<SelectItem> types;
    private List<AnimalType> animalTypes;

    public List<SelectItem> getTypes() {
        return types;
    }

    public void setTypes(List<SelectItem> types) {
        this.types = types;
    }

    public List<AnimalType> getAnimalTypes() {
        return animalTypes;
    }

    public void setAnimalTypes(List<AnimalType> animalTypes) {
        this.animalTypes = animalTypes;
    }

    public String getTypeName(int id) {
        for (AnimalType animalType : animalTypes)
            if (animalType.getId() == id) {
                return animalType.getDescription();
            }
        return "";
    }

    @PostConstruct
    public void init() {
        types.clear();
        animalTypes = animalTypeService.findList(1000, 0);
        for (AnimalType a : animalTypes)
            types.add(new SelectItem(a.getId(), a.getDescription()));
    }

    public AnimalTypeBean() {
        this.animalTypes = new ArrayList<>();
        this.types = new ArrayList<>();
    }
}

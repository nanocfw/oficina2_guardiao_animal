package br.com.ga.client.beans;

import br.com.ga.entity.AnimalType;
import br.com.ga.service.intf.IAnimalTypeService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class AnimalTypeBean extends DefaultBean {

    @EJB
    IAnimalTypeService animalTypeService;

    private String currentTypeName;
    private List<String> types;
    private List<AnimalType> animalTypes;

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
        animalTypes = animalTypeService.findList(1000, 0);
        for (AnimalType a : animalTypes)
            types.add(a.getDescription());
    }

    public void setCurrentTypeName(int id) {
        currentTypeName = "";
        for (AnimalType animalType : animalTypes)
            if (animalType.getId() == id) {
                currentTypeName = animalType.getDescription();
                break;
            }
    }

    public int getCurrentIdTypeName() {
        int id = 0;

        for (AnimalType animalType : animalTypes)
            if (animalType.getDescription().equals(currentTypeName)) {
                id = animalType.getId();
                break;
            }

        return id;
    }

    public String getTypeName(int id) {
        for (AnimalType animalType : animalTypes)
            if (animalType.getId() == id) {
                return animalType.getDescription();
            }
        return "";
    }

    public AnimalTypeBean() {
        this.animalTypes = new ArrayList<>();
        this.types = new ArrayList<>();
    }
}

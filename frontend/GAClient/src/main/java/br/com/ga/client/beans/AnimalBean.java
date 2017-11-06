package br.com.ga.client.beans;

import br.com.ga.entity.Animal;
import br.com.ga.service.intf.IAnimalService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class AnimalBean extends DefaultBean {

    @EJB
    IAnimalService animalService;

    private Animal currentAnimal;

    public Animal getCurrentAnimal() {
        return currentAnimal;
    }

    public void setCurrentAnimal(Animal currentAnimal) {
        this.currentAnimal = currentAnimal;
    }

    public AnimalBean() {
        super();
        this.currentAnimal = new Animal();
    }
}

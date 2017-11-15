package br.com.ga.client.beans;

import br.com.ga.entity.Animal;
import br.com.ga.entity.Picture;
import br.com.ga.entity.enums.AnimalSize;
import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.exceptions.InvalidEntity;
import br.com.ga.service.intf.IAnimalService;
import br.com.ga.service.intf.IPictureService;
import br.com.ga.util.FacesUtils;
import br.com.ga.util.Util;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ManagedBean
@SessionScoped
public class AnimalBean extends DefaultBean {
    @EJB
    IAnimalService animalService;

    @EJB
    IPictureService pictureService;

    @ManagedProperty(value = "#{loginBean}")
    LoginBean loginBean;

    @ManagedProperty(value = "#{animalTypeBean}")
    AnimalTypeBean animalTypeBean;

    private Animal currentAnimal;
    private String birthDate;
    private Picture picture;
    private String profilePic;
    private int animalSize;

    public Animal getCurrentAnimal() {
        return currentAnimal;
    }

    public void setCurrentAnimal(Animal currentAnimal) {
        this.currentAnimal = currentAnimal;
        this.animalSize = currentAnimal.getSize().ordinal();
        animalTypeBean.setCurrentTypeName(currentAnimal.getAnimalType_id());
        loadPictureFromDataBase();

        if (currentAnimal.getBirthDate() != null) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            this.birthDate = df.format(currentAnimal.getBirthDate());
        } else
            this.birthDate = "";
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
        this.picture.loadFromString(profilePic);
        this.picture.setUpdated(true);
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getAnimalSize() {
        return animalSize;
    }

    public void setAnimalSize(int animalSize) {
        this.animalSize = animalSize;
        this.currentAnimal.setSize(AnimalSize.values()[animalSize]);
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public AnimalTypeBean getAnimalTypeBean() {
        return animalTypeBean;
    }

    public void setAnimalTypeBean(AnimalTypeBean animalTypeBean) {
        this.animalTypeBean = animalTypeBean;
    }

    public String createUpdate() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(this.birthDate);
        this.currentAnimal.setBirthDate(date);
        this.currentAnimal.setOwner_id(loginBean.getAuthenticatedUser().getId());
        this.currentAnimal.setAnimalType_id(animalTypeBean.getCurrentIdTypeName());

        try {
            if (picture.isUpdated())
                picture = pictureService.createUpdate(picture);
            this.currentAnimal.setProfilePic_id(picture.getId());
            setCurrentAnimal(animalService.createUpdate(this.currentAnimal));
            clear();
            return redirectToAnimals();
        } catch (InvalidEntity e) {
            FacesUtils.addErrorMessage("form:register", e.getMessage());
            return "cadastroInvalido";
        } catch (Exception e) {
            FacesUtils.addErrorMessage("form:register", e.getMessage());
            return "erro";
        }
    }

    public void loadPictureFromDataBase() {
        if (currentAnimal == null || currentAnimal.getId() == 0 || currentAnimal.getProfilePic_id() == 0) {
            profilePic = "";
            return;
        }

        if (picture != null && picture.getId() == currentAnimal.getProfilePic_id() && picture.getPicture() == null && picture.getPicture().length > 0)// imagem já carregada e pertence ao currentPerson
            return;

        try {
            picture = pictureService.findById(currentAnimal.getProfilePic_id());
            picture.setTag(Util.curDate().getTime());
            profilePic = picture.asString();
        } catch (EntityNotFound e) {
            picture = new Picture();
            profilePic = "";
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Animal> list() {
        List<Animal> temp;
        temp = animalService.findList(loginBean.getAuthenticatedUser().getId(), 1000, 0);
        for (Animal a : temp) {
            a.setAge(Util.dateDiff(a.getBirthDate(), Util.curDate(), Calendar.YEAR));
            if (a.getProfilePic_id() > 0)
                try {
                    Picture p = pictureService.findById(a.getProfilePic_id());
                    a.setPicture(p.asString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return temp;
    }

    public void edit(Animal animal) {
        setCurrentAnimal(animal);
    }

    public String newAnimal() {
        clear();
        return "#animal";
    }

    public void clear() {
        currentAnimal = new Animal();
        profilePic = "";
        picture = new Picture();
    }

    public AnimalBean() {
        super();
        this.currentAnimal = new Animal();
        this.picture = new Picture();
    }

}

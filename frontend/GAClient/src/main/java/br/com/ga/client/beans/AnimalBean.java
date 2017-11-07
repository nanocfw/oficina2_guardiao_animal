package br.com.ga.client.beans;

import br.com.ga.entity.Animal;
import br.com.ga.entity.Picture;
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
import java.text.SimpleDateFormat;
import java.util.Date;

@ManagedBean
@SessionScoped
public class AnimalBean extends DefaultBean {

    @EJB
    IAnimalService animalService;
    @EJB
    IPictureService pictureService;
    @ManagedProperty(value = "#{personBean}")
    PersonBean personBean;

    private Animal currentAnimal;
    private String birthDate;
    private Picture picture;
    private String profilePic;

    public Animal getCurrentAnimal() {
        return currentAnimal;
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

    public void setCurrentAnimal(Animal currentAnimal) {
        this.currentAnimal = currentAnimal;
    }

    public AnimalBean() {
        super();
        this.currentAnimal = new Animal();
    }

    public String createUpdate() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(this.birthDate);
        this.currentAnimal.setBirthDate(date);
        this.currentAnimal.setOwner(personBean.getCurrentPerson().getId());

        try {
            setCurrentAnimal(animalService.createUpdate(this.currentAnimal));
            return redirectToLogin();
        } catch (InvalidEntity e) {
            FacesUtils.addErrorMessage("form:register", e.getMessage());
            return "cadastroInvalido";
        } catch (Exception e) {
            FacesUtils.addErrorMessage("form:register", e.getMessage());
            return "erro";
        }
    }

    public void loadPictureFromDataBase() {
        if (currentAnimal == null || currentAnimal.getId() == 0 || currentAnimal.getProfilePic() == 0) {
            profilePic = "";
            return;
        }

        if (picture != null && picture.getId() == currentAnimal.getProfilePic() && picture.getPicture() == null && picture.getPicture().length > 0)// imagem já carregada e pertence ao currentPerson
            return;

        try {
            picture = pictureService.findById(currentAnimal.getProfilePic());
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


}

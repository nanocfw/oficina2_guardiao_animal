/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.client.beans;

import br.com.ga.entity.Picture;
import br.com.ga.entity.ServiceProvider;
import br.com.ga.exceptions.InvalidEntity;
import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.entity.Person;
import br.com.ga.service.intf.IPersonService;
import br.com.ga.service.intf.IPictureService;
import br.com.ga.util.FacesUtils;
import br.com.ga.util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author Marciano
 */
@ManagedBean
@SessionScoped
public class PersonBean extends DefaultBean {

    @EJB
    IPersonService personService;

    @EJB
    IPictureService pictureService;

    private Person currentPerson;
    private String birthDate;
    private Picture picture;
    private String profilePic;

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
        this.picture.loadFromString(profilePic);
        this.picture.setUpdated(true);
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Person getCurrentPerson() {
        return currentPerson;
    }

    public void setCurrentPerson(Person currentPerson) {
        this.currentPerson = currentPerson;
        loadPictureFromDataBase();
        if (currentPerson.getBirthDate() != null) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            this.birthDate = df.format(currentPerson.getBirthDate());
        } else
            this.birthDate = "";
    }

    public String createUpdate() {
        try {
            setCurrentPerson(personService.createUpdate(this.currentPerson));
            return redirectToLogin();
        } catch (InvalidEntity e) {
            FacesUtils.addErrorMessage("form:register", e.getMessage());
            return "cadastroInvalido";
        } catch (Exception e) {
            FacesUtils.addErrorMessage("form:register", e.getMessage());
            return "erro";
        }
    }

    public String updateGuardiao() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(this.birthDate);
        this.currentPerson.setBirthDate(date);
        this.currentPerson.setFinishedRegister(true);

        try {
            if (picture.isUpdated())
                picture = pictureService.createUpdate(picture);
            this.currentPerson.setProfilePic(picture.getId());
            setCurrentPerson(personService.createUpdate(this.currentPerson));

            if (currentPerson.getBirthDate() == null) {
                return redirectToEndRegister();
            } else {
                return redirectToMain();
            }
        } catch (InvalidEntity e) {
            FacesUtils.addErrorMessage("gform:register", e.getMessage());
            return "cadastroInvalido";
        } catch (Exception e) {
            FacesUtils.addErrorMessage("gform:register", "Erro, na funçao updateGuardiao em personBean");
            return "erro";
        }
    }

    public void loadPictureFromDataBase() {
        if (currentPerson == null || currentPerson.getId() == 0 || currentPerson.getProfilePic() == 0) {
            profilePic = "";
            return;
        }

        if (picture != null && picture.getId() == currentPerson.getProfilePic() && picture.getPicture() == null && picture.getPicture().length > 0)// imagem já carregada e pertence ao currentPerson
            return;

        try {
            picture = pictureService.findById(currentPerson.getProfilePic());
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

    public List<ServiceProvider> getListServiceProvider(double lat, double lng, int ray, int rowsReturn, int rowsIgnore) {
        return personService.getServiceProviderList(lat, lng, ray, rowsReturn, rowsIgnore);
    }

    public List<ServiceProvider> getListServiceProvider(String country, String city, int rowsReturn, int rowsIgnore) {
        return personService.getServiceProviderList(country, city, rowsReturn, rowsIgnore);
    }

    public PersonBean() {
        currentPerson = new Person();
        picture = new Picture();
    }

}

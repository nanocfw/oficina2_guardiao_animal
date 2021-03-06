/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.client.beans;

import br.com.ga.entity.Picture;
import br.com.ga.entity.ServiceProviderSearch;
import br.com.ga.entity.enums.PersonType;
import br.com.ga.exceptions.InvalidEntity;
import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.entity.Person;
import br.com.ga.service.intf.IPersonService;
import br.com.ga.service.intf.IPictureService;
import br.com.ga.util.FacesUtils;
import br.com.ga.util.Util;
import org.primefaces.model.map.*;

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
    private Picture picture;
    private String profilePic;
    private int personType;

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
        this.picture.loadFromString(profilePic);
        this.picture.setUpdated(true);
    }

    public Person getCurrentPerson() {
        return currentPerson;
    }

    public void setCurrentPerson(Person currentPerson) {
        this.currentPerson = currentPerson;
        this.personType = currentPerson.getType().ordinal();
        loadPictureFromDataBase();
    }

    public int getPersonType() {
        return this.personType;
    }

    public void setPersonType(int personType) {
        this.personType = personType;
        this.currentPerson.setType(PersonType.values()[personType]);
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

    public String updateServiceProvider() throws Exception {
        currentPerson.setServiceProvider(true);
        return updateGuardiao();
    }

    public String updateClient() throws Exception {
        currentPerson.setServiceProvider(false);
        return updateGuardiao();
    }

    private String updateGuardiao() throws Exception {
        this.currentPerson.setFinishedRegister(true);

        try {
            if (picture.isUpdated())
                picture = pictureService.createUpdate(picture);
            this.currentPerson.setProfilePic_id(picture.getId());
            if (currentPerson.getCountry().equals("Brazil"))
                currentPerson.setCountry("Brasil");
            setCurrentPerson(personService.createUpdate(this.currentPerson));

            if (currentPerson.getBirthDate() == null) {
                return redirectToEndRegister();
            } else {
                if (currentPerson.isServiceProvider()) {
                    return redirectToMain();
                } else {
                    return redirectToMainClient();
                }
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
        if (currentPerson == null || currentPerson.getId() == 0 || currentPerson.getProfilePic_id() == 0) {
            profilePic = "";
            return;
        }

        if (picture != null && picture.getId() == currentPerson.getProfilePic_id() && picture.getPicture() == null && picture.getPicture().length > 0)// imagem já carregada e pertence ao currentPerson
            return;

        try {
            picture = pictureService.findById(currentPerson.getProfilePic_id());
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

    public List<ServiceProviderSearch> getListServiceProvider(double lat, double lng, int ray, int rowsReturn, int rowsIgnore) {
        return personService.getServiceProviderList(0, lat, lng, ray, rowsReturn, rowsIgnore);
    }

    public List<ServiceProviderSearch> getListServiceProvider(String country, String city, int rowsReturn, int rowsIgnore) {
        return personService.getServiceProviderList(0, country, city, rowsReturn, rowsIgnore);
    }

    public Person findById(long id) throws Exception {
        return personService.findById(id);
    }

    public PersonBean() {
        currentPerson = new Person();
        currentPerson.setType(PersonType.UNDEFINED);
        picture = new Picture();
    }

}

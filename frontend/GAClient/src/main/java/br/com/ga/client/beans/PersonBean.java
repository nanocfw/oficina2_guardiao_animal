/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.client.beans;

import br.com.ga.exceptions.InvalidEntity;
import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.entity.Person;
import br.com.ga.service.intf.IPersonService;
import br.com.ga.util.Consts;
import br.com.ga.util.Util;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;

/**
 * @author Marciano
 */
@ManagedBean
@SessionScoped
public class PersonBean implements Serializable {
    @EJB
    IPersonService personService;
    private Person currentPerson;
    private String birthDate;

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

        if (currentPerson.getBirthDate() != null) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            this.birthDate = df.format(currentPerson.getBirthDate());
        } else
            this.birthDate = "";
    }

    public String createUpdate() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        String url;
        try {
            setCurrentPerson(personService.createUpdate(this.currentPerson));
            return redirectToLogin();
        } catch (InvalidEntity e) {
            FacesContext.getCurrentInstance().addMessage("form:register", new FacesMessage("Este Email ja está sendo usado, por favor digite outro Email"));
            return "cadastroInvalido";
        } catch (Exception e) {
            return "erro";
        }
    }

    public String login() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        String url;
        try {
            Person p = personService.findByEmailPassword(this.currentPerson.getEmail(), this.currentPerson.getPassword());
            p = updateToken(p);
            setCurrentPerson(p);
            return redirectToMain();

        } catch (EntityNotFound e) {
            ctx.addMessage("lform:login", new FacesMessage("Email ou senha está incorreto"));
            return "error";
        } catch (Exception e) {
            return "erro";
        }
    }

    private String redirectToMain() throws IOException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        String url;
        if (!currentPerson.isFinishedRegister()) {

            url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, "/endRegister.xhtml"));
            extContext.redirect(url);
            return "endRegister";

        } else {
            url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, "/indexAuth.xhtml"));
            extContext.redirect(url);
            return "indexAuth";
        }
    }

    public String updateGuardiao() throws ParseException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        String url;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(this.birthDate);
        this.currentPerson.setBirthDate(date);
        this.currentPerson.setFinishedRegister(true);

        try {
            setCurrentPerson(personService.createUpdate(this.currentPerson));
            url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, "/endRegister.xhtml"));
            extContext.redirect(url);
            return "endregister.xhtml";
        } catch (InvalidEntity e) {
            FacesContext.getCurrentInstance().addMessage("gform:register", new FacesMessage(e.getMessage()));
            return "cadastroInvalido";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("gform:register", new FacesMessage("Erro, na funçao updateGuardiao em personBean"));
            return "erro";
        }
    }

    public String redirectToLogin() throws IOException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        String url;

        url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, "/index.xhtml#login"));
        extContext.redirect(url);
        return "#login";
    }

    public String validateCookie() throws Exception {
        Cookie cookie = Util.getCookie(Consts.COOKIE_NAME);

        if (cookie == null || cookie.getValue().isEmpty())
            return redirectToLogin();

        UUID token = UUID.fromString(cookie.getValue());

        if (currentPerson != null
                && currentPerson.getAuthToken() != null
                && currentPerson.getAuthToken() == token
                && personService.isValidToken(token)
                )
            return redirectToMain();

        Person p;
        try {
            p = personService.findByValidToken(token);
            p = updateToken(p);
            setCurrentPerson(p);
            return redirectToMain();
        } catch (Exception e) {
            return redirectToLogin();
        }
    }

    public Person updateToken(Person person) throws Exception {
        Person p = personService.updateAuthToken(person);// atualiza o tempo de validade do token
        Util.setCookie(Consts.COOKIE_NAME,
                p.getAuthToken().toString(),
                Consts.COOKIE_LIFE_TIME);
        return p;
    }

    public PersonBean() {
        currentPerson = new Person();
    }

}

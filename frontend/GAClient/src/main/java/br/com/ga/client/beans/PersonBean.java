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
import java.text.SimpleDateFormat;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Marciano
 */
@ManagedBean
@ViewScoped
public class PersonBean
{

    private Person currentPerson;

    public Person getCurrentPerson()
    {
        return currentPerson;
    }

    public void setCurrentPerson(Person currentPerson)
    {
        this.currentPerson = currentPerson;
    }

    @EJB
    IPersonService personService;

    public PersonBean()
    {
        currentPerson = new Person();
    }

    public String createUpdate()
    {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        String url;
        try
        {
            personService.createUpdate(this.currentPerson);
            url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, "/index.xhtml#login"));
            extContext.redirect(url);
            return "#login";
        } catch (InvalidEntity e)
        {
            FacesContext.getCurrentInstance().addMessage("form:register", new FacesMessage("Este Email ja está sendo usado, por favor digite outro Email"));
            return "cadastroInvalido";
        } catch (Exception e)
        {
            return "erro";
        }
    }

    public String login()
    {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        String url;
        try
        {
            Person p;
            p = personService.findByEmailPassword(this.currentPerson.getEmail(), this.currentPerson.getPassword());
            // p deve ser armazenado na memória para uso posterior

            if (p.getBirthDate() == null)
            {

                url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, "/endRegister.xhtml"));
                extContext.redirect(url);
                return "endRegister";

            } else
            {
                url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, "/indexAuth.xhtml"));
                extContext.redirect(url);
                return "indexAuth";
            }

        } catch (EntityNotFound e)
        {
            ctx.addMessage("lform:login", new FacesMessage("Email ou senha está incorreto"));
            return "error";
        } catch (Exception e)
        {
            return "erro";
        }
    }
    
    public String updateGuardiao()
    {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        String url;
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
        String formattedDate = formatter.format(getCurrentPerson().getBirthDate());
        System.out.println("aaaaa" + formattedDate);

                
        try
        {
            personService.createUpdate(this.currentPerson);
            url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, "/endRegister.xhtml"));
            extContext.redirect(url);
            return "endregister.xhtml";
        } catch (InvalidEntity e)
        {
            FacesContext.getCurrentInstance().addMessage("gform:register", new FacesMessage("Erro, na funçao updateGuardiao em personBean"));
            return "cadastroInvalido";
        } catch (Exception e)
        {
            return "erro";
        }
    }

}

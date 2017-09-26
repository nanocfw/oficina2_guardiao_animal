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
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
        try
        {
            personService.createUpdate(this.currentPerson);
            return "salvo";
        } catch (InvalidEntity e)
        {
            return "cadastroInvalido";
        } catch (Exception e)
        {
            return "erro";
        }
    }

    public String login()
    {
        try
        {
            Person p;
            p = personService.findByEmailPassword(this.currentPerson.getEmail(), this.currentPerson.getPassword());
            // p deve ser armazenado na memória para uso posterior

            if (p.getBirthDate() == null)
            {
                FacesContext.getCurrentInstance().getExternalContext().redirect("endRegister.xhtml");
                return "endRegister";
            } else
            {
                FacesContext.getCurrentInstance().getExternalContext().redirect("indexAuth.xhtml");
                return "indexAuth";
            }

        } catch (EntityNotFound e)
        {
            FacesContext.getCurrentInstance().addMessage("login", new FacesMessage("Email ou senha incorretos"));
            return "error";
        } catch (Exception e)
        {
            return "erro";
        }
    }

}

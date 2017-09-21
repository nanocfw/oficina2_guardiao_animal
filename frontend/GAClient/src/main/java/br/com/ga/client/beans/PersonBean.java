/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.client.beans;

import br.com.ga.Exceptions.EmailInUse;
import br.com.ga.entity.Person;
import br.com.ga.service.intf.IPersonService;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
        } catch (EmailInUse e)
        {
            return "emailEmUso";
        } catch (Exception e)
        {
            return "erro";
        }
    }
}

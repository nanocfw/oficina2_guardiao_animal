/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga;

import br.com.ga.exceptions.InvalidEntity;
import br.com.ga.client.implementations.PersonServiceImpl;
import br.com.ga.entity.Person;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marciano
 */
public class Application
{

    public static void main(String[] args)
    {
        PersonServiceImpl p = new PersonServiceImpl();
        try
        {
            Person pe = new Person();
            pe.setEmail("asddas1da2assdas");
            pe = p.createUpdate(pe);
        } catch (InvalidEntity ex)
        {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, "Email em uso", ex);
        } catch (Exception ex)
        {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}

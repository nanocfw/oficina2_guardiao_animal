/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.exceptions;

/**
 *
 * @author Marciano
 */
public class EmailInUse extends Exception
{

    public EmailInUse()
    {
        super("E-Mail em uso.");
    }

    public EmailInUse(String msg)
    {
        super(msg);
    }

}

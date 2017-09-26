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
public class EntityNotFound extends Exception
{

    public EntityNotFound()
    {
        super("Registro não encontrado.");
    }

    public EntityNotFound(String message)
    {
        super(message);
    }
}

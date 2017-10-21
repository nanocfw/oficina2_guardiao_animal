/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.exceptions;

/**
 * @author Marciano
 */
public class InvalidEntity extends Exception {

    public InvalidEntity() {
        super("Registro inválido");
    }

    public InvalidEntity(String msg) {
        super(msg);
    }
}

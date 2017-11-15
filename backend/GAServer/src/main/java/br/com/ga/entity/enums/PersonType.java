/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.entity.enums;

/**
 * @author Marciano
 */
public enum PersonType {
    MALE, FEMALE, UNDEFINED;

    @Override
    public String toString() {
        return asString(this);
    }

    public static String asString(PersonType personType) {
        switch (personType) {
            case MALE:
                return "Homem";
            case FEMALE:
                return "Mulher";
            default:
                return "Inválido";
        }
    }

    public static String[] getAll() {
        PersonType[] personTypes = values();
        String[] aux = new String[personTypes.length];

        for (int i = 0; i < personTypes.length; i++)
            aux[i] = asString(personTypes[i]);

        return aux;
    }
}

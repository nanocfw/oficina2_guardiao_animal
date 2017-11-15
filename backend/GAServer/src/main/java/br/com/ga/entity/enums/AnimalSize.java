/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.entity.enums;

/**
 * @author Marciano
 */
public enum AnimalSize {
    SMALL, MIDDLE, LARGE;

    @Override
    public String toString() {
        return asString(this);
    }

    public static String asString(AnimalSize animalSize) {
        switch (animalSize) {
            case SMALL:
                return "Pequeno";
            case MIDDLE:
                return "Médio";
            case LARGE:
                return "Grande";
            default:
                return "Inválido";
        }
    }

    public static String[] getAll() {
        AnimalSize[] animalSizes = values();
        String[] aux = new String[animalSizes.length];

        for (int i = 0; i < animalSizes.length; i++)
            aux[i] = asString(animalSizes[i]);

        return aux;
    }
};

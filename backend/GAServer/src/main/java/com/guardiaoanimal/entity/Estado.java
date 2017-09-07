package com.guardiaoanimal.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
class Estado {

    @Id
    private int codigo;
    private String UF;
    private String nome;

    public int getCodigo() {
        return codigo;
    }

    public String getUF() {
        return UF;
    }

    public String getNome() {
        return nome;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.codigo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Estado other = (Estado) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }

    public Estado() {
        super();
    }

}

package com.guardiaoanimal.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Cidade {

    @Id
    private int codigo;
    private String nome;
    private String CEP;
    @ManyToOne(fetch = FetchType.EAGER)
    private Estado estado;

    public String getNome() {
        return nome;
    }

    public String getCEP() {
        return CEP;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.codigo;
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
        final Cidade other = (Cidade) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }

    public Cidade() {
        super();
    }

}

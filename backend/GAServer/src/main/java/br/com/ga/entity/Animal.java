package br.com.ga.entity;

import br.com.ga.entity.enums.AnimalSize;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Animal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String type;
    private String specie;
    @Enumerated(EnumType.ORDINAL)
    private AnimalSize size;
    private int temperament;
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private double wheight;

    public Animal() {
        super();
    }

}

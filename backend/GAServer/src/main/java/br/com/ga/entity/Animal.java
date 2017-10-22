package br.com.ga.entity;

import br.com.ga.entity.enums.AnimalSize;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

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
    @ManyToOne
    private Person owner;

    public Animal() {
        super();
    }

}

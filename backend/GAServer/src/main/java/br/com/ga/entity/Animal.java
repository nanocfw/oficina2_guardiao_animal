package br.com.ga.entity;

import br.com.ga.entity.enums.AnimalSize;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private long id;
    private String name;
    private String type;
    private String specie;
    @Enumerated(EnumType.ORDINAL)
    private AnimalSize size;
    private int temperament;
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private double weight;
    private long owner_id;// não é necessário carregar os dados de Person a cada animal carregado do banco, é necessário apenas saber qual o id do dono
    private long profilePic_id;

    @Transient
    private int age;

    @Transient
    String picture;

    public Animal() {
        super();
    }

}

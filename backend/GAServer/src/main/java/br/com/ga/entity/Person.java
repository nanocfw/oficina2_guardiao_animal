package br.com.ga.entity;

import br.com.ga.entity.enums.PersonType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String city;
    private String state;
    private String country;
    private boolean serviceProvider;
    private String documentNumber;//cpf cnpj
    private String address;
    private String addressNumber;
    private String district;
    private String complement;
    private String postalCode;
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @ElementCollection
    private List<Animal> listAnimals;
    private String phone;
    private String cellPhone;
    private String additionalData;
    private double latitude;
    private double longitude;
    private boolean finishedRegister;
    private String profilePic;
    private PersonType type;
    @Type(type = "uuid-char")
    private UUID authToken;
    @Temporal(TemporalType.DATE)
    private Date authTokenExpiration;

    public Person() {
        super();
    }
}

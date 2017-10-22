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
    //@ElementCollection  // não é necessário ter a lista sempre carregada, por ser servidor REST, toda requisição irá transferir os dados de todos os animais, o que é desnecessário
    //private List<Animal> listAnimals; // adicionado a classe de animal pra informar a qual pessoa pertence
    private String phone;
    private String cellPhone;
    private String additionalData;
    private double latitude;
    private double longitude;
    private boolean finishedRegister;
    private long profilePic;// não é necessário carregar sempre, por isso é armazenado apenas qual o id da imagem para ser lida apenas quando precisar
    private PersonType type;
    @Type(type = "uuid-char")
    private UUID authToken;
    @Temporal(TemporalType.DATE)
    private Date authTokenExpiration;

    public Person() {
        super();
    }
}

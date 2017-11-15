package br.com.ga.entity;

import br.com.ga.entity.enums.BillingType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Appointment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long client_id; // id da pessoa que vai contratar um serviço
    private long serviceProvider_id; // id do prestador de serviços
    private long animal_id;// animal do cliente para o qual será prestado o serviço
    @Temporal(TemporalType.TIMESTAMP)
    private Date startService;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endService;
    private String additionalMessage;
    private int serviceProviderAnimalType_id;// tipo do serviço prestado
    private int serviceDuration;// em horas, arredonda para acertar o preço
    @Enumerated(value = EnumType.ORDINAL)
    private BillingType billingType;
    private double serviceCost;// preço pelo serviço

    private boolean serviceRated;
    private int serviceRating;//avaliação do serviço prestado
    private String serviceRatingMessage;
    @Temporal(TemporalType.TIMESTAMP)
    private Date serviceRatingDate;

    private boolean clientRated;
    private int clientRating;// avaliação do cliente pelo prestador
    private String clientRatingMessage;
    @Temporal(TemporalType.TIMESTAMP)
    private Date clientRatingDate;

    public Appointment() {
        super();
    }
}

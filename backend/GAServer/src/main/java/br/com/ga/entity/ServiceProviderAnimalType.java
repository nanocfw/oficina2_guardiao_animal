package br.com.ga.entity;

import br.com.ga.entity.enums.AnimalSize;
import br.com.ga.entity.enums.BillingType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceProviderAnimalType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long serviceProvider_id;
    private int serviceType_id;
    private int animalType_id;
    @Enumerated(value = EnumType.ORDINAL)
    private AnimalSize animalSize;
    private BillingType billingType;
    private double value;

    @Transient
    private String serviceTypeDescription;

    @Transient
    String animalTypeDescription;

    @Transient
    String description;//usado apenas para exibir na listagem

    public ServiceProviderAnimalType() {
        super();
        animalSize = AnimalSize.SMALL;
        billingType = BillingType.PER_HOUR;
    }
}

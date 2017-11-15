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
    private long serviceType_id;
    private long animalType_id;
    @Enumerated(value = EnumType.ORDINAL)
    private AnimalSize animalSize;
    private BillingType billingType;
    private double value;

    public ServiceProviderAnimalType() {
        super();
    }

    public ServiceProviderAnimalType(long serviceProvider_id, long serviceType_id, long animalType_id, AnimalSize animalSize, BillingType billingType, double value) {
        this.serviceProvider_id = serviceProvider_id;
        this.serviceType_id = serviceType_id;
        this.animalType_id = animalType_id;
        this.animalSize = animalSize;
        this.billingType = billingType;
        this.value = value;
    }
}

package br.com.ga.entity;

import br.com.ga.entity.enums.BillingType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ServiceType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private double value;
    private BillingType billingType;

    public ServiceType() {
        super();
    }

    public ServiceType(String description, double value, BillingType billingType) {
        this.description = description;
        this.value = value;
        this.billingType = billingType;
    }
}

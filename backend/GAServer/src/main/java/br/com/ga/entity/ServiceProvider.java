package br.com.ga.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServiceProvider implements Serializable {
    private String name;
    private String country;
    private String city;
    private double latitude;
    private double longitude;

    public ServiceProvider(String name, String country, String city, double latitude, double longitude) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

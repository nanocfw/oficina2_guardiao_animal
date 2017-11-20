package br.com.ga.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ServiceProviderSearch implements Serializable {
    private long id;
    private String name;
    private String message;
    private String country;
    private String city;
    private String picture;
    private double latitude;
    private double longitude;
    private List<ServiceProviderAnimalType> serviceList;

    public ServiceProviderSearch(long id, String name, String message, String country, String city, String picture, double latitude, double longitude) {
        this.serviceList = new ArrayList<>();
        this.id = id;
        this.name = name;
        this.message = message;
        this.country = country;
        this.city = city;
        this.picture = picture;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

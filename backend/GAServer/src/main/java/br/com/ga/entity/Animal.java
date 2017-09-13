package br.com.ga.entity;

import br.com.ga.entity.enums.AnimalSize;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Animal implements Serializable
{

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

    public Animal()
    {
        super();
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getSpecie()
    {
        return specie;
    }

    public void setSpecie(String specie)
    {
        this.specie = specie;
    }

    public AnimalSize getSize()
    {
        return size;
    }

    public void setSize(AnimalSize size)
    {
        this.size = size;
    }

    public int getTemperament()
    {
        return temperament;
    }

    public void setTemperament(int temperament)
    {
        this.temperament = temperament;
    }

    public Date getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(Date birthDate)
    {
        this.birthDate = birthDate;
    }

    public double getWheight()
    {
        return wheight;
    }

    public void setWheight(double wheight)
    {
        this.wheight = wheight;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 13 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Animal other = (Animal) obj;
        return this.id == other.id;
    }

}

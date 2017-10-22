package br.com.ga.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.engine.jdbc.internal.BinaryStreamImpl;
//import org.primefaces.model.DefaultStreamedContent;

import javax.persistence.*;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    private byte[] picture;

    @Transient
    private long tag;

    @Transient
    private boolean updated;

    public String asString() {
        if (picture == null || picture.length == 0)
            return "";

        return new String(picture);
    }

    public void loadFromString(String src) {
        picture = src.getBytes();
    }
}

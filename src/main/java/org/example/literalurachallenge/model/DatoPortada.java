package org.example.literalurachallenge.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatoPortada{
    @JsonAlias("image/jpeg")
    String portada;
    public String getPortada() {
        return portada;
    }
}

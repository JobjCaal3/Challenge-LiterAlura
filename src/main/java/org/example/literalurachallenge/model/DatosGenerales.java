package org.example.literalurachallenge.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosGenerales{
    @JsonAlias("results") List<DatosLibro> resultados;

    public List<DatosLibro> getResultados() {
        return resultados;
    }
}

package org.example.literalurachallenge.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosLibro{
    @JsonAlias("title")
    private String titulo;
    @JsonAlias("authors")
    private List<DatosAutor> autor;
    @JsonAlias("languages")
    private List<String> lenguaje;
    @JsonAlias("download_count")
    private Double descargas;
    @JsonAlias("formats")
    private DatoPortada portada;

    public List<DatosAutor> getAutor() {
        return autor;
    }

    public void setAutor(List<DatosAutor> autor) {
        this.autor = autor;
    }

    public Double getDescargas() {
        return descargas;
    }

    public void setDescargas(Double descargas) {
        this.descargas = descargas;
    }

    public List<String> getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(List<String> lenguaje) {
        this.lenguaje = lenguaje;
    }

    public DatoPortada getPortada() {
        return portada;
    }

    public void setPortada(DatoPortada portada) {
        this.portada = portada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}


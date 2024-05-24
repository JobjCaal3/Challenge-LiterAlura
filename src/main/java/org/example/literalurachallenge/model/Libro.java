package org.example.literalurachallenge.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private Long idLibro;
    private String titulo;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "libro_lenguaje")
    @Column(name = "lenguaje")
    private List<String> lenguaje;
    private Double descargas;
    private String portada;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "autores_libros", joinColumns = @JoinColumn(name = "libro_id", referencedColumnName = "id_libro"),
            inverseJoinColumns = @JoinColumn(name = "autor_id", referencedColumnName = "id_autor"))
    private List<Autor> autores = new ArrayList<>();

    public Libro(){}

    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.getTitulo();
        this.lenguaje = datosLibro.getLenguaje();
        this.descargas = (datosLibro.getDescargas() != null) ? datosLibro.getDescargas() : 0.0;
        this.portada = datosLibro.getPortada().getPortada();
    }


    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public List<String> getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(List<String> lenguaje) {
        this.lenguaje = lenguaje;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Double getDescargas() {
        return descargas;
    }

    public void setDescargas(Double descargas) {
        this.descargas = descargas;
    }

    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}

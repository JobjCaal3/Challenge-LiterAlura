package org.example.literalurachallenge.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor")
    private Long idautor;
    private String nombre;
    private String fechaNacimiento;
    private String fechaFallcimiento;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "autores_Libros"
            , joinColumns = @JoinColumn(name = "autor_id", referencedColumnName = "id_autor")
            , inverseJoinColumns = @JoinColumn(name = "libro_id", referencedColumnName = "id_libro"))
    private List<Libro> libros = new ArrayList<>();

    public Autor(){}

    public Autor(DatosAutor datosAutor){
        this.nombre = datosAutor.nombre();
        this.fechaNacimiento = datosAutor.fechaNacimiento();
        this.fechaFallcimiento = datosAutor.fechaFallcimiento();
    }

    public String getFechaFallcimiento() {
        return fechaFallcimiento;
    }

    public void setFechaFallcimiento(String fechaFallcimiento) {
        this.fechaFallcimiento = fechaFallcimiento;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getIdautor() {
        return idautor;
    }

    public void setIdautor(Long idAuthor) {
        this.idautor = idAuthor;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public String toString() {
        return "Autor{" +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", fechaFallcimiento='" + fechaFallcimiento + '\'' +
                ", libros=" + libros +
                '}';
    }
}

package org.example.literalurachallenge.model;


import org.example.literalurachallenge.service.ConsumoAPI;
import org.example.literalurachallenge.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Incio {
    private Scanner entrada = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private String URL_BASE = "https://gutendex.com/books";
    private ConvierteDatos conversor = new ConvierteDatos();
    private DatosAutor datosAutor;
    private DatosLibro datosLibro;
    private Autor autor;
    private Libro libro;

    public void muestraMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1- Buscar libros por nombre
                    2- Mostrar libros buscados
                    3- Mostrar autores
                    4- Buscar autores por determinado año
                    5- Buscar libros por idioma
                    0- Salir
                    """;
            System.out.println(menu);
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorNombre();
                    break;
                case 0:
                    System.out.println("Saliendo de la app");
                    break;
                default:
                    System.out.println("Opcion invalid");
            }
        }
    }

    private void buscarLibroPorNombre() {
        System.out.println("Escribe el nombre del libro");
        var nombreLibro = entrada.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE+"/?search="+nombreLibro.replace(" ","+"));
        DatosGenerales datosRespuesta = conversor.obtenerDatos(json, DatosGenerales.class);
        Optional<DatosLibro> datosLibroOpt = datosRespuesta.resultados().stream()
                .filter(l->l.titulo().toUpperCase().contains(nombreLibro.toUpperCase())).findFirst();

        if (datosLibroOpt.isPresent()) {
            DatosLibro datosLibro = datosLibroOpt.get();
            libro = new Libro(datosLibro);
            for (DatosAutor datosAutor : datosLibro.autor()) {
                autor = new Autor(datosAutor);
                autor.getLibros().add(libro);
                libro.getAutores().add(autor);
                System.out.println(autor);
            }
            System.out.println(libro);
        } else {
            System.out.println("No se encontró el libro con el nombre: " + nombreLibro);
        }
    }

    private void librosRegistrados() {

    }

    private void autoresResgistrados() {
    }

    private void autoresPorFecha() {
    }

    private void librosPorIdioma() {
    }
}

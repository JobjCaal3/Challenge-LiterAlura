package org.example.literalurachallenge.model;


import org.example.literalurachallenge.repositories.AutorRepository;
import org.example.literalurachallenge.repositories.LibroRepository;
import org.example.literalurachallenge.service.ConsumoAPI;
import org.example.literalurachallenge.service.ConvierteDatos;

import java.util.*;

public class Inicio {
    private Scanner entrada = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository libroRepo;
    private AutorRepository autorRepo;
    private Libro libro;
    private Autor autor;
    private DatosGenerales datosGenerales;

    public Inicio(LibroRepository libroRepo, AutorRepository autorRepo) {
        this.libroRepo = libroRepo;
        this.autorRepo = autorRepo;
    }

    public void muestraMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    \n1- Buscar libros por nombre
                    2- Mostrar libros registrados
                    3- Mostrar autores registrados
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
                case 2:
                    muestraLibros();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    buscarAutoresRangoanio();
                    break;
                case 5:

                    break;
                case 0:
                    System.out.println("Saliendo de la app");
                    break;
                default:
                    System.out.println("Opcion invalid");
            }
        }
    }

    public void buscarLibroPorNombre() {
        System.out.println("Ingrese el nombre del libro que desea buscar");
        var tituloLibro = entrada.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, DatosGenerales.class);
        Optional<DatosLibro> LibroBusqueda = datosBusqueda.resultados.stream()
                .filter(l -> l.getTitulo().toLowerCase().contains(tituloLibro.toLowerCase())).findFirst();
        if (LibroBusqueda.isPresent()) {
            // Crear y guardar los autores
            List<Autor> autores = new ArrayList<>();
            for (DatosAutor datosAutor : LibroBusqueda.get().getAutor()) {
                autor = new Autor(datosAutor);
                autor = autorRepo.save(autor);
                autores.add(autor);
            }
            // Crear el libro
            libro = new Libro(LibroBusqueda.get());
            libro.setAutores(autores);
            libro.setLenguaje(LibroBusqueda.get().getLenguaje());
            // Guardar el libro con sus autores
            libro = libroRepo.save(libro);
            // Actualizar los autores con el nuevo libro
            for (Autor autor : autores) {
                autor.getLibros().add(libro);
                autorRepo.save(autor);
            }
        } else {
            System.out.println("No se encontró ningún libro que coincida con tu búsqueda.");
        }
    }
    public void muestraLibros() {
        // Recuperar todos los libros de la base de datos
        List<Libro> libros = libroRepo.findAll();
        // Mostrar cada libro con sus autores y lenguajes
        libros.stream().forEach(l -> {
            System.out.println("\n-------------Datos del libro-------------");
            System.out.printf("Titulo: %s - Lenguaje: %s - Descargas: %s - Portada: %s\n",
                    l.getTitulo(), l.getLenguaje(), l.getDescargas(), l.getPortada());
            System.out.println("-------------Datos del Autor del libro-------------");
            l.getAutores().forEach(a -> System.out.printf("Nombre: %s - Fecha Nacimiento: %s - Fecha Fallecimiento: %s\n",
                    a.getNombre(), a.getFechaNacimiento(), a.getFechaFallecimiento()));
        });
    }
    public void listarAutoresRegistrados() {
        // Recuperar todos los autores de la base de datos
        List<Autor> autores = autorRepo.findAll();
        // Mostrar cada autor con sus libros
        autores.forEach(a -> {
            System.out.println("Nombre del a: " + a.getNombre());
            System.out.println("Libros del a:");
            a.getLibros().forEach(libro -> System.out.println("- " + libro.getTitulo()));
            System.out.println();
        });
    }

    public void buscarAutoresRangoanio() {
        System.out.println("Ingrese el año de inicio:");
        var inicio = entrada.nextLine();
        System.out.println("Ingrese el año final:");
        var fin = entrada.nextLine();

        List<Autor> autores = autorRepo.findAutoresByRangoDeAños(inicio, fin);
        autores.forEach(a -> {
            System.out.println("Nombre del autor: " + a.getNombre());
            System.out.println("Libros del autor:");
            a.getLibros().forEach(libro -> System.out.println("- " + libro.getTitulo()));
            System.out.println();
        });
    }
}

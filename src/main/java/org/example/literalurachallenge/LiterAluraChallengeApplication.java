package org.example.literalurachallenge;

import org.example.literalurachallenge.model.Inicio;
import org.example.literalurachallenge.repositories.AutorRepository;
import org.example.literalurachallenge.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraChallengeApplication implements CommandLineRunner {
    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LibroRepository libroRepository;
    public static void main(String[] args) {
        SpringApplication.run(LiterAluraChallengeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Inicio incio = new Inicio(libroRepository,autorRepository);
        incio.muestraMenu();
    }
}

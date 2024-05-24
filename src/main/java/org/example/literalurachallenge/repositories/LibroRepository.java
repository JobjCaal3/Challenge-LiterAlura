package org.example.literalurachallenge.repositories;

import org.example.literalurachallenge.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {
    Optional<Libro> findByTitulo(String titulo);
    List<Libro> findAllByLenguaje(String lenguaje);
}

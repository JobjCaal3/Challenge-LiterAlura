package org.example.literalurachallenge.repositories;

import org.example.literalurachallenge.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor,Long> {
    Optional<Autor> findByNombre(String nombre);
    //esta consulta trae a todos los autoes que nacieron en la fehca inicio, murieron entre esta y la fecha de fallecimiento
    //pero ademas trae a los autores que no tengan fecha de fallecimiento es decir que aun siguen vivos
    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento >= :inicio AND ((a.fechaFallecimiento <= :fin AND a.fechaFallecimiento IS NOT NULL) OR (a.fechaFallecimiento IS NULL AND a.fechaNacimiento <= :fin))")
    List<Autor> findAutoresByRangoDeAños(@Param("inicio") String inicio, @Param("fin") String fin);
}

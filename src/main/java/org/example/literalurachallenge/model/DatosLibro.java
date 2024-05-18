package org.example.literalurachallenge.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(@JsonAlias("title") String titulo,
                         @JsonAlias("languages") String lenguaje,
                         @JsonAlias("download_count") Double descargas,
                         @JsonAlias("image/jpeg") String portada,
                         @JsonAlias("authors") List<DatosAutor> autor) {
}

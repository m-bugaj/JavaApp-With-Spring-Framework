package com.example.javaappwithspringframework.api.mapper;

import com.example.javaappwithspringframework.api.model.MovieDTO;
import com.example.javaappwithspringframework.model.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieDTO movieToMovieDTO(Movie movie);

    Movie movieDTOToMovie(MovieDTO movieDTO);
}

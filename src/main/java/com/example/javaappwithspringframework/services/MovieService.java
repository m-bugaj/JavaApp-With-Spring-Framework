package com.example.javaappwithspringframework.services;

import com.example.javaappwithspringframework.api.model.DirectorDTO;
import com.example.javaappwithspringframework.api.model.MovieDTO;

import java.util.List;

public interface MovieService {
    List<MovieDTO> getAllMovies();

    MovieDTO getMoviesById(Long id);

    MovieDTO getMoviesByTitleAndReleaseDate(String title, String releaseDate);

    MovieDTO createNewMovie(MovieDTO movieDTO);

    MovieDTO updateMovie(Long id, MovieDTO movieDTO);

    void deleteMovieById(Long id);
}

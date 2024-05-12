package com.example.javaappwithspringframework.repositories;

import com.example.javaappwithspringframework.model.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MovieRepository  extends CrudRepository<Movie, Long> {
    Optional<Movie> getFirstByTitleAndReleaseDate(String title, String releaseDate);
}

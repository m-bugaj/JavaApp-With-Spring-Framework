package com.example.javaappwithspringframework.repositories;

import com.example.javaappwithspringframework.model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository  extends CrudRepository<Movie, Long> {
}

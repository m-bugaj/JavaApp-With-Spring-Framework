package com.example.javaappwithspringframework.repositories;

import com.example.javaappwithspringframework.model.Director;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DirectorRepository extends CrudRepository<Director, Long> {
    Optional<Director> getFirstByFirstNameAndLastName(String firstName, String lastName);

    Optional<Director> getFirstById(Long id);
}

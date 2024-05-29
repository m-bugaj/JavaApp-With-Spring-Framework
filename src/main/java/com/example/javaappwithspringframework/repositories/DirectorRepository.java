package com.example.javaappwithspringframework.repositories;

import com.example.javaappwithspringframework.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DirectorRepository extends JpaRepository<Director, Long> {
    Optional<Director> getFirstByFirstNameAndLastName(String firstName, String lastName);

    Optional<Director> getFirstById(Long id);

    List<Director> getByFirstName(String firstName);
}

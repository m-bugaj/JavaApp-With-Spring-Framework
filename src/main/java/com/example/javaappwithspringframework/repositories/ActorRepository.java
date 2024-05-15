package com.example.javaappwithspringframework.repositories;

import com.example.javaappwithspringframework.model.Actor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ActorRepository extends CrudRepository<Actor, Long> {
    Optional<Actor> getFirstByFirstNameAndLastName(String firstName, String lastName);
}

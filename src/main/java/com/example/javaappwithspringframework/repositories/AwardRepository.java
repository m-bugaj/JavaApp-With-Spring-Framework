package com.example.javaappwithspringframework.repositories;

import com.example.javaappwithspringframework.model.Award;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AwardRepository extends CrudRepository<Award, Long> {
    Optional<Award> getFirstByNameAndYear(String name, String year);
}

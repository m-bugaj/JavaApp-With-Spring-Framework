package com.example.javaappwithspringframework.repositories;

import com.example.javaappwithspringframework.model.Director;
import org.springframework.data.repository.CrudRepository;

public interface DirectorRepository extends CrudRepository<Director, Long> {
}

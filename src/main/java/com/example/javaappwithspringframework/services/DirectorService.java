package com.example.javaappwithspringframework.services;

import com.example.javaappwithspringframework.api.model.DirectorDTO;

import java.util.List;

public interface DirectorService {
    List<DirectorDTO> getAllDirectors();

    DirectorDTO getDirectorById(Long id);

    DirectorDTO getDirectorByFirstNameAndLastName(String firstName, String lastName);

    DirectorDTO createNewDirector(DirectorDTO directorDTO);

    DirectorDTO updateDirector(Long id, DirectorDTO directorDTO);

    void deleteDirectorById(Long id);

}

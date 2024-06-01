package com.example.javaappwithspringframework.api.mapper;

import com.example.javaappwithspringframework.api.model.DirectorDTO;
import com.example.javaappwithspringframework.model.Director;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DirectorMapper {

    DirectorDTO directorToDirectorDTO(Director director);

    Director directorDTOToDirector(DirectorDTO directorDTO);
}

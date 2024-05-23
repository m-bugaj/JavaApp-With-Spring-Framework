package com.example.javaappwithspringframework.api.mapper;

import com.example.javaappwithspringframework.api.model.DirectorDTO;
import com.example.javaappwithspringframework.model.Director;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class DirectorMapperTest {

    private DirectorMapper directorMapper = Mappers.getMapper(DirectorMapper.class);

    @Test
    void directorToDirectorDTO() {
        // setup
        Director director = new Director();
        director.setId(1L);
        director.setFirstName("Jan");
        director.setLastName("Kowalski");
        director.setDateOfBirth("1990-01-01");

        // when
        DirectorDTO directorDTO = directorMapper.directorToDirectorDTO(director);

        // then
        assertEquals(Long.valueOf(1L), directorDTO.getId());
        assertEquals("Jan", directorDTO.getFirstName());
        assertEquals("Kowalski", directorDTO.getLastName());
        assertEquals("1990-01-01", directorDTO.getDateOfBirth());
    }
}

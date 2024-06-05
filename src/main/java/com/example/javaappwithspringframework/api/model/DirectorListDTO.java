package com.example.javaappwithspringframework.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DirectorListDTO {
    private List<DirectorDTO> directorDTOList;
}

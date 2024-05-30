package com.example.javaappwithspringframework.api.model;

import lombok.Data;

@Data
public class DirectorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
}

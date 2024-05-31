package com.example.javaappwithspringframework.api.model;

import lombok.Data;

@Data
public class MovieDTO {
    private Long id;
    private String title;
    private String genre;
    private String releaseDate;
    private String language;
    private String country;
}

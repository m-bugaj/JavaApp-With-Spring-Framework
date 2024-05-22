package com.example.javaappwithspringframework.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieCommand {
    private Long id;
    private String title;
    private String genre;
    private String releaseDate;
    private String language;
    private String country;
    private Long directorId;
}

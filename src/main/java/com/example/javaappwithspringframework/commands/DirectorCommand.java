package com.example.javaappwithspringframework.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class DirectorCommand {
    private Long id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;

}

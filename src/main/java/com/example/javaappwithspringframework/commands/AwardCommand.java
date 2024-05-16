package com.example.javaappwithspringframework.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AwardCommand {
    private Long id;
    private String name;
    private String year; // Rok przyznania nagrody
}

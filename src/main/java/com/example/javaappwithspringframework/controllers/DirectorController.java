package com.example.javaappwithspringframework.controllers;

import com.example.javaappwithspringframework.repositories.DirectorRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class DirectorController {
    private DirectorRepository directorRepository;

    public DirectorController(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @RequestMapping("/directors")
    public String getDirectors(Model model) {
        model.addAttribute("direcotrs", directorRepository.findAll());
        return "directors";
    }
}

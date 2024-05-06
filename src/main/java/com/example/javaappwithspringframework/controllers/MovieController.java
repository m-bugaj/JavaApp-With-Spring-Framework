package com.example.javaappwithspringframework.controllers;

import com.example.javaappwithspringframework.repositories.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MovieController {
    private MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @RequestMapping("/movies")
    public String getMovies(Model model) {
        model.addAttribute("movies", movieRepository.findAll());
        return "movies";
    }
}

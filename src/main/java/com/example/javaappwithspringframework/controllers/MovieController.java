package com.example.javaappwithspringframework.controllers;

import com.example.javaappwithspringframework.commands.MovieCommand;
import com.example.javaappwithspringframework.model.Movie;
import com.example.javaappwithspringframework.repositories.DirectorRepository;
import com.example.javaappwithspringframework.repositories.MovieRepository;
import com.example.javaappwithspringframework.converters.MovieCommandToMovieConverter;
import com.example.javaappwithspringframework.converters.MovieToMovieCommandConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class MovieController {
    private MovieRepository movieRepository;
    private MovieCommandToMovieConverter movieCommandToMovie;
    private MovieToMovieCommandConverter movieToMovieCommand;
    private DirectorRepository directorRepository;

    public MovieController(MovieRepository movieRepository, MovieCommandToMovieConverter movieCommandToMovie, MovieToMovieCommandConverter movieToMovieCommand, DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.movieCommandToMovie = movieCommandToMovie;
        this.movieToMovieCommand = movieToMovieCommand;
        this.directorRepository = directorRepository;
    }

    @RequestMapping(value = {"/movies", "movie/list"})
    public String getMovies(Model model) {
        model.addAttribute("movies", movieRepository.findAll());
        return "movie/list";
    }

    @RequestMapping("/movie/{id}/show")
    public String getMovieDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("movie", movieRepository.findById(id).orElse(null));
        return "movie/show";
    }

    @GetMapping("/movie/new")
    public String newMovie(Model model){
        model.addAttribute("movie", new MovieCommand());
        model.addAttribute("directors", directorRepository.findAll());
        return "movie/addedit";
    }

    @GetMapping("/movie/{id}/edit")
    public String editMovie(@PathVariable Long id, Model model) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if (movieOptional.isPresent()) {
            Movie movie = movieOptional.get();
            MovieCommand movieCommand = movieToMovieCommand.convert(movie);
            System.out.println("Editing movie with director ID: " + movie.getDirectorId()); // Dodaj to logowanie
            model.addAttribute("movie", movieCommand);
            model.addAttribute("directors", directorRepository.findAll());
            return "movie/addedit";
        } else {
            System.out.println("Movie not found!");
            return "redirect:/movies";
        }
    }

    @GetMapping("/movie/{id}/delete")
    public String deleteMovie(@PathVariable Long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);

        if (movieOptional.isPresent()) {
            Movie movieToRemove = movieOptional.get();
            // Usuń powiązania z reżyserami i aktorami
            movieToRemove.getDirectors().clear();
            movieToRemove.getActors().clear();
            movieToRemove.getAwards().clear();  // Clearing related entities
            movieRepository.save(movieToRemove);  // Save changes before deleting movie
            movieRepository.delete(movieToRemove);
            return "redirect:/movies";
        } else {
            return "redirect:/movies";
        }
    }


    @PostMapping("/movie/")
    public String saveOrUpdate(@ModelAttribute MovieCommand command) {
        Optional<Movie> movieById = Optional.empty();
        if (command.getId() != null) {
            movieById = movieRepository.findById(command.getId());
        }

        Optional<Movie> movieByTitleAndDate = movieRepository.getFirstByTitleAndReleaseDate(command.getTitle(), command.getReleaseDate());

        if (movieById.isPresent()) {
            Movie editedMovie = movieById.get();
            editedMovie.setTitle(command.getTitle());
            editedMovie.setGenre(command.getGenre());
            editedMovie.setReleaseDate(command.getReleaseDate());
            editedMovie.setLanguage(command.getLanguage());
            editedMovie.setCountry(command.getCountry());
            directorRepository.findById(command.getDirectorId()).ifPresent(editedMovie.getDirectors()::add);
            movieRepository.save(editedMovie);
            return "redirect:/movie/" + editedMovie.getId() + "/show";
        } else if (movieByTitleAndDate.isEmpty()) {
            Movie newMovie = movieCommandToMovie.convert(command);
            Movie savedMovie = movieRepository.save(newMovie);
            return "redirect:/movie/" + savedMovie.getId() + "/show";
        } else {
            System.out.println("Sorry, there's such movie in db");
            return "redirect:/movie/" + movieByTitleAndDate.get().getId() + "/show";
        }
    }


}

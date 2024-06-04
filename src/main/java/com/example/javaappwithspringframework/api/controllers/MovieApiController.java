package com.example.javaappwithspringframework.api.controllers;

import com.example.javaappwithspringframework.api.model.MovieDTO;
import com.example.javaappwithspringframework.api.model.MovieListDTO;
import com.example.javaappwithspringframework.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movie/")
public class MovieApiController {
    private final MovieService movieService;

    public MovieApiController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping("{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable Long id){
        return new ResponseEntity<MovieDTO>(movieService.getMoviesById(id), HttpStatus.OK);
    }

    @GetMapping("getMovieByTitleAndReleaseDate")
    public ResponseEntity<MovieDTO> getMovieByTitleAndReleaseDate(@RequestParam String title, @RequestParam String releaseDate){
        return new ResponseEntity<MovieDTO>(movieService.getMoviesByTitleAndReleaseDate(title, releaseDate), HttpStatus.OK);
    }

    @GetMapping("getAllMovies")
    public ResponseEntity<MovieListDTO> getAllMovies(){
        return new ResponseEntity<MovieListDTO>(new MovieListDTO(movieService.getAllMovies()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MovieDTO> createNewMovie(@RequestBody MovieDTO movieDTO){
        return new ResponseEntity<MovieDTO>(movieService.createNewMovie(movieDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO){
        return new ResponseEntity<MovieDTO>(movieService.updateMovie(id, movieDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id){
        movieService.deleteMovieById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

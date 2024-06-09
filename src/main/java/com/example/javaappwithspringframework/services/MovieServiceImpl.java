package com.example.javaappwithspringframework.services;

import com.example.javaappwithspringframework.api.mapper.MovieMapper;
import com.example.javaappwithspringframework.api.model.DirectorDTO;
import com.example.javaappwithspringframework.api.model.MovieDTO;
import com.example.javaappwithspringframework.model.Director;
import com.example.javaappwithspringframework.model.Movie;
import com.example.javaappwithspringframework.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    MovieMapper movieMapper;
    MovieRepository movieRepository;

    public MovieServiceImpl(MovieMapper movieMapper, MovieRepository movieRepository) {
        this.movieMapper = movieMapper;
        this.movieRepository = movieRepository;
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(movieMapper::movieToMovieDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MovieDTO getMoviesById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);

        if (movie.isPresent()) return movieMapper.movieToMovieDTO(movie.get());

        return null;
    }

    @Override
    public MovieDTO getMoviesByTitleAndReleaseDate(String title, String releaseDate) {
        Optional<Movie> movie = movieRepository.getFirstByTitleAndReleaseDate(title, releaseDate);

        if (movie.isPresent()) return movieMapper.movieToMovieDTO(movie.get());

        return null;
    }

    @Override
    public MovieDTO createNewMovie(MovieDTO movieDTO) {

        Movie movie = movieMapper.movieDTOToMovie(movieDTO);
        Movie savedMovie = movieRepository.save(movie);

        return movieMapper.movieToMovieDTO(savedMovie);
    }

    @Override
    public MovieDTO updateMovie(Long id, MovieDTO movieDTO) {

        Movie movie = movieMapper.movieDTOToMovie(movieDTO);
        movie.setId(id);

        Movie savedMovie = movieRepository.save(movie);

        return movieMapper.movieToMovieDTO(savedMovie);
    }

    @Override
    public void deleteMovieById(Long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);

        if (movieOptional.isPresent()) {
            Movie movieToRemove = movieOptional.get();
            // Usuń powiązania z filmami
            movieToRemove.getDirectors().forEach(movie -> movie.getMovies().remove(movieToRemove));
            movieToRemove.getDirectors().clear();  // Wyczyść listę filmów
            movieRepository.save(movieToRemove);  // Zaktualizuj filmy przed usunięciem reżysera
            movieRepository.delete(movieToRemove);
        }
    }
}

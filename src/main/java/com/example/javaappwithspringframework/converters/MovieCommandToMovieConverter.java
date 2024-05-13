package com.example.javaappwithspringframework.converters;

import com.example.javaappwithspringframework.commands.MovieCommand;
import com.example.javaappwithspringframework.model.Movie;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class MovieCommandToMovieConverter implements Converter<MovieCommand, Movie> {
    @Synchronized
    @Nullable
    @Override
    public Movie convert(MovieCommand source) {
        if (source == null) {
            return null;
        }
        final Movie movie = new Movie();
        movie.setId(source.getId());
        movie.setTitle(source.getTitle());
        movie.setGenre(source.getGenre());
        movie.setReleaseDate(source.getReleaseDate());
        movie.setLanguage(source.getLanguage());
        movie.setCountry(source.getCountry());
        movie.setDirectorId(source.getDirectorId());
        return movie;
    }
}

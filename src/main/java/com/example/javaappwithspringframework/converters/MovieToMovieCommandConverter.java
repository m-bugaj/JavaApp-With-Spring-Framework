package com.example.javaappwithspringframework.converters;

import com.example.javaappwithspringframework.commands.MovieCommand;
import com.example.javaappwithspringframework.model.Movie;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class MovieToMovieCommandConverter implements Converter<Movie, MovieCommand> {
    @Synchronized
    @Nullable
    @Override
    public MovieCommand convert(Movie source) {
        if (source == null) {
            return null;
        }
        MovieCommand command = new MovieCommand();
        command.setId(source.getId());
        command.setTitle(source.getTitle());
        command.setGenre(source.getGenre());
        command.setReleaseDate(source.getReleaseDate());
        command.setLanguage(source.getLanguage());
        command.setCountry(source.getCountry());
        command.setDirectorId(source.getDirectorId());
        return command;
    }
}

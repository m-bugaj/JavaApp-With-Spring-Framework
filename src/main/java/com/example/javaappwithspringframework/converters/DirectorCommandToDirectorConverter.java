package com.example.javaappwithspringframework.converters;

import com.example.javaappwithspringframework.commands.DirectorCommand;
import com.example.javaappwithspringframework.model.Director;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DirectorCommandToDirectorConverter implements Converter<DirectorCommand, Director> {
    @Synchronized
    @Nullable
    @Override
    public Director convert(DirectorCommand source) {
        if (source == null) {
            return null;
        }
        final Director director = new Director();
        director.setFirstName(source.getFirstName());
        director.setLastName(source.getLastName());
        director.setDateOfBirth(source.getDateOfBirth());
        return director;
    }
}

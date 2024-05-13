package com.example.javaappwithspringframework.converters;

import com.example.javaappwithspringframework.commands.DirectorCommand;
import com.example.javaappwithspringframework.model.Director;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DirectorToDirectorCommandConverter implements Converter<Director, DirectorCommand> {
    @Synchronized
    @Nullable
    @Override
    public DirectorCommand convert(Director source) {
        if (source == null) {
            return null;
        }
        DirectorCommand command = new DirectorCommand();
        command.setId(source.getId());
        command.setFirstName(source.getFirstName());
        command.setLastName(source.getLastName());
        command.setDateOfBirth(source.getDateOfBirth());
        return command;
    }
}

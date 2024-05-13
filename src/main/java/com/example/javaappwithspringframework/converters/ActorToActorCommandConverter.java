package com.example.javaappwithspringframework.converters;

import com.example.javaappwithspringframework.commands.ActorCommand;
import com.example.javaappwithspringframework.model.Actor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ActorToActorCommandConverter implements Converter<Actor, ActorCommand> {
    @Synchronized
    @Nullable
    @Override
    public ActorCommand convert(Actor source) {
        if (source == null) {
            return null;
        }
        ActorCommand command = new ActorCommand();
        command.setId(source.getId());
        command.setFirstName(source.getFirstName());
        command.setLastName(source.getLastName());
        command.setDateOfBirth(source.getDateOfBirth());
        return command;
    }
}

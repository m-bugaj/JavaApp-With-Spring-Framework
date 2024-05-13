package com.example.javaappwithspringframework.converters;

import com.example.javaappwithspringframework.commands.ActorCommand;
import com.example.javaappwithspringframework.model.Actor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ActorCommandToActorConverter implements Converter<ActorCommand, Actor> {
    @Synchronized
    @Nullable
    @Override
    public Actor convert(ActorCommand source) {
        if (source == null) {
            return null;
        }
        final Actor actor = new Actor();
        actor.setId(source.getId());
        actor.setFirstName(source.getFirstName());
        actor.setLastName(source.getLastName());
        actor.setDateOfBirth(source.getDateOfBirth());
        return actor;
    }
}

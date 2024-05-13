package com.example.javaappwithspringframework.converters;

import com.example.javaappwithspringframework.commands.AwardCommand;
import com.example.javaappwithspringframework.model.Award;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AwardToAwardCommandConverter implements Converter<Award, AwardCommand> {
    @Synchronized
    @Nullable
    @Override
    public AwardCommand convert(Award source) {
        if (source == null) {
            return null;
        }
        AwardCommand command = new AwardCommand();
        command.setId(source.getId());
        command.setName(source.getName());
        command.setYear(source.getYear());
        return command;
    }
}

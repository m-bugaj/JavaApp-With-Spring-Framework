package com.example.javaappwithspringframework.converters;

import com.example.javaappwithspringframework.commands.AwardCommand;
import com.example.javaappwithspringframework.model.Award;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AwardCommandToAwardConverter implements Converter<AwardCommand, Award> {
    @Synchronized
    @Nullable
    @Override
    public Award convert(AwardCommand source) {
        if (source == null) {
            return null;
        }
        final Award award = new Award();
        award.setId(source.getId());
        award.setName(source.getName());
        award.setYear(source.getYear());
        return award;
    }
}

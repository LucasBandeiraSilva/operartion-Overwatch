package com.overwatch.agents.infrastructure.messaging;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.core.json.JsonWriteFeature;
import tools.jackson.databind.cfg.DateTimeFeature;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.datatype.jsr310.JavaTimeModule;


@Configuration
public class SpringConfig {

    @Bean
    public JsonMapper jsonMapper(){
         return JsonMapper.builder()
                .addModule(new JavaTimeModule())
                 .enable(DateTimeFeature.WRITE_DATES_AS_TIMESTAMPS)
                .changeDefaultPropertyInclusion(inc -> inc.withValueInclusion(JsonInclude.Include.NON_NULL).withContentInclusion(JsonInclude.Include.NON_NULL))
                 .build();
    }
}

package com.overwatch.logistic.infrastructure.messaging;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                .changeDefaultPropertyInclusion(incl -> incl.withContentInclusion(JsonInclude.Include.NON_NULL).withValueInclusion(JsonInclude.Include.NON_NULL))
                .build();
    }
}

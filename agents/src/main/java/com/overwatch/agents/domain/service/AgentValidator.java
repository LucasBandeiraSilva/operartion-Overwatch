package com.overwatch.agents.domain.service;

import com.overwatch.agents.domain.exception.AgentTooYoungException;
import com.overwatch.agents.domain.model.Agent;
import org.springframework.stereotype.Component;

@Component
public class AgentValidator {

    private static final int MINIMUM_AGE = 18;


    public void validateAge( Agent agent ){
        if (agent.getAge() < MINIMUM_AGE) throw new AgentTooYoungException("Agent must be at least 18 years old");
    }
}

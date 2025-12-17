package com.overwatch.logistic.infrastructure.messaging.subscriber.representation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.overwatch.logistic.domain.model.enums.AgentRole;
import com.overwatch.logistic.domain.model.enums.ThreatLevel;


import java.time.LocalDate;
import java.util.List;

public record DetailAgentRepresentation(
        String name,
        String agentCode,
        String nameSuper,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate supersDateOfBirth,
        String superCode,
        List <String> abilities,
        ThreatLevel threatLevel
) {
}

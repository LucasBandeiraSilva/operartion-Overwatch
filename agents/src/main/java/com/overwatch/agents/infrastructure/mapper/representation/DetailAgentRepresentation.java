package com.overwatch.agents.infrastructure.mapper.representation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.overwatch.agents.domain.enums.AgentRole;
import com.overwatch.agents.domain.enums.ThreatLevel;

import java.time.LocalDate;
import java.util.List;

public record DetailAgentRepresentation(
        String name,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate dateOfBirth,
        String agentCode,
        AgentRole agentRole,
        String nameSuper,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate birth,
        String superCode,
        List <String> abilities,
        ThreatLevel threatLevel
) {
}

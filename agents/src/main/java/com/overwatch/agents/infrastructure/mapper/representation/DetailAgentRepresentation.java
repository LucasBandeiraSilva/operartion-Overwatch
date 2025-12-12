package com.overwatch.agents.infrastructure.mapper.representation;

import com.overwatch.agents.domain.enums.AgentRole;
import com.overwatch.agents.domain.enums.ThreatLevel;

import java.time.LocalDate;
import java.util.List;

public record DetailAgentRepresentation(
        String name,
        LocalDate dateOfBirth,
        String agentCode,
        AgentRole agentRole,
        String nameSuper,
        LocalDate birth,
        String superCode,
        List <String> abilities,
        ThreatLevel threatLevel
) {
}

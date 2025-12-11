package com.overwatch.agents.infrastructure.client.representation;

import com.overwatch.agents.domain.enums.ThreatLevel;

import java.time.LocalDate;
import java.util.List;

public record SuperRepresentation(
        String name,
        LocalDate dateOfBirth,
        String superCode,
        List <String> abilities,
        ThreatLevel threatLevel
) {
}

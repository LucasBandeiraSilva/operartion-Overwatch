package com.overwatch.logistic.domain.model;

import com.overwatch.logistic.domain.model.enums.AgentRole;

import java.time.LocalDate;
import java.util.List;

public record Agent(
        Long id,
        String name,
        String agentCode,
        LocalDate dateOfBirth,
        AgentRole agentRole,
        Supers supers) {
}

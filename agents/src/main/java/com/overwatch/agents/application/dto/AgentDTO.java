package com.overwatch.agents.application.dto;

import com.overwatch.agents.domain.enums.AgentRole;

import java.time.LocalDate;

public record AgentDTO(Long id,String name, LocalDate dateOfBirth, String agentCode, AgentRole agentRole) {
}

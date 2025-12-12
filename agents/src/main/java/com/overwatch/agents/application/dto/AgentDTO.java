package com.overwatch.agents.application.dto;

import com.overwatch.agents.domain.enums.AgentRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AgentDTO(
        Long id,

        @NotBlank(message = "The name must be provided!")
        @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters long")
        String name,

        @Past(message = "The date of birth must be in the past!")
        @NotNull(message = "The date of Birth cannot be null")
        LocalDate dateOfBirth,

        @NotBlank(message = "Agent code must be provided for identification and operations")
        String agentCode,

        @NotNull(message = "Agent role must be provided")
        AgentRole agentRole,

        long superId) {

}

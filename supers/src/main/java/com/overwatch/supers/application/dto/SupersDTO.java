package com.overwatch.supers.application.dto;

import com.overwatch.supers.domain.model.ThreatLevel;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

public record SupersDTO(
        String name,
        LocalDate dateOfBirth,
        String superCode,
        List <String> abilities,
        ThreatLevel threatLevel
) {
}

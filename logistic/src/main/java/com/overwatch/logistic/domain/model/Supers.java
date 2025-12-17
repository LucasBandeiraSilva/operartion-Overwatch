package com.overwatch.logistic.domain.model;

import com.overwatch.logistic.domain.model.enums.ThreatLevel;

import java.time.LocalDate;
import java.util.List;

public record Supers(
        Long id,
        String nameSuper,
        LocalDate supersDateOfBirth,
        String superCode,
        List <String> abilities,
        ThreatLevel threatLevel) {
}

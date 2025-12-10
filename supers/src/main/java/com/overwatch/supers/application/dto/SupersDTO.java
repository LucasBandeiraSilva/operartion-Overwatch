package com.overwatch.supers.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.overwatch.supers.domain.model.ThreatLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

public record SupersDTO(

        @NotBlank(message = "The name must be provided")
        @Size(min = 3,max = 100, message = "Name must be between 3 and 100 characters long")
        String name,

        @Past(message = "The date of Birth must be in the past")
        @NotNull(message = "The date of birth must no be null")
        LocalDate dateOfBirth,

        @NotBlank(message = "Super code must be provided for identification and operations")
        @Size(min = 3, max = 50, message = "Super code must be between 3 and 50 characters long")
        String superCode,

        @NotNull(message = "You must to provide the abilities of the super")
        @Size(min = 1,message = "You must have at least one ability")
        List <String> abilities,

        @NotNull(message = "You must to provide the Threat Level of the super")
        ThreatLevel threatLevel
) {
}

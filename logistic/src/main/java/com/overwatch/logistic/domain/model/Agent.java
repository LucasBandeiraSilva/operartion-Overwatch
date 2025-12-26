package com.overwatch.logistic.domain.model;

import com.overwatch.logistic.domain.model.enums.AgentRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agent {

    private Long id;
    private String name;
    private String agentCode;
    private String dateOfBirth;
    private String agentRole;
    private Supers supers;
}


package com.overwatch.agents.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.overwatch.agents.domain.enums.AgentRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tb_agent")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String agentCode;
    @Enumerated(EnumType.STRING)
    private AgentRole agentRole;
    private boolean active;

    @PrePersist
    private void PrePersist(){
        this.active= true;
        if (this.agentRole == null) this.agentRole = AgentRole.AGENT;
    }

    @Transient
    public int getAge(){
        return Period.between(this.dateOfBirth,LocalDate.now()).getYears();
    }

}

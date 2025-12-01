package com.overwatch.agents.domain.repository;

import com.overwatch.agents.domain.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent,Long> {
}

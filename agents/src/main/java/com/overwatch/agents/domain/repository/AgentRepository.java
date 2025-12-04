package com.overwatch.agents.domain.repository;

import com.overwatch.agents.domain.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent,Long> {
    Optional<Agent> findByAgentCode( String agentCode );
}

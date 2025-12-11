package com.overwatch.agents.domain.service;

import com.overwatch.agents.application.assembler.AgentMapper;
import com.overwatch.agents.application.dto.AgentDTO;
import com.overwatch.agents.domain.exception.AgentNotFoundException;
import com.overwatch.agents.domain.exception.SuperNotFoundException;
import com.overwatch.agents.domain.model.Agent;
import com.overwatch.agents.infrastructure.client.SupersClient;
import com.overwatch.agents.infrastructure.client.representation.SuperRepresentation;
import com.overwatch.agents.infrastructure.repository.AgentRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgentService {

    private final AgentRepository repository;
    private final AgentMapper mapper;
    private final AgentValidator validator;
    private final SupersClient apiSupers;

    public SuperRepresentation findByIdSuper(Long id){
        try {
            var response = apiSupers.findById(id);
            return response.getBody();
        } catch (FeignException.NotFound e) {
            throw new SuperNotFoundException(id);
        }
    }

    private static void update( AgentDTO agentDTO, Agent savedAgent ) {
        savedAgent.setName(agentDTO.name());
        savedAgent.setDateOfBirth(agentDTO.dateOfBirth());
        savedAgent.setAgentRole(agentDTO.agentRole());
        savedAgent.setAgentCode(agentDTO.agentCode());
    }

    public Agent saveAgent( AgentDTO agentDTO ) {
        var agent = mapper.toEntity(agentDTO);
        validator.validateAge(agent);
        return repository.save(agent);
    }

    public List <AgentDTO> findAll() {
        return repository.findAll().stream()
                .filter(Agent::isActive)
                .map(mapper::toDto)
                .toList();
    }

    @Transactional
    public void disableAgent( String agentCode, String directorCode ) {
        Agent agent = validateDirectorAndFindAgent(agentCode, directorCode);
        agent.setActive(false);
    }

    @Transactional
    public void enableAgent( String agentCode, String directorCode ) {
        Agent agent = validateDirectorAndFindAgent(agentCode, directorCode);
        agent.setActive(true);
    }

    private Agent validateDirectorAndFindAgent( String agentCode, String directorCode ) {
        Agent agent = repository.findByAgentCode(agentCode).orElseThrow(() -> {
            throw new AgentNotFoundException(agentCode);
        });
        Agent director = repository.findByAgentCode(directorCode).orElseThrow(() -> {
            throw new AgentNotFoundException(directorCode);
        });
        validator.validateDirectorRole(director);
        return agent;
    }

    @Transactional
    public void updateAgent( String agentCode, String directorCode, AgentDTO agentDTO ) {

        Agent agent = validateDirectorAndFindAgent(agentCode, directorCode);

        update(agentDTO, agent);

    }

}

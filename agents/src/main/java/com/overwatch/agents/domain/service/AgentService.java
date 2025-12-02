package com.overwatch.agents.domain.service;

import com.overwatch.agents.application.assembler.AgentMapper;
import com.overwatch.agents.application.dto.AgentDTO;
import com.overwatch.agents.domain.model.Agent;
import com.overwatch.agents.domain.repository.AgentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgentService {

    private final AgentRepository repository;
    private final AgentMapper mapper;
    private final AgentValidator validator;

    public Agent saveAgent( AgentDTO agentDTO ) {
        var agent = mapper.toEntity(agentDTO);
        validator.validateAge(agent);
        return repository.save(agent);
    }
}

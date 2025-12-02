package com.overwatch.agents.application.controller;

import com.overwatch.agents.application.dto.AgentDTO;
import com.overwatch.agents.domain.model.Agent;
import com.overwatch.agents.domain.service.AgentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/agent")
@RequiredArgsConstructor
@Slf4j
public class AgentController {

    private final AgentService service;

    private static URI getUri(Long id ) {
        var location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return location;
    }

    @PostMapping
    public ResponseEntity <Void> saveAgent( @RequestBody AgentDTO agentDTO ) {
        Agent savedAgent = service.saveAgent(agentDTO);
        log.info("id do agente: {}",agentDTO.id());
        var location = getUri(savedAgent.getId());
        return ResponseEntity.created(location).build();
    }

}

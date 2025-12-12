package com.overwatch.agents.application.controller;

import com.overwatch.agents.application.dto.AgentDTO;
import com.overwatch.agents.domain.model.Agent;
import com.overwatch.agents.domain.service.AgentService;
import com.overwatch.agents.infrastructure.client.representation.SuperRepresentation;
import com.overwatch.agents.infrastructure.mapper.DetailSupersMapper;
import com.overwatch.agents.infrastructure.mapper.representation.DetailAgentRepresentation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/agent")
@RequiredArgsConstructor
@Slf4j
public class AgentController {

    private final AgentService service;
    private final DetailSupersMapper mapper;

    private static URI getUri( Long id ) {
        var location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return location;
    }

    @PostMapping
    public ResponseEntity <Void> saveAgent( @RequestBody @Valid AgentDTO agentDTO ) {
        Agent savedAgent = service.saveAgent(agentDTO);
        var location = getUri(savedAgent.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity <List <AgentDTO>> findAllAgents() {
        List <AgentDTO> agentDTOList = service.findAll();
        return ResponseEntity.ok(agentDTOList);
    }

    @DeleteMapping("/{agentCode}")
    public ResponseEntity <Void> deleteAgent( @PathVariable String agentCode, @RequestHeader(name = "directorCode") String directorCode ) {
        service.disableAgent(agentCode, directorCode);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{agentCode}")
    public ResponseEntity <Void> updateAgent( @PathVariable String agentCode, @RequestHeader(name = "directorCode") String directorCode, @RequestBody AgentDTO agentDTO ) {
        service.updateAgent(agentCode, directorCode, agentDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{agentCode}/enable")
    public ResponseEntity <Void> enableAgent( @PathVariable String agentCode, @RequestHeader(name = "directorCode") String directorCode ) {
        service.enableAgent(agentCode, directorCode);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/supers")
    public ResponseEntity <DetailAgentRepresentation> testFeign( @PathVariable Long id ) {
        return service.loadFullData(id)
                .map(mapper::toRepresentation)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

}

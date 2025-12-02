package com.overwatch.agents.application.assembler;


import com.overwatch.agents.application.dto.AgentDTO;
import com.overwatch.agents.domain.model.Agent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgentMapper {

    Agent toEntity( AgentDTO agentDTO );
    AgentDTO toDto( Agent agent );

}

package com.overwatch.agents.infrastructure.mapper;

import com.overwatch.agents.domain.model.Agent;
import com.overwatch.agents.infrastructure.mapper.representation.DetailAgentRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetailSupersMapper {

    @Mapping(source = "name",target = "name")
    @Mapping(source = "dateOfBirth",target = "dateOfBirth")
    @Mapping(source = "agentCode",target = "agentCode")
    @Mapping(source = "agentRole",target = "agentRole")
    @Mapping(source = "superRepresentation.name",target = "nameSuper")
    @Mapping(source = "superRepresentation.dateOfBirth",target = "birth")
    @Mapping(source = "superRepresentation.superCode",target = "superCode")
    @Mapping(source = "superRepresentation.abilities",target = "abilities")
    @Mapping(source = "superRepresentation.threatLevel",target = "threatLevel")
    DetailAgentRepresentation toRepresentation( Agent agent );
}

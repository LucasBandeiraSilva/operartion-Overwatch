package com.overwatch.logistic.infrastructure.mapping;

import com.overwatch.logistic.domain.model.Agent;
import com.overwatch.logistic.infrastructure.messaging.subscriber.representation.DetailAgentRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgentMapper {

    @Mapping(source = "nameSuper",target = "supers.nameSuper")
    @Mapping(source = "birth",target = "supers.birth")
    @Mapping(source = "superCode",target = "supers.superCode")
    @Mapping(source = "abilities",target = "supers.abilities")
    @Mapping(source = "threatLevel",target = "supers.threatLevel")
    Agent toAgent( DetailAgentRepresentation detailAgentRepresentation );
}

package com.overwatch.agents.infrastructure.messaging.publisher;

import com.overwatch.agents.domain.model.Agent;
import com.overwatch.agents.infrastructure.mapper.DetailAgentsSupersMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.json.JsonMapper;

@Component
@RequiredArgsConstructor
@Slf4j
public class AgentPublisher {

    private final DetailAgentsSupersMapper mapper;
    private final JsonMapper jsonMapper;
    private final KafkaTemplate<String,String> kafkaTemplate;

    @Value("${overwatch.config.kafka.topics.published-supers}")
    private String topic;


    public void publish( Agent agent ){
        log.info("Publishing Entity: {}",agent.getId());

        try {
            var representation = mapper.toRepresentation(agent);
            var json = jsonMapper.writeValueAsString(representation);
            kafkaTemplate.send(topic,"data",json);
        } catch (JacksonException e) {
            log.error("Error while publishing the JSON: {}",e.getMessage());
        }
    }


}

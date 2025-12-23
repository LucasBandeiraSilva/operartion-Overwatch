package com.overwatch.logistic.subscriber;

import com.overwatch.logistic.domain.model.Agent;
import com.overwatch.logistic.domain.service.ReportGeneratorService;
import com.overwatch.logistic.infrastructure.mapping.AgentMapper;
import com.overwatch.logistic.infrastructure.messaging.subscriber.representation.DetailAgentRepresentation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.json.JsonMapper;

@Configuration
@EnableKafka
@RequiredArgsConstructor
@Slf4j
public class PublishedSupersSubscriber {

    private final JsonMapper jsonMapper;
    private final AgentMapper mapper;
    private final ReportGeneratorService service;

    @KafkaListener(groupId = "overwatch-logistic",topics = "${overwatch.config.kafka.topics.published-supers}")
    public void listen(String json){
        try {
            log.info("Receiving JSON: {} ", json);
            var representation = jsonMapper.readValue(json, DetailAgentRepresentation.class);
            Agent agent = mapper.toAgent(representation);
            service.generateReport(agent);
        } catch (JacksonException e) {
            log.error("Error when consuming published supers topic");
        }
    }

}

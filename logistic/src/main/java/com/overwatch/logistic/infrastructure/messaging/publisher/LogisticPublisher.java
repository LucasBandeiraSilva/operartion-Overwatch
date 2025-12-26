package com.overwatch.logistic.infrastructure.messaging.publisher;

import com.overwatch.logistic.domain.model.Agent;
import com.overwatch.logistic.infrastructure.messaging.publisher.representation.UrlRepresentation;
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
public class LogisticPublisher {

    private final JsonMapper jsonMapper;
    private final KafkaTemplate<String,String> kafkaTemplate;

    @Value("${overwatch.config.kafka.topics.report-supers-url}")
    private String topic;

    public void publishReport( Agent agent, String urlReport ){
        var representation = new UrlRepresentation(agent.getAgentCode(), urlReport);

        try {
            String json = jsonMapper.writeValueAsString(representation);
            kafkaTemplate.send(topic,"data",json);
            log.info("Published url: {}",urlReport);
        } catch (JacksonException e) {
            log.error(e.getMessage(),e);
        }
    }

}

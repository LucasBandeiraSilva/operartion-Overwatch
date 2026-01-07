package com.overwatch.supers.infrastructure.messaging.subscriber;

import com.overwatch.supers.domain.service.ReportUpdateService;
import com.overwatch.supers.infrastructure.messaging.representation.UrlRepresentation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import tools.jackson.databind.json.JsonMapper;

@Configuration
@EnableKafka
@RequiredArgsConstructor
@Slf4j
public class LogisticSubscriber {

    private final JsonMapper jsonMapper;
    private final ReportUpdateService reportUpdateService;

    @KafkaListener(groupId = "overwatch-supers",topics = "${overwatch.config.kafka.topics.report-supers-url}")
    public void listen(String json){
        log.info("json: {}",json );

        var representation = jsonMapper.readValue(json, UrlRepresentation.class);
        log.info("Representation: {}", representation );
        reportUpdateService.updateReport(representation.supersCode(),representation.urlReport());
    }
}

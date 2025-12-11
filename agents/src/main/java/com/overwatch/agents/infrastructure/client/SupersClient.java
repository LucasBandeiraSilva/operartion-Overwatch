package com.overwatch.agents.infrastructure.client;

import com.overwatch.agents.infrastructure.client.representation.SuperRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "OW-supers", url = "${overwatch.agents.clients.supers.url}")
public interface SupersClient {

    @GetMapping("/{id}")
    ResponseEntity <SuperRepresentation> findById( @PathVariable Long id );

}

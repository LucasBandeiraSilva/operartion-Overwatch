package com.overwatch.supers.application.controller;

import com.overwatch.supers.application.dto.SupersDTO;
import com.overwatch.supers.domain.model.Supers;
import com.overwatch.supers.domain.service.SupersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/supers")
@RequiredArgsConstructor
@Slf4j
public class SupersController {

    private final SupersService service;

    private static URI getUri( Supers savedSuper ) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedSuper.getId()).toUri();
    }

    @PostMapping
    public ResponseEntity <Void> saveSuper( @RequestBody SupersDTO supersDTO ) {
        Supers savedSuper = service.save(supersDTO);
        log.info("saved hero: {}",savedSuper);
        URI location = getUri(savedSuper);
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<SupersDTO>>findAll(){
        List <SupersDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupersDTO>findById(@PathVariable Long id){
        SupersDTO superFound = service.findById(id);
        return ResponseEntity.ok(superFound);
    }

}

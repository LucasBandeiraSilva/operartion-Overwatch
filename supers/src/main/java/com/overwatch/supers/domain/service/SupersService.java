package com.overwatch.supers.domain.service;

import com.overwatch.supers.application.assembler.SupersMapper;
import com.overwatch.supers.application.dto.SupersDTO;
import com.overwatch.supers.domain.model.Supers;
import com.overwatch.supers.infrastructure.SupersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SupersService {

    private final SupersMapper mapper;
    private final SupersRepository repository;


    public Supers save( SupersDTO supersDTO ){
        Supers supers = mapper.toEntity(supersDTO);
        return repository.save(supers);
    }

    public List<SupersDTO> findAll(){
        return repository.findAll()
                .stream()
                .filter(Supers::isActive)
                .map(mapper::toDto)
                .toList();
    }

    public SupersDTO findById(Long id){
        SupersDTO supersDTO = repository.findById(id).filter(Supers::isActive).map(mapper::toDto).orElseThrow(() -> {
            throw new RuntimeException("Super not found in the database");
        });
        return supersDTO;
    }


    public void disableSuper(String supersCode){}


    public void enableSuper(){}
}

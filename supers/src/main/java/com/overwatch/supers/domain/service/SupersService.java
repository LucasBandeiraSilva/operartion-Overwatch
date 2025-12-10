package com.overwatch.supers.domain.service;

import com.overwatch.supers.application.assembler.SupersMapper;
import com.overwatch.supers.application.dto.SupersDTO;
import com.overwatch.supers.domain.exception.SuperNotFoundException;
import com.overwatch.supers.domain.model.Supers;
import com.overwatch.supers.domain.validator.SupersValidator;
import com.overwatch.supers.infrastructure.SupersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SupersService {

    private final SupersMapper mapper;
    private final SupersRepository repository;
    private final SupersValidator validator;


    public Supers save( SupersDTO supersDTO ) {
        Supers supers = mapper.toEntity(supersDTO);
        validator.validateNewSuper(supers);
        return repository.save(supers);
    }

    public List <SupersDTO> findAll() {
        return repository.findAll()
                .stream()
                .filter(Supers::isActive)
                .map(mapper::toDto)
                .toList();
    }

    public SupersDTO findById( Long id ) {
        SupersDTO supersDTO = repository.findById(id).filter(Supers::isActive).map(mapper::toDto).orElseThrow(() -> {
            throw new SuperNotFoundException(id);
        });
        return supersDTO;

    }

    @Transactional
    public void updateSuper( SupersDTO dto, String superCode ) {
        Supers supers = repository.findBySuperCode(superCode).orElseThrow(() -> {
            throw new SuperNotFoundException(superCode);
        });

        validator.validateUniqueSupersCodeOnUpdate(dto.superCode(), supers.getId());
        validateAndUpdate(dto, supers);
    }

    private void validateAndUpdate( SupersDTO dto, Supers supers ) {
        validator.validateExistingSuper(supers);
        supers.setName(dto.name());
        supers.setAbilities(dto.abilities());
        supers.setThreatLevel(dto.threatLevel());
        supers.setSuperCode(dto.superCode());
    }

    @Transactional
    public void disableSuper( String supersCode ) {
        Supers supers = repository.findBySuperCode(supersCode).orElseThrow(() -> {
            throw new SuperNotFoundException(supersCode);
        });
        supers.setActive(false);
    }

    @Transactional
    public void enableSuper(String supersCode) {
        Supers supers = repository.findBySuperCode(supersCode).orElseThrow(() -> {
            throw new SuperNotFoundException(supersCode);
        });
        supers.setActive(true);
    }
}

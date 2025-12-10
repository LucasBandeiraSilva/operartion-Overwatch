package com.overwatch.supers.domain.validator;

import com.overwatch.supers.domain.exception.BusinessException;
import com.overwatch.supers.domain.model.Supers;
import com.overwatch.supers.infrastructure.SupersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SupersValidator {

    private final SupersRepository repository;

    public void validateNewSuper( Supers supers){
        validateUniqueSupersCodeOnCreate(supers.getSuperCode());
        validateThreatLevelAndAbilities(supers);
        validateAbilityCompatibility(supers.getAbilities());
    }

    public void validateExistingSuper(Supers supers){
        validateAbilityCompatibility(supers.getAbilities());
        validateThreatLevelAndAbilities(supers);
    }

    public void validateUniqueSupersCodeOnCreate( String superCode ) {
        if (repository.existsBySuperCode(superCode)) throw new BusinessException("Super code already exists");
    }

    public void validateUniqueSupersCodeOnUpdate(String superCode, Long currentId){
       repository.findBySuperCode(superCode).ifPresent(existingSuper ->{
           if (!existingSuper.getId().equals(currentId)) throw new BusinessException("Another super already uses this super code");
       });
    }

    public void validateThreatLevelAndAbilities( Supers supers ) {
        int abilitiesCount = supers.getAbilities() == null
                ? 0
                : supers.getAbilities().size();

        int requiredMin = switch (supers.getThreatLevel()) {
            case LOW -> 1;
            case MODERATE -> 2;
            case HIGH -> 3;
            case SEVERE -> 4;
            case CRITICAL -> 5;
        };

        if (abilitiesCount < requiredMin)
            throw new BusinessException("Supers with threat level " + supers.getThreatLevel() + " must have at least " + requiredMin + " abilities");
    }

    public void validateAbilityCompatibility( List <String> abilities){
        if (abilities == null || abilities.isEmpty()) throw new BusinessException("A super must have at least one ability");
    }
}

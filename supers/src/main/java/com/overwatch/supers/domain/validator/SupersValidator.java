package com.overwatch.supers.domain.validator;

import com.overwatch.supers.domain.model.Supers;
import com.overwatch.supers.domain.model.ThreatLevel;
import com.overwatch.supers.infrastructure.SupersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SupersValidator {

    private final SupersRepository repository;

    public void validateNewSuper(Supers supers){
        validateUniqueSupersCode(supers.getSuperCode());
        validateThreatLevelAndAbilities(supers);
        validateAbilityCompatibility(supers.getAbilities());
    }


    public void validateUniqueSupersCode( String superCode ) {
        if (repository.existsBySuperCode(superCode)) throw new RuntimeException("Super code already exists");
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
            throw new RuntimeException("Supers with threat level " + supers.getThreatLevel() + " must have at least " + requiredMin + " abilities");
    }

    public void validateAbilityCompatibility( List <String> abilities){
        if (abilities == null || abilities.isEmpty()) throw new RuntimeException("A super must have at least one ability");
    }
}

package com.overwatch.supers.infrastructure;

import com.overwatch.supers.domain.model.Supers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupersRepository extends JpaRepository<Supers, Long> {
    Optional<Supers> findBySuperCode( String superCode );

    boolean existsBySuperCode( String heroCode );
}

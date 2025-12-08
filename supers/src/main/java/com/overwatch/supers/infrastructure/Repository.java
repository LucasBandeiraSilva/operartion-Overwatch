package com.overwatch.supers.infrastructure;

import com.overwatch.supers.domain.model.Supers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Supers, Long> {
}

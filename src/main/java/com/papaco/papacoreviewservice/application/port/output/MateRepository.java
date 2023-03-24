package com.papaco.papacoreviewservice.application.port.output;

import com.papaco.papacoreviewservice.domain.entity.Mate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MateRepository extends JpaRepository<Mate, UUID> {
}

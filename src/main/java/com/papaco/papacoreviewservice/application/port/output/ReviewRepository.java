package com.papaco.papacoreviewservice.application.port.output;

import com.papaco.papacoreviewservice.domain.entity.Review;
import com.papaco.papacoreviewservice.domain.vo.ReviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    long countByMateIdAndStatusNot(UUID id, ReviewStatus status);
}

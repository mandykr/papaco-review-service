package com.papaco.papacoreviewservice.application.port.input;

import com.papaco.papacoreviewservice.application.dto.ReviewResponse;

import java.util.UUID;

public interface ReviewUseCase {
    ReviewResponse demandReview(UUID mateId);

    void completeReview(UUID reviewId);
}

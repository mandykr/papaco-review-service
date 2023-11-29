package com.papaco.papacoreviewservice.application.port.usecase;

import com.papaco.papacoreviewservice.application.dto.ReviewResponse;

import java.util.UUID;

public interface ReviewUseCase {
    ReviewResponse demandReview(UUID mateId, String memberName, String codeStoreName);

    void completeReview(UUID reviewId);
}

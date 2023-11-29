package com.papaco.papacoreviewservice.application.dto;

import com.papaco.papacoreviewservice.domain.entity.Mate;
import com.papaco.papacoreviewservice.domain.entity.Review;
import com.papaco.papacoreviewservice.domain.vo.MateStatus;
import com.papaco.papacoreviewservice.domain.vo.ReviewStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponse {
    private UUID id;
    private ReviewStatus reviewStatus;
    private UUID mateId;
    private UUID reviewerId;
    private MateStatus mateStatus;

    public static ReviewResponse of(Review review) {
        Mate mate = review.getMate();
        return new ReviewResponse(
                review.getId(),
                review.getStatus(),
                mate.getId(),
                mate.getReviewerId(),
                mate.getStatus()
        );
    }
}

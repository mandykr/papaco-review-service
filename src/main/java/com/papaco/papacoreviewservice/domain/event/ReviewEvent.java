package com.papaco.papacoreviewservice.domain.event;

import com.papaco.papacoreviewservice.domain.entity.Review;
import com.papaco.papacoreviewservice.domain.vo.ReviewStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@ToString
@Getter
@AllArgsConstructor
public class ReviewEvent {
    private UUID id;
    private UUID mateId;
    private ReviewStatus status;

    public static ReviewEvent of(Review review) {
        return new ReviewEvent(
                review.getId(),
                review.getMate().getId(),
                review.getStatus()
        );
    }
}

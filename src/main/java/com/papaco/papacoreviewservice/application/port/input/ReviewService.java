package com.papaco.papacoreviewservice.application.port.input;

import com.papaco.papacoreviewservice.application.dto.ReviewResponse;
import com.papaco.papacoreviewservice.application.port.output.MateRepository;
import com.papaco.papacoreviewservice.application.port.output.ReviewRepository;
import com.papaco.papacoreviewservice.domain.entity.Mate;
import com.papaco.papacoreviewservice.domain.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ReviewService implements ReviewUseCase {
    private final ReviewRepository reviewRepository;
    private final MateRepository mateRepository;

    @Override
    public ReviewResponse demandReview(UUID mateId) {
        Mate mate = findMate(mateId);
        Review review = new Review(UUID.randomUUID(), mate);
        review.demand();
        Review saveReview = reviewRepository.save(review);
        return ReviewResponse.of(saveReview);
    }

    private Mate findMate(UUID mateId) {
        return mateRepository.findById(mateId)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void completeReview(UUID reviewId) {
        Review review = findReview(reviewId);
        review.complete();
    }

    private Review findReview(UUID reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(EntityNotFoundException::new);
    }
}

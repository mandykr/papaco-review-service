package com.papaco.papacoreviewservice.application.port.input;

import com.papaco.papacoreviewservice.application.dto.ReviewResponse;
import com.papaco.papacoreviewservice.application.port.output.MateRepository;
import com.papaco.papacoreviewservice.application.port.output.ReviewEventPublisher;
import com.papaco.papacoreviewservice.application.port.output.ReviewRepository;
import com.papaco.papacoreviewservice.application.port.usecase.ReviewUseCase;
import com.papaco.papacoreviewservice.domain.entity.Mate;
import com.papaco.papacoreviewservice.domain.entity.Review;
import com.papaco.papacoreviewservice.domain.event.ReviewEvent;
import com.papaco.papacoreviewservice.domain.service.ReviewValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

import static com.papaco.papacoreviewservice.domain.vo.ReviewStatus.COMPLETED;

@Transactional
@RequiredArgsConstructor
@Service
public class ReviewService implements ReviewUseCase {
    private final ReviewRepository reviewRepository;
    private final MateRepository mateRepository;
    private final ReviewValidationService reviewValidationService;
    private final ReviewEventPublisher reviewEventPublisher;

    @Override
    public ReviewResponse demandReview(UUID mateId, String memberName, String codeStoreName) {
        Mate mate = findMate(mateId);
        validateDemandReview(mate, memberName, codeStoreName);

        Review review = new Review(UUID.randomUUID(), mate);
        review.demand();
        Review saveReview = reviewRepository.save(review);
        reviewEventPublisher.publish(ReviewEvent.of(saveReview));
        return ReviewResponse.of(saveReview);
    }

    private Mate findMate(UUID mateId) {
        return mateRepository.findById(mateId)
                .orElseThrow(EntityNotFoundException::new);
    }

    private void validateDemandReview(Mate mate, String memberName, String codeStoreName) {
        long nonCompletedCount = reviewRepository.countByMateIdAndStatusNot(mate.getId(), COMPLETED);
        reviewValidationService.validateExistsNonCompleted(nonCompletedCount);
    }

    @Override
    public void completeReview(UUID reviewId) {
        Review review = findReview(reviewId);
        review.complete();
        reviewEventPublisher.publish(ReviewEvent.of(review));
    }

    private Review findReview(UUID reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(EntityNotFoundException::new);
    }
}



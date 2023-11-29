package com.papaco.papacoreviewservice.application.port.input;

import com.papaco.papacoreviewservice.application.port.output.GithubApiClient;
import com.papaco.papacoreviewservice.application.port.output.MateRepository;
import com.papaco.papacoreviewservice.application.port.output.ReviewRepository;
import com.papaco.papacoreviewservice.domain.entity.Mate;
import com.papaco.papacoreviewservice.domain.entity.Review;
import com.papaco.papacoreviewservice.domain.exception.NonCompletedReviewExistsException;
import com.papaco.papacoreviewservice.domain.exception.PullRequestsNotExistsException;
import com.papaco.papacoreviewservice.domain.service.ReviewValidationService;
import com.papaco.papacoreviewservice.domain.vo.MateStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReviewServiceTest {
    @Autowired
    private MateRepository mateRepository;

    @Autowired
    private ReviewRepository reviewRepository;
    private ReviewService reviewService;
    private FakeReviewEventPublisher eventPublisher;

    private UUID mateId;
    private String memberName;
    private String codeStoreName;
    private UUID reviewerId;

    @BeforeEach
    void setUp() {
        ReviewValidationService reviewValidationService = new ReviewValidationService();
        githubClient = new FakeGithubApiClient();
        eventPublisher = new FakeReviewEventPublisher();
        reviewService = new ReviewService(reviewRepository, mateRepository, reviewValidationService, eventPublisher);
        mateId = UUID.fromString("6fec2b25-9ad4-4dba-9f54-bc3c3305a6a5");
        memberName = "mandykr";
        codeStoreName = "papaco";
        reviewerId = UUID.fromString("2070c21b-60ad-4b8b-bc89-a8099531d2d4");
    }

    @DisplayName("메이트의 모든 리뷰가 완료 상태가 아니면 리뷰 요청이 되지 않는다.")
    @Test
    void exists_non_completed_reviews() {
        Mate mate = new Mate(mateId, reviewerId, MateStatus.JOINED);
        mateRepository.save(mate);

        Review review = new Review(UUID.randomUUID(), mate);
        review.demand();
        reviewRepository.save(review);

        assertThatThrownBy(() -> reviewService.demandReview(mateId, memberName, codeStoreName))
                .isInstanceOf(NonCompletedReviewExistsException.class);
    }

    @DisplayName("Github PR 이 없으면 리뷰 요청이 되지 않는다")
    @Test
    void not_exists_PR() {
        Mate mate = new Mate(mateId, reviewerId, MateStatus.JOINED);
        mateRepository.save(mate);

        assertThatThrownBy(() -> reviewService.demandReview(mateId, memberName, codeStoreName))
                .isInstanceOf(PullRequestsNotExistsException.class);
    }
}

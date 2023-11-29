package com.papaco.papacoreviewservice.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.UUID;

import static com.papaco.papacoreviewservice.domain.vo.MateStatus.JOINED;
import static com.papaco.papacoreviewservice.domain.vo.MateStatus.PROPOSED;
import static com.papaco.papacoreviewservice.domain.vo.ReviewStatus.COMPLETED;
import static com.papaco.papacoreviewservice.domain.vo.ReviewStatus.DEMANDED;
import static org.assertj.core.api.Assertions.*;

class ReviewTest {
    private UUID reviewId;
    private Mate mate;
    private UUID reviewerId;

    @BeforeEach
    void setUp() {
        reviewId = UUID.fromString("e747975b-caa3-402a-bf10-a4aca10473f6");
        mate = new Mate(UUID.fromString("6fec2b25-9ad4-4dba-9f54-bc3c3305a6a5"), reviewerId, JOINED);
        reviewerId = UUID.fromString("2070c21b-60ad-4b8b-bc89-a8099531d2d4");
    }

    @DisplayName("리뷰를 생성한다")
    @Test
    void create() {
        assertThatCode(() -> new Review(reviewId, mate))
                .doesNotThrowAnyException();
    }

    @DisplayName("리뷰를 요청한다")
    @Test
    void demand() {
        Review review = new Review(reviewId, mate);
        review.demand();
        assertThat(review.getStatus()).isEqualTo(DEMANDED);
    }

    @DisplayName("리뷰를 완료한다")
    @Test
    void complete() {
        Review review = new Review(reviewId, mate);
        ReflectionTestUtils.setField(review, "status", DEMANDED);

        review.complete();

        assertThat(review.getStatus()).isEqualTo(COMPLETED);
    }

    @DisplayName("메이트가 연결된 상태가 아니면 리뷰를 생성할 수 없다")
    @Test
    void not_joined() {
        mate = new Mate(UUID.fromString("6fec2b25-9ad4-4dba-9f54-bc3c3305a6a5"), reviewerId, PROPOSED);
        assertThatThrownBy(() -> new Review(reviewId, mate))
                .isInstanceOf(IllegalStateException.class);
    }
}

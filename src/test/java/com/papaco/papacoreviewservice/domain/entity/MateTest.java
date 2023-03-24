package com.papaco.papacoreviewservice.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.papaco.papacoreviewservice.domain.vo.MateStatus.JOINED;
import static org.assertj.core.api.Assertions.assertThatCode;

class MateTest {
    private UUID mateId;
    private UUID reviewerId;

    @BeforeEach
    void setUp() {
        mateId = UUID.fromString("6fec2b25-9ad4-4dba-9f54-bc3c3305a6a5");
        reviewerId = UUID.fromString("2070c21b-60ad-4b8b-bc89-a8099531d2d4");
    }

    @DisplayName("메이트를 생성한다")
    @Test
    void create() {
        assertThatCode(() -> new Mate(mateId, reviewerId, JOINED))
                .doesNotThrowAnyException();
    }
}

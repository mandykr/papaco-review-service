package com.papaco.papacoreviewservice.framework.adapter.output;

import com.papaco.papacoreviewservice.domain.event.ReviewEvent;
import com.papaco.papacoreviewservice.domain.vo.ReviewStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
class ReviewKafkaProducerTest {
    @Autowired
    private ReviewKafkaProducer producer;
    private UUID reviewId;
    private UUID mateId;

    @BeforeEach
    void setUp() {
        reviewId = UUID.fromString("e747975b-caa3-402a-bf10-a4aca10473f6");
        mateId = UUID.fromString("6fec2b25-9ad4-4dba-9f54-bc3c3305a6a5");
    }

    @DisplayName("카프카 서버로 메시지를 전송한다")
    @Test
    void sendMessage() {
        ReviewEvent reviewEvent = new ReviewEvent(reviewId, mateId, ReviewStatus.DEMANDED);
        assertThatCode(() -> producer.sendMessage(reviewEvent))
                .doesNotThrowAnyException();
    }
}

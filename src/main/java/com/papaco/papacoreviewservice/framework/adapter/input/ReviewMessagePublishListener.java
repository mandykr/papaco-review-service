package com.papaco.papacoreviewservice.framework.adapter.input;

import com.papaco.papacoreviewservice.domain.event.ReviewEvent;
import com.papaco.papacoreviewservice.framework.adapter.output.ReviewKafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@RequiredArgsConstructor
@Component
public class ReviewMessagePublishListener {
    private final ReviewKafkaProducer reviewKafkaProducer;

    @Transactional
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendMessage(ReviewEvent event) {
        log.info("listen spring event: {}", event.toString());
        reviewKafkaProducer.sendMessage(event);
    }
}

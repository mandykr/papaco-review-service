package com.papaco.papacoreviewservice.framework.adapter.output;

import com.papaco.papacoreviewservice.application.port.output.ReviewEventPublisher;
import com.papaco.papacoreviewservice.domain.event.ReviewEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Component
public class ReviewSpringEventPublisher implements ReviewEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void publish(ReviewEvent event) {
        log.info("publish spring event: {}", event.toString());
        eventPublisher.publishEvent(event);
    }
}

package com.papaco.papacoreviewservice.application.port.input;

import com.papaco.papacoreviewservice.application.port.output.ReviewEventPublisher;
import com.papaco.papacoreviewservice.domain.event.ReviewEvent;

public class FakeReviewEventPublisher implements ReviewEventPublisher {
    @Override
    public void publish(ReviewEvent event) {

    }
}

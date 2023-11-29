package com.papaco.papacoreviewservice.application.port.output;

import com.papaco.papacoreviewservice.domain.event.ReviewEvent;

public interface ReviewEventPublisher {
    void publish(ReviewEvent event);
}

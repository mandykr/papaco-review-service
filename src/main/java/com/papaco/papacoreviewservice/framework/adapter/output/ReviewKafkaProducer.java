package com.papaco.papacoreviewservice.framework.adapter.output;

import com.papaco.avro.schema.ReviewMessage;
import com.papaco.papacoreviewservice.domain.event.ReviewEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@RequiredArgsConstructor
@Component
public class ReviewKafkaProducer {
    @Value("${papaco.kafka.review.avro.topic.name}")
    private String topic;
    private final KafkaTemplate<String, ReviewMessage> kafkaTemplate;

    public void sendMessage(ReviewEvent event) {
        ReviewMessage message = new ReviewMessage(
                event.getId().toString(),
                event.getMateId().toString(),
                event.getStatus().toString()
        );

        String key = message.getId();
        kafkaTemplate.send(topic, key, message).addCallback(new ListenableFutureCallback<SendResult<String, ReviewMessage>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("kafka send error: {}", ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, ReviewMessage> result) {
                log.info("kafka send result: {}", result.toString());
            }
        });
    }
}

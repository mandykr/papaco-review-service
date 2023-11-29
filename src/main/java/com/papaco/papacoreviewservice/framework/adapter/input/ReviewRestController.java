package com.papaco.papacoreviewservice.framework.adapter.input;

import com.papaco.papacoreviewservice.application.dto.ReviewResponse;
import com.papaco.papacoreviewservice.application.port.usecase.ReviewUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/reviews")
@RestController
public class ReviewRestController {
    private final ReviewUseCase reviewUseCase;

    @PostMapping("/{memberName}/{codeStoreName}/mates/{mateId}/demand")
    public ResponseEntity<ReviewResponse> demandReview(
            @PathVariable String memberName,
            @PathVariable String codeStoreName,
            @PathVariable UUID mateId) {
        ReviewResponse review = reviewUseCase.demandReview(mateId, memberName, codeStoreName);
        return ResponseEntity.created(URI.create("/reviews/" + review.getId())).body(review);
    }

    @PutMapping("/{reviewId}/complete")
    public ResponseEntity<Void> completeReview(@PathVariable UUID reviewId) {
        reviewUseCase.completeReview(reviewId);
        return ResponseEntity.ok().build();
    }
}

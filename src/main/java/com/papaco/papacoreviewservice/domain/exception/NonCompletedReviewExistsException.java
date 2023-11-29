package com.papaco.papacoreviewservice.domain.exception;

public class NonCompletedReviewExistsException extends BusinessException {
    private static final String ALL_REVIEWS_MUST_BE_COMPLETED = "완료되지 않은 리뷰가 존재하면 새로운 리뷰를 요청할 수 없습니다.";

    public NonCompletedReviewExistsException() {
        super(ALL_REVIEWS_MUST_BE_COMPLETED);
    }

    public NonCompletedReviewExistsException(String message) {
        super(message);
    }
}

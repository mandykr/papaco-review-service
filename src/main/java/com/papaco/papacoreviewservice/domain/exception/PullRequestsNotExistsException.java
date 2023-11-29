package com.papaco.papacoreviewservice.domain.exception;

public class PullRequestsNotExistsException extends BusinessException {
    private static final String PULL_REQUEST_MUST_BE_EXISTS = "PR이 존재하지 않아 리뷰를 요청할 수 없습니다.";

    public PullRequestsNotExistsException() {
        super(PULL_REQUEST_MUST_BE_EXISTS);
    }

    public PullRequestsNotExistsException(String message) {
        super(message);
    }
}

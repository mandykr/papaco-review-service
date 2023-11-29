package com.papaco.papacoreviewservice.domain.service;

import com.papaco.papacoreviewservice.domain.exception.NonCompletedReviewExistsException;
import com.papaco.papacoreviewservice.domain.exception.PullRequestsNotExistsException;
import org.springframework.stereotype.Service;

@Service
public class ReviewValidationService {

    public void validateExistsNonCompleted(long nonCompletedCount) {
        if (nonCompletedCount > 0) {
            throw new NonCompletedReviewExistsException();
        }
    }

    public void validateNotExistsPullRequests(long pullRequestsCount) {
        if (pullRequestsCount <= 0) {
            throw new PullRequestsNotExistsException();
        }
    }
}

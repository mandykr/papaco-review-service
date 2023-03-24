package com.papaco.papacoreviewservice.acceptance;

import com.papaco.papacoreviewservice.application.port.output.MateRepository;
import com.papaco.papacoreviewservice.domain.entity.Mate;
import com.papaco.papacoreviewservice.domain.vo.MateStatus;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static com.papaco.papacoreviewservice.acceptance.ReviewSteps.*;

class ReviewAcceptanceTest extends AcceptanceTest {
    @Autowired
    private MateRepository mateRepository;
    private UUID 메이트;

    @BeforeEach
    public void setUp() {
        super.setUp();
        메이트 = UUID.fromString("6fec2b25-9ad4-4dba-9f54-bc3c3305a6a5");

        UUID reviewerId = UUID.fromString("2070c21b-60ad-4b8b-bc89-a8099531d2d4");
        Mate mate = new Mate(메이트, reviewerId, MateStatus.JOINED);
        mateRepository.save(mate);
    }

    /**
     * Feature: 리뷰를 관리
     *
     *   Scenario: 리뷰를 관리한다.
     *     When 리뷰 요청
     *     Then 리뷰 요청됨
     *     When 리뷰 완료
     *     Then 리뷰 완료됨
     */
    @DisplayName("리뷰를 관리한다")
    @Test
    void manage() {
        ExtractableResponse<Response> demandResponse = 리뷰_요청(메이트);
        리뷰_요청됨(demandResponse);

        ExtractableResponse<Response> completeResponse = 리뷰_완료(demandResponse);
        리뷰_완료됨(completeResponse);
    }
}

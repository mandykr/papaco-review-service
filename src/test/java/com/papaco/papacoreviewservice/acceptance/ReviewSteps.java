package com.papaco.papacoreviewservice.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ReviewSteps {
    private static final String ENDPOINT = "/reviews";

    public static ExtractableResponse<Response> 리뷰_요청(UUID mateId) {
        String uri = String.format("%s/%s/%s/%s", ENDPOINT, "mandykr/papaco/mates", mateId, "demand");
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post(uri)
                .then().log().all().extract();
    }

    public static void 리뷰_요청됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.jsonPath().getString("id")).isNotNull();
    }

    public static ExtractableResponse<Response> 리뷰_완료(ExtractableResponse<Response> response) {
        String id = response.jsonPath().getString("id");
        String uri = String.format("%s/%s/%s", ENDPOINT, id, "complete");

        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().put(uri)
                .then().log().all().extract();
    }

    public static void 리뷰_완료됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}

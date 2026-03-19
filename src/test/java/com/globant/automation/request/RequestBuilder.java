package com.globant.automation.request;

import com.globant.automation.config.TestRunner;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RequestBuilder {

    /**
     * Genera una especificación base reutilizable con logs, URL y Auth.
     */
    private static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(TestRunner.getBaseurl())
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    public static Response getRequest(String path) {
        return RestAssured.given()
                .spec(getRequestSpec())
                .when()
                .get(path);
    }

    public static Response getRequestWithParam(String path, String paramName, Object paramValue) {
        var request = io.restassured.RestAssured.given().spec(getRequestSpec());

        if (paramName != null && paramValue != null) {
            request.pathParam(paramName, paramValue);
        }

        return request.when().get(path);
    }

    public static Response getRequestWithQueryParams(String path, Map<String, ?> queryParams) {
        var request = io.restassured.RestAssured.given().spec(getRequestSpec());

        if (queryParams != null && !queryParams.isEmpty()) {
            request.queryParams(queryParams);
        }

        return request.when().get(path);
    }

    public static Response postRequest(String path, Object body) {
        return RestAssured.given()
                .spec(getRequestSpec())
                .body(body)
                .when()
                .post(path);
    }

    public static Response deleteRequest(String path, String paramName, Object paramValue) {
        RequestSpecification request = RestAssured.given().spec(getRequestSpec());

        if (paramName != null) {
            request.pathParam(paramName, paramValue);
        }

        return request.when().delete(path);
    }
}
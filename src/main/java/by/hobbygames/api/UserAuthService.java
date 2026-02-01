package by.hobbygames.api;


import io.restassured.*;
import io.restassured.filter.session.*;
import io.restassured.http.*;
import io.restassured.response.*;

import java.util.*;

import static io.restassured.RestAssured.given;

public class UserAuthService {
    private final String BASE_URL = "https://hobbygames.by/";
    private Response response;

    private Map<String, String> getQueryParams() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("route", "account/login/modalAjax");
        return queryParams;
    }

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("x-requested-with", "XMLHttpRequest");
        return headers;
    }

    private String getBody(String login, String password, String scenario) {
        String body = "login=" + login + "&password=" + password + "&scenario=" + scenario;
        return body;
    }

    public void doRequest(String login, String password, String scenario) {
        response =
                given()
                        .baseUri(BASE_URL)
                        .queryParams(getQueryParams())
                        .headers(getHeaders())
                        .body(getBody(login, password, scenario))
                .when()
                        .post();

    }

    public void printResponse() {
        response.then()
                .log().all();
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getMessage(String path) {
        return response.body().jsonPath().getString(path);
    }
}

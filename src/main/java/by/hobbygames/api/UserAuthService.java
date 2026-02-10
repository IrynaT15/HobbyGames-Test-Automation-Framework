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

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("x-requested-with", "XMLHttpRequest");
        return headers;
    }

    public void doRequest(String login, String password) {
        response =
                given()
                        .baseUri(BASE_URL)
                        .queryParams("route", "account/login/modalAjax")
                        .headers(getHeaders())
                        .formParam("login", login)
                        .formParam("password", password)
                        .formParam("scenario", "email")
                .when()
                        .post();
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getMessage(String path) {
        return response.body().jsonPath().getString(path);
    }

    public boolean isSuccess() {
        return response.body().jsonPath().getBoolean("success");
    }
}

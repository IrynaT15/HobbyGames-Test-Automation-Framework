package by.hobbygames.api;


import io.qameta.allure.*;
import io.restassured.*;
import io.restassured.filter.session.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.apache.logging.log4j.*;

import java.util.*;

import static io.restassured.RestAssured.given;

public class UserAuthService {
    private final String BASE_URL = "https://hobbygames.by/";
    private final String ROUTE = "account/login/modalAjax";
    private Response response;

    private static final Logger logger = LogManager.getLogger();

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("x-requested-with", "XMLHttpRequest");
        return headers;
    }

    @Step("Send POST login request")
    public void doRequest(String login, String password) {
        logger.info("Sending POST request to {}} endpoint with login: {}", ROUTE, login);
        response =
                given()
                        .baseUri(BASE_URL)
                        .queryParams("route", ROUTE)
                        .headers(getHeaders())
                        .formParam("login", login)
                        .formParam("password", password)
                        .formParam("scenario", "email")
                .when()
                        .post();
    }

    @Step("Get response status code")
    public int getStatusCode() {
        int statusCode = response.getStatusCode();
        logger.info("Received response with status code: {}", statusCode);
        return statusCode;
    }

    @Step("Get message from the Response body")
    public String getMessage(String path) {
        String message = response.body().jsonPath().getString(path);
        logger.info("Error message for {}: {}", path, message);
        return message;
    }

    @Step("Check response 'success' flag")
    public boolean isSuccess() {
        boolean success = response.body().jsonPath().getBoolean("success");
        logger.info("Response contains 'success': {}", success);
        return success;
    }
}

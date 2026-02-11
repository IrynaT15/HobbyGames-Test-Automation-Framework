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
    private Response response;
    private static final Logger logger = LogManager.getLogger();

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("x-requested-with", "XMLHttpRequest");
        return headers;
    }

    @Step("Run POST login request")
    public void doRequest(String login, String password) {
        logger.info("Sending POST request to /account/login/modalAjax endpoint with login: {}", login);
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

    @Step("Get Status Code of the Response")
    public int getStatusCode() {
        logger.info("Received response with status code: {}", response.getStatusCode());
        return response.getStatusCode();
    }

    @Step("Get message from the Response body")
    public String getMessage(String path) {
        logger.info("Error message for {}: {}", path, response.body().jsonPath().getString(path));
        return response.body().jsonPath().getString(path);
    }

    @Step("Ensure the Response body has \"success\"")
    public boolean isSuccess() {
        logger.info("Response contains 'success': {}", response.body().jsonPath().getBoolean("success") );
        return response.body().jsonPath().getBoolean("success");
    }
}

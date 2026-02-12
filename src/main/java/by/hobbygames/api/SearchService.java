package by.hobbygames.api;

import io.qameta.allure.*;
import io.restassured.response.*;
import org.apache.logging.log4j.*;
import org.jsoup.*;
import org.jsoup.nodes.*;

import static io.restassured.RestAssured.given;

public class SearchService {
    private final String BASE_URI = "https://hobbygames.by/";
    private final String SEARCH_BASE_PATH = "/catalog/search";
    public final long MAX_ALLOWED_RESPONSE_TIME = 9000L;

    private final String PRODUCT_CARD_CSS_LOCATOR = "div.product-card";
    public final String H1_TEXT = "Ничего не найдено.";

    private Response response;
    private Document document;
    private static final Logger logger = LogManager.getLogger();

    @Step("Send GET search request")
    public void doRequest(String value) {
        logger.info("Sending GET request to {} endpoint with value: {}", SEARCH_BASE_PATH, value);
        response =
                given()
                        .baseUri(BASE_URI)
                        .basePath(SEARCH_BASE_PATH)
                        .queryParam("keyword", value)
                .when()
                        .get();

        document = Jsoup.parse(response.asString());
    }

    @Step("Get response status code")
    public int getStatusCode() {
        int statusCode = response.getStatusCode();
        logger.info("Received response with status code: {}", statusCode);
        return statusCode;
    }

    @Step("Get response content type")
    public String getContentType() {
        String contentType = response.getContentType();
        logger.info("Received response with content type: {}", contentType);
        return contentType;
    }

    @Step("Get text from h1 element in response body")
    public String getH1Text() {
        String h1Text = document.selectFirst("h1").text();
        logger.info("Getting text from h1 element: {}", h1Text);
        return h1Text;
    }

    @Step("Get text from element with class 'result' in response body")
    public String getResultsText() {
        String resultsText = document.selectFirst("div.result").text();
        logger.info("Getting text from 'result' class: {}", resultsText);
        return resultsText;
    }

    @Step("Get total number of product cards in search results")
    public int getProductCardsCount() {
        int totalProductCardsCount = document.select(PRODUCT_CARD_CSS_LOCATOR).size();
        logger.info("Getting total number of product cards in search results: {}", totalProductCardsCount);
        return totalProductCardsCount;
    }

    @Step("Get response time")
    public long getResponseTime() {
        long responseTime = response.time();
        logger.info("Getting response time: {}", responseTime);
        return responseTime;
    }

    @Step("Check whether product cards list is empty")
    public boolean isProductCardsListEmpty() {
        boolean emptyList = document.select(PRODUCT_CARD_CSS_LOCATOR).isEmpty();
        logger.info("Product cards list is empty: {}", emptyList);
        return emptyList;
    }
}

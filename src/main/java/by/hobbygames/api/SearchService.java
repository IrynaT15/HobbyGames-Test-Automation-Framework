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

    private final String PRODUCT_CARD_CSS_LOCATOR = "div.product-card";

    private Response response;
    private Document document;
    private static final Logger logger = LogManager.getLogger();

    @Step("Run GET search request")
    public void doRequest(String value) {
        logger.info("Running GET search request");
        response =

                given()
                        .baseUri(BASE_URI)
                        .basePath(SEARCH_BASE_PATH)
                        .queryParam("keyword", value)
                .when()
                        .get();

        document = Jsoup.parse(response.asString());
    }

    @Step("Get response status cod")
    public int getStatusCode() {
        logger.info("Getting response status code");
        return response.getStatusCode();
    }

    @Step("Get response content type")
    public String getContentType() {
        logger.info("Getting response content type");
        return response.getContentType();
    }

    @Step("Get text from h1 element in response body")
    public String getH1Text() {
        logger.info("Getting text from h1 element in response body");
        return document.selectFirst("h1").text();
    }

    @Step("Get text from element with class 'result' in response body")
    public String getResultsText() {
        logger.info("Getting text from element with class 'result' in response body");
        return document.selectFirst(".result").text();
    }

    @Step("Get total number of product cards in search results")
    public int getProductCardsCount() {
        logger.info("Getting total number of product cards in search results");
        return document.select(PRODUCT_CARD_CSS_LOCATOR).size();
    }

    @Step("Get response time")
    public long getResponseTime() {
        logger.info("Getting response time");
        return response.time();
    }

    @Step("Check whether product cards list is empty")
    public boolean isProductCardsListEmpty() {
        logger.info("Checking whether product cards list is empty");
        return document.select(PRODUCT_CARD_CSS_LOCATOR).isEmpty();
    }
}

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

    @Step("Get Status Code of the Response")
    public int getStatusCode() {
        logger.info("Getting the Status Code of the Response");
        return response.getStatusCode();
    }

    @Step("Get Content Type of the Response")
    public String getContentType() {
        logger.info("Getting the Content Type of the Response");
        return response.getContentType();
    }

    @Step("Get the text of h1 of the Response body")
    public String getH1Text() {
        logger.info("Getting the text of h1 of the Response body");
        return document.selectFirst("h1").text();
    }

    @Step("Get the text of \"result\" class of the Response body")
    public String getResultsText() {
        logger.info("Getting the text of \"result\" class of the Response body");
        return document.selectFirst("[class='result']").text();
    }

    @Step("Get the total count of the Product Cards for the found items")
    public int getProductCardsCount() {
        logger.info("Getting the total count of the Product Cards for the found items");
        return document.select(PRODUCT_CARD_CSS_LOCATOR).size();
    }

    @Step("Get the Response time")
    public long getResponseTime() {
        logger.info("Getting the Response time");
        return response.time();
    }

    @Step("Ensure whether the Product Cards list is empty or not empty")
    public boolean isProductCardsListEmpty() {
        logger.info("Ensuring whether the Product Cards list is empty or not empty");
        return document.select(PRODUCT_CARD_CSS_LOCATOR).isEmpty();
    }
}

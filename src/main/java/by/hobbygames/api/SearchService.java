package by.hobbygames.api;

import io.restassured.response.*;
import org.jsoup.*;
import org.jsoup.nodes.*;

import static io.restassured.RestAssured.given;

public class SearchService {
    private final String BASE_URI = "https://hobbygames.by/";
    private final String SEARCH_BASE_PATH = "/catalog/search";

    private final String PRODUCT_CARD_CSS_LOCATOR = "div.product-card";

    private Response response;
    private Document document;

    public void doRequest(String value) {
        response =

                given()
                        .baseUri(BASE_URI)
                        .basePath(SEARCH_BASE_PATH)
                        .queryParam("keyword", value)
                .when()
                        .get();

        document = Jsoup.parse(response.asString());
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getContentType() {
        return response.getContentType();
    }

    public String getH1Text() {
        return document.selectFirst("h1").text();
    }

    public String getResultsText() {
        return document.selectFirst("[class='result']").text();
    }

    public int getProductCardsCount() {
        return document.select(PRODUCT_CARD_CSS_LOCATOR).size();
    }

    public long getResponseTime() {
        return response.time();
    }

    public boolean isProductCardsListEmpty() {
        return document.select(PRODUCT_CARD_CSS_LOCATOR).isEmpty();
    }
}

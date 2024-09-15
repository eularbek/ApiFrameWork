package com.get;

import com.pojos.BrakongBadPojo.BBPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class BreakingBad {

    @Test
    public void getQuotes(){

        RestAssured.baseURI = "https://api.breakingbadquotes.xyz";
        RestAssured.basePath = "v1/quotes";

        Response response = RestAssured.given().accept(ContentType.JSON).when().get().then().statusCode(200).extract().response();

        BBPojo[] parsedResponse = response.as(BBPojo[].class);
        System.out.println(parsedResponse[0].getQuote());
        System.out.println(parsedResponse[0].getAuthor());

    }

}

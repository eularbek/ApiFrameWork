package com.get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class CurrencyAPI {

    @Test
    public void validateCurrencyEndPont(){

       String endPoint = "https://openexchangerates.org/api/currencies.json";

        Response response = RestAssured.given()
                .accept("application/json")
                .when()
                .get(endPoint)
                .then()
                .statusCode(200)
                .extract()
                .response();

        Map<String, String> deserializeResponse = response.as(new TypeRef<Map<String, String>>() {
        });
        String currencyName = deserializeResponse.get("USD");
        String expectedCurrencyName = "United States Dollar";

        Assert.assertEquals(currencyName, expectedCurrencyName, "Failed to validate a field from JSON");

        for (Map.Entry m: deserializeResponse.entrySet()){

            if (m.getKey().equals("USD")){
                System.out.println(m.getKey());
                System.out.println(m.getValue());
            }
        }




    }

    @Test
    public void validateCatFacts(){

        String catEndPoint = "https://catfact.ninja/facts?page=10";

        Response response = RestAssured.given()
                .accept("application/json")
                .when()
                .get(catEndPoint)
                .then()
                .statusCode(200)
                .extract()
                .response();
        Map<String, Object> parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
       // System.out.println("parsedResponse = " + parsedResponse);
        List<Map<String, Object>> data = (List<Map<String, Object>>) parsedResponse.get("data");

        //System.out.println(data);
        for (int i = 0; i < data.size(); i++) {
            Map<String, Object> map = data.get(i);

            if (map.get("length").equals(122)){
                System.out.println(map.get("fact"));
            }
        }


    }


}

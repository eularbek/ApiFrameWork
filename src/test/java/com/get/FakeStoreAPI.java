package com.get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class FakeStoreAPI {

    @Test
    public void validateFields(){

        //https://fakestoreapi.com/products/12
        RestAssured.baseURI = "https://fakestoreapi.com";
        RestAssured.basePath = "products/12";

        Response response = RestAssured.given().accept("application/json").when().get().then().statusCode(200).extract().response();
        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        Integer actualID = (Integer) deserializedResponse.get("id");
        Integer expectedID = 12;

        Assert.assertEquals(actualID, expectedID);

        Integer actualPrice = (Integer) deserializedResponse.get("price");
        Integer expectedPrice = 114;

        Assert.assertEquals(actualPrice, expectedPrice);

        Map<String, Object> rating = (Map<String, Object>) deserializedResponse.get("rating");

        double rate = (double) rating.get("rate");
        int count = (int) rating.get("count");

        Assert.assertTrue(rate == 4.8);
        Assert.assertTrue(count == 400);
        System.out.println(rate);
        System.out.println(count);

    }

    @Test
    public void validateFields20(){

        RestAssured.baseURI = "https://fakestoreapi.com";
        RestAssured.basePath = "products/20";

        Response response = RestAssured.given().accept("application/json").when().get().then().statusCode(200).extract().response();
        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        Integer actualID = (Integer) deserializedResponse.get("id");
        Integer expectedID = 20;

        Assert.assertEquals(actualID, expectedID);

    }
    @Test
    public void taskToValidateFields(){

        RestAssured.baseURI = "https://fakestoreapi.com";
        RestAssured.basePath = "products";

        Response response = RestAssured.given().accept("application/json").when().get().then().statusCode(200).extract().response();

        List<Map<String, Object>> parseResponse = response.as(new TypeRef<List<Map<String, Object>>>() {
        });

        double totalPrice = 0;
        for (int i = 0; i < parseResponse.size(); i++) {
            Map<String, Object> map = parseResponse.get(i);
          //                   parsing
            double price = Double.parseDouble(map.get("price").toString());
            totalPrice +=price;

        }
        Assert.assertTrue(totalPrice>2000);
        Assert.assertEquals(totalPrice, 3240.9199999999987);
        System.out.println(totalPrice);


    }






}

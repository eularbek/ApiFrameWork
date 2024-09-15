package com.get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

public class ChuckNorrisJokes {
//
//    @Test
//    public void getChuckNorrisJokes(){
//
//        RestAssured.baseURI = "https://api.chucknorris.io";
//        RestAssured.basePath = "jokes/random";
//
//        Response response = RestAssured.given().accept(ContentType.JSON).when().get().then().statusCode(200).extract().response();
//
//        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
//        });
//
//        System.out.println(deserializedResponse.get("value"));
//    }




    @Test
    public void karateNorris(){

        RestAssured.baseURI = "https://api.chucknorris.io";
        RestAssured.basePath = "jokes/random";

        Response response = RestAssured.given().accept(ContentType.JSON).when().get().then().statusCode(200).extract().response();

        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        System.out.println(deserializedResponse.get("value"));


    }


























}

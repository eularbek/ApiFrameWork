package com.get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class GoT {

    @Test
    public void findJonSnow(){

        RestAssured.baseURI = "https://thronesapi.com";
        RestAssured.basePath = "api/v2/Characters/";

       Response response= RestAssured.given().accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();


        List<Map<String, Object>> parsedResponse = response.as(new TypeRef<List<Map<String, Object>>>() {
        });

        for (int i = 0; i < parsedResponse.size(); i++) {

            Map<String, Object> map = parsedResponse.get(i);

            if (map.get("lastName").equals("Snow")){
                System.out.println(map.get("firstName"));
                System.out.println(map.get("fullName"));
                System.out.println(map.get("title"));
                System.out.println(map.get("family"));
                break;

            }
        }

    }






















}

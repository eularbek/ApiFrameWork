package com.get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class Pokemon {

    @Test
    public void findPikachu(){

        Response response = RestAssured.given().accept(ContentType.JSON).when().get("https://pokeapi.co/api/v2/pokemon").then().statusCode(200).extract().response();

        Map<String, Object> parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        String pikachuURL = "";

        while (parsedResponse.get("next") !=null){

            response = RestAssured.given().accept(ContentType.JSON).when().get((String) parsedResponse.get("next")).then().statusCode(200).extract().response();
            parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
            });
            List<Map<String, Object>> result = (List<Map<String, Object>>) parsedResponse.get("results");

            for (int i = 0; i < result.size(); i++) {
                Map<String, Object> map = result.get(i);

                if (map.get("name").equals("pikachu")){
                   pikachuURL = (String) map.get("url");
                   break;
                }
            }
        }
        System.out.println(pikachuURL);

        response = RestAssured.given().accept(ContentType.JSON).when().get(pikachuURL).then().statusCode(200).extract().response();

        parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        List<Map<String, Object>> abilities = (List<Map<String, Object>>) parsedResponse.get("abilities");
        System.out.println(abilities);

    }

}

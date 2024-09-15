package com.get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class GetAirports {

    @Test
    public void getGetAirports(){

        RestAssured.baseURI = "https://airportgap.com";
        RestAssured.basePath = "api/airports";

        Response response = RestAssured.given().accept(ContentType.JSON).when().get().then().statusCode(200).extract().response();

        Map<String, Object> parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
       List<Map<String, Object>> data = (List<Map<String, Object>>) parsedResponse.get("data");

        for (int i = 0; i < data.size(); i++) {

            Map<String,Object> outerMap = (Map<String, Object>) data.get(i).get("attributes");

            if (outerMap.get("iata").equals("RKV")){
                System.out.println(outerMap.get("name"));
                System.out.println(outerMap.get("city"));
                System.out.println(outerMap.get("country"));
                break;
            }
        }

//            Map<String, Object> innerMap = (Map<String, Object>) outerMap.get("attributes");
//
//            if (innerMap.get("iata").equals("RVK")){
//                System.out.println(innerMap.get("name"));
//                System.out.println(innerMap.get("city"));
//                System.out.println(innerMap.get("country"));
//                break;
//            }
//        }

    }















}

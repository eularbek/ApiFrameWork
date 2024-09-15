package com.airporttoken;

import com.payloads.Payloads;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

public class AirportTokenGenerator {

    @Test
    public void generateToken(){
      // 1. generated token
        String credentials = "email=ulk@mail.com&password=Bazuka14";
        RestAssured.baseURI = "https://airportgap.com/api";
        Response response = RestAssured.given().header("Content-Type", "application/x-www-form-urlencoded")
                .body(credentials)
                .when()
                .post("/tokens")
                .then()
                .statusCode(200)
                .extract().response();
       //2. deserialized response using TypeRef
        Map<String, Object> parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
      // 3. Storing the generated token in a sting
        String token = (String) parsedResponse.get("token");

        //Consider our next request requires a token to authenticate the user
        // 4. Using this generated token in our POST request to get the distance between 2 airports
        String distanceURL = "https://airportgap.com/api/airports/distance";
       Response res = RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .header("token", token)
                .body(Payloads.getDistancePayload("ORD", "SEA"))
                .when()
                .post(distanceURL)
                .then()
                .statusCode(200)
                .extract().response();
        System.out.println(res.prettyPrint());


    }

}

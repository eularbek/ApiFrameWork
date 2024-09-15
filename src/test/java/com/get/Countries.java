package com.get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Countries {

    @Test
    public void findCapital(){

        String str = "";

        //
        RestAssured.baseURI = "https://restcountries.com";
        RestAssured.basePath = "v3.1/all";

        Response response= RestAssured.given().accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<Map<String, Object>> deserializedResponse = response.as(new TypeRef<List<Map<String, Object>>>() {
        });

       for (Map<String, Object> m: deserializedResponse){
           Map<String, Object> map = (Map<String, Object>) m.get("name");

           if (map.get("common").equals("")){
               System.out.println(m.get("capital"));
               break;

           }
       }


    }

    @Test
    public void findCapitalWithJsonPath(){

      RestAssured.baseURI ="https://restcountries.com";
      RestAssured.basePath = "v3.1/all";

      Response response = RestAssured.given().accept(ContentType.JSON).when()
              .get()
             .then().statusCode(200)
              .extract().response();

        JsonPath deserializedResponse = response.jsonPath();

        List<Object> list = deserializedResponse.getList("name.common");


        for (int i = 0; i < list.size(); i++) {

           if (list.get(i).equals("Germany")){
               list = deserializedResponse.getList("capital");
               Object capital = list.get(i);
               System.out.println(capital);
               break;
           }


            }
        }



    }
















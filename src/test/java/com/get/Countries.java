package com.get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
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

        @Test
    public void getCapitalWithGroovy(){

            RestAssured.baseURI ="https://restcountries.com";
            RestAssured.basePath = "v3.1/all";

            Response response = RestAssured.given().accept(ContentType.JSON).when()
                    .get()
                    .then().statusCode(200)
                    .extract().response();

           List<Object> capital = response.path("find {it.name.common== 'Germany'}.capital");
            System.out.println(capital);

            //Print out country/countries from continents Africa
           List<Object> countriesInAfrica = response.path("findAll {it.continents[0] == 'Africa'}.name.common");
            System.out.println(countriesInAfrica);
            System.out.println(countriesInAfrica.size());

            String currencyOfCountry = response.path("find {it.name.common == 'Germany'}.currencies.EUR.symbol");
            System.out.println(currencyOfCountry);

            //Print out all the countries
            List<Object> allCountries = response.path("findAll {it}.name.common");
            System.out.println(allCountries);



        }

        @Test
    public void validateCountriesEndPoint(){

            RestAssured.baseURI ="https://restcountries.com";
            RestAssured.basePath = "v3.1/all";

            Response response = RestAssured
                    .given()
                    .accept(ContentType.JSON)
                    .when()
                    .get()
                    .then()
                    .body("find {it.name.common == 'United Kingdom'}.capital", Matchers.hasItem("London"))
                    .and()
                    .body("find {it.name.common == 'Germany'}.currencies.EUR.symbol", Matchers.is("€"))
                    .and()
                    .body("find {it.name.common == 'United States'}.borders", Matchers.hasItems("CAN", "MEX"))
                    .statusCode(200)
                    .extract().response();

        }



    }
















package com.get;

import com.pojos.petPojo.PetPojo;
import com.pojos.petPojo.Tags;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class PetStore {

    @Test
    public void getDog(){

        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/pet/2000";

        Response response = RestAssured.given().accept(ContentType.JSON).when().get().then().statusCode(200).extract().response();

        PetPojo parsedResponse = response.as(PetPojo.class);

        int dogID = parsedResponse.getId();
        String dogName = parsedResponse.getName();
        String dogStatus = parsedResponse.getStatus();

       String categoryName = parsedResponse.getCategory().getName();

        System.out.println(categoryName);

        List<String> photoUrls = parsedResponse.getPhotoUrls();

        System.out.println(photoUrls);

        List<Tags> tags = parsedResponse.getTags();

        for (int i = 0; i < tags.size(); i++) {
//            System.out.println(tags.get(i).getId());
//            System.out.println(tags.get(i).getName());
            if (tags.get(i).getName().equals("Sharik")){
                System.out.println(tags.get(i).getName());
                System.out.println(tags.get(i).getId());
            }
        }


    }

    @Test
    public void getDogWithJsonPath(){

        //JsonPath to deserialize our response
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet/";

        Response response = RestAssured.given().accept(ContentType.JSON).when().get("100")
                .then().statusCode(200).extract().response();
        JsonPath parsedResponse = response.jsonPath();
        String dogName = parsedResponse.get("category.name");
        System.out.println(dogName);

        int dogId = parsedResponse.get("id");
        System.out.println(dogId);

        String dogFirstName = parsedResponse.get("name");
        System.out.println(dogFirstName);

       List<Object> photoUrls = parsedResponse.getList("photoUrls");
        System.out.println(photoUrls);

        List<Object> tags = parsedResponse.getList("tags");
        System.out.println(tags);

        List<Object> findRoxy = parsedResponse.get("tags.name");
        System.out.println(findRoxy);

        List<Map<String, Object>> findRoxy2 = parsedResponse.getList("tags");
        System.out.println(findRoxy2);

        Map<String, Object> map = findRoxy2.get(0);
        String name = (String) map.get("name");
        System.out.println(name);

        String status = parsedResponse.get("status");
        System.out.println(status);

    }




















}

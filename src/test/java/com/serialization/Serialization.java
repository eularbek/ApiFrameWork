package com.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payloads.Payloads;
import com.pojos.airportPojo.DistanceCalculator;
import com.pojos.petPojo.Category;
import com.pojos.petPojo.PetPojo;
import com.pojos.petPojo.Tags;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Serialization {

    @Test
    public void writeIntoJsonPet() throws IOException {

        DistanceCalculator distanceCalculator = new DistanceCalculator();
        distanceCalculator.setFrom("ORD");
        distanceCalculator.setTo("MIA");

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/resources/airport/distance.json");

        objectMapper.writeValue(file, distanceCalculator);

    }

    @Test
    public void writeIntoJson() throws IOException {


        PetPojo petPojo = new PetPojo();
        petPojo.setId(1980);

        Category category = new Category();
        category.setId(2231);
        category.setName("Togo");

        petPojo.setCategory(category);
        petPojo.setName("Mufasa");

        List<String> photoURLS = new ArrayList<>();
        photoURLS.add("some dog images");
        photoURLS.add("another images");
        photoURLS.add("one images");

        petPojo.setPhotoUrls(photoURLS);
        List<Tags> dogTags = new ArrayList<>();
        Tags tags = new Tags();
        tags.setId(551);
        tags.setName("Rocky");

        dogTags.add(tags);
        petPojo.setTags(dogTags);

        petPojo.setStatus("Always barking, and playing with kids");


        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/resources/petstore/createpet.json");

        objectMapper.writeValue(file, petPojo);


        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet/";

        //1. Create a pet
        // We are updating an existing pet using their id
        RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(file).when().post().then()
                .statusCode(200).extract().response();

    }




















}

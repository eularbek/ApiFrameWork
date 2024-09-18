package com.post;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Telegram {

    @Test
    public void validateSendMessageFunctionality() {

        String botToken = ConfigReader.readProperty("telegramToken");
        String chatID = ConfigReader.readProperty("telegramChatID");
        String message = "Message from Intellij Bek Bot";
        String baseURL = "https://api.telegram.org";
        String username = "beko0214bot";
        String title = "B23-CodeFish";


        //Given method is imported as static
       Response response = given()
                .baseUri(baseURL)
                .basePath("/bot" + botToken + "/sendMessage")
                .queryParam("chat_id", chatID)
                .queryParam("text", message)
                .when()
                .post()
                .then().statusCode(200)
                .extract().response();

        /*
        Validate the following:
        1. username
        2. chat id
        3. title
        4. text
         */
        JsonPath parsedResponse = response.jsonPath();

        //Printing username
        String actualUsername = parsedResponse.get("result.from.username");
        //System.out.println(actualUsername);
        Assert.assertEquals(actualUsername, username, "Failed to validate username");

        //Printing chat id from response
        long actualChatID = parsedResponse.get("result.chat.id");
       // System.out.println(actualChatID);
        Assert.assertEquals(String.valueOf(actualChatID), chatID);

        //Printing title
        String actualTitle = parsedResponse.get("result.chat.title");
       // System.out.println(actualTitle);
        Assert.assertEquals(actualTitle, title, "Failed to validate group name");

        //Printing actual message from/sendMessage endpoint
        String actualText = parsedResponse.get("result.text");
        //System.out.println(actualText);
        Assert.assertEquals(actualText, message, "Failed to validate message");
    }

    @Test
    public void getUpdates(){

        // https://api.telegram.org/bot7295595128:AAGstm7LX-LWTbwGxtKQYmGpm3aMNffa6lk/getUpdates
        RestAssured.baseURI = "https://api.telegram.org";
       // RestAssured.basePath = "bot"+ConfigReader.readProperty("telegramToken")+"/getUpdates";

        Response response = given().accept(ContentType.JSON)
                .when()
                .get("bot"+ConfigReader.readProperty("telegramToken")+"/getUpdates")
                .then()
                .statusCode(200).log().body()
                .extract().response();

        Map<String, Object> parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        List<Map<String, Object>> result = (List<Map<String, Object>>) parsedResponse.get("result");
        for (int i = 0; i < result.size(); i++) {

                Map<String, Object> map = result.get(i);

                if (map.containsKey("message")){

                    Map<String, Object> innerMap = (Map<String, Object>) map.get("message");
                    if (innerMap.containsKey("new_chat_member")){

                        Map<String, Object> new_chat_member = (Map<String, Object>) innerMap.get("new_chat_member");
                        if (new_chat_member.get("first_name").equals("Bek")){
                            System.out.println(new_chat_member.get("username"));
                            System.out.println(new_chat_member.get("is_bot"));

                        }
                    }
                }


        }


    }






}

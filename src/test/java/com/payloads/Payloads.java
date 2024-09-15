package com.payloads;

public class Payloads {

    public static String getPetPayload(int id, String name){

        return "{\n" +
                "  \"id\": "+id+",\n" +
                "  \"name\": \""+name+"\",\n" +
                "  \"status\": \"Always barking, and playing with kids\",\n" +
                "  \"category\": {\n" +
                "    \"id\": 2231,\n" +
                "    \"name\": \"Togo\"\n" +
                "  },\n" +
                "  \"photoUrls\": [\n" +
                "    \"some dog images\",\n" +
                "    \"another images\",\n" +
                "    \"one images\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 551,\n" +
                "      \"name\": \"Rocky\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String getDistancePayload(String from, String to){

        return "{\n" +
                "  \"from\": \""+from+"\",\n" +
                "  \"to\": \""+to+"\"\n" +
                "}";
    }

}

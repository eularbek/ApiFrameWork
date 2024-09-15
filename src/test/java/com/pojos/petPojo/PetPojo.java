package com.pojos.petPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PetPojo {

    private int id;

    private String name;

    private String status;

    private Category category;

    private List<String> photoUrls;

    private List<Tags> tags;


}

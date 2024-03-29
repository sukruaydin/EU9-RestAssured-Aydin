package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter //coming from lombok
@Setter
@ToString

@JsonIgnoreProperties(ignoreUnknown = true) //coming from jackson
public class Regions {

    private List<Region> items;
    private int count;
    private int offset;

}

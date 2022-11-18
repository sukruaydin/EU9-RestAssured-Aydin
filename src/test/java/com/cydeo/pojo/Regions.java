package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter //comes from lombok
@Setter
@ToString
public class Regions {

    //this JsonProperty allows us to link 2 variable names
    //first variable name stands for the name coming from API
    //second variable name stands for the name that i create
    //so, i can name my variable freely
    //this annotation comes from jackson
    //useful to use when we have spaces in variable names
    @JsonProperty("region_id")
    private int regionId;

    @JsonProperty("region_name")
    private String region_name;

    @JsonProperty("links")
    private List<Links> links;


    /*public int getRegion_id() {
        return region_id;
    }
    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }
    public String getRegion_name() {
        return region_name;
    }
    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }
    public List<Links> getLinks() {
        return links;
    }
    public void setLinks(List<Links> links) {
        this.links = links;
    }*/

   /* @Override
    public String toString() {
        return "Regions{" +
                "region_id=" + region_id +
                ", region_name='" + region_name + '\'' +
                ", links=" + links +
                '}';
    }*/
}

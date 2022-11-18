package com.cydeo.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Regions {

    private int region_id;
    private String region_name;
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

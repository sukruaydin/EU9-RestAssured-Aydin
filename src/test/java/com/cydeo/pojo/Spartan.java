package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//by sending post request, i don't need to specify id value
//with this annotation i can ignore id key
//excluded for serialization, but still included for de-serialization
//also we can create another class with 3 instances, but it doesn't make sense since we have solution
@JsonIgnoreProperties(value = "id", allowSetters = true)
public class Spartan {

    //fields for a spartan
    private int id;
    private String name;
    private String gender;
    private long phone;



}

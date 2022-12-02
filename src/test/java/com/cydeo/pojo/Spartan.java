package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//while sending post request, i am not allowed to specify id value
//with this annotation I can ignore id key
//excluded for serialization, but still included for de-serialization
//also we can create another class with 3 instances, but it doesn't make sense since we have solution
//@JsonIgnoreProperties(value = "id", allowSetters = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Spartan {

    //fields for a spartan
    private int id;
    private String name;
    private String gender;
    private long phone;



}

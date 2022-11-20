package com.cydeo.day05;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

//java.class_1
public class HamcrestMatchersIntro {

    @DisplayName("Assertion with numbers")
    @Test
    public void test1(){

        //we have hamcrest dependency already in rest-assured dependency
        //asserting 5+5=10
        MatcherAssert.assertThat(5+5, is(10)); //is
        assertThat(5+5, equalTo(10)); //equal to

        //matchers has 2 overloaded version
        //first accepts actual value
        //second accepts another matchers
        //below examples is method is accepting another matchers equal to make it readable
        assertThat(5+5, is(equalTo(10))); //chaining

        //chaining example
        assertThat(5+5, not(9));
        assertThat(5+5, is(not(9)));
        assertThat(5+5, is(not(equalTo(9))));

        //number comparison
        assertThat(5+5,is(greaterThan(9)));

    }

    @DisplayName("Assertion with String")
    @Test
    public void test2(){

        String text = "EU9 is learning Hamcrest";

        //checking for equality as same as numbers
        assertThat(text,is(equalTo("EU9 is learning Hamcrest")));
        assertThat(text,equalTo("EU9 is learning Hamcrest"));
        assertThat(text,is(equalTo("EU9 is learning Hamcrest")));

        //check if this text starts with EU9
        assertThat(text,startsWith("EU9"));
        //now do it in case in-sensitive manner
        assertThat(text,startsWithIgnoringCase("eu9"));
        //ends-with
        assertThat(text,endsWith("rest"));

        //check if text contains String learning
        assertThat(text,containsString("learning"));
        //with ignoring case
        assertThat(text,containsStringIgnoringCase("LEARNİNG"));

        String str ="  ";

        //check if above str is blank
        assertThat(str,is(blankString()));
        //check if trimmed str is empty string
        assertThat(str.trim(),emptyString());

    }

    @DisplayName("Hamcrest for Collection")
    @Test
    public void test3(){

        List<Integer> listOfNumbers = Arrays.asList(1,4,5,6,32,54,66,77,45,23);

        //check size of the list
        assertThat(listOfNumbers,hasSize(10));
        //check if this list hasItem 77
        assertThat(listOfNumbers,hasItem(77));
        //check if this list hasItems 77,54,23
        assertThat(listOfNumbers,hasItems(77,54,23));

        //this is great
        //check if all numbers greater than 0
        assertThat(listOfNumbers,everyItem(greaterThan(0)));

    }

}

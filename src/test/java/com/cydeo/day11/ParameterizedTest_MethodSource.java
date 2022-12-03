package com.cydeo.day11;

import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//java_class_5
public class ParameterizedTest_MethodSource {

    public static List<String> getNames(){
        //you can get value from anywhere almost anytype and return to your test
        //DB
        //Excel
        //other APIs
        List<String> nameList = Arrays.asList("şükrü","oğuz","pişgin","kavak");
        return nameList;
    }

    @ParameterizedTest
    @MethodSource("getNames")
    public void test1(String name){
        System.out.println("name = " + name);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //I wanna read the exel sheet data in data driven approach
    public static List<Map<String,String>> getExelData(){
        ExcelUtil vytrackFile = new ExcelUtil("src/test/resources/Vytracktestdata.xlsx","QA3-short");
        return vytrackFile.getDataList();
    }

    @ParameterizedTest
    @MethodSource("getExelData")
    public void test2(Map<String,String> userInfo){

        //print firstName and lastName
        System.out.println(userInfo.get("firstname") + " " + userInfo.get("lastname"));

    }

}

package com.cydeo.day11;

import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class ExelUtilPractice {

    //let's understand how ExelUtils work
    @Test
    public void test1(){

        //How to use excelUtil file ?
        //it accepts two arguments
        //Argument 1: location of the file(path)
        //Argument 2: sheet that we want to open
        ExcelUtil vytrackFile = new ExcelUtil("src/test/resources/Vytracktestdata.xlsx","QA3-short");
        
        //method for returning List of Map
        List<Map<String, String>> dataList = vytrackFile.getDataList();
        for (Map<String, String> rowMap : dataList) {
            System.out.println("rowMap = " + rowMap);
        }

    }

}

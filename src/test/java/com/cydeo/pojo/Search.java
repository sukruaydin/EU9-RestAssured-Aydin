package com.cydeo.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Search {

    private List<Spartan> content;
    private int totalElement;

    /*public List<Spartan> getContent() {
        return content;
    }
    public void setContent(List<Spartan> content) {
        this.content = content;
    }
    public int getTotalElement() {
        return totalElement;
    }
    public void setTotalElement(int totalElement) {
        this.totalElement = totalElement;
    }

    @Override
    public String toString() {
        return "Search{" +
                "content=" + content +
                ", totalElement=" + totalElement +
                '}';
    }*/
}

package edu.omur.msworld.moviecatalogservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CatalogItem {
    private String id;
    private String name;
    private int year;
    private float rating;
    private int reviewerCount;

    public CatalogItem(String id, String name, int year, float rating, int reviewerCount) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.rating = rating;
        this.reviewerCount = reviewerCount;
    }
}

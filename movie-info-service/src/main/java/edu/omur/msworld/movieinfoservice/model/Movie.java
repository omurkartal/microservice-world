package edu.omur.msworld.movieinfoservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Movie {
    private String id;
    private String name;
    private int year;

    public Movie(String id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }
}

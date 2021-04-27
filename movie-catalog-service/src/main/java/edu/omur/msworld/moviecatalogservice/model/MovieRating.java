package edu.omur.msworld.moviecatalogservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MovieRating {
    private String movieId;
    private float rating;
    private int reviewerCount;

    public MovieRating() {
    }

    public MovieRating(String movieId, float rating, int reviewerCount) {
        this.movieId = movieId;
        this.rating = rating;
        this.reviewerCount = reviewerCount;
    }
}

package edu.omur.msworld.movieratingservice.service;

import edu.omur.msworld.movieratingservice.model.MovieRating;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    private static final Logger logger = LogManager.getLogger(RatingServiceImpl.class);
    private static List<MovieRating> ratingList;

    public MovieRating getMovieRating(String movieId) {
        MovieRating movieRating = ratingList.stream()
                .filter(item -> item.getMovieId().equalsIgnoreCase(movieId))
                .findAny()
                .orElse(null);
        return movieRating;
    }

    @PostConstruct
    private void buildRatingList() {
        ratingList = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            ratingList.add(new MovieRating(String.valueOf(i), ((70 + i) / 10f), (70 + i)));
        }
    }
}

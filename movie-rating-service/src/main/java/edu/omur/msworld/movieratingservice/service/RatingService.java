package edu.omur.msworld.movieratingservice.service;

import edu.omur.msworld.movieratingservice.model.MovieRating;

public interface RatingService {
    MovieRating getMovieRating(String movieId);
}

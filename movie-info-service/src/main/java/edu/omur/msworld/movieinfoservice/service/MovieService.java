package edu.omur.msworld.movieinfoservice.service;

import edu.omur.msworld.movieinfoservice.model.Movie;
import edu.omur.msworld.movieinfoservice.model.MovieList;

public interface MovieService {
    MovieList getAll();

    Movie getMovie(String movieId);
}

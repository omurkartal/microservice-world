package edu.omur.msworld.movieinfoservice.service;

import edu.omur.msworld.movieinfoservice.model.Movie;
import edu.omur.msworld.movieinfoservice.model.MovieList;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private static List<Movie> movieList;

    public MovieList getAll() {
        return new MovieList(movieList);
    }

    public Movie getMovie(String movieId) {
        Movie movie = movieList.stream()
                .filter(item -> item.getId().equalsIgnoreCase(movieId))
                .findFirst()
                .orElse(null);
        return movie;
    }

    @PostConstruct
    private void getUserRating() {
        movieList = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            movieList.add(new Movie(String.valueOf(i), "Movie-" + i, (2010 + i)));
        }
    }
}

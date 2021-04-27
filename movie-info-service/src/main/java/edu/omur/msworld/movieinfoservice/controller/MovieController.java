package edu.omur.msworld.movieinfoservice.controller;

import edu.omur.msworld.corelib.annotation.Loggable;
import edu.omur.msworld.movieinfoservice.model.Movie;
import edu.omur.msworld.movieinfoservice.model.MovieList;
import edu.omur.msworld.movieinfoservice.service.MovieService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private static final Logger logger = LogManager.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @Loggable(logArguments = true, logExecutionTime = true)
    @RequestMapping("/")
    public MovieList getAll() {
        return movieService.getAll();
    }

    @Loggable(logArguments = true, logExecutionTime = true)
    @RequestMapping("/{movieId}")
    public Movie getMovie(@PathVariable("movieId") String movieId) {
        return movieService.getMovie(movieId);
    }
}

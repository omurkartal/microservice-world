package edu.omur.msworld.movieratingservice.controller;

import edu.omur.msworld.corelib.annotation.Loggable;
import edu.omur.msworld.movieratingservice.model.MovieRating;
import edu.omur.msworld.movieratingservice.service.RatingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    private static final Logger logger = LogManager.getLogger(RatingController.class);

    @Autowired
    private RatingService ratingService;

    @Loggable(logArguments = true, logExecutionTime = true)
    @RequestMapping("/{movieId}")
    public MovieRating getMovieRating(@PathVariable("movieId") String movieId) {
        return ratingService.getMovieRating(movieId);
    }
}

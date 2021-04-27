package edu.omur.msworld.moviecatalogservice.controller;

import edu.omur.msworld.corelib.annotation.Loggable;
import edu.omur.msworld.moviecatalogservice.model.CatalogItem;
import edu.omur.msworld.moviecatalogservice.service.MovieCatalogService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
    private static final Logger logger = LogManager.getLogger(MovieCatalogController.class);

    @Autowired
    private MovieCatalogService movieCatalogService;

    @Loggable(logArguments = true, logExecutionTime = true)
    @RequestMapping("/")
    public List<CatalogItem> getMovieCatalog() {
        return movieCatalogService.getCatalog();
    }

    @Loggable(logArguments = true, logExecutionTime = true)
    @RequestMapping("/{movieId}")
    public CatalogItem getMovieCatalog(@PathVariable String movieId) {
        return movieCatalogService.getCatalog(movieId);
    }
}

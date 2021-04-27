package edu.omur.msworld.moviecatalogservice.service;

import edu.omur.msworld.moviecatalogservice.model.CatalogItem;
import edu.omur.msworld.moviecatalogservice.model.Movie;
import edu.omur.msworld.moviecatalogservice.model.MovieList;
import edu.omur.msworld.moviecatalogservice.model.MovieRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieCatalogServiceImpl implements MovieCatalogService {
    private static final String MOVIES_SERVICE_URL = "http://movie-info-service/movies/";
    //private static final String MOVIES_SERVICE_URL = "http://localhost:8081/movies/";

    private static final String RATINGS_SERVICE_URL = "http://movie-rating-service/ratings/";
    //private static final String RATINGS_SERVICE_URL = "http://localhost:8082/ratings/";

    @Autowired
    private RestTemplate restTemplate;

    public List<CatalogItem> getCatalog() {
        List<CatalogItem> catalogItemList = new ArrayList<>();
        try {
            MovieList movieList = restTemplate.getForObject(MOVIES_SERVICE_URL, MovieList.class);
            for (Movie movie : movieList.getList()) {
                MovieRating movieRating = restTemplate.getForObject(RATINGS_SERVICE_URL + movie.getId(), MovieRating.class);
                CatalogItem catalogItem = new CatalogItem(movie.getId()
                        , movie.getName()
                        , movie.getYear()
                        , movieRating.getRating()
                        , movieRating.getReviewerCount());
                catalogItemList.add(catalogItem);
            }
        } catch (Exception ex) {
            catalogItemList.add(new CatalogItem("static-data-id1", "static-data-name1", 20141, 20.141F, 19051));
            catalogItemList.add(new CatalogItem("static-data-id2", "static-data-name2", 20142, 20.142F, 19052));
            ex.printStackTrace();
        }
        return catalogItemList;
    }

    @Override
    public CatalogItem getCatalog(String movieId) {
        CatalogItem catalogItem = null;
        try {
            Movie movie = restTemplate.getForObject(MOVIES_SERVICE_URL + movieId, Movie.class);
            MovieRating movieRating = restTemplate.getForObject(RATINGS_SERVICE_URL + movie.getId(), MovieRating.class);
            catalogItem = new CatalogItem(movie.getId()
                    , movie.getName()
                    , movie.getYear()
                    , movieRating.getRating()
                    , movieRating.getReviewerCount());
        } catch (Exception ex) {
            //catalogItem = new CatalogItem("static-data-id", "static-data-name", 20142, 20.142F, 19052);
            ex.printStackTrace();
        }
        return catalogItem;
    }
}

package edu.omur.msworld.moviecatalogservice.service;

import edu.omur.msworld.moviecatalogservice.model.CatalogItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieCatalogService {
    List<CatalogItem> getCatalog();

    CatalogItem getCatalog(String movieId);
}

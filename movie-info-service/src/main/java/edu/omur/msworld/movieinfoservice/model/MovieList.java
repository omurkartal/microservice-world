package edu.omur.msworld.movieinfoservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieList {
    private List<Movie> list;

    public MovieList(List<Movie> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        if (this != null) {
            StringBuffer sb = new StringBuffer();
            for (Movie movie : this.list) {
                sb.append(String.format("[%s]", movie.toString()));
            }
            return sb.toString();
        }
        return null;
    }
}

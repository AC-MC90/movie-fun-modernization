package org.superbiz.moviefun.moviesapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestOperations;

import java.util.List;

public class MoviesClient {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String moviesUrl;
    private final RestOperations restOperations;

    public MoviesClient(String moviesUrl, RestOperations restOperations) {
        this.moviesUrl = moviesUrl;
        this.restOperations = restOperations;
    }

    public MovieInfo find(Long id) {
        return restOperations.getForObject(moviesUrl+"/{id}",MovieInfo.class,id);
    }

    public void addMovie(MovieInfo movie) {
        restOperations.postForObject(moviesUrl,movie, Void.class);
    }

    public void updateMovie(MovieInfo movie) {
        restOperations.put(moviesUrl, movie);
    }

    public void deleteMovie(MovieInfo movie) {
        restOperations.delete(moviesUrl+"/movie",movie);
    }

    public void deleteMovieId(long id) {
        restOperations.delete(moviesUrl+"/{id}",id);
    }

    public List<MovieInfo> getMovies() {
        return restOperations.getForObject(moviesUrl,List.class);
    }

    public List<MovieInfo> findAll(int firstResult, int maxResults) {
        return restOperations.getForObject(moviesUrl+"/find?firstResult={firstResult}&maxResults={maxResults}",List.class, firstResult,maxResults);
    }

    public int countAll() {
        return restOperations.getForObject(moviesUrl+"/countAll",Integer.class);
    }

    public int count(String field, String searchTerm) {
        return restOperations.getForObject(moviesUrl+"/count?field={field}&searchTerm={searchTerm}",Integer.class, field, searchTerm);
    }

    public List<MovieInfo> findRange(String field, String searchTerm, int firstResult, int maxResults) {
        return restOperations.getForObject(moviesUrl + "/findRange?field={field}&searchTerm={searchTerm}&firstResult={firstResult}&maxResults={maxResults}", List.class, field, searchTerm, firstResult, maxResults);
    }

    public void clean() {
        restOperations.delete(moviesUrl, Void.class);
    }

}

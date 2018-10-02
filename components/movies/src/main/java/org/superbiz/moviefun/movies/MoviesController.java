package org.superbiz.moviefun.movies;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    private final MoviesRepository moviesRepository;

    public MoviesController(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @GetMapping("/{id}")
    public Movie find(Long id) {
        return moviesRepository.find(id);
    }

    @PostMapping
    public void addMovie(Movie movie) {
        moviesRepository.addMovie(movie);
    }

    @PutMapping
    public void updateMovie(Movie movie) {
        moviesRepository.updateMovie(movie);
    }

    @DeleteMapping("/movie")
    public void deleteMovie(Movie movie) {
        moviesRepository.deleteMovie(movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovieId(long id) {
        moviesRepository.deleteMovieId(id);
    }

    @GetMapping
    public List<Movie> getMovies() {
        return moviesRepository.getMovies();
    }

    @GetMapping("/find")
    public List<Movie> findAll(@RequestParam("firstResult") int firstResult, @RequestParam("maxResults") int maxResults) {
        return moviesRepository.findAll(firstResult, maxResults);
    }

    @GetMapping("/countAll")
    public int countAll() {
        return moviesRepository.countAll();
    }

    @GetMapping("/count")
    public int count(@RequestParam("field") String field, @RequestParam("searchTerm") String searchTerm) {
        return moviesRepository.count(field, searchTerm);
    }

    @GetMapping("/findRange")
    public List<Movie> findRange(@RequestParam("field") String field, @RequestParam("searchTerm") String searchTerm, @RequestParam("firstResult") int firstResult, @RequestParam("maxResults") int maxResults) {
        return moviesRepository.findRange(field, searchTerm, firstResult, maxResults);
    }

    @DeleteMapping
    public void clean() {
        moviesRepository.clean();
    }
}

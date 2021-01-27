package netex.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import netex.model.Movie;
import netex.model.MovieSearch;
import netex.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @PostMapping("/addMovie")
    public Movie addMovie(@RequestBody Movie movie) {
        return service.saveMovie(movie);
    }

    @PostMapping("/addMovies")
    public List<Movie> addMovie(@RequestBody List<Movie> movie) {
        return service.saveMovie(movie);
    }

    @GetMapping("/movies")
    public List<Movie> findAllMovies() {
        return service.getMovies();
    }

    @GetMapping("/movieById/{id}")
    public Movie findMoviesById(@PathVariable int id) {
        return service.getMoviesById(id);
    }

    @DeleteMapping("/deleteMovie/{id}")
    public void deleteMovie(@PathVariable int id) {
        service.deleteMovie(id);
    }

    @DeleteMapping("/deleteAllMovies")
    public void deleteAllMovies() {
        service.deleteAllMovie();
    }

    @PutMapping("/putMovie")
    public Movie updateMovie(@RequestBody Movie movie) {
        return service.updateMovie(movie);
    }
}


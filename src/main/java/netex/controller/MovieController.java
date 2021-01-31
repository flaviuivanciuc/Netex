package netex.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import netex.model.Movie;
import netex.model.MovieSearch;
import netex.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping(path = "/movies")
public class MovieController {

        private static final String POSTS_API_URL_S = "http://www.omdbapi.com/?s=Fellowship&apikey=7b8f241f&page=2";
    private static final String POSTS_API_URL = "http://www.omdbapi.com/?apikey=7b8f241f&t=The_Lord_of_the_Rings:_The_Fellowship_of_the_Ring";

    @Autowired
    private MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping("/getMovies")
    public List<Movie> getMoviesSearch() throws IOException, InterruptedException {
        List<Movie> movies = getMovieListRequest();
        return service.saveMovies(movies);
    }

    private List<Movie> getMovieListRequest() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(POSTS_API_URL_S))
                .build();
        ObjectMapper mapper = new ObjectMapper();
        MovieSearch movieSearch = mapper.readValue(client.send(request, HttpResponse.BodyHandlers.ofString()).body(), new TypeReference<MovieSearch>() {
        });
        List<Movie> movies = movieSearch.getMovieList();
        return movies;
    }

    @GetMapping("/getMovie")
    public Movie getMovie() throws IOException, InterruptedException {
        return service.saveMovie(get(POSTS_API_URL));
    }

    private Movie get(String POSTS_API_URL) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(POSTS_API_URL))
                .build();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(client.send(request, HttpResponse.BodyHandlers.ofString()).body(), new TypeReference<Movie>() {
        });
    }

    @PostMapping("/addMovie")
    public Movie addMovie(@RequestBody Movie movie) {
        return service.saveMovie(movie);
    }

    @PostMapping("/addMovies")
    public List<Movie> addMovie(@RequestBody List<Movie> movie) {
        return service.saveMovies(movie);
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


package netex.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import netex.model.Movie;
import netex.model.MovieSearch;
import netex.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class MovieService {

    private static final String POSTS_API_URL = "http://www.omdbapi.com/?s=Batman&apikey=7b8f241f";
    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MovieRepository repository;

    public MovieService(MovieRepository repository) throws IOException, InterruptedException {
        this.repository = repository;
    }

    public HttpResponse<String> response() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(POSTS_API_URL))
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    //POST
    public Movie saveMovie(Movie movie) {
        return repository.save(movie);
    }

    //POST
    public List<Movie> saveMovie(List<Movie> movie) {
        return repository.saveAll(movie);
    }

    //GET
    public List<Movie> getMovies() {
        return repository.findAll();
    }

    //GET
    public Movie getMoviesById(int id) {
        return repository.findById(id).orElse(null);
    }

    //DELETE
    public void deleteMovie(int id) {
        repository.deleteById(id);
    }

    //DELETE
    public void deleteAllMovie() {
        repository.deleteAll();
    }

    //PUT
    public Movie updateMovie(Movie movie) {
        Movie existingMovie = repository.findById(movie.getId()).orElse(null);
        existingMovie.setTitle(movie.getTitle());
        existingMovie.setReleased(movie.getReleased());
        existingMovie.setRated(movie.getRated());
        existingMovie.setImdbRating(movie.getImdbRating());
        existingMovie.setGenre(movie.getGenre());
        existingMovie.setWriter(movie.getWriter());
        existingMovie.setActors(movie.getActors());
        existingMovie.setDirector(movie.getDirector());
        existingMovie.setPlot(movie.getPlot());
        return repository.save(existingMovie);
    }

}

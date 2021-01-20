package netex.service;

import netex.model.Movie;
import netex.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

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

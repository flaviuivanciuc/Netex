package netex.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import netex.model.Movie;
import netex.model.MovieSearchObject;
import netex.repositories.MovieRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Service
public class MovieService {

    private MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
//        for (int i = 1; i <= 10; i++) {
//            final String BASE_URL = "http://www.omdbapi.com";
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
//
//            RestTemplate restTemplate = new RestTemplate();
//            ResponseEntity<MovieSearchObject> responseEntity = restTemplate.exchange(BASE_URL + "/?apikey=7b8f241f&" +
//                    "type=movie&" + "s=Batman&" + "page=" + i, HttpMethod.GET, httpEntity, MovieSearchObject.class);
//            MovieSearchObject movieSearchObject = responseEntity.getBody();
//            assert movieSearchObject != null;
//
//            for (int j = 0; j < 10; j++) {
//                String movieName = movieSearchObject.getMovieList().get(j).getTitle();
//                ResponseEntity<Movie> entity = restTemplate.exchange(BASE_URL + "/?apikey=7b8f241f&" +
//                        "type=movie&" + "t=" + movieName, HttpMethod.GET, httpEntity, Movie.class);
//                Movie movie = entity.getBody();
//                assert movie != null;
//                repository.save(movie);
//            }
//        }
    }

    //POST
    public Movie saveMovie(Movie movie) {
        return repository.save(movie);
    }

    //POST
    public List<Movie> saveMovies(List<Movie> movies) {
        return repository.saveAll(movies);
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

    public JPAQueryFactory queryFactory() {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("movies");
        EntityManager em = emf.createEntityManager();
        return new JPAQueryFactory(em);
    }

}

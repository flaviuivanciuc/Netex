package netex.controller;

import com.querydsl.jpa.impl.JPAQueryFactory;
import netex.model.Movie;
import netex.model.QMovie;
import netex.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/movies")
public class MovieController {

    private JPAQueryFactory factory;
    private MovieService service;

    @Autowired
    public MovieController(MovieService service, JPAQueryFactory factory) {
        this.factory = factory;
        this.service = service;
//        for (int i = 0; i < 1; i++) {
//            final String BASE_URL = "http://www.omdbapi.com";
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
//
//            RestTemplate restTemplate = new RestTemplate();
//            ResponseEntity<Movie> responseEntity = restTemplate.exchange(BASE_URL + "/?apikey=7b8f241f&" +
//                    "i=tt" + (int) (Math.random() * (1000000) + 1000000) +
//                    "&type=movie", HttpMethod.GET, httpEntity, Movie.class);
//
//            Movie movie = responseEntity.getBody();
//            assert movie != null;
//            if (movie.getTitle() == null) {
//                continue;
//            }
//            service.saveMovie(movie);
//        }
    }

    @GetMapping("/getMovieByTitleAndYear")
    public Movie getMovieByTitleAndYear() {
        return service.getMovieByTitleAndYear();
    }

    @GetMapping("/getMovieByTitle")
    public Movie getMovieByTitle() {
        return service.getMovieByTitle();
    }

    @GetMapping("/get-all-{genre}-movies")
    public List<Movie> getMoviesByGenre(@PathVariable String genre) {
        return factory.selectFrom(QMovie.movie).where(QMovie.movie.genre.eq(genre)).fetch();
    }

    @GetMapping("/search-for-{title}")
    public List<Movie> getMovieByTitle(@PathVariable String title) {
        List<Movie> movies = factory.selectFrom(QMovie.movie).where(QMovie.movie.title.eq(title)).fetch();
        return movies;
    }

    @GetMapping("/order-by-title")
    public List<Movie> orderMoviesAlphabetically() {
        List<Movie> movies = factory.selectFrom(QMovie.movie).orderBy(QMovie.movie.title.asc()).fetch();
        return movies;
    }

    @GetMapping("/search-movies-between-{yearX}-{yearY}")
    public List<Movie> sortByYear(@PathVariable int yearX, @PathVariable int yearY) {
        List<Movie> movieList = new ArrayList<>();
        List<Movie> movies = factory.selectFrom(QMovie.movie).fetch();
        for (int i = 0; i < movies.size(); i++) {
            int movieYear = Integer.parseInt(movies.get(i).getYear());
            if (yearX <= movieYear && movieYear <= yearY)
                movieList.add(movies.get(i));
        }
        return movieList;
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


package netex.controller;

import com.querydsl.jpa.impl.JPAQueryFactory;
import netex.model.Movie;
import netex.model.QMovie;
import netex.service.MovieService;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/movies")
public class MovieController {

    @Autowired
    private MovieService service;

//        private static final String POSTS_API_URL_S = "http://www.omdbapi.com/?s=Fellowship&apikey=7b8f241f&page=2";
//    private static final String POSTS_API_URL = "http://www.omdbapi.com/?apikey=7b8f241f&t=The_Lord_of_the_Rings:_The_Fellowship_of_the_Ring";

    String urlString = "http://localhost:8983/solr/moviedatabase";
    HttpSolrClient solr = new HttpSolrClient.Builder(urlString).build();
    List<Movie> movies = queryFactory().selectFrom(QMovie.movie).fetch();
//    solr.addBean(movies);
//    solr.commit("indexing");

//    @GetMapping("/getMovies")
//    public List<Movie> getMoviesSearch() throws IOException, InterruptedException {
//        List<Movie> movies = getMovieListRequest();
//        return service.saveMovies(movies);
//    }
//
//    private List<Movie> getMovieListRequest() throws IOException, InterruptedException {
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .GET()
//                .header("accept", "application/json")
//                .uri(URI.create(POSTS_API_URL_S))
//                .build();
//        ObjectMapper mapper = new ObjectMapper();
//        MovieSearch movieSearch = mapper.readValue(client.send(request, HttpResponse.BodyHandlers.ofString()).body(), new TypeReference<MovieSearch>() {
//        });
//        List<Movie> movies = movieSearch.getMovieList();
//        return movies;
//    }
//
//    @GetMapping("/getMovie")
//    public Movie getMovie() throws IOException, InterruptedException {
//        return service.saveMovie(get(POSTS_API_URL));
//    }
//
//    private Movie get(String POSTS_API_URL) throws IOException, InterruptedException {
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .GET()
//                .header("accept", "application/json")
//                .uri(URI.create(POSTS_API_URL))
//                .build();
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.readValue(client.send(request, HttpResponse.BodyHandlers.ofString()).body(), new TypeReference<Movie>() {
//        });
//    }

    public MovieController() throws IOException, SolrServerException {
//        String name = "Flaviu is doing stuff here!";
//        System.out.println(name);
//        try {
//            for (int i = 0; i < 1; i++) {
//                final String BASE_URL = "http://www.omdbapi.com";
//
//                HttpHeaders headers = new HttpHeaders();
//                headers.setContentType(MediaType.APPLICATION_JSON);
//
//                HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
//
//                RestTemplate restTemplate = new RestTemplate();
//                ResponseEntity<Movie> responseEntity = restTemplate.exchange(BASE_URL + "/?apikey=7b8f241f&" +
//                        "i=tt" + (int) (Math.random() * (1000000) + 1000000) +
//                        "&type=movie", HttpMethod.GET, httpEntity, Movie.class);
//
//                Movie movie = responseEntity.getBody();
//                assert movie != null;
//                if (movie.getTitle() == null) {
//                    continue;
//                }
//                service.saveMovie(movie);
//            }
//        } catch (RestClientException e) {
//            e.printStackTrace();
//        }
    }

    public MovieController(MovieService service) throws IOException, SolrServerException {
        this.service = service;

    }

    @GetMapping("/order-by-title")
    public List<Movie> orderMoviesAlphabetically() {
        List<Movie> movies = queryFactory().selectFrom(QMovie.movie).orderBy(QMovie.movie.title.asc()).fetch();
        return movies;
    }

    @GetMapping("/search-movies-between-{yearX}-{yearY}")
    public List<Movie> sortByYear(@PathVariable int yearX, @PathVariable int yearY) {
        List<Movie> movieList = new ArrayList<>();
        List<Movie> movies = queryFactory().selectFrom(QMovie.movie).fetch();
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

    public JPAQueryFactory queryFactory() {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("movies");
        EntityManager em = emf.createEntityManager();
        return new JPAQueryFactory(em);
    }
}


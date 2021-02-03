package netex;

import netex.model.Movie;
import netex.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

@SpringBootApplication
@PropertySource({"/templates/application.properties"})
public class Main /*implements Serializable, CommandLineRunner*/ {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    //Here is a method for retrieving 100 movies from the OMDB,
    // by generating with the Math.random function a random ID that's fetching a movie
    // from he OMDB.

//    @Autowired
//    MovieService service;
//
//    @Override
//    public void run(String... args) {
//
//        for (int i = 0; i < 100; i++) {
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
////            service.saveMovie(movie);
//        }
//
//        //This is another method, with two while loops for retrieving 100 movies from the OMDB and
//        // unmarshalling them into Movie.class objects and store them into the MariaDB
//
////        for (int i = 0; i < 10; i++) {
////            final String BASE_URL = "http://www.omdbapi.com";
////
////            HttpHeaders headers = new HttpHeaders();
////            headers.setContentType(MediaType.APPLICATION_JSON);
////
////            HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
////
////            RestTemplate restTemplate = new RestTemplate();
////            ResponseEntity<MovieSearchObject> responseEntity = restTemplate.exchange(BASE_URL + "/?apikey=7b8f241f&" +
////                    "type=movie&" + "s=Batman&" + "page=" + i, HttpMethod.GET, httpEntity, MovieSearchObject.class);
////            MovieSearchObject movieSearchObject = responseEntity.getBody();
////            assert movieSearchObject != null;
////
////            for (int j = 0; j < 10; j++) {
////                String movieName = movieSearchObject.getMovieList().get(j).getTitle();
////                ResponseEntity<Movie> entity = restTemplate.exchange(BASE_URL + "/?apikey=7b8f241f&" +
////                        "type=movie&" + "t=" + movieName, HttpMethod.GET, httpEntity, Movie.class);
////                Movie movie = entity.getBody();
////                service.saveMovie(movie);
////            }
////        }
//    }
}

package netex;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import netex.model.MovieSearchObject;
import netex.service.MovieService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class JSON {

    private static final String POSTS_API_URL = "http://www.omdbapi.com/?s=Batman&apikey=7b8f241f";

    public static void main(String[] args) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(POSTS_API_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

//         parse JSON into objects
//         configuring the ObjectMapper to allow stuff
        JsonFactory factory = new JsonFactory();
        factory.enable(JsonParser.Feature.IGNORE_UNDEFINED);

        ObjectMapper mapper = new ObjectMapper(factory);
        MovieSearchObject movieSearchObject = mapper.readValue(response.body(), new TypeReference<>() {
        });
        System.out.println(movieSearchObject.getMovieList().get(2));

        MovieService movieService = null;
        movieSearchObject.getMovieList().forEach(movie -> movieService.saveMovie(movie));
//        movieService.saveMovie(movieSearch.getMovieList());
    }
}

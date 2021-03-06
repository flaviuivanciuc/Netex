package netex.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.solr.client.solrj.beans.Field;

import javax.persistence.*;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonProperty("Title")
    @Field
    private String title;
    @JsonProperty("Year")
    @Field
    private String year;
    @JsonProperty("Released")
    @Field
    private String released;
    @JsonProperty("Plot")
    @Field
    private String plot;
    @JsonProperty("Rated")
    @Field
    private String rated;
    @JsonProperty("Genre")
    @Field
    private String genre;
    @JsonProperty("imdbRating")
    @Field
    private String imdbRating;
    @JsonProperty("Actors")
    @Field
    private String actors;
    @JsonProperty("Writer")
    @Field
    private String writer;
    @JsonProperty("Director")
    @Field
    private String director;

    public Movie() {
    }

    public Movie(Integer id, String title, String year, String released, String plot, String rated, String genre, String imdbRating, String actors, String writer, String director) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.released = released;
        this.plot = plot;
        this.rated = rated;
        this.genre = genre;
        this.imdbRating = imdbRating;
        this.actors = actors;
        this.writer = writer;
        this.director = director;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", released='" + released + '\'' +
                ", plot='" + plot + '\'' +
                ", rated='" + rated + '\'' +
                ", genre='" + genre + '\'' +
                ", imdbRating=" + imdbRating +
                ", actors='" + actors + '\'' +
                ", writer='" + writer + '\'' +
                ", director='" + director + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && Objects.equals(title, movie.title) && Objects.equals(year, movie.year) && Objects.equals(released, movie.released) && Objects.equals(plot, movie.plot) && Objects.equals(rated, movie.rated) && Objects.equals(genre, movie.genre) && Objects.equals(imdbRating, movie.imdbRating) && Objects.equals(actors, movie.actors) && Objects.equals(writer, movie.writer) && Objects.equals(director, movie.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, year, released, plot, rated, genre, imdbRating, actors, writer, director);
    }
}

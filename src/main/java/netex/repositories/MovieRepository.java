package netex.repositories;

import netex.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

//    List<Movie> findByTitle(String title);

}
package netex.repositories;

import netex.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    public Movie findByTitle(String Title);
    public Movie findByTitleAndYear(String Title, String Year);

}
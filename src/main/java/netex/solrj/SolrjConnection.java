package netex.solrj;

import com.querydsl.jpa.impl.JPAQueryFactory;
import netex.model.Movie;
import netex.model.QMovie;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;

@Component
public class SolrjConnection {

    public SolrjConnection() {
//        indexingByUsingJavaObjectBinding();
    }

    public void indexingByUsingJavaObjectBinding() {
        try {
            List<Movie> movies = queryFactory().selectFrom(QMovie.movie).fetch();
            System.out.printf("Indexing %d articles...\n", movies.size());
            // send articles to Solr
            SOLR_CLIENT.addBeans(movies);

            // explicit commit pending documents for indexing
            SOLR_CLIENT.commit();

            System.out.printf("%d articles indexed.\n", movies.size());
        } catch (SolrServerException | IOException e) {
            System.err.printf("\nFailed to indexing articles: %s", e.getMessage());
        }
    }

    private static final String SOLR_CORE_URL = "http://localhost:8983/solr/moviedatabase";
    private static final SolrClient SOLR_CLIENT = getSolrClient();

    private static SolrClient getSolrClient() {
        return new HttpSolrClient.Builder(SOLR_CORE_URL).withConnectionTimeout(5000).withSocketTimeout(3000).build();
    }

    @Bean
    public void query() {
        // constructs a SolrQuery instance
        final SolrQuery solrQuery = new SolrQuery("year:2011");
        solrQuery.addField("id");
//        solrQuery.addField("actors");
//        solrQuery.addField("plot");
        solrQuery.setSort("id", SolrQuery.ORDER.asc);
        solrQuery.setRows(10);

        // send search request and gets the response
        QueryResponse queryResponse = null;
        try {
            queryResponse = SOLR_CLIENT.query(solrQuery);
        } catch (SolrServerException | IOException e) {
            System.err.printf("Failed to search articles: %s", e.getMessage());
        }

        // converts to domain object and prints to standard output
        if (queryResponse != null) {
            List<Movie> movies = queryResponse.getBeans(Movie.class);
            for (Movie movie : movies) {
                System.out.println(movie.toString());
            }
        }
    }

    public JPAQueryFactory queryFactory() {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("movies");
        EntityManager em = emf.createEntityManager();
        return new JPAQueryFactory(em);
    }
}


package netex.solrj;

import com.querydsl.jpa.impl.JPAQueryFactory;
import netex.model.Movie;
import netex.model.QMovie;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.MapSolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SolrjConnection {

    @Autowired
    private JPAQueryFactory factory;

    private static final String SOLR_CORE_URL = "http://localhost:8983/solr/moviedatabase";
    private static final SolrClient SOLR_CLIENT = getSolrClient();

    private static SolrClient getSolrClient() {
        return new HttpSolrClient.Builder(SOLR_CORE_URL).withConnectionTimeout(5000).withSocketTimeout(3000).build();
    }

    public SolrjConnection() {
//        indexingByUsingJavaObjectBinding();
    }

    public void indexingByUsingJavaObjectBinding() {
        try {
            List<Movie> movies = factory.selectFrom(QMovie.movie).fetch();
            System.out.printf("Indexing %d movies...\n", movies.size());
            // send articles to Solr
            SOLR_CLIENT.addBeans(movies);

            // explicit commit pending documents for indexing
            SOLR_CLIENT.commit();

            System.out.printf("%d movies indexed.\n", movies.size());
        } catch (SolrServerException | IOException e) {
            System.err.printf("\nFailed to indexing movies: %s", e.getMessage());
        }
    }

    @Bean
    public void query() {
        // constructs a MapSolrParams instance
        final Map<String, String> queryParamMap = new HashMap<String, String>();
        queryParamMap.put("q", "year:2011");
        queryParamMap.put("fl", "id, title, actors");
        queryParamMap.put("sort", "id asc");
        MapSolrParams queryParams = new MapSolrParams(queryParamMap);

        // send search request and gets the response
        QueryResponse response = null;
        try {
            response = SOLR_CLIENT.query(queryParams);
        } catch (SolrServerException | IOException e) {
            System.err.printf("Failed to search movies: %s", e.getMessage());
        }

        // print results to stdout
        if (response != null) {
            System.out.println(response.getResults());
        }
    }
}


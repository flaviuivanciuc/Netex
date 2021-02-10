package netex;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
public class AppConfiguration {

    @Bean
    public JPAQueryFactory queryFactory() {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("movies");
        EntityManager em = emf.createEntityManager();
        return new JPAQueryFactory(em);
    }
}

package netex.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMovie is a Querydsl query type for Movie
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMovie extends EntityPathBase<Movie> {

    private static final long serialVersionUID = -2035755475L;

    public static final QMovie movie = new QMovie("movie");

    public final StringPath actors = createString("actors");

    public final StringPath director = createString("director");

    public final StringPath genre = createString("genre");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath imdbRating = createString("imdbRating");

    public final StringPath plot = createString("plot");

    public final StringPath rated = createString("rated");

    public final StringPath released = createString("released");

    public final StringPath title = createString("title");

    public final StringPath writer = createString("writer");

    public final StringPath year = createString("year");

    public QMovie(String variable) {
        super(Movie.class, forVariable(variable));
    }

    public QMovie(Path<? extends Movie> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMovie(PathMetadata metadata) {
        super(Movie.class, metadata);
    }

}


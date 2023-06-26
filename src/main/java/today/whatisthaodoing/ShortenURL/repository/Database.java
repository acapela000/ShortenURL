package today.whatisthaodoing.ShortenURL.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import today.whatisthaodoing.ShortenURL.model.ShortenURL;

@Repository
public interface Database extends MongoRepository<ShortenURL, String> {

    // class is implementation of interface




}

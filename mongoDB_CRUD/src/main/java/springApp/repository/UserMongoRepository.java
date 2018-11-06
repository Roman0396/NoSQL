package springApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import springApp.model.User;

import java.util.List;

@Repository
public interface UserMongoRepository extends MongoRepository<User, Integer> {

    List<User> findByName(String name);

    @Query("{ 'role' : { $regex: ?0 } }")
    List<User> findUsersByRegexpRole(String regex);
}

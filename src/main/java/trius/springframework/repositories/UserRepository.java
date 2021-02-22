package trius.springframework.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import trius.springframework.security.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}

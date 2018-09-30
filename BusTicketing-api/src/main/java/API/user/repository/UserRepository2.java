package API.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;


import API.user.model.Users;

public interface UserRepository2  extends MongoRepository<Users, String > {

}

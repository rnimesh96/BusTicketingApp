package API.journey.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import API.journey.model.Journey;
import API.user.model.Users;

public interface JourneyRepository2 extends CrudRepository<Journey, String>{
	
	public List<Journey> findJourneyByUserIdAndStatus(String userId, String status);
	
	public List<Journey> findJourneyByUserId(String userId);

	
	

}

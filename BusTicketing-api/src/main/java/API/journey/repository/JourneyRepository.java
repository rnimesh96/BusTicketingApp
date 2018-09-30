package API.journey.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import API.journey.model.Journey;



public interface JourneyRepository extends MongoRepository<Journey, String >{
	
}



package API.user.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import API.user.model.Users;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class UserService {
	private static final AtomicInteger count = new AtomicInteger(0); 
	
	@Autowired
	private API.user.repository.UserRepository URepository;
	
	@Autowired
	private API.user.repository.UserRepository2 URepository2;
	
	@Autowired
    MongoOperations mongoOperation;
	
	public Users loginUser(String Username, String Password) {
		Users user = this.findUser(Username);
		if(user != null && user.getPassword().equals(Password)) {
			return user;
		}
		
		return null;
	}

	private Users findUser(String username) {
		return URepository2.findOne(username);
	}

	public List<Users> getAllUsers() {
		List<Users> userList = null;
		userList = URepository2.findAll();
		return userList;
	}
	
	
	public List<Users> getUsers() {
		List<Users> userList = null;
		userList = URepository.findUsersBypasswordAndUserName("123","su");
		return userList;
	}

	public void addUser(Users userIns) {
		userIns.setUserID( String.valueOf( count.incrementAndGet() ) ) ;
		URepository2.save(userIns);  
	}

	

}

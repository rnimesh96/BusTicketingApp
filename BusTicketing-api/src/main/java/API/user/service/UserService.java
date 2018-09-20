package API.user.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import API.user.model.Users;

@Service
public class UserService {
	private static final AtomicInteger count = new AtomicInteger(0); 
	
	@Autowired
	private API.user.repository.UserRepository URepository;
	
	public Users loginUser(String Username, String Password) {
		Users user = this.findUser(Username);
		if(user != null && user.getPassword().equals(Password)) {
			return user;
		}
		
		return null;
	}

	private Users findUser(String username) {
		return URepository.findOne(username);
	}

	public List<Users> getAllUsers() {
		List<Users> userList = null;
		userList = URepository.findAll();
		return userList;
	}

	public void addUser(Users userIns) {
		userIns.setUserID( String.valueOf( count.incrementAndGet() ) ) ;
		URepository.save(userIns);  
	}

	

}

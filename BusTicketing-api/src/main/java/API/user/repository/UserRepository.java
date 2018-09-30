package API.user.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import API.user.model.Users;




public interface UserRepository extends Repository<Users, String >{

	public List<Users> findUsersBypasswordAndUserName(String password, String userName);

	

	
	
}

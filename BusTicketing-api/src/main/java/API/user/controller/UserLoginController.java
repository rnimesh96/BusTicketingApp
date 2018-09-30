package API.user.controller;



import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import API.user.model.Users;







@RestController
@RequestMapping
public class UserLoginController {
	
	@Autowired
	private API.user.service.UserService UService;
	
	
	@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"})
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginForm(){
		return "login";
	}
	
	@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"})
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public void AddUser(@Validated @RequestBody Users userIns) {
		
		try {
			 UService.addUser(userIns);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<Users> ViewAllUsers() {
		List<Users> userlist = null;
		try {
			userlist = UService.getAllUsers();  
		} catch(Exception e) {
			e.printStackTrace();
		}
		return userlist;
	}
	
	
	@RequestMapping(value = "/userone", method = RequestMethod.GET)
	public List<Users> ViewUsers() {
		List<Users> userlist = null;
		try {
			userlist = UService.getUsers();  
		} catch(Exception e) {
			e.printStackTrace();
		}
		return userlist;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String verifyLogin(@RequestParam String username,@RequestParam String password,
			HttpSession session, Model model){
		Users user = UService.loginUser(username, password);
		if(user == null) {
			model.addAttribute("loginError","Error login in. please try again.");
			return "login";
		}
		session.setAttribute("loggedInUser",user);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session){
		session.removeAttribute("loggedInUser");
		return "login";
	}
}

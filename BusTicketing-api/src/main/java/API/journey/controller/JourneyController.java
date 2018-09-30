package API.journey.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import API.journey.model.Journey;
import API.user.model.Users;

@RestController
@RequestMapping
public class JourneyController {

	@Autowired
	private API.journey.service.JourneyService JService;
	
	@RequestMapping(value = "/journeyhistory/{id}", method = RequestMethod.GET)
	public List<Journey> showJourneyHistory(@PathVariable String id){
		List<Journey> JourneyList = null;
		try {
			JourneyList = JService.getjourneyHistory(id);  
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return JourneyList;
	}
	
	@RequestMapping(value = "/journeyongoing/{id}", method = RequestMethod.GET)
	public Journey showJourney(@PathVariable String id){
		Journey Journey = null;
		try {
			Journey = JService.getjourney(id);  
		} catch(Exception e) {
			e.printStackTrace();
		}
		return Journey;
	}
	
	
	
	@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"})
	
	@RequestMapping(value = "/journey", method = RequestMethod.POST)
	public void AddUser(@Validated @RequestBody Journey journeyIns) {
		
		try {
			 JService.addJourney(journeyIns);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"})
	@RequestMapping(value = "/startbus", method = RequestMethod.POST)
	public void StartJourney(@Validated @RequestBody Journey journeyIns){
		try {
			 JService.StartJourney(journeyIns);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"})
	@RequestMapping(value = "/endbus", method = RequestMethod.PUT)
	public void EndJourney(@Validated @RequestBody Journey journeyIns){
		try {
			 JService.EndJourney(journeyIns);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	
}

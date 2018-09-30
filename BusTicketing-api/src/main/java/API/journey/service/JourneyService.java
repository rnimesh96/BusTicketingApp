package API.journey.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import API.journey.model.Journey;
import API.user.model.Users;

@Service
public class JourneyService {
	
	private static final AtomicInteger count = new AtomicInteger(0); 
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd"); 
	LocalDateTime now = LocalDateTime.now();  
	   
	@Autowired
	//private static final AtomicInteger count = new AtomicInteger(0);
	private API.journey.repository.JourneyRepository JRepository;
	private API.journey.repository.JourneyRepository2 JRepository2;

	/*public Journey loginUser(String Username, String Password) {
		Users user = this.findUser(Username);
		if(user != null && user.getPassword().equals(Password)) {
			return user;
		}
		
		return null;
	}*/ 

	private Journey findJourney(String date_) {
		return JRepository.findOne(date_);
	}
	
	public List<Journey> getAllJourneys() {;
		List<Journey> journeyList = null;
		journeyList = JRepository.findAll();
		return journeyList;
	}
	
	
	
	public void addJourney(Journey journeyIns) {
		journeyIns.setUserId( String.valueOf( count.incrementAndGet() ) ) ;
		JRepository.save(journeyIns);  
	}

	public void updateJourney(Journey journeyIns) {
		journeyIns.setUserId( String.valueOf( count.incrementAndGet() ) ) ;
		JRepository.save(journeyIns);  
	}

	public void StartJourney(Journey journeyIns) {
		journeyIns.setJourneyId( String.valueOf( count.incrementAndGet() ) ) ;
		journeyIns.setDate_(df.format(now));
		journeyIns.setStartTime(dtf.format(now));
		JRepository.save(journeyIns);
	}

	public void EndJourney(Journey journeyIns) {
	
		
		Journey journeyIns2 = this.getOneJourney(journeyIns,"Incomplete");
		
		journeyIns2.setEndTime(dtf.format(now));
		journeyIns2.setEndLocation(journeyIns.getEndLocation());
		journeyIns2.setDistance(journeyIns.getDistance());
		journeyIns2.setStatus(journeyIns.getStatus());
		
		JRepository.save(journeyIns2);
		
		
	}
	
	@SuppressWarnings("finally")
	public Journey getOneJourney(Journey JourneyIns,String status) {
		
		Journey instance = null;
		try {
			List<Journey> JourneyList = JRepository.findAll();
			for(Journey jI: JourneyList) {
				if(jI.getUserId().equals(JourneyIns.getUserId().toString()) && jI.getStatus().equals(status) ) {
					instance =  jI;
					break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			return instance;
		}
	}
	
	@SuppressWarnings("finally")
	public Journey getOneJourneyOngoing(String userID,String status) {
		
		Journey instance = null;
		try {
			List<Journey> JourneyList = JRepository.findAll();
			for(Journey jI: JourneyList) {
				if(jI.getUserId().equals(userID) && jI.getStatus().equals(status) ) {
					instance =  jI;
					break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			return instance;
		}
	}
	
	
	
	@SuppressWarnings({ "finally"})
	public List<Journey> getJourneys(String userID) {
		
		List<Journey> JourneyList,l2 = null;
		
		try {
			 JourneyList = JRepository.findAll();
			 l2 = JRepository.findAll();
			 l2.removeAll(JourneyList);
			 l2.clear();
			for(Journey jI: JourneyList) {
				if(jI.getUserId().equals(userID)  ) {
					l2.add(jI);
				}
			
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		
		} finally {
			return l2;
		}
	}

	public Journey getjourney(String UserId) {
		Journey  Journey = null;
		
		Journey = this.getOneJourneyOngoing(UserId,"Incomplete");
		return Journey;
	}

	public List<Journey> getjourneyHistory(String userId) {

		return getJourneys(userId);
	}
	
}

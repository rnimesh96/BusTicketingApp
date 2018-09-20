package API.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloContraller {
	
	@RequestMapping("/hello")
	public String SayHi() {
		return "Hi";
	}

}

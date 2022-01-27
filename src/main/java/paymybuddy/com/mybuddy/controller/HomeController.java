package paymybuddy.com.mybuddy.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/homepage")
	public String homePage() {
		Authentication authen =  SecurityContextHolder.getContext().getAuthentication();
		return "homepage";
	}
}

package paymybuddy.com.mybuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import paymybuddy.com.mybuddy.exception.UserDoesNotExist;
import paymybuddy.com.mybuddy.model.User;
import paymybuddy.com.mybuddy.service.UserService;

import java.security.Principal;

@Controller
public class ProfileController {
	private final UserService userService;

	public ProfileController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/profile")
	public String profile(Model model, Principal principal) {
		String userEmail = principal.getName();
		try {
			User user = userService.getUserByEmail(userEmail);
			model.addAttribute("user", user);
			return "profile";
		} catch (UserDoesNotExist e) {
			return "homepage";
		}
	}
}

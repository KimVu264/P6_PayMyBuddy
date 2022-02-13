package paymybuddy.com.mybuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import paymybuddy.com.mybuddy.exception.UserExisted;
import paymybuddy.com.mybuddy.model.User;
import paymybuddy.com.mybuddy.service.UserService;

@Controller
public class SignupController {

	private final UserService userService;

	public SignupController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signUpView(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signUp(@Validated User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "signup";
		}
		try {
			User createdUser = userService.createUser(user);
			return "redirect:/login";
		} catch (UserExisted e) {
			model.addAttribute("errorMsg", "User existed in the database");
		}
		return "signup";
	}

}

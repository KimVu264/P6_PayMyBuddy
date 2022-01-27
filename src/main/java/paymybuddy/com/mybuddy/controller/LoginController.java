package paymybuddy.com.mybuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import paymybuddy.com.mybuddy.model.User;
import paymybuddy.com.mybuddy.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	//Return page login
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPage(@RequestParam(value = "error", required = false) String error,
	                        @RequestParam(value = "logout", required = false) String logout,
	                        Model model) {
		String errorMessge = null;
		if(error != null) {
			errorMessge = "Username or Password is incorrect !!";
		}
		if(logout != null) {
			errorMessge = "You have been successfully logged out !!";
		}
		model.addAttribute("errorMessge", errorMessge);
		return "login";
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout=true";
	}

	//https://kipalog.com/posts/Lap-trinh-Spring-voi-ung-dung-MyContact

	@GetMapping("/signup")
	public String signupGet(Model model) {
		 /* model sẽ giúp truyền data lên html*/
		User user = new User();
		/* attributeName sẽ dùng trên html*/
		model.addAttribute("user",user);
		return "signup";
	}

	@PostMapping("/signup")
	public String signupPost(@Validated User user) {
     /*validated nó sẽ tự map dũ liệu trên form cho mình*/

		userService.createUser(user);
		return "redirect:/login";
	}
}

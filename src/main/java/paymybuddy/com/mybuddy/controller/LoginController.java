package paymybuddy.com.mybuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import paymybuddy.com.mybuddy.model.User;
import paymybuddy.com.mybuddy.service.UserService;

import java.util.Date;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	// Return page login
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	/*
	 Return page signup
	@GetMapping("/signup")
	public String signupGet() {
		return "signup";
	}
	@PostMapping("/signup_post")
	public String signupPost(@RequestParam("userName") String userName,
					         @RequestParam("email") String email,
	                         @RequestParam("password") String password,
						     @RequestParam("address") String address,
						     @RequestParam("tel") int tel,
						     @RequestParam("birthday") Date birthday) {

		User u = new User();
		u.setUserName(userName);
		u.setEmail(email);
		u.setPassword(password);
		u.setAddress(address);
		u.setTel(tel);
		u.setBirthday(birthday);
		userService.createUser(u);
		return "redirect:/login";
	}*/

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

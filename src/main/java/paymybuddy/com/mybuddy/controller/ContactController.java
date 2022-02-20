package paymybuddy.com.mybuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import paymybuddy.com.mybuddy.exception.UserAlreadyInYourContact;
import paymybuddy.com.mybuddy.exception.UserDoesNotExist;
import paymybuddy.com.mybuddy.model.User;
import paymybuddy.com.mybuddy.service.ContactService;
import paymybuddy.com.mybuddy.service.UserService;

import java.security.Principal;
import java.util.Set;

@Controller
public class ContactController {
	private final ContactService contactService;

	private final UserService userService;

	public ContactController(ContactService contactService, UserService userService) {
		this.contactService = contactService;
		this.userService = userService;
	}

	@GetMapping("/contact")
	public String transfer(Model model, Principal principal) {
		String userEmail = principal.getName();
		try {
			User user = userService.getUserByEmail(userEmail);
			Set<User> contact = user.getContacts();
			model.addAttribute("contacts", contact);
			return "contact";
		} catch (UserDoesNotExist e) {
			return "homepage";
		}
	}

	@GetMapping("/searchContact")
	public String searchContact(Model model, @RequestParam(value = "email") String email,
	                            Principal principal) throws UserDoesNotExist {
		try {
			User user = userService.getUserByEmail(email);
			if (email.equalsIgnoreCase(principal.getName())) {
				model.addAttribute("searchError", "User doesn't exist");
			} else {
				model.addAttribute("searchResult", user);
			}
		} catch (UserDoesNotExist e) {
			model.addAttribute("searchError", "User doesn't exist");
		}
		model.addAttribute("contacts", userService.getUserByEmail(principal.getName()).getContacts());
		return "contact";
	}

	@GetMapping("/addToContact")
	public String addToContact(Model model, @RequestParam(value = "email") String email,
	                           Principal principal) throws UserDoesNotExist {
		try {
			User fromUser = userService.getUserByEmail(principal.getName());
			User toUser = userService.getUserByEmail(email);
			contactService.addToContact(fromUser, toUser);
			model.addAttribute("addToContactMsgSuc", "User " + email + " have been added to your contact");
		} catch (UserDoesNotExist e) {
			model.addAttribute("addToContactMsgErr", "User " + email + " doesn't exist");
		} catch (UserAlreadyInYourContact userAlreadyInYourContact) {
			model.addAttribute("addToContactMsgErr", "User " + email + " already in your contact");
		}
		model.addAttribute("contacts", userService.getUserByEmail(principal.getName()).getContacts());
		return "contact";
	}

}

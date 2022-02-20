package paymybuddy.com.mybuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import paymybuddy.com.mybuddy.exception.UserDoesNotExist;
import paymybuddy.com.mybuddy.exception.YourBalanceIsNotEnough;
import paymybuddy.com.mybuddy.model.Account;
import paymybuddy.com.mybuddy.model.Transaction;
import paymybuddy.com.mybuddy.service.AccountService;
import paymybuddy.com.mybuddy.service.UserService;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Set;

@Controller
public class TransferController {

	private final AccountService accountService;

	private final UserService userService;

	public TransferController(UserService userService, AccountService accountService) {
		this.userService = userService;
		this.accountService = accountService;
	}

	@GetMapping("/transfer")
	public String transfer(Model model, Principal principal) throws UserDoesNotExist {
		return addToModel(model, principal);
	}

	@GetMapping("/transferMoney")
	public String addToContact(Model model,
	                           @RequestParam(value = "connection") String connection,
	                           @RequestParam(value = "motif") String motif,
	                           @RequestParam(value = "quantity") BigDecimal quantity,
	                           Principal principal) throws UserDoesNotExist {
		try {
			Account fromAccount = userService.getUserByEmail(principal.getName()).getAccount();
			Account toAccount = userService.getUserByEmail(connection).getAccount();
			accountService.makePayment(fromAccount, toAccount, motif, quantity);
			model.addAttribute("success", "Transfer " + quantity + " from " + principal.getName()
					+ " to " + connection + " successfully");
		} catch (UserDoesNotExist e) {
			model.addAttribute("error", "User " + connection + " doesn't exist");
		} catch (YourBalanceIsNotEnough yourBalanceIsNotEnough) {
			model.addAttribute("error", "Your balance is not enough");
		}
		return addToModel(model, principal);
	}

	private String addToModel(Model model, Principal principal) throws UserDoesNotExist {
		model.addAttribute("contacts", userService.getUserByEmail(principal.getName()).getContacts());
		Set<Transaction> transactions = userService.getUserByEmail(principal.getName()).getAccount().getTransactions();
		transactions.addAll(userService.getUserByEmail(principal.getName()).getAccount().getReceiverTransactions());
		model.addAttribute("transactions", transactions);
		return "transfer";
	}
}

package paymybuddy.com.mybuddy.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import paymybuddy.com.mybuddy.exception.UserDoesNotExist;
import paymybuddy.com.mybuddy.exception.YourBalanceIsNotEnough;
import paymybuddy.com.mybuddy.model.Account;
import paymybuddy.com.mybuddy.model.Transaction;
import paymybuddy.com.mybuddy.service.AccountService;
import paymybuddy.com.mybuddy.service.TransactionService;
import paymybuddy.com.mybuddy.service.UserService;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class TransferController {

	private final AccountService accountService;

	private final UserService userService;

	private final TransactionService transactionService;

	public TransferController(UserService userService, AccountService accountService, TransactionService transactionService) {
		this.userService = userService;
		this.accountService = accountService;
		this.transactionService = transactionService;
	}

	@GetMapping("/transfer")
	public String transfer(Model model, Principal principal) throws UserDoesNotExist {
		addToModel(model, principal);
		return getOnePage(model, 1);
	}

/*
	@GetMapping("/transfer")
	public String getAllPage(Model model) {
		return getOnePage(model, 1);
	}
 */

	@GetMapping("/transfer/page/{pageNumber}")
	public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage){
		Page<Transaction> page = transactionService.findPage(currentPage);
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
		List<Transaction> transactions = page.getContent();

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("transactions", transactions);
		return "transfer";
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

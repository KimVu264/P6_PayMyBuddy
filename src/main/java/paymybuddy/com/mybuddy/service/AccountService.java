package paymybuddy.com.mybuddy.service;

import org.springframework.stereotype.Service;
import paymybuddy.com.mybuddy.exception.YourBalanceIsNotEnough;
import paymybuddy.com.mybuddy.model.Account;
import paymybuddy.com.mybuddy.model.Transaction;
import paymybuddy.com.mybuddy.repository.AccountRepository;
import paymybuddy.com.mybuddy.repository.TransactionRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;

@Service
public class AccountService {

	private final AccountRepository accountRepository;

	private final TransactionRepository transactionRepository;

	private final BigDecimal PERCENT = BigDecimal.valueOf(0.5);

	public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
	}

	@Transactional
	public synchronized void makePayment(Account fromAccount, Account toAccount, String motif, BigDecimal quantity) throws YourBalanceIsNotEnough {
		BigDecimal quantityAfterPercent = quantity.add(quantity.multiply(PERCENT));
		BigDecimal fromCurrentBalance = fromAccount.getBalance();
		BigDecimal toCurrentBalance = toAccount.getBalance();
		if (fromCurrentBalance.compareTo(quantityAfterPercent) < 0) {
			throw new YourBalanceIsNotEnough();
		}
		fromAccount.setBalance(fromCurrentBalance.subtract(quantityAfterPercent));
		toAccount.setBalance(toCurrentBalance.add(quantity));
		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);
		Transaction transaction = Transaction.builder()
				.datetime(new Date())
				.amount(quantity)
				.motif(motif)
				.receiver(toAccount)
				.sender(fromAccount)
				.build();
		transactionRepository.save(transaction);
	}
}

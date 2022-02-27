package paymybuddy.com.mybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import paymybuddy.com.mybuddy.model.Transaction;
import paymybuddy.com.mybuddy.repository.TransactionRepository;

import java.util.List;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	public List<Transaction> findAll() {
		return transactionRepository.findAll();
	}

	public Page<Transaction> findPage(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber - 1, 3);
		return transactionRepository.findAll(pageable);
	}

}

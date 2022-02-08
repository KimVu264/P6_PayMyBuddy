package paymybuddy.com.mybuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import paymybuddy.com.mybuddy.model.Account;

import javax.transaction.Transactional;
import java.math.BigDecimal;


@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Long> {
/*
	Account findAccountById(long id);

	//@Modifying
	//@Query("update Account a set a.balance = ?1 WHERE a.id = ?2 ");
	void changeBalance(long id, BigDecimal balance);

*/
}

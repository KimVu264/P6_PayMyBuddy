package paymybuddy.com.mybuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import paymybuddy.com.mybuddy.model.Account;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional//(readOnly = true)
public interface AccountRepository extends JpaRepository<Account, Long> {
/*
	Optional<Account> findByAccountId(Long id);

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Transactional
	@Query("SELECT a FROM Account a WHERE a.accountId = ?1")
	Optional<Account> getAccountForUpdate(Long id);
*/
}

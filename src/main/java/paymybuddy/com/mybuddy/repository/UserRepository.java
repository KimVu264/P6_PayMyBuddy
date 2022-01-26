package paymybuddy.com.mybuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import paymybuddy.com.mybuddy.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail (String email);

}

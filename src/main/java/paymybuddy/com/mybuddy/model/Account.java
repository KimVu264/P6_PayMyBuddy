package paymybuddy.com.mybuddy.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "account")
public class Account {

	@Id
	@Column(name="account_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;

	@Column(name="number")
	private int accountNumber;

	@Column(name = "amount")
	//@Min(value = 0, message = "account balance must be positive")
	//private BigDecimal balance;
	private BigDecimal accountAmount;

	@OneToOne
	@JoinColumn(name="user_id" )
	private User user;

}

package paymybuddy.com.mybuddy.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "accounts")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//private String accountNumber;

	private BigDecimal balance;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
	private Set<Transaction> Transactions;

	@OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
	private Set<Transaction> receiverTransactions;

}

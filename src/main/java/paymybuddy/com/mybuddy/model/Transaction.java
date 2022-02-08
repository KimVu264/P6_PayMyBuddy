package paymybuddy.com.mybuddy.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="account_sender_id")
	private Account sender;

	@ManyToOne
	@JoinColumn(name="account_receipt_id")
	private Account receiver;

	private BigDecimal amount;

	private String motif;

	private Date datetime;

}

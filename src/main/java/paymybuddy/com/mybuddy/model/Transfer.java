package paymybuddy.com.mybuddy.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "transfer")
public class Transfer {

	@Id
	@Column(name="transfer_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transferId;

	//@NotNull
	//@ApiModelProperty(required = true)
	@Column(name="account_sender_id")
	private Long accountFromId;

	@Column(name="account_receipt_id")
	private Long accountToId;

	private BigDecimal amount;
	private String motif;
	private Date date;

}

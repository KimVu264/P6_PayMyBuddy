package paymybuddy.com.mybuddy.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "users")
public class User {

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(name="user_name")
	private String userName;

	private String email;

	private String password;

	private String address;

	private int tel;

	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date birthday;

}

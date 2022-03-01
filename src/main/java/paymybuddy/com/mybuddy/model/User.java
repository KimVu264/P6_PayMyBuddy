package paymybuddy.com.mybuddy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "User's first name cannot be empty.")
	@NotNull(message = "User's first name cannot be null.")
	private String firstName;

	@NotEmpty(message = "User's last name cannot be empty.")
	@NotNull(message = "User's last name cannot be null.")
	private String lastName;

	@NotEmpty(message = "User's email cannot be empty.")
	@NotNull(message = "User's email cannot be null.")
	private String email;

	@NotEmpty(message = "User's password cannot be empty.")
	@NotNull(message = "User's password cannot be null.")
	private String password;

	@NotEmpty(message = "User's address cannot be empty.")
	@NotNull(message = "User's address cannot be null.")
	private String address;

	@NotEmpty(message = "User's tel cannot be empty.")
	@NotNull(message = "User's tel cannot be null.")
	private String tel;

	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date birthday;

	@OneToOne(mappedBy = "user")
	private Account account;

	@OneToMany
	@JoinTable( name = "contacts",
			joinColumns = @JoinColumn( name = "id" ),
			inverseJoinColumns = @JoinColumn( name = "user_id" ) )
	private Set<User> contacts = new HashSet<>();


}

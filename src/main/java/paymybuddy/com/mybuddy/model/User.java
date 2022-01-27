package paymybuddy.com.mybuddy.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	private String password;

	private String address;

	private String tel;

	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date birthday;

	@OneToOne(mappedBy = "user")
	private Account account;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Contact> contacts;

	@OneToMany(mappedBy = "connectionUser", cascade = CascadeType.ALL)
	private Set<Contact> connectionUsers;

}

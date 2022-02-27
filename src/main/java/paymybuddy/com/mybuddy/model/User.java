package paymybuddy.com.mybuddy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
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

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private String address;

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

package paymybuddy.com.mybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import paymybuddy.com.mybuddy.model.User;
import paymybuddy.com.mybuddy.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * Create user
	 * @param user
	 * @return
	 */
	public User createUser(User user) {
		if (user.getEmail() != null)
		{
			if(isExistUserByEmail(user)) {
				return null;
			}
			User u = new User();
			u.setEmail(user.getEmail());
			u.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			u.setTel(user.getTel());
			u.setAddress(user.getAddress());
			u.setBirthday(user.getBirthday());
			userRepository.save(u);
			return u;
		}
		return null;
	}

	public boolean isExistUserByEmail( User u) {
		User user = userRepository.findByEmail(u.getEmail());
		if (user != null){
			return true;
		}
		return false;
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}

package paymybuddy.com.mybuddy.service;

import org.springframework.stereotype.Service;
import paymybuddy.com.mybuddy.exception.UserAlreadyInYourContact;
import paymybuddy.com.mybuddy.model.User;
import paymybuddy.com.mybuddy.repository.UserRepository;

import java.util.Set;

@Service
public class ContactService {
	private final UserRepository userRepository;

	public ContactService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void addToContact(User fromUser, User toUser) throws UserAlreadyInYourContact {
		Set<User> users = fromUser.getContacts();
		if (users.contains(toUser)) {
			throw new UserAlreadyInYourContact();
		}
		users.add(toUser);
		userRepository.save(fromUser);
	}
}

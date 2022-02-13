package paymybuddy.com.mybuddy.util;

import java.util.UUID;

public class AccountUtil {

	public synchronized static String generateNewAccountNumber() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
}

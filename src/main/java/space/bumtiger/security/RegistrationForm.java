package space.bumtiger.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import space.bumtiger.domain.User;

@Data
public class RegistrationForm {
	private String username;
	private String password;
	private String fullname;
	private String addrRoad;
	private String addrDetail;
	private String addrZip;
	private String phoneNumber;

	public User toUser(PasswordEncoder encoder) {
		return new User(username, encoder.encode(password), 
				fullname, addrRoad, addrDetail, addrZip, phoneNumber);
	}
}

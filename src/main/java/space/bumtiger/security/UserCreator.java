package space.bumtiger.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import space.bumtiger.domain.User;
import space.bumtiger.repository.UserRepository;

@Component
public class UserCreator {
	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	public void init() {
		User admin = new User("admin", "1234", 
				"ROLE_ADMIN,ROLE_USER", true, null);
		userRepository.save(admin);
	}
}

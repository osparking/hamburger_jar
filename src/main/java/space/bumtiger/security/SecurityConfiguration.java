package space.bumtiger.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfiguration {
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	UserDetailsService userDetailsService(PasswordEncoder encoder) {
		List<UserDetails> users = new ArrayList<>();
		users.add(new User("hong", encoder.encode("1234"),
				Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
		users.add(new User("park", encoder.encode("1234"),
				Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
		return (UserDetailsService) new InMemoryUserDetailsManager(users);
	}
}

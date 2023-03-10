package space.bumtiger.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import space.bumtiger.domain.User;
import space.bumtiger.repository.UserRepository;

@Configuration
public class SecurityConfiguration {
	// @formatter:off
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				(authorize) -> {
					try {
						authorize
							.requestMatchers("/design", "/orders").hasRole("USER")
							.requestMatchers("/", "/**").permitAll()
						.and()
							.formLogin()
								.loginPage("/login");
					} catch (Exception e) {
						e.printStackTrace();
					}
				});

		return http.build();
	}
	// @formatter:on

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	UserDetailsService userDetailsService(UserRepository repository) {

		return username -> {
			User user = repository.findByUsername(username);
			if (user == null) {
				String msg = username + "은 존재하지 않는 사용자입니다.";
				throw new UsernameNotFoundException(msg);
			} else {
				return user;
			}
		};

	}

}

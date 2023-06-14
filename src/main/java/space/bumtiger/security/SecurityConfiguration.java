package space.bumtiger.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import space.bumtiger.domain.User;
import space.bumtiger.repository.UserRepository;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {
	// @formatter:off
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
					authorize -> authorize
						.requestMatchers(HttpMethod.POST, "/api/ingredients").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/api/ingredients/**").hasRole("ADMIN")
						.requestMatchers("/design", "/orders").hasRole("USER")
						.requestMatchers("/ingredient/**").hasRole("ADMIN")
						.requestMatchers("/", "/**").permitAll())
			  .formLogin().loginPage("/login")
				.and()
				.oauth2Login(oauth2 -> oauth2
					.loginPage("/login")
					.userInfoEndpoint()
					.userService(oauth2UserService)
				.and()
					.successHandler(oAuth2LoginSuccessHandler)
			);
		http.httpBasic();
		http.csrf().disable();
		
		return http.build();
	}
	// @formatter:on

	@Autowired
	private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
	
	@Autowired
	private CustomOAuth2UserService oauth2UserService;

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

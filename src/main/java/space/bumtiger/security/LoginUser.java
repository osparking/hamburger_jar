package space.bumtiger.security;

import org.springframework.security.core.Authentication;

import space.bumtiger.domain.User;

public class LoginUser {
	public static Integer getUserId(Authentication authentication) {
		var principal = authentication.getPrincipal();
		Integer id = null;
		if (principal instanceof CustomOAuth2User) {
			id = ((CustomOAuth2User) principal).getIdLocal();
		} else {
			id = ((User)principal).getId();
		}
		return id;
	}
}


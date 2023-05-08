package space.bumtiger.security;

import space.bumtiger.domain.User;

public class LoginUser {
	public static Integer getUserId(Object user) {
		Integer id = null;
		if (user instanceof CustomOAuth2User) {
			id = ((CustomOAuth2User) user).getIdLocal();
		} else {
			id = ((User)user).getId();
		}
		return id;
	}
}


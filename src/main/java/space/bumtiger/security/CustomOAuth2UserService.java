package space.bumtiger.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import space.bumtiger.domain.User;
import space.bumtiger.repository.UserRepository;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest)
			throws OAuth2AuthenticationException {
		OAuth2User user = super.loadUser(userRequest);
		return new CustomOAuth2User(user);
	}

	@Autowired
	private UserRepository userRepo;

	public void processOAuthPostLogin(String email,
			Authentication authentication) {
		User dbUser = userRepo.findByUsername(email);

		if (dbUser == null) {
			User facebookUser = new User(email, "(NA)", "ROLE_USER", true,
					Provider.FACEBOOK);
			dbUser = userRepo.save(facebookUser);
		}
		var coa2User = (CustomOAuth2User) authentication.getPrincipal();
		coa2User.getAttributes().put("idLocal", dbUser.getId());
	}

}

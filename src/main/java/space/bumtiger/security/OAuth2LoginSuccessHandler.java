package space.bumtiger.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2LoginSuccessHandler
		extends SavedRequestAwareAuthenticationSuccessHandler {
	@Autowired
	private CustomOAuth2UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		CustomOAuth2User user = (CustomOAuth2User) authentication.getPrincipal();
		userService.processOAuthPostLogin(user.getEmail(), authentication);
		super.onAuthenticationSuccess(request, response, authentication);
	}
}

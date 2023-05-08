package space.bumtiger.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomOAuth2User implements OAuth2User, Serializable {
	private static final long serialVersionUID = 1L;
	private OAuth2User oAuth2User;
	
	@Transient
	private Map<String, Object> attributes;

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		oAuth2User.getAuthorities().forEach(ga -> authorities.add(ga));
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		return authorities;
	}

	@Override
	public String getName() {
		return oAuth2User.getAttribute("name");
	}

	public String getEmail() {
		return oAuth2User.<String>getAttribute("email");
	}
	
	public Integer getIdLocal() {
		return (Integer)attributes.get("idLocal");
	}

	public CustomOAuth2User(OAuth2User oAuth2User) {
		super();
		this.oAuth2User = oAuth2User;
		this.attributes = new HashMap<String, Object>(); 
		attributes.putAll(oAuth2User.getAttributes());
	}
}

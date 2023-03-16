package space.bumtiger.domain;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table
@NoArgsConstructor(access = AccessLevel.PRIVATE, force=true)
@AllArgsConstructor
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	private String username;
  private String password;
  private String fullname;
  private String addrRoad;
  private String addrDetail;
  private String addrZip;
  private String phoneNumber;

	public User(String username, String password, String fullname,
			String addrRoad, String addrDetail, String addrZip, 
			String phoneNumber) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.addrRoad = addrRoad;
		this.addrDetail = addrDetail;
		this.addrZip = addrZip;
		this.phoneNumber = phoneNumber;
	}	
  
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}

}

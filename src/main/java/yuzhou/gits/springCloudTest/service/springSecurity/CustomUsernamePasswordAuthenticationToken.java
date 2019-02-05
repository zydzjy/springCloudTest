package yuzhou.gits.springCloudTest.service.springSecurity;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import yuzhou.gits.springCloudTest.bean.SystemUser;

@SuppressWarnings("serial")
public class CustomUsernamePasswordAuthenticationToken 	
extends UsernamePasswordAuthenticationToken {

	private final String extToken;

	// used for attempting authentication
	public CustomUsernamePasswordAuthenticationToken(String principal, 
			String credentials, String extToken) {
		super(principal, credentials);
		this.extToken = extToken;
	}

	// used for returning to Spring Security after being
	// authenticated
	public CustomUsernamePasswordAuthenticationToken(SystemUser principal, 
			String credentials, String extToken,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
		this.extToken = extToken;
	}

	public String getExtToken() {
		return extToken;
	}
}

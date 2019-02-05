package yuzhou.gits.springCloudTest.service.springSecurity;

import java.util.Collections;

import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

	@Override
	public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
		// 结合具体的逻辑去实现用户认证，并返回继承UserDetails的用户对象; 
		UserDetails user = new User(
				token.getAssertion().getPrincipal().getName(), 
				"", Collections.emptyList());
		return user;
	}
}

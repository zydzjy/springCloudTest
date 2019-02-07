package yuzhou.gits.springCloudTest.service.springSecurity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import yuzhou.gits.springCloudTest.bean.SystemUser;
import yuzhou.gits.springCloudTest.service.UserService;

@ConditionalOnExpression("${_CONFIG.OAUTH2CLIENT:false}")
@Component("customUserAuthenticationProvider")
public class CustomUserAuthenticationProvider implements AuthenticationProvider {
	private PasswordEncoder passwordEncoder;
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	@Autowired
	private UserService userServiceImpl;

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		CustomUsernamePasswordAuthenticationToken token = (CustomUsernamePasswordAuthenticationToken) authentication;
		String userName = token.getName();
		String extToken = token.getExtToken();
		SystemUser user = null;
		if (userName != null) {
			try {
				user = this.userServiceImpl.getUserByLoginName(userName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username/password");
		}
		String password = user.getLoginPwd();
		System.out.println("password:"+password);
		String credentials = (String) token.getCredentials();
		System.out.println("credentials:"+credentials);
		if (!passwordEncoder.matches(credentials,password)) {
			throw new BadCredentialsException("Invalid username/password");
		}
		Collection<? extends GrantedAuthority> authorities = Collections.emptyList();
		return new CustomUsernamePasswordAuthenticationToken(user, password, extToken, authorities);
	}

	public boolean supports(Class<?> authentication) {
		return true;
		//return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}

}

package yuzhou.gits.springCloudTest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import yuzhou.gits.springCloudTest.bean.SystemUser;
import yuzhou.gits.springCloudTest.service.UserService;

@ConditionalOnExpression("${_CONFIG.OAUTH2CLIENT:false}")
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public SystemUser getUserByLoginName(String loginName) {
		SystemUser aUser = new SystemUser();
		
		aUser.setLoginName("user1");
		aUser.setLoginPwd(passwordEncoder.encode("pwd1"));
		
		return aUser;
	}

}

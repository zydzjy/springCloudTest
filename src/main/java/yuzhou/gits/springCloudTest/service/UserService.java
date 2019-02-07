package yuzhou.gits.springCloudTest.service;

import yuzhou.gits.springCloudTest.bean.SystemUser;

public interface UserService {

	public SystemUser getUserByLoginName(String loginName) throws Exception;
}

package yuzhou.gits.springCloudTest.bean;

import java.io.Serializable;

public class SystemUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8102471002918298666L;
	private long userId;
	private String loginName;
	private String loginPwd;
	private String email;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String toString() {
		return email;
	}
	
}

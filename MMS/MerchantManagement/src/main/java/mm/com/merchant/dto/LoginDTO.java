package mm.com.merchant.dto;

import org.springframework.stereotype.Component;

@Component
public class LoginDTO {
	private int userId;
	private String loginId;
	private String password;
	private String pwdRecQues;
	private String pwdRecAns;
	private String firstLogin;
	private String newPassword;
	private String passwordText;
	private String userStatusCode;
	private String statusCode;
	private String isPwdReq;
	private String lkpvalue;
	private String lkpdesc;
	private boolean insertLogin;
	private String oldPassword;
	private String confirmPassword;
	private String loginStatus;
	

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	
	public boolean isInsertLogin() {
		return insertLogin;
	}

	public void setInsertLogin(boolean insertLogin) {
		this.insertLogin = insertLogin;
	}

	public String getLkpvalue() {
		return lkpvalue;
	}

	public void setLkpvalue(String lkpvalue) {
		this.lkpvalue = lkpvalue;
	}

	public String getLkpdesc() {
		return lkpdesc;
	}

	public void setLkpdesc(String lkpdesc) {
		this.lkpdesc = lkpdesc;
	}

	public String getUserStatusCode() {
		return userStatusCode;
	}

	public void setUserStatusCode(String userStatusCode) {
		this.userStatusCode = userStatusCode;
	}
	

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getIsPwdReq() {
		return isPwdReq;
	}

	public void setIsPwdReq(String isPwdReq) {
		this.isPwdReq = isPwdReq;
	}

	public String getPasswordText() {
		return passwordText;
	}

	public void setPasswordText(String passwordText) {
		this.passwordText = passwordText;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(String firstLogin) {
		this.firstLogin = firstLogin;
	}



	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPwdRecQues() {
		return pwdRecQues;
	}

	public void setPwdRecQues(String pwdRecQues) {
		this.pwdRecQues = pwdRecQues;
	}

	public String getPwdRecAns() {
		return pwdRecAns;
	}

	public void setPwdRecAns(String pwdRecAns) {
		this.pwdRecAns = pwdRecAns;
	}

}

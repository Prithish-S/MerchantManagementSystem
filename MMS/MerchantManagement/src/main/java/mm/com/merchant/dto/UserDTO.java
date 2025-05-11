package mm.com.merchant.dto;

import org.springframework.stereotype.Component;

@Component
public class UserDTO {
	private int userId;
	private String loginId;
	private String firstName;
	private String lastName;
	private String country;
	private String state;
	private String city;
	private String password;
	private String roleType;
	private String assRoleList;
	private String roleList;
	private String pwdrecques;
	private String pwdrecans;
	private String status;
	private String isPwdReq;
	private String statusCode;
	
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

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getAssRoleList() {
		return assRoleList;
	}

	public void setAssRoleList(String assRoleList) {
		this.assRoleList = assRoleList;
	}

	
	public String getRoleList() {
		return roleList;
	}

	public void setRoleList(String roleList) {
		this.roleList = roleList;
	}



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPwdrecques() {
		return pwdrecques;
	}

	public void setPwdrecques(String pwdrecques) {
		this.pwdrecques = pwdrecques;
	}

	public String getPwdrecans() {
		return pwdrecans;
	}

	public void setPwdrecans(String pwdrecans) {
		this.pwdrecans = pwdrecans;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}

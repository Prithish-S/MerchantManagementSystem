package mm.com.merchant.service;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;

import mm.com.merchant.dto.LoginDTO;
import mm.com.merchant.dto.UserDTO;


public interface LoginService {
	public UserDTO getUserInfo(LoginDTO loginDTO);
	public boolean checkLoginID(String loginID);
	public String getpwdRecQues(String loginID);
	public long verifyPwdRec(LoginDTO loginId);
	public boolean isPwdRecAns(long userid);
	public LoginDTO recoveryUserPwd(LoginDTO loginDTO) throws Exception;
	public List<LoginDTO> getLookUpPwdQues();
	public LoginDTO changeUserPassword(LoginDTO loginDTO) throws Exception;
	public void updateLogOutDate(LoginDTO loginDTO) throws Exception;
	public UserDTO doLogin(LoginDTO loginDTO);
}

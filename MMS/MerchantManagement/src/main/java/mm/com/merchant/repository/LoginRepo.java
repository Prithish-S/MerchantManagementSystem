package mm.com.merchant.repository;

import java.util.List;

import mm.com.merchant.dto.LoginDTO;
import mm.com.merchant.dto.UserDTO;


public interface LoginRepo {
	public UserDTO getUserInfo(LoginDTO loginDTO);
	public boolean checkLoginID(String loginId);
	public String getpwdRecQues(String loginId);
	public boolean isPwdRecAns(long userid);
	public long verifyPwdRec(LoginDTO loginDTO);
	public LoginDTO recoveryUserPwd(LoginDTO loginDTO);
	public List<LoginDTO> getLookUpPwdQues();
	public LoginDTO changeUserPassword(LoginDTO loginDTO) ;
	 public void updateLogOutDate(LoginDTO loginDTO) ;
	public UserDTO doLogin(LoginDTO loginDTO);

}

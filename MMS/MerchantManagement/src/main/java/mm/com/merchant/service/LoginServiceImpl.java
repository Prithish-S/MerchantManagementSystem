package mm.com.merchant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import mm.com.merchant.constant.TransactConstant;
import mm.com.merchant.dto.LoginDTO;
import mm.com.merchant.dto.UserDTO;
import mm.com.merchant.repository.LoginRepo;


@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	LoginRepo loginRepo;
	
	@Override
	public UserDTO getUserInfo(LoginDTO loginDTO) {
		return loginRepo.getUserInfo(loginDTO);
	}
	@Override
	public UserDTO doLogin(LoginDTO loginDTO) {
		return loginRepo.doLogin(loginDTO);

	}
	@Override
	public boolean checkLoginID(String loginID) {

		return loginRepo.checkLoginID(loginID);
	}
	
	@Override
	public String getpwdRecQues(String loginID) {

		return loginRepo.getpwdRecQues(loginID);
	}
	
	@Override
	public long verifyPwdRec(LoginDTO loginId) {
		return loginRepo.verifyPwdRec(loginId);
	}
	@Override
	public boolean isPwdRecAns(long userid) {
		return loginRepo.isPwdRecAns(userid);
	}
	@Override
	public LoginDTO recoveryUserPwd(LoginDTO loginDTO) throws Exception {
		// TODO Auto-generated method stub
		String methodName = "recoveryUserPwd";
		String loginId = loginDTO.getLoginId();

		System.out.println("inside login seviceimpl" + loginId);
		UserDTO UserDTO = loginRepo.getUserInfo(loginDTO);
		if (loginDTO.getNewPassword().equals(loginDTO.getConfirmPassword())) {
					loginDTO = loginRepo.recoveryUserPwd(loginDTO);
					System.out.println("password changed successfully");
					loginDTO.setStatusCode(TransactConstant.SUCCESS);
				}
			else {
				loginDTO.setStatusCode(TransactConstant.FAIL);

			}
		return loginDTO;
	}
	
	
	@Override
	public LoginDTO changeUserPassword(LoginDTO loginDTO) throws Exception {
		String methodName = "changeUserPassword";
		UserDTO UserDTO = loginRepo.getUserInfo(loginDTO);
		if (!loginDTO.getOldPassword().equals(UserDTO.getPassword())) {
				System.out.println("User entered wrong old password");
				loginDTO.setStatusCode(TransactConstant.OLD_ASINFO_WRONG);
			return loginDTO;
		} else {
			if (loginDTO.getNewPassword().equals(loginDTO.getOldPassword())) {
				System.out.println("User old and new password matched");
				loginDTO.setStatusCode(TransactConstant.OLD_NEW_ASINFO_MATCHED);
				return loginDTO;
			} else if (loginDTO.getNewPassword().equals(loginDTO.getConfirmPassword())) {
				loginDTO.setIsPwdReq(TransactConstant.LOGIN_SUCESSSTATUS);
				loginDTO = loginRepo.changeUserPassword(loginDTO);
				loginDTO.setUserStatusCode("S");
				System.out.println("Password Changed successfully ");
				loginDTO.setStatusCode(TransactConstant.TRANSACT_SUCCESS);
			} else {
				System.out.println("New password and confirm password not matched ");
				loginDTO.setStatusCode(TransactConstant.TRANSACT_FAIL);
				return loginDTO;
			}
		}

		return loginDTO;
	}
	
	
	@Override
	public void updateLogOutDate(LoginDTO loginDTO) throws Exception {

		String methodName = "updateLogOutDate";
		loginRepo.updateLogOutDate(loginDTO);
	}
	public List<LoginDTO> getLookUpPwdQues(){
		return loginRepo.getLookUpPwdQues();
	}
}

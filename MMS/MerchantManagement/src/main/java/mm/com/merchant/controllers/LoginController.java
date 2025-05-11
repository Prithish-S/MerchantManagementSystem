package mm.com.merchant.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mm.com.merchant.constant.TransactConstant;
import mm.com.merchant.dto.LoginDTO;
import mm.com.merchant.dto.UserDTO;
import mm.com.merchant.service.LoginService;

@Controller
@SessionAttributes("userId")
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@GetMapping("/login")
	public String getUserPage(@ModelAttribute("loginDTO") LoginDTO loginDTO,HttpServletRequest req,Model model) {
		return "login";
	}
	
	@PostMapping("/doLogin")
	public String doLogin(@ModelAttribute("loginDTO") LoginDTO loginDTO,HttpServletRequest req,Model model,HttpSession session) {		
			UserDTO UserDTO = loginService.doLogin(loginDTO);
			//session.setAttribute("userId", loginDTO.getUserId());
			if(UserDTO.getLoginId().equalsIgnoreCase(loginDTO.getLoginId()) && UserDTO.getPassword().equalsIgnoreCase(loginDTO.getPassword())) {
			if(!UserDTO.getStatusCode().equalsIgnoreCase(TransactConstant.USER_DEF_FIRSTLOGIN)) {
				session.setAttribute("userId", UserDTO.getUserId());
				model.addAttribute("userId", UserDTO.getUserId());
				System.out.println(">>>>doLogin>>>dologin>"+session.getAttribute("userId"));

				return "Dashboard";
			}
			else {
				session.setAttribute("userId", UserDTO.getUserId());
				model.addAttribute("userId", UserDTO.getUserId());
				return "forward:changePassword";
			}
			
		}
		else
			return "login";
	}
	
	@PostMapping("/forgotpsswd")
	public String forgotPwd(@RequestParam("loginId") String loginid,HttpServletRequest request, ModelMap model) throws Exception {

		String methodName = "forgotPwd";
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setLoginId(loginid);
		System.out.println(loginDTO.getLoginId());
		UserDTO user=loginService.getUserInfo(loginDTO);
		model.addAttribute("userId", user.getUserId());
		boolean valid = loginService.checkLoginID(loginDTO.getLoginId());
		System.out.println("valid");
		if (!valid) {
			model.addAttribute("errorStatus","Invalid credential");
			model.addAttribute("loginDTO", new LoginDTO());
			return "login";
		} else {
				String psswdrecQues = loginService.getpwdRecQues(loginDTO.getLoginId());
			if (psswdrecQues.length() == 0) {
				model.addAttribute("errorStatus","Invalid question");
			}
			loginDTO.setPwdRecQues(psswdrecQues);
			model.addAttribute("psswrdrecQues", psswdrecQues);
			model.addAttribute("showButton", "Y");
			model.addAttribute("loginDTO", loginDTO);
			model.addAttribute("hideMenu", "Y");
			model.addAttribute("hidepwd", "Y");
			System.out.println("Forgot password");		
			return "forgotPwd";
		}
	}
	
	@PostMapping("/verifyRecAns")
	public String verifyRecAns(@RequestParam("loginId") String loginId, @RequestParam("pwdRecQues") String pwdQues,
			@RequestParam("pwdRecAns") String pwdAns, HttpServletRequest request, ModelMap model,HttpSession session) {

		String methodName = "verifyRecAns";
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setLoginId(loginId);
		loginDTO.setPwdRecQues(pwdQues);
		loginDTO.setPwdRecAns(pwdAns);
		UserDTO user=loginService.getUserInfo(loginDTO);
		model.addAttribute("userId", user.getUserId());
		System.out.println("User Change Password Called.");
		long isUserAvail = loginService.verifyPwdRec(loginDTO);
		if (isUserAvail != 0) {
			model.addAttribute("loginId", loginId);
			model.addAttribute("recButton", "Y");
			model.addAttribute("loginDTO", new LoginDTO());
			model.addAttribute("hideOld", "hideOld");
			model.addAttribute("hideMenu", "Y");
			model.addAttribute("hidepwd", "Y");
			model.addAttribute("successStatus","");
			return "changePassword";
		} else {

			loginDTO.setPwdRecAns("");
			model.addAttribute("showButton", "Y");
			model.addAttribute("loginDTO", loginDTO);
			model.addAttribute("hideMenu", "Y");
			model.addAttribute("hidepwd", "Y");
			model.addAttribute("errorStatus","");
			return "forgotPwd";
		}
	}
	
	/**
	 * This method is used to show change password .
	 */
	@PostMapping("/changePassword")
	public String changePassword(@ModelAttribute("loginDTO") LoginDTO loginDTO,Model model,HttpSession session) {

		int userId = (int)session.getAttribute("userId");
		model.addAttribute("userId", userId);
			if (!loginService.isPwdRecAns(userId)) {
			model.addAttribute("firstlogin", "firstlogin");
			loginDTO.setFirstLogin("firstlogin");
			loginDTO.setUserStatusCode("Y");
			List<LoginDTO> psswdRecryQues = loginService.getLookUpPwdQues();
			model.addAttribute("psswdRecryQues", psswdRecryQues);
			model.addAttribute("showButton", "Y");
			model.addAttribute("loginDTO", loginDTO);
			return "changePassword";
		}
		model.addAttribute("showButton", "Y");
		model.addAttribute("loginDTO", new LoginDTO());
		return "changePassword";
	}
	
	
	private LoginDTO getLoginUser(UserDTO userDTO,LoginDTO loginDTO) {
		loginDTO.setUserId(userDTO.getUserId());
		loginDTO.setLoginId(userDTO.getLoginId());
		loginDTO.setPassword(userDTO.getPassword());
		loginDTO.setUserId(userDTO.getUserId());
		loginDTO.getPwdRecQues();
		loginDTO.getPwdRecAns();
		loginDTO.setUserStatusCode("Y");
		return loginDTO;
	}
	
	/**
	 * This method is used to user change password .
	 */
	@PostMapping("/changeUserPassword")
	public String changeUserPassword(@ModelAttribute("loginDTO") LoginDTO loginDTO, HttpSession session,Model model, RedirectAttributes redir) throws Exception {

		String methodName = "changeUserPassword";
		
		boolean isUserFirstLogin = false;
		if(!(session.getAttribute("userId")==null)) {
		int userId=(int)session.getAttribute("userId");
		loginDTO.setUserId(userId);
		model.addAttribute("userId", userId);
		}
		UserDTO user=loginService.getUserInfo(loginDTO);
		loginDTO=getLoginUser(user,loginDTO);
		System.out.println("Which format password recovery answer" + loginDTO.getPwdRecAns()); 
			if (loginDTO.getUserStatusCode().equalsIgnoreCase("Y")|| !loginService.isPwdRecAns(loginDTO.getUserId())) {
				isUserFirstLogin = true;
			}
		if (loginDTO.getNewPassword() == null || loginDTO.getNewPassword().equals("") || loginDTO.getConfirmPassword() == null
				|| loginDTO.getConfirmPassword().equals("")) {
			System.out.println(loginDTO.getConfirmPassword());
			System.out.println(loginDTO.getOldPassword());
			System.out.println(loginDTO.getNewPassword());

			if (isUserFirstLogin) {
				if (loginDTO.getPwdRecQues() == null || loginDTO.getPwdRecAns() == null) {
					model.addAttribute("errorStatus", "Pwd Recovery field is null");
				}

			}
			System.out.println("User Validation failed");
			model.addAttribute("errorStatus", "User Validation failed");

		} else if (loginDTO.getOldPassword() == "" || loginDTO.getOldPassword() == null) {
			if (loginDTO.getNewPassword() != null && loginDTO.getConfirmPassword() != null) {
				loginDTO = loginService.recoveryUserPwd(loginDTO);
			} else {
				model.addAttribute("errorStatus", "change password empty");
			}

		} else {
			loginDTO = loginService.changeUserPassword(loginDTO);
			user.setStatusCode(TransactConstant.TRANSACT_SUCCESS);
			user.setIsPwdReq(TransactConstant.LOGIN_SUCESSSTATUS);
			model.addAttribute("successStatus", "Password Change is Successfull.");

		}
		if (isUserFirstLogin) {
			model.addAttribute("firstlogin", "firstlogin");
			loginDTO.setFirstLogin("firstlogin");
			loginDTO.setPwdRecAns("");
			List<LoginDTO> psswdRecryQues = loginService.getLookUpPwdQues();
			model.addAttribute("psswdRecryQues", psswdRecryQues);
			System.out.println("User password validation failed");
		}

		model.addAttribute("loginDTO", loginDTO);
		model.addAttribute("showButton", "Y");
		System.out.println("Complete. showing Change Password.");
		return "login";
	}
	@PostMapping("/logout")
	public String logout(@ModelAttribute("loginDTO") LoginDTO loginDTO,Model model, HttpServletRequest request)
			throws Exception {
		String methodName = "logout";
		loginDTO.setInsertLogin(false);
		loginService.updateLogOutDate(loginDTO);
		return "login";
	}


}

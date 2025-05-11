package mm.com.merchant.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import mm.com.merchant.dto.CityDTO;
import mm.com.merchant.dto.StateDTO;
import mm.com.merchant.dto.UserDTO;
import mm.com.merchant.service.UserService;


@Controller
@SessionAttributes("userId")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping(value = "/getState", produces = "application/json")
	public ResponseEntity<Object> getState(@RequestParam("countryid") int countryId, Model model) throws Exception {

		List<StateDTO> stateList =userService.getStateDetail(countryId);
		model.addAttribute("stateList", stateList);
		return new ResponseEntity<Object>(stateList, HttpStatus.OK);

	}
	

	@GetMapping(value = "/getCity", produces = "application/json")
	public ResponseEntity<Object> getCity(@RequestParam("countryid") int countryId,
			@RequestParam("stateid") int stateId, Model model) throws Exception {
		List<CityDTO> cityList =userService.getCityDetail(stateId, countryId);
		model.addAttribute("cityList", cityList);
		return new ResponseEntity<Object>(cityList, HttpStatus.OK);

	}
	
	
	@PostMapping("/getUserForm")
	public String getUserForm(@RequestParam("userId") int userId,@ModelAttribute("UserDTO") UserDTO user,HttpSession session, Model model) throws Exception {
		
		String methodName = "getUserForm";
		model.addAttribute("requestType", "A");
		model.addAttribute("adduser","A");
		System.out.println("uerid>>>>"+session.getAttribute("userId"));
		model.addAttribute("countryNames", userService.getCountryDetail());
		model.addAttribute("userId", (int)session.getAttribute("userId"));
		return "AddEditUser";
	}
	
	@PostMapping("/addUser")
	public String addUser(@RequestParam("userId") int userId,@ModelAttribute("UserDTO") UserDTO user,HttpSession session,Model model) throws Exception {
		
		String methodName = "addUser";
		int userid=0;
		model.addAttribute("requestType", "A");
		model.addAttribute("adduser","A");
		model.addAttribute("countryNames", userService.getCountryDetail());
		UserDTO userDTO=userService.addUser(user);
		userid=userDTO.getUserId();
		List<UserDTO> userList=new ArrayList<UserDTO>();
		userList=userService.getUserList();
		model.addAttribute("userDTO", userList);
		model.addAttribute("userId", (int)session.getAttribute("userId"));
		return "UserLandingPage";
	}

	
	@PostMapping("/viewUser")
	public String viewUser(@RequestParam("userId") int userId,@ModelAttribute("UserDTO") UserDTO user,HttpSession session,ModelMap model)throws Exception {
		
		String methodName = "viewUser";
		model.addAttribute("requestType", "E");
		System.out.println(user.getUserId());
		UserDTO userDTO=new UserDTO();
		userDTO=userService.getUser((int)session.getAttribute("userId"));
		model.addAttribute("userVO", userDTO);
		model.addAttribute("userId", session.getAttribute("userId"));
		return "profile";
	}
	
	
}

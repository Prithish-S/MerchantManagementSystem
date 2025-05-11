/* @Author: Kavitha
 * 
 @Creation-Date: 10/02/2024
 @Copyright: First Melon Software Systems Pvt. Ltd.
 @Description: This class is used as role module in User Management.
 @Program-Specs-Referred: Functional Specification and TAD documents
 @Revision:
 ----------------------------------------------------------------------------------------------------------------
 @Version    | @Last Revision Date | @Name                  | @Function/Module affected    | @Modifications Done
 ----------------------------------------------------------------------------------------------------------------	
 1.0			10/02/2024		       Kavitha			 Initial Version
*
 */


package mm.com.merchant.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import mm.com.merchant.constant.TransactConstant;
import mm.com.merchant.dto.RoleDTO;
import mm.com.merchant.service.RoleService;

@Controller
@SessionAttributes("userId")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	String ipAddress="127.0.0.1";
	String loginId="FirstMelon";

	
	@GetMapping(value = "/getFunction", produces = "application/json")
	public ResponseEntity<Object> getFunction(@RequestParam("roleType") String roleType,
			@RequestParam("moduleId") int moduleId,Model model) throws Exception {
		model.addAttribute("functionList", roleService.getFunction(roleType, moduleId));
		return new ResponseEntity<Object>(roleService.getFunction(roleType,moduleId), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getFunctionId", produces = "application/json")
	public ResponseEntity<Object> getFunctionId(int funcId,	Model model) throws Exception {
		
		model.addAttribute("FunctionId", roleService.getFunctionId(funcId));
		return new ResponseEntity<Object>(roleService.getFunctionId(funcId), HttpStatus.OK);
	}
	@GetMapping(value = "/geMappedRoles", produces = "application/json")
	public ResponseEntity<Object> geMappedRoles(@RequestParam("roleType") String roleType, Model model) throws Exception {

		model.addAttribute("mappedRoles", roleService.getEditRoles(roleType));
		System.out.println(">>>>>>>>>AJAX>>>>>>>>"+roleService.getEditRoles(roleType));
		return new ResponseEntity<Object>(roleService.getEditRoles(roleType), HttpStatus.OK);

	}
	
	/* This Method lands on ADDEDITROLE page.*/
	@PostMapping("/AddEditRole")
	public String AddRole(Model model, HttpSession session,HttpServletRequest request)throws Exception {
	
		String methodName = "addRole";	
		int userId= (int) session.getAttribute("userId");
		model.addAttribute("moduleNames", roleService.getModules());
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setUserId((int)session.getAttribute("userId"));
		model.addAttribute("addRole", TransactConstant.ADDROLE);
		model.addAttribute("showbutton",TransactConstant.ADDROLE);
		model.addAttribute("roleDTO", roleDTO);
		model.addAttribute("functionDTO",roleDTO.getFunctionDTO());
		model.addAttribute("requestType", TransactConstant.ADDROLE);
		model.addAttribute("userId", userId);
		model.addAttribute("successStatus", "");
		model.addAttribute("errorStatus", "");
		return "AddEditRole";
	}
	
	@PostMapping("/viewRole")
	public String viewRole(@ModelAttribute("RoleDTO") RoleDTO roleDTO, HttpSession session, ModelMap model, Locale locale)throws Exception {
		
		String methodName = "viewRole";
		model.addAttribute("userId", session.getAttribute("userId"));
		roleDTO.setUserId((int)session.getAttribute("userId"));
		System.out.println("Get role information from final table");
		List<RoleDTO> roleList=new ArrayList<RoleDTO>();
		roleList=roleService.getUserRoleList();
		model.addAttribute("functionDTO",roleDTO.getFunctionDTO());
		model.addAttribute("roleDTOList", roleList);
		model.addAttribute("roleDTO", roleList);
		model.addAttribute("requestType", TransactConstant.EDITROLE);
		return "RoleLandingPage";
	}
	

	

	/**
	 * This Method diverts to the View Particular role data page depending on the
	 * role id selected.
	 */
	@PostMapping("/viewEachRole")
	public String getRole(@RequestParam("roleId") int roleId,@ModelAttribute("RoleDTO") RoleDTO roleDTO, HttpSession session, ModelMap model, Locale locale)throws Exception {
		
		String methodName = "getRole";
		roleDTO.setUserId((int)session.getAttribute("userId"));
		model.addAttribute("moduleNames", roleService.getModules());
		model.addAttribute("userId", session.getAttribute("userId"));
		roleDTO.setRoleId(roleId);
		model.addAttribute("assignFunList", roleService.getFunctionNames(roleId));
		if (roleId !=0) {
			roleDTO = roleService.getRole(roleDTO);
			model.addAttribute("roleDTO", roleDTO);
			System.out.println("displaying role information"+roleId);
		}
		else {
			model.addAttribute("errorStatus","role Id  is NULL");
		}
		model.addAttribute("functionDTO",roleService.getFunctionList());
		model.addAttribute("editRole", TransactConstant.EDITROLE);
		model.addAttribute("requestType", TransactConstant.EDITROLE);
		model.addAttribute("showbutton",TransactConstant.EDITROLE);
		return "AddEditRole";
	}

	
	/**
	 * This Method lands executes the functionality of adding role.
	 */
	@PostMapping("/addRole")
	public String addRole( @ModelAttribute("RoleDTO") RoleDTO roleDTO, BindingResult result, Model model,HttpSession session, 
			HttpServletRequest request, Locale locale)throws Exception {
		
		String methodName = "addRole";
		int roleId=roleDTO.getRoleId();
		String role=roleDTO.getAssignFunList().toString();
		model.addAttribute("userId", session.getAttribute("userId"));
		String assignFunList[] = (role != null) ? role.trim().split("#"): "".split("#");
		String roles = "";
		for (int i = 0; i < assignFunList.length; i++) {
			roles = roles + "," + assignFunList[i];
		}
		roles = roles.replaceFirst(",", "");
		roleDTO.setAssignFunList(roles);
			System.out.println("validation for Role field success");
			roleDTO = roleService.addEditRole(roleDTO);
			if (roleDTO.getStatusCode().equals(TransactConstant.TRANSACT_SUCCESS)) {
				System.out.println("Role created successfully in Temporary Table");
				model.addAttribute("successStatus", "Role created Successfully");
				model.addAttribute("showbutton", TransactConstant.ADDROLE);
				model.addAttribute("addRole", TransactConstant.ADDROLE);
			} else if (roleDTO.getStatusCode().equals(TransactConstant.TRANSACT_FAIL)) {
				System.out.println("Role already exists with same name.");
				model.addAttribute("errorStatus", "It is already available in Role Table");
				model.addAttribute("showbutton", TransactConstant.ADDROLE);
				model.addAttribute("addRole", TransactConstant.ADDROLE);
			}
		model.addAttribute("roleDTOList", roleService.getUserRoleList());
		model.addAttribute("moduleNames", roleService.getModules());
		model.addAttribute("requestType",TransactConstant.ADDROLE);
		model.addAttribute("roleDTO", roleDTO);
		return "RoleLandingPage";
	}
	
	@PostMapping("/editRole")
	public String editRole(@ModelAttribute("RoleDTO") RoleDTO roleDTO, BindingResult result, Model model,
			HttpServletRequest request, HttpSession session, Locale locale)
			throws Exception {
		String methodName = "editRole";
		int roleId=roleDTO.getRoleId();
		int userId= (int) session.getAttribute("userId");
		String role=roleDTO.getAssignFunList().toString();
		model.addAttribute("userId", userId);

		String assignFunList[] = (role != null) ? role.trim().split("#"): "".split("#");
		String roles = "";
		for (int i = 0; i < assignFunList.length; i++) {
			roles = roles + "," + assignFunList[i];
		}
		roles = roles.replaceFirst(",", "");
		roleDTO.setAssignFunList(roles);
		model.addAttribute("assignFunList", roleService.getFunctionNames(roleId));
		//model.addAttribute("roleDTOList", roleService.getUserRoleList());
		model.addAttribute("moduleNames", roleService.getModules());
			roleDTO = roleService.editRoleList(roleDTO);
			if (roleDTO.getStatusCode() == TransactConstant.TRANSACT_SUCCESS) {
				System.out.println("Role updated successfully");
				model.addAttribute("successStatus", "Role created Success");

			} else {
				System.out.println("Fail to update Role ");
				model.addAttribute("errorStatus", "Error updating Role");
				model.addAttribute("showbutton", TransactConstant.EDIT_ROLE);
			}
		model.addAttribute("editRole", TransactConstant.EDITROLE);
		model.addAttribute("editRoleName", TransactConstant.EDIT_ROLE);
		model.addAttribute("requestType", TransactConstant.EDITROLE);
		model.addAttribute("roleDTO", roleDTO);
		return "RoleLandingPage";
	}
}

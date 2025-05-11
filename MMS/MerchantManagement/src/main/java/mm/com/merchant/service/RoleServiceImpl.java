package mm.com.merchant.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mm.com.merchant.constant.TransactConstant;
import mm.com.merchant.dto.FunctionDTO;
import mm.com.merchant.dto.RoleDTO;
import mm.com.merchant.repository.RoleRepo;

@Service
public class RoleServiceImpl implements RoleService{

	
	@Autowired
	RoleRepo roleRepo;
	
	String ipAddress="127.0.0.1";
	String loginId="FirstMelon";
	

	@Override
	public List<RoleDTO> getModules() {
		String methodName="getModules";
		return roleRepo.getModuleName();
	}

	@Override
	public List<FunctionDTO> getFunctionList() {
		String methodName="getFunctionList";
		return roleRepo.getFunction();
	}
	
	@Override
	public List<FunctionDTO> getFunction(String roleType,int moduleId) {
		String methodName="getFunction";
		return roleRepo.getFunctionDetail(roleType,moduleId);
	}

	//This method is used here to view all roles
	@Override
	public List<RoleDTO> getUserRoleList() {
		String methodName="getUserRoleList";
		List<RoleDTO> listLeaveObject = roleRepo.getFinalRoleList();
		return listLeaveObject;
	}


	//This method is used here to view a particular role
	@Override
	public RoleDTO getRole(RoleDTO roleDTO) throws Exception{
		String methodName="getRole";
		return roleRepo.getRole(roleDTO);
	}


	//This method is used here to add a role
	@Override
	public RoleDTO addEditRole(RoleDTO roleDTO)throws Exception {
		
		String methodName = "addEditRole";
		if(!roleRepo.checkForDuplicateRole(roleDTO)) {
		roleRepo.createRole(roleDTO);
		if (roleDTO.getStatusCode().equals(TransactConstant.TRANSACT_SUCCESS)) {
			System.out.println("ROLE is created in Role table");

		} else {
			System.out.println("Failed to created ROLE in Role table");
		}
		}
		else {
			roleDTO.setStatusCode(TransactConstant.DUPLICATE_NAME);
		}
		return roleDTO;	
	}
	
	
	
	//This method is used here to edit a role
	@Override
	public RoleDTO editRoleList(RoleDTO roleDTO) throws Exception {
		String methodName = "editRoleList";		
		roleDTO = roleRepo.editRole(roleDTO);
			if (roleDTO.getStatusCode().equals(TransactConstant.TRANSACT_SUCCESS)) {
				System.out.println("ROLE is updated in Role table");
			} else {
				System.out.println("Failed to updated ROLE in Role table");
			}
		return roleDTO;
	}
	
	@Override
	public List<FunctionDTO> getFunctionNames(int roleId)throws Exception {
		String methodName="getFunctionNames";
		System.out.println("Fetching All the Function Names from ROLE MASTER");
		return roleRepo.functionNames(roleId);
	}

	public FunctionDTO getFunctionId(int funcId) {
		String methodName="getFunctionId";
		System.out.println("Fetching All the Function NAME,ID from ROLE FUNCTION TABLE");		
		return roleRepo.getFuncId(funcId);
	}
	@Override
	public List<FunctionDTO> getFunctionId(String funcName) {
		String methodName="getFunctionId";
		System.out.println("Fetching All the Function NAME,ID from ROLE FUNCTION TABLE");
		return roleRepo.getFunctionId(funcName);
	}

	@Override
	public List<RoleDTO> getEditRoles(String roleType) {
		return roleRepo.getEditRoles(roleType);
	}
}

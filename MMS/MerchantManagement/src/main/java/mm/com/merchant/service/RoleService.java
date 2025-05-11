package mm.com.merchant.service;

import java.util.List;
import mm.com.merchant.dto.FunctionDTO;
import mm.com.merchant.dto.RoleDTO;


public interface RoleService {
	
	public List<RoleDTO> getModules();
	
	public List<FunctionDTO> getFunctionList();
	
	public List<FunctionDTO> getFunction(String roleType,int moduleId);
	
	public FunctionDTO getFunctionId(int funcId);
	
	public List<FunctionDTO> getFunctionNames(int roleId)throws Exception;
	
	public List<RoleDTO> getUserRoleList(); 
	
	public RoleDTO getRole(RoleDTO roleDTO)throws Exception; // to view a particular role
	
	public RoleDTO addEditRole(RoleDTO roleVO)throws Exception ; //to add a role
	
	public RoleDTO editRoleList(RoleDTO roleVO)throws Exception ;// to edit a role
	
	
	public List<FunctionDTO> getFunctionId(String funcName); //not yet done

	public List<RoleDTO> getEditRoles(String roleType);
}

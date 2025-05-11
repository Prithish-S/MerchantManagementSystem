package mm.com.merchant.repository;
import java.util.List;
import mm.com.merchant.dto.FunctionDTO;
import mm.com.merchant.dto.RoleDTO;
 
public interface RoleRepo {
	
	public List<RoleDTO> getModuleName();
	
	public List<FunctionDTO> getFunction();	//for addeditrole function dropdown
	
	public List<FunctionDTO> getFunctionDetail(String roleType,int moduleId); //for availablefunlist drop down
	
	public FunctionDTO getFuncId(int funcId);
	
	public List<FunctionDTO> functionNames(int roleId) throws Exception; // for assignfunList
	
	public List<RoleDTO> getFinalRoleList();
	
	public RoleDTO getRole(RoleDTO roleDTO) throws Exception;
	
	public boolean checkForDuplicateRole(RoleDTO roleDTO) throws Exception;
	
	public RoleDTO createRole(RoleDTO roleDTO)throws Exception;
	
	public RoleDTO editRole(RoleDTO roleDTO)throws Exception;
	
	public List<FunctionDTO> getFunctionId(String funcName); 
	
	public List<RoleDTO> getEditRoles(String roleType);
	
}

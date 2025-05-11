package mm.com.merchant.dto;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleDTO{
	
	@Autowired
	private FunctionDTO functionDTO;
	private int userId;
	private int roleId;
	private String roleName;
	private String roleDesc;
	private int sessionTime;
	private int funcId;
	private String funcName;
	private String instId;
	private String moduleName;
	private int moduleId;
	private String roleType;
	private List<FunctionDTO> availableFunList;
	private String assignFunList;
	private String statusCode;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public FunctionDTO getFunctionDTO() {
		return functionDTO;
	}
	public void setFunctionDTO(FunctionDTO functionDTO) {
		this.functionDTO = functionDTO;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public int getSessionTime() {
		return sessionTime;
	}
	public void setSessionTime(int sessionTime) {
		this.sessionTime = sessionTime;
	}
	
	public int getFuncId() {
		return funcId;
	}
	public void setFuncId(int funcId) {
		this.funcId = funcId;
	}
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	public String getInstId() {
		return instId;
	}
	public void setInstId(String instId) {
		this.instId = instId;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public List<FunctionDTO> getAvailableFunList() {
		return availableFunList;
	}
	public void setAvailableFunList(List<FunctionDTO> availableFunList) {
		this.availableFunList = availableFunList;
	}
	
	public String getAssignFunList() {
		return assignFunList;
	}
	public void setAssignFunList(String assignFunList) {
		this.assignFunList = assignFunList;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
}

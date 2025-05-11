package mm.com.merchant.dto;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class FunctionDTO{

	private int funcId;
	private String	funcName;
	private String 	funcDesc;
	private String status;
	private List<Integer> rolelist;

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
	public String getFuncDesc() {
		return funcDesc;
	}
	public void setFuncDesc(String funcDesc) {
		this.funcDesc = funcDesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Integer> getRolelist() {
		return rolelist;
	}
	public void setRolelist(List<Integer> rolelist) {
		this.rolelist = rolelist;
	}
	
}

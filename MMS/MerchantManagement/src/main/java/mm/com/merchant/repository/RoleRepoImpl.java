package mm.com.merchant.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import mm.com.merchant.constant.TransactConstant;
import mm.com.merchant.dto.FunctionDTO;
import mm.com.merchant.dto.RoleDTO;

@Repository
public class RoleRepoImpl implements RoleRepo {


	private String className = this.getClass().getSimpleName();

	@Autowired
	@Qualifier(value = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Autowired
	FunctionDTO functionDTO;

	
	@Override
	public List<RoleDTO> getModuleName() {

		List<RoleDTO> moduleList = null;
		String ModuleQuery="SELECT moduleid as moduleId, modulename as moduleName FROM UM_ROLE_MODULE";
		moduleList = jdbcTemplate.query(ModuleQuery,new BeanPropertyRowMapper<RoleDTO>(RoleDTO.class));
		return moduleList;
	}

	@Override
	public List<FunctionDTO> getFunction() {

		List<FunctionDTO> functionList = null;
		String functionQuery="SELECT funcid as funcId, funcname as funcName from um_role_function";
		functionList = jdbcTemplate.query(functionQuery, new BeanPropertyRowMapper<FunctionDTO>(FunctionDTO.class));
		return functionList;

	}

	// this method is to load the functionalities in AJAX
	@Override
	public List<FunctionDTO> getFunctionDetail(String roleType, int moduleId) {
		List<FunctionDTO> functionList = null;
		String functionQuery="SELECT funcid as funcId, funcname as funcName from um_role_function where lower(roletype)=? and moduleid=?";
		functionList = jdbcTemplate.query(functionQuery,new BeanPropertyRowMapper<FunctionDTO>(FunctionDTO.class),new Object[] { roleType.toLowerCase(), moduleId });
		//System.out.println("functionList" + functionList.isEmpty());
		return functionList;
	}

	@Override
	public FunctionDTO getFuncId(int funcId) {
		FunctionDTO functionList = null;
		String functionQuery="SELECT funcid as funcId, funcname as funcName from um_role_function where funcid=?";
		functionList = jdbcTemplate.queryForObject(functionQuery, new BeanPropertyRowMapper<FunctionDTO>(FunctionDTO.class),new Object[] { funcId });
		return functionList;
	}

	// This method is to load the function names from database
	@Override
	public List<FunctionDTO> getFunctionId(String funcName) {

		List<FunctionDTO> functionList = null;
		String functionQuery="SELECT funcid as funcId, funcname as funcName from um_role_function where lower(funcname)=?";
		functionList = jdbcTemplate.query(functionQuery,new BeanPropertyRowMapper<FunctionDTO>(FunctionDTO.class),
				new Object[] { funcName });
		return functionList;
	}

	// this method is to view all the roles available in um_role_master table
	@Override
	public List<RoleDTO> getFinalRoleList() {
		String methodName = "getFinalRoleList";
		List<RoleDTO> roleList = new ArrayList<RoleDTO>();
		String getRolesQuery="select roleid  as roleId, rolename as roleName,roledesc as roleDesc,roletype as roleType,inst_id as instId,sessiontime as sessionTime from UM_ROLE_MASTER";
		roleList = jdbcTemplate.query(getRolesQuery,new BeanPropertyRowMapper<RoleDTO>(RoleDTO.class));
		return roleList;

	}

	// this method is to view a particular role in um_role_master table
	@Override
	public RoleDTO getRole(RoleDTO roleDTO) throws Exception {
		String methodName = "getRole";
		String getRolesQuery="select roleid  as roleId, rolename as roleName,roledesc as roleDesc,roletype as roleType,inst_id as instId,sessiontime as sessionTime,assignfunlist as assignFunList from UM_ROLE_MASTER WHERE roleid=? limit 1";
		roleDTO = jdbcTemplate.queryForObject(getRolesQuery,new BeanPropertyRowMapper<RoleDTO>(RoleDTO.class),
						new Object[] { roleDTO.getRoleId() });
		System.out.println("value retrieved");
		roleDTO.setStatusCode("SUCCESS");
		
		return roleDTO;
	}

	@Override
	public boolean checkForDuplicateRole(RoleDTO roleDTO) throws Exception {
		/**
		 * status: true meaning same data already available 
		 * status:false meaning same data not there in Data Base
		 */
		boolean status = false;
		int count = 0;
		String query="SELECT COUNT(1) FROM UM_ROLE_MASTER WHERE UPPER(ROLENAME)= ?";
		count = jdbcTemplate.queryForObject(query,Integer.class,new Object[] {roleDTO.getRoleName().toUpperCase()});
		if (count > 0) {
			status = true;
		}
		return status;
	}

	private List<Integer> funcListForId(int roleId) {
		List<Integer> funcList = null;
		String query="SELECT funcid from um_user_crlefncmap where roleid=?";
		funcList = jdbcTemplate.query(query, new BeanPropertyRowMapper<Integer>(Integer.class),new Object[] { roleId });
		return funcList;
	}

	// Recursive Function inside FunctionNames
	private String funcNameQuerying(int funcId) {
		String query="SELECT funcname from um_role_function where funcid=?";
		return jdbcTemplate.queryForObject(query,String.class,new Object[] { funcId });
	}

	// Retrieving data from database as modelAttribute to JSP in assignFunList
	@Override
	public List<FunctionDTO> functionNames(int roleId) throws Exception {
		String methodName = "functionNames";
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setRoleId(roleId);
		List<FunctionDTO> functionDTO = new ArrayList<FunctionDTO>();
			roleDTO = getRole(roleDTO);
			String[] funId = roleDTO.getAssignFunList().split(",");
			for (String s : funId) {
				int id = Integer.parseInt(s);
				FunctionDTO data = getFuncId(id);
				functionDTO.add(data);
				while (s == null)
					break;
			}
			return functionDTO;
		} 
		

	@Override
	public RoleDTO createRole(RoleDTO roleDTO) throws Exception {
		String methodName = "createRole";
				String queryForSeq="select nextval('role_seq')";
				int roleid = jdbcTemplate.queryForObject(queryForSeq,Integer.class);
				roleDTO.setRoleId(roleid);
				String query="insert into um_role_master(roleid,rolename, roledesc,sessiontime, inst_id, roletype,assignFunList) values(?,?,?,?,?,?,?)";
				int status1 = jdbcTemplate.update(query,roleDTO.getRoleId(),
					roleDTO.getRoleName(), roleDTO.getRoleDesc(), roleDTO.getSessionTime(), roleDTO.getInstId(),
					roleDTO.getRoleType(), roleDTO.getAssignFunList());
			if (status1 == 1) {
				String cusRoleQuery="insert into um_user_rolefuncmap(roleid, funcid,status, crtdate) values (?,?,'A',current_timestamp)";
				String funcId = roleDTO.getAssignFunList();
				String[] function = funcId.split(",");
				for (String f : function) {
					int i = Integer.parseInt(f);
					int countFunc = jdbcTemplate.update(cusRoleQuery,roleDTO.getRoleId(), i);
				}
				roleDTO.setStatusCode(TransactConstant.TRANSACT_SUCCESS);
			} else {
				roleDTO.setStatusCode(TransactConstant.TRANSACT_FAIL);
			}
			return roleDTO;
		}

	@Override
	public RoleDTO editRole(RoleDTO roleDTO) throws Exception {

		int id = roleDTO.getRoleId();

			String query="UPDATE um_role_master SET roledesc=?,sessiontime=?,inst_id=?,roletype=?,assignfunlist=? WHERE roleid=?";
			int count = jdbcTemplate.update(query, roleDTO.getRoleDesc(),roleDTO.getSessionTime(), roleDTO.getInstId(), roleDTO.getRoleType(), roleDTO.getAssignFunList(),
					roleDTO.getRoleId());
			String deleteQuery="DELETE FROM um_user_rolefuncmap WHERE roleid=?";
			if (count > 0) {
				
				roleDTO.setStatusCode(TransactConstant.TRANSACT_SUCCESS);
				int count1 = jdbcTemplate.update(deleteQuery, id);
				if(count1>0) {
					System.out.println("Record Deleted");
				}
				String funcId = roleDTO.getAssignFunList();
				String[] function = funcId.split(",");
				String cusRoleQuery="insert into um_user_rolefuncmap(roleid, funcid,status, crtdate) values (?,?,'A',current_timestamp)";
				for (String f : function) {
					int funcEditId = Integer.parseInt(f);
					int countFunc = jdbcTemplate.update(cusRoleQuery,roleDTO.getRoleId(), funcEditId);
					if(countFunc>0) {
						System.out.println("Record Updated");
					}
				}
				System.out.println("Record Updated");
			} else {
				System.out.println("Failed to update role information in Role table");
			}
		return roleDTO;
	}
	@Override
	public List<RoleDTO> getEditRoles(String roleType) {
		String query="select roleid as roleId, rolename as roleName from um_role_master where lower(roletype)=?";
		List<RoleDTO> user=jdbcTemplate.query(query,new BeanPropertyRowMapper<RoleDTO>(RoleDTO.class), new Object[]{roleType.toLowerCase()});
		return user;
	}
	
}

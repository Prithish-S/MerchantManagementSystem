package mm.com.merchant.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import mm.com.merchant.constant.TransactConstant;
import mm.com.merchant.dto.LoginDTO;
import mm.com.merchant.dto.UserDTO;

@Repository
public class LoginRepoImpl implements LoginRepo{
	

	@Autowired
	@Qualifier(value = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public UserDTO getUserInfo(LoginDTO loginDTO) {

	      String methodName = "getUserInfo";
	      UserDTO userDTO= null;
	      String loginId=loginDTO.getLoginId();
	      System.out.println(">>>>>>>loginId>>>>"+loginId);

	      if(!loginId.isEmpty()) {
		      userDTO= jdbcTemplate.queryForObject("SELECT userid as userId,loginid as loginId, firstname as firstName, lastname as lastName,country,password,pwdrecques,pwdrecans,status,ispwdreq as isPwdReq FROM UM_USER_MASTER where loginid=?", new BeanPropertyRowMapper<UserDTO>(UserDTO.class) , new Object[]{loginId});
	      }
	      else {
			   userDTO= jdbcTemplate.queryForObject("SELECT userid as userId,loginid as loginId, firstname as firstName, lastname as lastName,country,password,pwdrecques,pwdrecans,status,ispwdreq as isPwdReq FROM UM_USER_MASTER where userid=?", new BeanPropertyRowMapper<UserDTO>(UserDTO.class) , new Object[]{loginDTO.getUserId()});
	      }
	      System.out.println("the login id is available in database:"+userDTO.getPwdrecans());
	      return userDTO;
	      
	}
	@Override
	public UserDTO doLogin(LoginDTO loginDTO) {
		loginDTO.setLoginStatus(TransactConstant.LOGIN_SUCESSSTATUS);
		UserDTO userDTO=getUserInfo(loginDTO);
		loginDTO.setUserId(userDTO.getUserId());
		if (userDTO.getIsPwdReq().equals(TransactConstant.USER_DEF_FIRSTLOGIN)) {
			System.out.println("First Time Login: User password change required");
			// Here need to do first time login changes
			loginDTO.setIsPwdReq(TransactConstant.USER_DEF_FIRSTLOGIN);
			userDTO.setStatusCode(TransactConstant.USER_DEF_FIRSTLOGIN);
		}
		else {
			userDTO.setStatusCode(TransactConstant.LOGIN_SUCESSSTATUS);
		}
		return userDTO;
	}
	
	
	@Override
	public boolean checkLoginID(String loginId) {
		
		String methodName = "checkLoginID";
		int count = 0;
		boolean flag = false;
		String loginChkQuery="select COUNT(LOGINID) from UM_USER_MASTER WHERE UPPER(LOGINID)=UPPER(?) AND STATUS='A'";
		count = jdbcTemplate.queryForObject(loginChkQuery,Integer.class, new Object[] { loginId });
		System.out.println("LoginRepoImpl>>>>>"+count);
		if (count > 0) {
			System.out.println("login id is found in table");
			flag = true;
		}
		System.out.println(flag);
		return flag;
}
	
	@Override
	public String getpwdRecQues(String loginId) {
		String methodName = "getpwdRecQues";
		String pwdRecQues = "";
		String pwdRecQuery="select quesname from um_user_pwdrecques, UM_USER_MASTER where PWDRECQUES = cast(quesid AS VarChar) and UPPER(LOGINID)=UPPER(?)";
		pwdRecQues = jdbcTemplate.queryForObject(pwdRecQuery, String.class,	new Object[] { loginId });
		return pwdRecQues;
}
	
	@Override
	public long verifyPwdRec(LoginDTO loginDTO) {
		String methodName = "verifyPwdRec";
		boolean flag = false;
			long count = 0;
		String query="SELECT USERID as userId FROM UM_USER_MASTER WHERE upper(LOGINID)=upper(?) AND lower(PWDRECANS)= lower(?) ";
		count = jdbcTemplate.queryForObject(query,int.class,new Object[] { loginDTO.getLoginId(), loginDTO.getPwdRecAns()});
		System.out.println(count);
		 if (count > 0) {
				flag = true;
		}
		if(flag){
	    		  System.out.println("succesfully logged exception");
	    }
		return count;
		}
	
	
	@Override
	public boolean isPwdRecAns(long userid) {
		String methodName = "isPwdRecAns";
		boolean flag = false;
		String query="SELECT PWDRECANS FROM UM_USER_MASTER WHERE USERID=?";
		String pwdRecAns = jdbcTemplate.queryForObject(query,String.class, new Object[] {userid});
		if (pwdRecAns != null && pwdRecAns.length() > 0) {
			flag =true;
		}
		else {
			flag=false;
		}
		System.out.println(">>>>>>>>>>>"+flag);
		return flag;
}
	
	@Override
	public LoginDTO recoveryUserPwd(LoginDTO loginDTO) {
		// TODO Auto-generated method stub
		String methodName = "changeUserPwd";

		System.out.println("Update pwd change");
		String query="UPDATE UM_USER_MASTER SET PASSWORD= ? WHERE USERID = ?";
		// this method should be part of common class

		int rows = jdbcTemplate.update(query,new Object[] { loginDTO.getConfirmPassword(), loginDTO.getUserId() });
		if (rows > 0) {
			System.out.println("Pwd updated  successfully");
			loginDTO.setStatusCode(TransactConstant.TRANSACT_SUCCESS);
		} else {
			System.out.println("Pwd updation failed");
			loginDTO.setStatusCode(TransactConstant.TRANSACT_FAIL);
		}
		return loginDTO;
	}
	
	
	@Override
	public LoginDTO changeUserPassword(LoginDTO loginDTO) {
	      String methodName = "changeUserPassword";
	      String query="UPDATE UM_USER_MASTER SET ISPWDREQ = ?,PASSWORD= ? WHERE USERID = ?";
	      int rows = jdbcTemplate.update(query, new Object[]{loginDTO.getIsPwdReq(), loginDTO.getConfirmPassword(), loginDTO.getUserId()});
	      if (rows > 0) {
	    	    if (!isPwdRecAns(loginDTO.getUserId())) {
						boolean recRow = updatePwdRecQuest(loginDTO);
					if (recRow) {
						System.out.println("Pwd updated  successfully");
						loginDTO.setStatusCode(TransactConstant.TRANSACT_SUCCESS);
					}
				}}
	      else
	      {
	    	  System.out.println("Password updation failed");
	         loginDTO.setStatusCode(TransactConstant.TRANSACT_FAIL);
	      }
	      return loginDTO;
	   }

	
	public boolean updatePwdRecQuest(LoginDTO loginDTO) {
		// TODO Auto-generated method stub
		boolean flag=false;
			String loginId = loginDTO.getLoginId();
			String query="UPDATE UM_USER_MASTER SET PWDRECQUES = ? , PWDRECANS = ? WHERE lower(LOGINID)= lower(?)";
			int recRow = jdbcTemplate.update(query,new Object[] { loginDTO.getPwdRecQues(), loginDTO.getPwdRecAns(), loginId});
			if (recRow > 0) {
				System.out.println("password updated  successfully");
				loginDTO.setStatusCode(TransactConstant.TRANSACT_SUCCESS);
				flag =true;	
			}
			 else
		      {
		    	 System.out.println("Password updation failed");
		         loginDTO.setStatusCode(TransactConstant.TRANSACT_FAIL);
		      }
		return flag;
	}
	@Override
	public List<LoginDTO> getLookUpPwdQues() {
		// TODO Auto-generated method stub
		String methodName = "getLookUpPwdQues";
		List<LoginDTO> lookupList = new ArrayList<LoginDTO>();
		String query="SELECT quesname AS lkpvalue, quesid AS lkpdesc FROM UM_USER_PWDRECQUES WHERE STATUS='A'";
		lookupList = jdbcTemplate.query(query,new BeanPropertyRowMapper<LoginDTO>(LoginDTO.class));
		return lookupList;
}
	
	  @Override
	   public void updateLogOutDate(LoginDTO loginDTO) {
	      String query="UPDATE UM_USER_MASTER SET LASTLOGOUTDATE=CURRENT_TIMESTAMP WHERE USERID=?";
	      	 jdbcTemplate.update(query, loginDTO.getUserId());
	    	  System.out.println("userid of loginDTO inside"+loginDTO.getUserId());
	   
	   }

}

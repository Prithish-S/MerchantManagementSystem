package mm.com.merchant.repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mm.com.merchant.dto.CityDTO;
import mm.com.merchant.dto.CountryDTO;
import mm.com.merchant.dto.StateDTO;
import mm.com.merchant.dto.UserDTO;

@Repository
public class UserRepoImpl implements UserRepo {

	@Autowired
	@Qualifier(value = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<CountryDTO> getCountryDetail() {
		
		List<CountryDTO> countryList=null;
		countryList = jdbcTemplate.query("SELECT countryid, countryname FROM UM_USER_COUNTRY", new BeanPropertyRowMapper<CountryDTO>(CountryDTO.class));
		return countryList;
		}
	
	@Override
	public List<StateDTO> getStateDetail(int countryId) {
	
		List<StateDTO> stateList = null;
		stateList = jdbcTemplate.query("SELECT S.stateid, S.statename from UM_USER_STATE as S inner join UM_USER_COUNTRY as C on C.countryid=S.countryid where S.countryid=?",new BeanPropertyRowMapper<StateDTO>(StateDTO.class), new Object[] {countryId});
		return stateList;
		}
	
	
	@Override
	public List<CityDTO> getCityDetail(int stateId, int countryId) {
	
		 List<CityDTO> cityList = null;
		 cityList = jdbcTemplate.query("SELECT cityid, cityname from UM_USER_CITY where stateid=? and countryid=?", new BeanPropertyRowMapper<CityDTO>(CityDTO.class),new Object[] {stateId,countryId});
		 return cityList;
		}


	@Override
	public UserDTO addUser(UserDTO userDTO) throws Exception {
		String methodName = "addUser";
		String loginId=userDTO.getLoginId();
		String pwd=generatePassword(8).toString();
		System.out.println("password"+pwd);
		userDTO.setPassword(pwd);
		String userIdSeq="SELECT nextval('USERSEQ')";
		 int userId=jdbcTemplate.queryForObject(userIdSeq, Integer.class);
		 userDTO.setUserId(userId);
		try {
		     FileWriter myWriter = new FileWriter("C:\\Users\\prith\\OneDrive\\Desktop\\user_login\\file.txt");
		      BufferedWriter buffer = new BufferedWriter(myWriter);  
		      buffer.write("loginID"+loginId+"Password"+pwd);
		      buffer.close();
		      System.out.println("Successfully wrote to the file.");
		      String UserQuery = "INSERT INTO UM_USER_MASTER (userid, loginid, firstName, lastName,country,state,city,password,roletype,assrolelist,status,ispwdreq) values (?,?,?,?,?,?,?,?,?,?,'A','Y')";
		      int status = jdbcTemplate.update(UserQuery, userDTO.getUserId(),userDTO.getLoginId(), userDTO.getFirstName(), userDTO.getLastName(),
				userDTO.getCountry(),userDTO.getState(),userDTO.getCity(),userDTO.getPassword(),userDTO.getRoleType(),userDTO.getAssRoleList());
		if (status > 0) {
			System.out.println("record Inserted into table Successful..." + methodName);
		}
		 } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return userDTO;
	}
	
	
	@Override
	public UserDTO getUser(int userId) {
		String methodName = "getUser";
		UserDTO userDTO = new UserDTO();
			userDTO = jdbcTemplate.queryForObject("SELECT userid as userId,loginid as loginId, firstname as firstName, lastName as lastName,country,password FROM UM_USER_MASTER where userid=?",
					new BeanPropertyRowMapper<UserDTO>(UserDTO.class),new Object[] {userId});
				return userDTO;
	}
	
	@Override
	public List<UserDTO> getUserList() {
		String methodName = "getUserList";
		List<UserDTO> userList = new ArrayList<UserDTO>();
		try {
			userList = jdbcTemplate.query("SELECT userid as userId,loginId as loginId, firstName, lastName,country,password,roletype as roleType,assrolelist as assRoleList FROM UM_USER_MASTER",
					new BeanPropertyRowMapper<UserDTO>(UserDTO.class));

		} catch (EmptyResultDataAccessException ex) {
			System.out.println("An Exception occured in User>>>" + methodName + " and the exception is>>>" + ex);
		}
		return userList;
	}
	
	 private static String generatePassword(int length) {
	        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	        StringBuilder password = new StringBuilder(length);
	        Random random = new Random();

	        for (int i = 0; i < length; i++) {
	            int index = random.nextInt(characters.length());
	            password.append(characters.charAt(index));
	        }

	        return password.toString();
	    }
}

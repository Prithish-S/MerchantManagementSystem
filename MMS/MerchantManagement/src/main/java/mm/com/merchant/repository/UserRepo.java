package mm.com.merchant.repository;

import java.util.List;

import mm.com.merchant.dto.CityDTO;
import mm.com.merchant.dto.CountryDTO;
import mm.com.merchant.dto.StateDTO;
import mm.com.merchant.dto.UserDTO;

public interface UserRepo {
	public List<CountryDTO> getCountryDetail();
	public List<StateDTO> getStateDetail(int countryId);
	public List<CityDTO> getCityDetail(int stateId, int countryId);
	public UserDTO addUser(UserDTO user)throws Exception ; //to add a user
	public List<UserDTO> getUserList();//to view all users
	public UserDTO getUser(int userId);
}

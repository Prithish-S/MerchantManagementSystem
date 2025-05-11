package mm.com.merchant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mm.com.merchant.dto.CityDTO;
import mm.com.merchant.dto.CountryDTO;
import mm.com.merchant.dto.StateDTO;
import mm.com.merchant.dto.UserDTO;
import mm.com.merchant.repository.UserRepo;

@Service
public class UserSerrviceImpl implements UserService {

	@Autowired
	UserRepo userRepo;

	@Override
	public UserDTO addUser(UserDTO userDTO) throws Exception {
		UserDTO user = userRepo.addUser(userDTO);
		return user;
	}

	@Override
	public List<UserDTO> getUserList() {
		List<UserDTO> userList = userRepo.getUserList();
		return userList;
	}
	
	@Override
	public UserDTO getUser(int userId) {
		return userRepo.getUser(userId);
	}

	@Override
	public List<CountryDTO> getCountryDetail() {
		return userRepo.getCountryDetail();
	}

	@Override
	public List<StateDTO> getStateDetail(int countryId) {

		return userRepo.getStateDetail(countryId);
	}

	@Override
	public List<CityDTO> getCityDetail(int stateId, int countryId) {
		return userRepo.getCityDetail(stateId, countryId);
	}

	
}

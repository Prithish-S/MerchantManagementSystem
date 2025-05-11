package mm.com.merchant.repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import mm.com.merchant.constant.TransactConstant;
import mm.com.merchant.dto.MerchantDTO;
import mm.com.merchant.dto.PosDTO;

@Repository
public class MerchantRepoImpl implements MerchantRepo {

	@Autowired
	@Qualifier(value = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<MerchantDTO> getMerchantList(){
		List<MerchantDTO> merchantList = new ArrayList<MerchantDTO>();
		String sql="SELECT merchant_id, business_name, business_address, city, state, zip_code, country, business_phone, business_email, website_url, owner_name, owner_email, owner_phone, business_type, registration_number, tax_id, merchant_status, created_at, updated_at FROM mm_merchant_master";
		merchantList = jdbcTemplate.query(sql,new BeanPropertyRowMapper<MerchantDTO>(MerchantDTO.class));
		return merchantList;

	}
	@Override
	public MerchantDTO getMerchant(String merchant_id){
		MerchantDTO merchant = new MerchantDTO();
		String sql="SELECT merchant_id, business_name, business_address, city, state, zip_code, country, business_phone, business_email, website_url, owner_name, owner_email, owner_phone, business_type, registration_number, tax_id, merchant_status, created_at, updated_at FROM mm_merchant_master where merchant_id=?";
		merchant = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<MerchantDTO>(MerchantDTO.class),new Object[]{merchant_id});
		return merchant;

	}

	@Override
	public MerchantDTO addMerchant(MerchantDTO merchantDTO) throws Exception {
		String methodName = "addMerchant";
		String merSeq = "SELECT nextval('merchantid')";
		int incrId = jdbcTemplate.queryForObject(merSeq, Integer.class);
		merchantDTO.setMerchant_id("MER" + incrId);
		System.out.println("MERID" + merchantDTO.getMerchant_id());
		String termQuery = "INSERT INTO mm_merchant_master(merchant_id, business_name, business_address, city, state, zip_code, country, business_phone, business_email, website_url, owner_name, owner_email, owner_phone, business_type, registration_number, tax_id, merchant_status, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int status = jdbcTemplate.update(termQuery, merchantDTO.getMerchant_id(),merchantDTO.getBusiness_name(),merchantDTO.getBusiness_address(),
				merchantDTO.getCity(), merchantDTO.getState(), merchantDTO.getZip_code(),merchantDTO.getCountry(), merchantDTO.getBusiness_phone(),
				merchantDTO.getBusiness_email(), merchantDTO.getWebsite_url(),merchantDTO.getOwner_name(),merchantDTO.getOwner_email(),merchantDTO.getOwner_phone(),
				merchantDTO.getBusiness_type(),merchantDTO.getRegistration_number(),merchantDTO.getTax_id(),merchantDTO.getMerchant_status(),
				merchantDTO.getCreated_at(),merchantDTO.getUpdated_at());
		if (status > 0) {
			merchantDTO.setStatusCode(TransactConstant.TRANSACT_SUCCESS);
			System.out.println("record Inserted into table Successful..." + methodName);
		}
		else {
			merchantDTO.setStatusCode(TransactConstant.TRANSACT_FAIL);
			System.out.println("record Insertion Failed" + methodName);

		}
		return merchantDTO;
	}
	
	

	@Override
	public PosDTO addMerchantPos(PosDTO posDTO) throws Exception {
		String methodName = "addMerchantPos";
		String posSeq = "SELECT nextval('posid')";
		int incrId = jdbcTemplate.queryForObject(posSeq, Integer.class);
		posDTO.setPos_machine_id("POS" + incrId);
		System.out.println("MERID>>>" + posDTO.getMerchant_id());
		System.out.println("POSID>>>" + posDTO.getPos_machine_id());

		String sql = "INSERT INTO mm_merchant_pos(pos_machine_id, merchant_id, machine_model, serial_number, install_date, status, ip_address, location, last_maintenance_date, created_at, updated_at)	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int status = jdbcTemplate.update(sql,posDTO.getPos_machine_id(),posDTO.getMerchant_id(),posDTO.getMachine_model(),posDTO.getSerial_number(),posDTO.getInstall_date(),posDTO.getStatus(),posDTO.getIp_address(),posDTO.getLocation(),
				posDTO.getLast_maintenance_date(),posDTO.getCreated_at(),posDTO.getUpdated_at());
		if (status > 0) {
			posDTO.setStatusCode(TransactConstant.TRANSACT_SUCCESS);
			System.out.println("record Inserted into table Successful..." + methodName);
		}
		else {
			posDTO.setStatusCode(TransactConstant.TRANSACT_FAIL);
			System.out.println("record Insertion Failed" + methodName);

		}
		return posDTO;
	}

	@Override
	public List<PosDTO> getPosList(String MerchantId) {
		List<PosDTO> posList = new ArrayList<PosDTO>();
		String sql="SELECT pos_machine_id, merchant_id, machine_model, serial_number, install_date, status, ip_address, location, last_maintenance_date, created_at, updated_at FROM mm_merchant_pos where merchant_id=?";
		posList = jdbcTemplate.query(sql,new BeanPropertyRowMapper<PosDTO>(PosDTO.class),new Object[]{MerchantId});
		return posList;
	}
	
	

}

package mm.com.merchant.repository;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import mm.com.merchant.constant.TransactConstant;
import mm.com.merchant.dto.CountryDTO;
import mm.com.merchant.dto.TransactionsDTO;

@Repository
public class ExtractionRepoImpl implements ExtractionRepo {

	@Autowired
	@Qualifier(value = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public String insertIntoDatabase(TransactionsDTO transactionsDTO) throws Exception {
		String methodName = "insertIntoDatabase";
		String sql = "INSERT INTO mer_pos_transactions(merchant_id, record_type, transaction_id, merchant_code, card_number, expiry_date, tran_date, settle_date, amount_cents, unique_id, product_code, reference_number, tran_amount, status, discount, tran_type, flag, item_count, processor, merchant_name, location, placeholder, country_code, customer_id, zeroplaceholder, code1, code2, code3, code4, redacted, code5, pos_id, extractdate)"
				+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_DATE)";
		int status1 = jdbcTemplate.update(sql, transactionsDTO.getMerchant_id(), transactionsDTO.getRecord_type(),
				transactionsDTO.getTransaction_id(), transactionsDTO.getMerchant_code(), transactionsDTO.getCard_number(),
				transactionsDTO.getExpiry_date(), transactionsDTO.getTran_date(),transactionsDTO.getSettle_Date(),
				transactionsDTO.getAmount_cents(), transactionsDTO.getUnique_id(),transactionsDTO.getProduct_code(),
				transactionsDTO.getReference_number(), transactionsDTO.getTran_amount(), transactionsDTO.getStatus(),
				transactionsDTO.getDiscount(), transactionsDTO.getTran_type(), transactionsDTO.getFlag(),
				transactionsDTO.getItem_count(), transactionsDTO.getProcessor(),transactionsDTO.getMerchant_name(),transactionsDTO.getLocation(),
				transactionsDTO.getPlaceholder(),transactionsDTO.getCountry_code(),transactionsDTO.getCustomer_id(),transactionsDTO.getZeroplaceholder(),
				transactionsDTO.getCode1(),transactionsDTO.getCode2(),transactionsDTO.getCode3(),transactionsDTO.getCode4(),
				transactionsDTO.getRedacted(),transactionsDTO.getCode5(),transactionsDTO.getPos_id());
		if (status1 == 1) {
			transactionsDTO.setStatus(TransactConstant.TRANSACT_SUCCESS);
		} else {
			transactionsDTO.setStatus(TransactConstant.TRANSACT_FAIL);
		}
		return transactionsDTO.getStatus();
	}

	@Override
	public List<TransactionsDTO> getTransactions() {
		List<TransactionsDTO> tranList = null;
		tranList = jdbcTemplate.query("SELECT merchant_id, record_type, transaction_id, merchant_code, card_number, expiry_date, tran_date, settle_date, amount_cents, unique_id, product_code, reference_number, tran_amount, status, discount, tran_type, flag, item_count, processor, merchant_name, location, placeholder, country_code, customer_id, zeroplaceholder, code1, code2, code3, code4, redacted, code5, pos_id, extractdate FROM mer_pos_transactions",new BeanPropertyRowMapper<TransactionsDTO>(TransactionsDTO.class));
		return tranList;
	}
	
	@Override
	 public TransactionsDTO summaryReportGen(String csvFile)throws IOException {
		TransactionsDTO transactionDTO=null;
        String sql = "SELECT COUNT(*) AS recordCount, SUM(CAST(tran_amount AS NUMERIC)) AS totalSum FROM mer_pos_transactions";
        transactionDTO=jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<TransactionsDTO>(TransactionsDTO.class));
		return transactionDTO;
	}

}

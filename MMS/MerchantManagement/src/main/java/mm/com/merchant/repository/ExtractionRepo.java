package mm.com.merchant.repository;

import java.io.IOException;
import java.util.List;

import mm.com.merchant.dto.TransactionsDTO;

public interface ExtractionRepo {
	public List<TransactionsDTO> getTransactions();
	public String insertIntoDatabase(TransactionsDTO transactionsDTO) throws Exception ;
	 public TransactionsDTO summaryReportGen(String csvFile)throws IOException ;

}

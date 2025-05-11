package mm.com.merchant.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mm.com.merchant.dto.TransactionsDTO;
import mm.com.merchant.repository.ExtractionRepoImpl;

@Service
public class ExtractionServiceImpl implements ExtractionService {
	@Autowired
	ExtractionRepoImpl extractionRepo;

	@Override
	public String readFile(String filePath) throws Exception {
		String fileReadStatus = "N";
		try (BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(filePath))))) {
			String dataLine;
			String lastLine = null;

			while ((dataLine = br.readLine()) != null) {
				lastLine = dataLine;
				  String[] fields = dataLine.split("\\|");

			        // Check if the number of fields matches the expected count
			        if (fields.length != 32) {
			            System.err.println("Unexpected number of fields: " + fields.length);
			           // return;
			        }
			        
			        // Extract fields
			        String merchant_id = fields[0];
			        String record_type = fields[1];
			        String transaction_id = fields[2];
			        String merchant_code = fields[3];
			        String card_number = fields[4];
			        String expiry_date = fields[5];
			        String tran_date = fields[6];
			        String settle_Date = fields[7];
			        String amount_cents = fields[8];
			        String unique_id = fields[9];
			        String product_code = fields[10];
			        String reference_number = fields[11];
			        String tran_amount = fields[12];
			        String status = fields[13];
			        String discount = fields[14];
			        String tran_type = fields[15];
			        String flag = fields[16];
			        String item_count = fields[17];
			        String processor = fields[18];
			        String merchant_name = fields[19];
			        String location = fields[20];
			        String placeholder = fields[21];
			        String country_code = fields[22];
			        String customer_id = fields[23];
			        String zeroplaceholder = fields[24];
			        String code1 = fields[25];
			        String code2 = fields[26];
			        String code3 = fields[27];
			        String code4 = fields[28];
			        String redacted = fields[29];
			        String code5 = fields[30];
			        String pos_id=fields[31];

				TransactionsDTO transactionsDTO = new TransactionsDTO();
				transactionsDTO.setMerchant_id(merchant_id);
				transactionsDTO.setRecord_type(record_type);
				transactionsDTO.setTransaction_id(transaction_id);
				transactionsDTO.setMerchant_code(merchant_code);
				transactionsDTO.setCard_number(card_number);
				transactionsDTO.setExpiry_date(expiry_date);
				transactionsDTO.setTran_date(tran_date);
				transactionsDTO.setSettle_Date(settle_Date);
				transactionsDTO.setAmount_cents(amount_cents);
				transactionsDTO.setUnique_id(unique_id);
				transactionsDTO.setProduct_code(product_code);
				transactionsDTO.setReference_number(reference_number);
				transactionsDTO.setTran_amount(tran_amount);
				transactionsDTO.setStatus(status);
				transactionsDTO.setDiscount(discount);
				transactionsDTO.setTran_type(tran_type);
				transactionsDTO.setFlag(flag);
				transactionsDTO.setItem_count(item_count);
				transactionsDTO.setProcessor(processor);
				transactionsDTO.setMerchant_name(merchant_name);
				transactionsDTO.setLocation(location);
				transactionsDTO.setPlaceholder(placeholder);
				transactionsDTO.setCountry_code(country_code);
				transactionsDTO.setCustomer_id(customer_id);
				transactionsDTO.setZeroplaceholder(zeroplaceholder);
				transactionsDTO.setCode1(code1);
				transactionsDTO.setCode2(code2);
				transactionsDTO.setCode3(code3);
				transactionsDTO.setCode4(code4);
				transactionsDTO.setRedacted(redacted);
				transactionsDTO.setCode5(code5);
				transactionsDTO.setPos_id(pos_id);
				extractionRepo.insertIntoDatabase(transactionsDTO);
				fileReadStatus = "P";
			}

			// After reading all lines, print the last line if it exists
			if (lastLine != null) {
				System.out.println("File Read Completed....");
				fileReadStatus = "C";

			}
		}
		return fileReadStatus;

	}

	@Override
	public void detailReportGen(String csvFile) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));
		writer.write("merchant_id, record_type, transaction_id, merchant_code, card_number, expiry_date, tran_date, settle_date, amount_cents, unique_id, product_code, reference_number, tran_amount, status, discount, tran_type, flag, item_count, processor, merchant_name, location, placeholder, country_code, customer_id, zeroplaceholder, code1, code2, code3, code4, redacted, code5, pos_id, extractdate");
		writer.newLine();
		List<TransactionsDTO> tranList = extractionRepo.getTransactions();
		for (TransactionsDTO transaction : tranList) {
			StringBuilder row = new StringBuilder();
				 row.append(transaction.getMerchant_id()).append(",").append(transaction.getRecord_type()).append(",")
					.append(transaction.getTransaction_id()).append(",").append(transaction.getMerchant_code())
					.append(",").append(transaction.getCard_number()).append(",").append(transaction.getExpiry_date())
					.append(",").append(transaction.getTran_date()).append(",").append(transaction.getSettle_Date())
					.append(",").append(transaction.getAmount_cents()).append(",").append(transaction.getUnique_id())
					.append(",").append(transaction.getProduct_code()).append(",").append(transaction.getReference_number())
					.append(",").append(transaction.getTran_amount()).append(",").append(transaction.getStatus())
					.append(",").append(transaction.getDiscount()).append(",").append(transaction.getTran_type())
					.append(",").append(transaction.getFlag()).append(",").append(transaction.getItem_count())
					.append(",").append(transaction.getProcessor()).append(",").append(transaction.getMerchant_name())
					.append(",").append(transaction.getLocation()).append(",").append(transaction.getPlaceholder())
					.append(",").append(transaction.getCountry_code()).append(",").append(transaction.getCustomer_id())
					.append(",").append(transaction.getZeroplaceholder()).append(",").append(transaction.getCode1())
					.append(",").append(transaction.getCode2()).append(",").append(transaction.getCode3())
					.append(",").append(transaction.getCode4()).append(",").append(transaction.getRedacted())
					.append(",").append(transaction.getCode5()).append(",").append(transaction.getPos_id())
					.append(",").append(transaction.getExtractdate()); 
																								

			writer.write(row.toString());
			writer.newLine();
		}
		writer.close();
		System.out.println("Report generated successfully: " + csvFile);

	}

	@Override
	public void summaryReportGen(String csvFile) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));
		TransactionsDTO transactionDTO = extractionRepo.summaryReportGen(csvFile);
		if (transactionDTO == null) {
		    System.err.println("No data retrieved from the database.");
		    return; // Early exit if no data
		}
		 // Write a simple report to the file
        writer.newLine(); // Add a blank line
        writer.write("=============== SUMMARY REPORT ================");
        writer.newLine();
        writer.write("TOTAL RECORDS: " + transactionDTO.getRecordCount());
        writer.newLine();
        writer.write("TOTAL SUM OF TRANSACTION AMOUNT: " + transactionDTO.getTotalSum());
        writer.newLine();
        writer.write("===============================================");
        writer.newLine();
        writer.close();
        System.out.println("Summary report generated successfully: " + csvFile);
	}

}

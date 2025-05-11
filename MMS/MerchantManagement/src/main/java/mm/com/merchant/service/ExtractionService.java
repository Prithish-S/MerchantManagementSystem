package mm.com.merchant.service;

import java.io.IOException;

public interface ExtractionService {
	public String readFile(String filePath) throws Exception;
	public void detailReportGen(String csvFile) throws IOException ;
	 public void summaryReportGen(String path)throws IOException ;
	
}

package mm.com.merchant.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class FileMasterDTO {
	private int	userId;
	private String filename;
	private String 	file_dest_path;
	private String availFileList;
	private String selectFileList;
	private String status;
	private String statusCode;
	private String extractdate;
	private String processName;
	private String onDate;
	public String getExtractdate() {
		return extractdate;
	}

	public void setExtractdate(String extractdate) {
		this.extractdate = extractdate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFile_dest_path() {
		return file_dest_path;
	}

	public void setFile_dest_path(String file_dest_path) {
		this.file_dest_path = file_dest_path;
	}

	public String getAvailFileList() {
		return availFileList;
	}

	public void setAvailFileList(String availFileList) {
		this.availFileList = availFileList;
	}

	public String getSelectFileList() {
		return selectFileList;
	}

	public void setSelectFileList(String selectFileList) {
		this.selectFileList = selectFileList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getOnDate() {
		return onDate;
	}

	public void setOnDate(String onDate) {
		this.onDate = onDate;
	}
	
}

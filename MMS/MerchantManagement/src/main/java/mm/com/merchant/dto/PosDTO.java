package mm.com.merchant.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class PosDTO {
	private String pos_machine_id;
	private int userId;
	private String merchant_id;
	private String machine_model;
	private String serial_number;
	private Date install_date;
	private String status;
	private String ip_address;
	private String location;
	private Date last_maintenance_date;
	private Date created_at;
	private Date updated_at;
	private String statusCode;
	
	public String getPos_machine_id() {
		return pos_machine_id;
	}
	public void setPos_machine_id(String pos_machine_id) {
		this.pos_machine_id = pos_machine_id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}
	public String getMachine_model() {
		return machine_model;
	}
	public void setMachine_model(String machine_model) {
		this.machine_model = machine_model;
	}
	public String getSerial_number() {
		return serial_number;
	}
	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}
	
	public Date getInstall_date() {
		return install_date;
	}
	public void setInstall_date(Date install_date) {
		this.install_date = install_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getLast_maintenance_date() {
		return last_maintenance_date;
	}
	public void setLast_maintenance_date(Date last_maintenance_date) {
		this.last_maintenance_date = last_maintenance_date;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	
}

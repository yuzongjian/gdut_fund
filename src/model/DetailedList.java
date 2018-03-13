package model;

import java.io.Serializable;
import java.sql.Date;

public class DetailedList implements Serializable {
	private String id;
	private String name;
	private String type;
	private String department;
	private String applicant;
	private String science_code;
	private String apply_code;
	private String version;
	private Date create_time;
	private String user_id;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public String getScience_code() {
		return science_code;
	}
	public void setScience_code(String science_code) {
		this.science_code = science_code;
	}
	public String getApply_code() {
		return apply_code;
	}
	public void setApply_code(String apply_code) {
		this.apply_code = apply_code;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
}

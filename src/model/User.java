package model;

public class User {

	private String id;
	private String account;
	private String name;
	private String password;
	private String department;
	private String sex;
	private String birthday;
	private String education_background;
	private String job_title;
	private String telephone;
	private String state;
	private String email;

	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", name=" + name + ", password=" + password + ", department="
				+ department + ", sex=" + sex + ", birthday=" + birthday + ", education_background="
				+ education_background + ", job_title=" + job_title + ", telephone=" + telephone + ", state=" + state
				+ ", email=" + email + ", getId()=" + getId() + ", getAccount()=" + getAccount() + ", getName()="
				+ getName() + ", getPassword()=" + getPassword() + ", getDepartment()=" + getDepartment()
				+ ", getSex()=" + getSex() + ", getBirthday()=" + getBirthday() + ", getEducation_background()="
				+ getEducation_background() + ", getJob_title()=" + getJob_title() + ", getTelephone()="
				+ getTelephone() + ", getState()=" + getState() + ", getEmail()=" + getEmail() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEducation_background() {
		return education_background;
	}

	public void setEducation_background(String education_background) {
		this.education_background = education_background;
	}

	public String getJob_title() {
		return job_title;
	}

	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

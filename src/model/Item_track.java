package model;

import java.util.List;

public class Item_track {

	private String id;
	private String user_id;
	private String name;
	private String department;
	private String email;
	private String research;
	private String team;
	private String team_leader;
	private String b_expert;
	private String s_expert;
	private String advice;
	private String title;
	private String apply_field;
	private String old_apply;
	private String apply_year;
	private String join_year;
	private String major_content;
	private String sci_first_count;
	private String sci_second_count;
	private String ei_count;
	private String invent_count;
	private String invent_accredit_count;
	private String utility_count;
	private String utility_accredit_count;
	private String situation;
	private String team_basic;
	private String old_name;
	private String evaluate;
	private String time;
	private String details;
	private String sign;
	private String telephone;
	private List<Item_track_add> list;

	public List<Item_track_add> getList() {
		return list;
	}

	public void setList(List<Item_track_add> list) {
		this.list = list;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getOld_name() {
		return old_name;
	}

	public void setOld_name(String old_name) {
		this.old_name = old_name;
	}

	public String getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getResearch() {
		return research;
	}

	public void setResearch(String research) {
		this.research = research;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getTeam_leader() {
		return team_leader;
	}

	public void setTeam_leader(String team_leader) {
		this.team_leader = team_leader;
	}

	public String getB_expert() {
		return b_expert;
	}

	public void setB_expert(String b_expert) {
		this.b_expert = b_expert;
	}

	public String getS_expert() {
		return s_expert;
	}

	public void setS_expert(String s_expert) {
		this.s_expert = s_expert;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getApply_field() {
		return apply_field;
	}

	public void setApply_field(String apply_field) {
		this.apply_field = apply_field;
	}

	public String getOld_apply() {
		return old_apply;
	}

	public void setOld_apply(String old_apply) {
		this.old_apply = old_apply;
	}

	public String getApply_year() {
		return apply_year;
	}

	public void setApply_year(String apply_year) {
		this.apply_year = apply_year;
	}

	public String getJoin_year() {
		return join_year;
	}

	public void setJoin_year(String join_year) {
		this.join_year = join_year;
	}

	public String getMajor_content() {
		return major_content;
	}

	public void setMajor_content(String major_content) {
		this.major_content = major_content;
	}

	public String getSci_first_count() {
		return sci_first_count;
	}

	public void setSci_first_count(String sci_first_count) {
		this.sci_first_count = sci_first_count;
	}

	public String getSci_second_count() {
		return sci_second_count;
	}

	public void setSci_second_count(String sci_second_count) {
		this.sci_second_count = sci_second_count;
	}

	public String getEi_count() {
		return ei_count;
	}

	public void setEi_count(String ei_count) {
		this.ei_count = ei_count;
	}

	public String getInvent_count() {
		return invent_count;
	}

	public void setInvent_count(String invent_count) {
		this.invent_count = invent_count;
	}

	public String getInvent_accredit_count() {
		return invent_accredit_count;
	}

	public void setInvent_accredit_count(String invent_accredit_count) {
		this.invent_accredit_count = invent_accredit_count;
	}

	public String getUtility_count() {
		return utility_count;
	}

	public void setUtility_count(String utility_count) {
		this.utility_count = utility_count;
	}

	public String getUtility_accredit_count() {
		return utility_accredit_count;
	}

	public void setUtility_accredit_count(String utility_accredit_count) {
		this.utility_accredit_count = utility_accredit_count;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public String getTeam_basic() {
		return team_basic;
	}

	public void setTeam_basic(String team_basic) {
		this.team_basic = team_basic;
	}

	@Override
	public String toString() {
		return "Item_track [id=" + id + ", user_id=" + user_id + ", name=" + name + ", department=" + department
				+ ", email=" + email + ", research=" + research + ", team=" + team + ", team_leader=" + team_leader
				+ ", b_expert=" + b_expert + ", s_expert=" + s_expert + ", advice=" + advice + ", title=" + title
				+ ", apply_field=" + apply_field + ", old_apply=" + old_apply + ", apply_year=" + apply_year
				+ ", join_year=" + join_year + ", major_content=" + major_content + ", sci_first_count="
				+ sci_first_count + ", sci_second_count=" + sci_second_count + ", ei_count=" + ei_count
				+ ", invent_count=" + invent_count + ", invent_accredit_count=" + invent_accredit_count
				+ ", utility_count=" + utility_count + ", utility_accredit_count=" + utility_accredit_count
				+ ", situation=" + situation + ", team_basic=" + team_basic + ", old_name=" + old_name + ", evaluate="
				+ evaluate + ", time=" + time + ", details=" + details + ", sign=" + sign + ", telephone=" + telephone
				+"list"+list
				+ "]";
	}

}

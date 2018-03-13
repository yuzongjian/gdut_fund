package model;

import java.io.Serializable;

public class Statistic implements Serializable{
	private String dept_name;
	private int teacherNum;
	private int itemTrackNum;
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public int getTeacherNum() {
		return teacherNum;
	}
	public void setTeacherNum(int teacherNum) {
		this.teacherNum = teacherNum;
	}
	public int getItemTrackNum() {
		return itemTrackNum;
	}
	public void setItemTrackNum(int itemTrackNum) {
		this.itemTrackNum = itemTrackNum;
	}
	
}

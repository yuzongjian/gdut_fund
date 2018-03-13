package service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dao.DepartmentDao;
import dao.Item_trackDao;
import dao.UserDao;
import model.Department;
import model.Statistic;
import service.StatisticService;

@Component
public class StatisticServiceImpl implements StatisticService {

	private DepartmentDao departmentDao;
	@Autowired
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	private UserDao userDao;
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	private Item_trackDao item_trackDao; 
	@Autowired
	public void setItem_trackDao(Item_trackDao item_trackDao) {
		this.item_trackDao = item_trackDao;
	}
	
	//获取部门id
	public String getDepartmentId(String name) {
		Map<String, String> hashmap = departmentDao.Select();
		Iterator iter = hashmap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			if (name.equals(entry.getValue())) {
				String id = (String) entry.getKey();
				return id;

			}

		}
		return null;
	}
	
	public List<Statistic> getStatistics(List<Department> depts, Integer firstYear, Integer secondYear) {
		List<Statistic> statistics = new ArrayList<Statistic>();
		for(Department dept : depts) {
			String dept_name = dept.getDept_name();
			String dept_id = getDepartmentId(dept_name);
			int teacherNum = userDao.selectByDept(dept_id);
			int itemTrackNum = item_trackDao.selectByDept(dept_name, firstYear, secondYear);
			Statistic statistic = new Statistic();
			statistic.setDept_name(dept_name);
			statistic.setItemTrackNum(itemTrackNum);
			statistic.setTeacherNum(teacherNum);
			statistics.add(statistic);
		}
		return statistics;
	}
	
	public List<Department> getDepts() {
		List<Department> depts = departmentDao.selectAllDepartment();
		return depts;
	}
	
	public List<Statistic> selectAllStatistic() {
		List<Department> depts = getDepts();
		return getStatistics(depts, null, null);
	}

	public List<Statistic> selectStatistic(String dept_name, String firstYear, String secondYear) {
		if(firstYear==null || "".equals(firstYear)) {
			firstYear = "2000";
		}
		if(secondYear==null || "".equals(secondYear)) {
			secondYear = "2199";
		}
		Integer firstYearNum = Integer.parseInt(firstYear);
		Integer secondYearNum = Integer.parseInt(secondYear);
		List<Department> depts;
		if(dept_name == null || "".equals(dept_name)) {
			depts = getDepts();
		}
		else {
			depts = departmentDao.selectDepartmentByName(dept_name);
		}
		return getStatistics(depts, firstYearNum, secondYearNum);
	}

}


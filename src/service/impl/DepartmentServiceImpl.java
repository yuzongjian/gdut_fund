package service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DepartmentDao;
import model.Department;
import service.DepartmentService;
import util.UUIDUtils;
@Service
public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentDao departmentDao;
	@Autowired
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	@Override
	public HashMap<String,String> Select() {
		return departmentDao.Select();
	}
	
	public List<Department> selectAllDepartment() {
		return departmentDao.selectAllDepartment();
	}

	public int insertDepartment(Department department) {
		List<Department> departments = departmentDao.selectAllDepartment();
		for(Department depat : departments) {
			if(depat.getDept_name().equals(department.getDept_name())) {
				return -1;
			}
		}
		department.setDept_id(UUIDUtils.getUUID());
		return departmentDao.insertDepartment(department);
	}

	public int updateDepartmentById(Department department) {
		return departmentDao.updateDepartmentById(department);
	}

	public int deleteDepartment(String dept_id) {
		return departmentDao.deleteDepartment(dept_id);
	}

	public List<Department>selectDepartmentByName(String dept_name) {
		return departmentDao.selectDepartmentByName(dept_name);
	}

	public Department selectDepartmentById(String dept_id) {
		return departmentDao.selectDepartmentById(dept_id);
	}
	
}

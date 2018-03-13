package service;

import java.util.HashMap;
import java.util.List;

import model.Department;

public interface DepartmentService {
	List<Department> selectAllDepartment();
	List<Department> selectDepartmentByName(String dept_name);
	Department selectDepartmentById(String dept_id);
	int insertDepartment(Department department);
	int updateDepartmentById(Department department);
	int deleteDepartment(String dept_id);
	HashMap<String,String>  Select();
}

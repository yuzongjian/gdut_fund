package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import dao.DepartmentDao;
import model.Department;

public class DepartmentDaoImpl extends SqlSessionDaoSupport implements DepartmentDao {

	@Override
	public HashMap<String,String>  Select() {
		HashMap<String, String> hashmap = new HashMap<String, String>();
		List<Department> departments = this.getSqlSession().selectList("model.department.mapper.select");
		for (Department b : departments) {
			hashmap.put(b.getDept_id(), b.getDept_name());
		}
		return hashmap;
	}
	
	public List<Department> selectAllDepartment() {
		return this.getSqlSession().selectList("model.department.mapper.selectAllDepartment");
	}

	public int insertDepartment(Department department) {
		return this.getSqlSession().insert("model.department.mapper.insertDepartment", department);
	}

	public int updateDepartmentById(Department department) {
		return this.getSqlSession().update("model.department.mapper.updateDepartmentById", department);
	}

	public int deleteDepartment(String dept_id) {
		return this.getSqlSession().delete("model.department.mapper.deleteDepartment", dept_id);
	}

	public List<Department> selectDepartmentByName(String dept_name) {
		Map<String,String> dn = new HashMap<String,String>();
		dn.put("dept_name", dept_name);
		return this.getSqlSession().selectList("model.department.mapper.selectDepartmentByName", dn);
	}

	public Department selectDepartmentById(String dept_id) {
		return this.getSqlSession().selectOne("model.department.mapper.selectDepartmentById", dept_id);
	}
	

}

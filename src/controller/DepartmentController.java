package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Department;
import service.DepartmentService;
import util.ExcelDownUtils;

@Controller
@RequestMapping(value="admin")
public class DepartmentController {

	private DepartmentService deparmtentService;
	@Autowired
	public void setDeparmtentService(DepartmentService deparmtentService) {
		this.deparmtentService = deparmtentService;
	}
	
	@RequestMapping(value="DepartmentList")
	public String departmentList(Model model) {
		model.addAttribute("departments", deparmtentService.selectAllDepartment());
		return "departmentList.jsp";
	}
	
	@RequestMapping(value="UpdateDepartment")
	public String updateDepartment(Model model, @RequestParam String dept_id) {
		model.addAttribute("department", deparmtentService.selectDepartmentById(dept_id));
		return "editDepartment.jsp";
	}

	@RequestMapping(value="UpdateDepartmentOver")
	public String updateDepartmentOver(Department department) {
		deparmtentService.updateDepartmentById(department);
		return "redirect:DepartmentList.do";
	}
	
	@RequestMapping(value="AddDepartment")
	public String addDepartment(Model model) {
		model.addAttribute("department", new Department());
		return "addDepartment.jsp";
	}
	
	@RequestMapping(value="AddDepartmentOver")
	public String addDepartmentOver(Department department) {
		deparmtentService.insertDepartment(department);
		return "redirect:DepartmentList.do";
	}
	
	@RequestMapping(value="DeleteDepartment")
	public String deleteDepartmentOver(@RequestParam String dept_id) {
		deparmtentService.deleteDepartment(dept_id);
		return "redirect:DepartmentList.do";
	}
	
	@RequestMapping(value="SearchDepartment")
	public String SearchDepartment(HttpServletRequest request, Model model) {
		String dept_name = request.getParameter("dept_name");
		model.addAttribute("departments", deparmtentService.selectDepartmentByName(dept_name));
		return "departmentList.jsp";
	}
	
	@RequestMapping(value="DownLoadDepartmentExcel")
	public String downloadExcel(HttpServletResponse response,HttpServletRequest request) {
		String filename = "部门列表";
		String[] keys= {"dept_name"};
		String[] columnNames = {"部门名称"};
		List<Map<String, Object>> list = getList();
		ExcelDownUtils.excelDownUtils(list, keys, columnNames, response, filename);
		return null;
	}
	
	public List<Map<String, Object>> getList(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Department> departments = deparmtentService.selectAllDepartment();
		Map<String, Object> m1 = new HashMap<String,Object>();
		m1.put("sheetName", "申报项目清单");
		list.add(m1);
		for(Department department : departments) {
			Map<String, Object> m = new HashMap<String,Object>();
			m.put("dept_name", department.getDept_name());	
			list.add(m);
		}
		return list;
	}
}

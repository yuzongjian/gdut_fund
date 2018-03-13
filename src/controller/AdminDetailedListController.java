package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.DetailedList;
import service.DepartmentService;
import service.DetailedListService;
import util.ExcelDownUtils;
import util.IsEmpty;
import util.PageUtil;
import util.StringUtil;

@Controller
@RequestMapping(value="admin")
public class AdminDetailedListController {
	private DetailedListService detailedListService;
	@Autowired 
	public void setdetailedListService(DetailedListService detailedListService) {
		this.detailedListService = detailedListService;
	}
	private DepartmentService departmentService;
	@Autowired
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	@RequestMapping(value="ProjectList")
	public String projectList(Model model,@RequestParam(value="page",required=false)String page,HttpServletRequest request) {
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("currpage", 1);
		}else{
			
		}
		int total =detailedListService.selectAllMyList(null,null).size();
		model.addAttribute("projectList", detailedListService.selectAllMyListPage(null,null,page));
		model.addAttribute("departments", departmentService.selectAllDepartment());
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/admin/ProjectList.do"+"?", total, Integer.parseInt(page), 7);
		model.addAttribute("pageCode", pageCode);
		return "projectList.jsp";
	}
	@RequestMapping(value="SearchOrChangProject")
	public String searchOrChang(@RequestParam String id, Model model){
		DetailedList detailedList = detailedListService.selectListById(id);
		model.addAttribute("project", detailedList);
		model.addAttribute("departments", departmentService.selectAllDepartment());
		return "editProject.jsp";
	}
	
	@RequestMapping(value="EditProject")
	public String editProject(@ModelAttribute DetailedList detailedList,Model model) {
		int flag = detailedListService.updateDetailedList(detailedList, detailedList.getUser_id());
		if(flag==-2) {
			model.addAttribute("error", "该用户当前学年已经申报过同项目的类型！");
			model.addAttribute("project", detailedList);
			model.addAttribute("departments", departmentService.selectAllDepartment());
			return "editProject.jsp";
		}
		return "redirect:ProjectList.do";
	}
	
	@RequestMapping(value="DeleteProject")
	public String deleteProject(@RequestParam String id) {
		detailedListService.deleteDetailedList(id, null);
		return "redirect:ProjectList.do";
	}
	
	@RequestMapping(value="SearchProject")
	public String searchProject(@RequestParam(value="page",required=false)String page,@RequestParam(value="name",required=false)String name,@RequestParam(value="type",required=false)String type,@RequestParam(value="dept_name",required=false)String dept_name,HttpServletRequest request, Model model) {
		HttpSession session=request.getSession();
		name = IsEmpty.isEmpty(name);
		type = IsEmpty.isEmpty(type);
		model.addAttribute("name", name);
		model.addAttribute("type", type);
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("currpage", 1);
		}else{
			
		}
		int total =detailedListService.selectMyList(null, dept_name, name, type).size();
		model.addAttribute("departments", departmentService.selectAllDepartment());
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/admin/SearchProject.do"+"?"+"name="+name+"&"+"type="+type+"&"+"dept_name="+dept_name+"&", total, Integer.parseInt(page), 7);
		model.addAttribute("pageCode", pageCode);
		model.addAttribute("projectList", detailedListService.selectMyListPage(null, dept_name, name, type,page));
		return "projectList.jsp";
	}
	
	@RequestMapping(value="DownLoadDetailListExcel")
	public String downloadExcel(HttpServletResponse response,HttpServletRequest request) {
		String filename = "申报项目清单";
		String[] keys= {"name","type","department","applicant","science_code","apply_code","version","create_time"};
		String[] columnNames = {"项目名称","项目类型","所属学院","申请人","科学部编码","申请代码","版本号","创建时间"};
		String name = IsEmpty.isEmpty(request.getParameter("name"));
		String type = IsEmpty.isEmpty(request.getParameter("type"));
		String dept_name = IsEmpty.isEmpty(request.getParameter("dept_name"));
		List<Map<String, Object>> list = getList(name,type,dept_name);
		ExcelDownUtils.excelDownUtils(list, keys, columnNames, response, filename);
		return null;
	}
	
	public List<Map<String, Object>> getList(String name,String type,String dept_name){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<DetailedList> detailedLists = detailedListService.selectMyList(null, dept_name, name, type);
		Map<String, Object> m1 = new HashMap<String,Object>();
		m1.put("sheetName", "申报项目清单");
		list.add(m1);
		for(DetailedList detailedList : detailedLists) {
			Map<String, Object> m = new HashMap<String,Object>();
			m.put("name", detailedList.getName());
			m.put("type", detailedList.getType());
			m.put("department", detailedList.getDepartment());
			m.put("applicant", detailedList.getApplicant());
			m.put("science_code", detailedList.getScience_code());
			m.put("apply_code", detailedList.getApply_code());
			m.put("version", detailedList.getVersion());
			m.put("create_time", detailedList.getCreate_time());
			list.add(m);
		}
		return list;
	}
}

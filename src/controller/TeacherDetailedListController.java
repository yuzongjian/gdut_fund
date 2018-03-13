package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.DetailedList;
import model.User;
import service.DepartmentService;
import service.DetailedListService;
import util.IsEmpty;
import util.PageUtil;
import util.StringUtil;

@Controller
@RequestMapping(value="teacher")
public class TeacherDetailedListController {
	
	
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
	public String projectList(Model model,HttpSession httpSession,@RequestParam(value="page",required=false)String page,HttpServletRequest request) {
		User user = (User)httpSession.getAttribute("user");
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("currpage", 1);
		}else{
			
		}
		int total =detailedListService.selectAllMyList(user.getId(),null).size();
		model.addAttribute("projectList", detailedListService.selectAllMyListPage(user.getId(),null,page));
		 String pageCode=PageUtil.getPagation(request.getContextPath()+"/teacher/ProjectList.do"+"?", total, Integer.parseInt(page), 7);
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
	public String editProject(@ModelAttribute DetailedList detailedList, HttpSession httpSession,Model model) {
		User user = (User)httpSession.getAttribute("user");
		int flag = detailedListService.updateDetailedList(detailedList,user.getId());
		if(flag==-2) {
			model.addAttribute("error", "您当前学年已经申报过同项目的类型！");
			model.addAttribute("project", detailedList);
			model.addAttribute("departments", departmentService.selectAllDepartment());
			return "editProject.jsp";
		}
		return "redirect:ProjectList.do";
	}
	
	@RequestMapping(value="DeleteProject")
	public String deleteProject(@RequestParam String id, HttpSession httpSession) {
		User user = (User)httpSession.getAttribute("user");
		detailedListService.deleteDetailedList(id, user.getId());
		return "redirect:ProjectList.do";
	}
	
	@RequestMapping(value="CreateProject")
	public String createProject(Model model) {
		DetailedList detailedList = new DetailedList();
		model.addAttribute("project", detailedList);
		model.addAttribute("departments", departmentService.selectAllDepartment());
		return "addProject.jsp";
	}
	
	@RequestMapping(value="CreateProjectOver")
	public String createProjectOver(@ModelAttribute DetailedList detailedList,HttpSession httpSession, Model model) {
		User user = (User)httpSession.getAttribute("user");
		detailedList.setUser_id(user.getId());
		int flag = detailedListService.insertDetailedList(detailedList);
		if(flag==-2) {
			model.addAttribute("error", "您当前学年已经申报过同项目的类型！");
			model.addAttribute("project", detailedList);
			model.addAttribute("departments", departmentService.selectAllDepartment());
			return "addProject.jsp";
		}
		return "redirect:ProjectList.do";
	}
	
	@RequestMapping(value="SearchProject")
	public String searchProject(HttpServletRequest request, Model model) {
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		name = IsEmpty.isEmpty(name);
		type = IsEmpty.isEmpty(type);
		User user = (User)(request.getSession().getAttribute("user"));
		model.addAttribute("name", name);
		model.addAttribute("type", type);
		model.addAttribute("projectList", detailedListService.selectMyList(user.getId(), null, name, type));
		return "projectList.jsp";
	}
	
}

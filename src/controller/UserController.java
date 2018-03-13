package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.User;
import service.DepartmentService;
import service.UserService;
import service.impl.UserServiceImpl;
import util.ExcelDownUtils;
import util.IsEmpty;
import util.PageUtil;
import util.StringUtil;

@Controller
public class UserController {

	private UserService userService;

	private DepartmentService departmentService;

	@Autowired
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/Login")
	public String Login(HttpServletRequest request,Model model) {
		String account = request.getParameter("account");
		String password = request.getParameter("password");

		User user = userService.login(account);

		if (user == null || !user.getPassword().equals(password)) {
			model.addAttribute("error", "您输入的账号或密码有误！");
			return "login.jsp";
		} else {
			request.getSession().setAttribute("user", user);
			if (user.getState().equals("2"))
				return "redirect:teacher/SelectAll.do";
			else

				return "redirect:/adminSelectAll.do";

		}
	}

	@RequestMapping("/adminInsertUser.do")
	public String insertUser(Model model, User user) {

		int a = userService.insertUser(user);

		if (a == 0) {
			model.addAttribute("error", "用户已经存在");
			model.addAttribute("User", user);
			model.addAttribute("departments", departmentService.selectAllDepartment());
			return "admin/addTeacher.jsp";
		} else {
			return "redirect:/adminSelectUser.do";
		}
	}

	@RequestMapping("/adminA.do")
	public String a(Model model) {
		model.addAttribute("departments", departmentService.selectAllDepartment());
		model.addAttribute("User", new User());
		return "admin/addTeacher.jsp";
	}

	@RequestMapping(value = "/adminSelectUser.do")
	public String SelectAll(HttpServletRequest request, Model model,@RequestParam(value="page",required=false)String page,@RequestParam(value="name",required=false)String name,@RequestParam(value="account",required=false)String account,@RequestParam(value="department",required=false)String department) {
		Map<String, Object> map = new HashMap<>();
		map.put("name", IsEmpty.isEmpty(name));
		map.put("account", IsEmpty.isEmpty(account));
		map.put("department", IsEmpty.isEmpty(department));
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("currpage", 1);
		}else{
			
		}
		int total = userService.SelectUser(map).size();
		List<User> users = userService.SelectUserPage(map,page);
		model.addAttribute("department", department);
		model.addAttribute("departments", departmentService.selectAllDepartment());
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/adminSelectUser.do"+"?"+"name="+name+"&"+"account="+account+"&"+"department="+department+"&", total, Integer.parseInt(page), 7);
		model.addAttribute("pageCode", pageCode);
		model.addAttribute("User", users);
		return "admin/teacherList.jsp";
	}

	@RequestMapping(value = "/adminSelectuser.do")
	public String SelectById(@RequestParam String id, Model model, HttpServletRequest request) {
		
		model.addAttribute("User", userService.SelectUser(id));
		model.addAttribute("departments", departmentService.selectAllDepartment());
		return "admin/editTeacher.jsp";
	}

	@RequestMapping(value = "/adminUpdate.do")
	public String Updata(HttpServletRequest request, User user) {
		userService.Update(user);
		return "redirect:/adminSelectUser.do";
	}
	
	@RequestMapping(value = "/teacherSelectuser.do")
	public String SelectTeacherById(Model model, HttpServletRequest request) {
		String id  = ((User)request.getSession().getAttribute("user")).getId();
		model.addAttribute("User", userService.SelectUser(id));
		model.addAttribute("departments", departmentService.selectAllDepartment());
		return "teacher/editTeacher.jsp";
	}
	
	@RequestMapping(value = "/teacherUpdate.do")
	public String UpdataTeacher(HttpServletRequest request, User user) {
		userService.Update(user);
		return "redirect:teacher/SelectAll.do";
	}

	@RequestMapping(value = "/adminDeleteUser.do")
	public String DeleteUser(HttpServletRequest request, User user) {
		int a = userService.DeleteUser(user.getId());

		if (a != 0)
			return "redirect:/adminSelectUser.do";

		return "maim.jsp";
	}

	@RequestMapping(value = "/adminRepassword.do")
	public String Repassword(HttpServletRequest request, User user) {
		int a = userService.Repassword(user.getId());

		if (a != 0)
			return "redirect:/adminSelectUser.do";

		return "maim.jsp";
	}

	
	@RequestMapping(value="/DownLoadTeachersExcel")
	public String downloadExcel(HttpServletResponse response,HttpServletRequest request) {
		String filename = "教师列表";
		String[] keys= {"account","name","password","department","sex","birthday","education_background","job_title","telephone","email"};
		String[] columnNames = {"账号","姓名","账号密码","所属部门","性别","出生年月","学历","职称","联系电话","邮箱"};
		String dept_name = request.getParameter("department");
		List<Map<String, Object>> list = getList(dept_name);
		ExcelDownUtils.excelDownUtils(list, keys, columnNames, response, filename);
		return null;
	}
	
	public List<Map<String, Object>> getList(String dept_name){
		Map<String, Object> map = new HashMap<>();
		map.put("department", dept_name);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<User> teachers = userService.SelectUser(map);
		Map<String, Object> m1 = new HashMap<String,Object>();
		m1.put("sheetName", "教师列表");
		list.add(m1);
		for(User teacher : teachers) {
			Map<String, Object> m = new HashMap<String,Object>();
			m.put("account", teacher.getAccount());
			m.put("name", teacher.getName());
			m.put("password", teacher.getPassword());
			m.put("department", teacher.getDepartment());
			m.put("sex", getSex(teacher.getSex()));
			m.put("birthday", teacher.getBirthday());
			m.put("education_background", teacher.getEducation_background());
			m.put("job_title", teacher.getJob_title());
			m.put("telephone", teacher.getTelephone());
			m.put("email", teacher.getEmail());
			list.add(m);
		}
		return list;
	}
	
	public String getSex(String s) {
		if(s.equals("1"))return "男";
		else return "女";
	}
}

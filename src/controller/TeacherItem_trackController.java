package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Item_track;
import model.Item_track_add;
import model.User;
import service.DepartmentService;
import service.Item_trackService;
import service.Item_track_addService;
import util.IsEmpty;
import util.PageUtil;
import util.StringUtil;
import util.UUIDUtils;
@Controller
@RequestMapping(value="teacher")
public class TeacherItem_trackController {
	private Item_trackService item_trackService;
	private DepartmentService departmentService;
	private Item_track_addService item_track_addService;

	@Resource
	public void setItem_track_addService(Item_track_addService item_track_addService) {
		this.item_track_addService = item_track_addService;
	}

	@Autowired
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@Resource
	public void setItem_trackService(Item_trackService item_trackService) {
		this.item_trackService = item_trackService;
	}

	@RequestMapping(value = "SelectAll")
	public String SelectAll(HttpServletRequest request, Model model,@RequestParam(value="page",required=false)String page) {
		String title = request.getParameter("title");	
		String apply_year = request.getParameter("apply_year");
		Map<String, Object> map = new HashMap<>();
		map.put("title", IsEmpty.isEmpty(title));
		map.put("apply_year", IsEmpty.isEmpty(apply_year));
		map.put("user_id", ((User)request.getSession().getAttribute("user")).getId());
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("currpage", 1);
		}else{
			
		}
		int total =item_trackService.SelectAll(map).size();
		List list1234 =item_trackService.SelectAllPage(map,page);
		model.addAttribute("item_track", list1234);
		 String pageCode=PageUtil.getPagation(request.getContextPath()+"/teacher/SelectAll.do"+"?", total, Integer.parseInt(page), 7);
		model.addAttribute("pageCode", pageCode);
		return "projectApplyList.jsp";
	}
	@RequestMapping(value = "SelectById")
	public String SelectById(@RequestParam String id, Model model, HttpServletRequest request) {

		model.addAttribute("departments", departmentService.selectAllDepartment());
		model.addAttribute("Item_track", item_trackService.SelectById(id));
		return "editProjectApply.jsp";
	}

	public void insertItemTrackAdd(HttpServletRequest request,String track_id,int strat,int end) {
		for (int j = strat; j < end; j++) {
			if(isEmpty(request,j)) {
				continue;
			}
			Item_track_add item_track_add = new Item_track_add();
			item_track_add.setOld_title(request.getParameterValues("old_title")[j]);
			item_track_add.setOld_evaluate(request.getParameterValues("old_evaluate")[j]);
			item_track_add.setTime(request.getParameterValues("time")[j]);
			item_track_add.setContent(request.getParameterValues("content")[j]);
			item_track_add.setSign(request.getParameterValues("sign")[j]);
			item_track_add.setTrack_id(track_id);
			item_track_addService.insert(item_track_add);
		}
	}
	
	public boolean isEmpty(HttpServletRequest request,int j) {
		if(IsEmpty.isEmpty(request.getParameterValues("old_title")[j])==null&&
				IsEmpty.isEmpty(request.getParameterValues("old_evaluate")[j])==null&&
				IsEmpty.isEmpty(request.getParameterValues("time")[j])==null&&
				IsEmpty.isEmpty(request.getParameterValues("content")[j])==null&&
				IsEmpty.isEmpty(request.getParameterValues("sign")[j])==null) {
			return true;
		}
		return false;
	}
	
	@RequestMapping(value = "Updata.do")
	public String Updata(HttpServletRequest request, Item_track item_track) {
		String track_id = item_track.getId();
		int count = item_track_addService.selectCount(track_id);//计算原来这个item_track有无附加信息
		if(count==0) {
			if (request.getParameterValues("time") != null) {
				int i = request.getParameterValues("time").length;
				insertItemTrackAdd(request,track_id,0,i);
			}
		}
		else {		
			int i = request.getParameterValues("time").length;
			int tid_length = request.getParameterValues("tid").length;
			List<Item_track_add> list = new ArrayList<Item_track_add>();
			for (int j = 0; j < tid_length; j++) {
				list.add(new Item_track_add());
				if(isEmpty(request,j)) {
					item_track_addService.delete(Integer.parseInt(request.getParameterValues("tid")[j]));
					continue;
				}
				list.get(j).setOld_title(request.getParameterValues("old_title")[j]);
				list.get(j).setOld_evaluate(request.getParameterValues("old_evaluate")[j]);
				list.get(j).setTime(request.getParameterValues("time")[j]);
				list.get(j).setContent(request.getParameterValues("content")[j]);
				list.get(j).setSign(request.getParameterValues("sign")[j]);
				list.get(j).setTrack_id(item_track.getId());
				list.get(j).setId((Integer.parseInt(request.getParameterValues("tid")[j])));
			}
			item_track_addService.Update(list);
			if(tid_length<i) {
				//如果又添加了附加信息，那么插入添加的附近信息
				insertItemTrackAdd(request,track_id,tid_length,i);
			}	
		}		
		item_trackService.Updata(item_track);
		return "redirect:SelectAll.do";
	}

	@RequestMapping(value = "DeleteById.do")
	public String DeleteById(HttpServletRequest request, Item_track Item_track) {
		int a = item_trackService.DeleteById(Item_track.getId());
		if (a != 0)
			return "redirect:SelectAll.do";
		return "/cancel.do";
	}

	@RequestMapping("B.do")
	public String a(Model model, Item_track item_track) {
		model.addAttribute("Item_track", new Item_track());
		return "ProjectApply.jsp";
	}

	@RequestMapping(value = "AddItem_track.do")
	public String addItem_track(HttpServletRequest request, Item_track item_track) {
		item_track.setUser_id(((User)(request.getSession()).getAttribute("user")).getId());
		item_track.setId(UUIDUtils.getUUID());
		String track_id = item_track.getId();
		if(request.getParameterValues("time")!=null) {
			int i = request.getParameterValues("time").length;
			insertItemTrackAdd(request,track_id,0,i);
		}
		item_trackService.addItem_track(item_track);
		return "redirect:SelectAll.do";
	}
	
	@RequestMapping(value = "AddItem_trackBefore.do")
	public String addItem_trackBefore(Model model) {
		model.addAttribute("Item_track", new Item_track());
		return "addProjectApply.jsp";
	}
}

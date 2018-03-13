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

import model.Item_track;
import model.Item_track_add;
import service.DepartmentService;
import service.Item_trackService;
import service.Item_track_addService;
import util.ExcelDownUtils;
import util.IsEmpty;
import util.PageUtil;
import util.StringUtil;

@Controller
public class Item_trackController {

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

	@RequestMapping(value = "/adminSelectAll")
	public String SelectAll(HttpServletRequest request, Model model,@RequestParam(value="page",required=false)String page,@RequestParam(value="title",required=false)String title,@RequestParam(value="apply_year",required=false)String apply_year) {
		Map<String, Object> map = new HashMap<>();
		map.put("title", IsEmpty.isEmpty(title));
		map.put("apply_year", IsEmpty.isEmpty(apply_year));
		map.put("user_id", null);
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("currpage", 1);
		}else{
			
		}
		int total =item_trackService.SelectAll(map).size();
		List list1234 =item_trackService.SelectAllPage(map,page);
		model.addAttribute("item_track", list1234);
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/adminSelectAll.do"+"?"+"title="+title+"&"+"apply_year="+apply_year+"&", total, Integer.parseInt(page), 7);
		model.addAttribute("pageCode", pageCode);
		return "admin/projectApplyList.jsp";
	}

	@RequestMapping(value = "/adminSelectById")
	public String SelectById(@RequestParam String id, Model model, HttpServletRequest request) {

		model.addAttribute("departments", departmentService.selectAllDepartment());
		model.addAttribute("Item_track", item_trackService.SelectById(id));

		return "admin/editProjectApply.jsp";
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
	
	@RequestMapping(value = "/adminUpdata.do")
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
		return "redirect:/adminSelectAll.do";
	}

	@RequestMapping(value = "/adminDeleteById.do")
	public String DeleteById(HttpServletRequest request, Item_track Item_track) {
		int a = item_trackService.DeleteById(Item_track.getId());

		if (a != 0)
			return "redirect:/adminSelectAll.do";

		return "cancel.do";
	}

	@RequestMapping("/adminB.do")
	public String a(Model model, Item_track item_track) {
		model.addAttribute("Item_track", new Item_track());
		return "teacher/ProjectApply.jsp";
	}

	@RequestMapping(value="DownLoadItemTrackExcel")
	public String downloadExcel(HttpServletResponse response,HttpServletRequest request) {
		String filename = "项目跟踪表";
		String[] keys= {"name","department","research","telephone","email","team","team_leader","b_expert","s_expert","advice","title","apply_field","old_apply","apply_year","join_year","major_content","sci_first_count","sci_second_count","ei_count","invent_count","invent_accredit_count","utility_count","utility_accredit_count","situation","team_basic"};
		String[] columnNames = {"姓名","学院","研究领域","手机","邮箱","所属团队","团队负责人"	,"熟悉大同行专家（会评专家）","熟悉小同行专家（函评专家）","需要学校开展的工作建议","拟报项目题目","申请领域","过往申报基金情况","申报年份","参与申请年份",	"主要内容及创新点","近3年发表SCI一区论文篇数","近3年发表SCI二区论文篇数	","近3年发表EI论文篇数","近3年申请发明数量","近3年授权发明数量","近3年申请实用新型数量","近3年授权实用新型数量","项目情况",	"队伍基础"};
		String title = IsEmpty.isEmpty(request.getParameter("title"));
		String apply_year = IsEmpty.isEmpty(request.getParameter("apply_year"));		
		List<Map<String, Object>> list = getList(title,apply_year);
		ExcelDownUtils.excelDownUtils(list, keys, columnNames, response, filename);
		return null;
	}
	
	public List<Map<String, Object>> getList(String title,String apply_year){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<>();
		map.put("title", title);
		map.put("apply_year", apply_year);
		map.put("user_id", null);
		List<Item_track> item_tracks = item_trackService.SelectAll(map);
		Map<String, Object> m1 = new HashMap<String,Object>();
		m1.put("sheetName", "项目跟踪表");
		list.add(m1);
		for(Item_track item_track : item_tracks) {
			Map<String, Object> m = new HashMap<String,Object>();
			m.put("name", item_track.getName());
			m.put("department", item_track.getDepartment());
			m.put("research", item_track.getResearch());
			m.put("telephone", item_track.getTelephone());
			m.put("email", item_track.getEmail());
			m.put("team", item_track.getTeam());
			m.put("team_leader", item_track.getTeam_leader());
			m.put("b_expert", item_track.getB_expert());
			m.put("s_expert", item_track.getS_expert());
			m.put("advice", item_track.getAdvice());
			m.put("title", item_track.getTitle());
			m.put("apply_field", item_track.getApply_field());
			String old_apply = "否";
			if(null!=item_track.getOld_apply()&&item_track.getOld_apply().equals("1")) {
				old_apply = "是";
			}
			m.put("old_apply", old_apply);
			m.put("apply_year", item_track.getApply_year());
			m.put("join_year", item_track.getJoin_year());
			m.put("major_content", item_track.getMajor_content());
			m.put("sci_first_count", item_track.getSci_first_count());
			m.put("sci_second_count", item_track.getSci_second_count());
			m.put("ei_count", item_track.getEi_count());
			m.put("invent_count", item_track.getInvent_count());
			m.put("invent_accredit_count", item_track.getInvent_accredit_count());
			m.put("utility_count", item_track.getUtility_count());
			m.put("utility_accredit_count", item_track.getUtility_accredit_count());
			m.put("situation", item_track.getSituation());
			m.put("team_basic", item_track.getTeam_basic());
			list.add(m);
		}
		return list;
	}

}
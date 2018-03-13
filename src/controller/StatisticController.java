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

import model.Statistic;
import service.StatisticService;
import util.ExcelDownUtils;
import util.IsEmpty;

@Controller

public class StatisticController {
	
	private StatisticService statisticService;
	@Autowired
	public void setStatistic(StatisticService statisticService) {
		this.statisticService = statisticService;
	}
	
	//澶嶇敤浠ｇ爜
	public void statisticList(Model model) {
		List<Statistic> statistics  = statisticService.selectAllStatistic();
		model.addAttribute("statistics", statistics);
	}
	
	//澶嶇敤浠ｇ爜
	public void searchStatistics(Model model,HttpServletRequest request) {
		String dept_name = request.getParameter("dept_name");
		String firstYear = request.getParameter("firstYear");
		String secondYear = request.getParameter("secondYear");
		List<Statistic> statistics = statisticService.selectStatistic(dept_name, firstYear, secondYear);
		model.addAttribute("statistics", statistics);
		model.addAttribute("dept_name", dept_name);
		model.addAttribute("firstYear", firstYear);
		model.addAttribute("secondYear", secondYear);
	}
	
	@RequestMapping(value="admin/StatisticList")
	public String statisticListAdmin(Model model) {
		statisticList(model);
		return "statistics.jsp"; 
	}
	
	@RequestMapping(value="admin/SearchStatistics")
	public String searchStatisticsAdmin(Model model,HttpServletRequest request) {
		searchStatistics(model, request);
		return "statistics.jsp";
	}
	
	@RequestMapping(value="teacher/StatisticList")
	public String statisticListTeacher(Model model) {
		statisticList(model);
		return "statistics.jsp"; 
	}
	
	@RequestMapping(value="teacher/SearchStatistics")
	public String searchStatisticsTeacher(Model model,	HttpServletRequest request) {
		searchStatistics(model, request);
		return "statistics.jsp";
	}
	
	@RequestMapping(value="DownLoadStatisticExcel")
	public String downloadExcel(HttpServletResponse response,HttpServletRequest request) {
		String filename = "项目统计分析";
		String[] keys= {"dept_name","teacherNum","itemTrackNum"};
		String[] columnNames = {"学院","教师人数","提交跟踪表"};
		String dept_name = IsEmpty.isEmpty(request.getParameter("d"));
		String firstYear = IsEmpty.isEmpty(request.getParameter("f"));
		String secondYear = IsEmpty.isEmpty(request.getParameter("s"));
		List<Map<String, Object>> list = getList(dept_name,firstYear,secondYear);
		ExcelDownUtils.excelDownUtils(list, keys, columnNames, response, filename);
		return null;
	}
	
	public List<Map<String, Object>> getList(String dept_name,String firstYear,String secondYear){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Statistic> statistics = statisticService.selectStatistic(dept_name, firstYear, secondYear);
		Map<String, Object> m1 = new HashMap<String,Object>();
		m1.put("sheetName", "项目统计分析");
		list.add(m1);
		for(Statistic statistic : statistics) {
			Map<String, Object> m = new HashMap<String,Object>();
			m.put("dept_name", statistic.getDept_name());
			m.put("teacherNum", statistic.getTeacherNum());
			m.put("itemTrackNum", statistic.getItemTrackNum());
			list.add(m);
		}
		return list;
	}
}

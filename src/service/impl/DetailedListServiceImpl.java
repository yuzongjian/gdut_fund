package service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DepartmentDao;
import dao.DetailedListDao;
import model.DetailedList;
import model.Item_track;
import model.PageBean;
import service.DetailedListService;
import util.UUIDUtils;
import util.WebTime;
@Service
public class DetailedListServiceImpl implements DetailedListService{

	private DetailedListDao detailedListDao;
	@Autowired
	public void setDetailedListDao(DetailedListDao detailedListDao) {	
		this.detailedListDao = detailedListDao;
	}
	private DepartmentDao departmentDao;
	@Autowired
	public void setDetailedListDao(DepartmentDao departmentDao) {	
		this.departmentDao = departmentDao;
	}
	
	//将多个DetailedList的部门由id转为名字
	public List<DetailedList> getList(List<DetailedList> old_list){
		List<DetailedList> new_list = new ArrayList<DetailedList>();
		String dept_name;
 		HashMap<String, String> hashmap = departmentDao.Select();
		for(DetailedList detailedList : old_list) {
			dept_name = hashmap.get(detailedList.getDepartment());
			detailedList.setDepartment(dept_name);
			new_list.add(detailedList);
		}
		return new_list;
	}
	
	//将一个DetailedList的部门由id转为名字
	public DetailedList getDetailedList(DetailedList detailedList) {
		HashMap<String, String> hashmap = departmentDao.Select();
		String dept_name = hashmap.get(detailedList.getDepartment());
		detailedList.setDepartment(dept_name);
		return detailedList;
	}

	//将一个DetailedList的部门由名字转为id
	public DetailedList setDetailedList(DetailedList detailedList) {
		HashMap<String, String> hashmap = departmentDao.Select();
		Iterator<Entry<String, String>> iter = hashmap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String,String> entry = (Map.Entry<String,String>) iter.next();
			if (detailedList.getDepartment().equals(entry.getValue())) {
				String id = (String) entry.getKey();
				detailedList.setDepartment(id);
				break;
			}
		}
		return detailedList;
	}
	
	public List<DetailedList> selectAllMyList(String user_id, String department) {
		List<DetailedList> detailedLists = detailedListDao.selectAllMyList(user_id,department);
		return getList(detailedLists);
	}
	public List<DetailedList> selectAllMyListPage(String user_id, String deparmtent, String page) {
		List<DetailedList> detailedLists = detailedListDao.selectAllMyList(user_id,deparmtent);
		int total=detailedLists.size();
		PageBean pageBean=new PageBean(Integer.parseInt(page),7);
		List<DetailedList> list12345 =new  ArrayList<DetailedList>();
		if(total!=0) {			
			for(int i=pageBean.getStart();i<(pageBean.getStart()+7)&&i<total;i++) {
				list12345.add(detailedLists.get(i));		
			}
		}
		return getList(list12345);
		
	}


	public List<DetailedList> selectMyList(String user_id, String department, String name, String type) {
		if(null!=department) {
			HashMap<String, String> hashmap = departmentDao.Select();
			Iterator<Entry<String, String>> iter = hashmap.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<String,String> entry = (Map.Entry<String,String>) iter.next();
				if (department.equals(entry.getValue())) {
					department = (String) entry.getKey();
					break;
				}
			}
			}
		List<DetailedList> detailedLists = detailedListDao.selectMyList(user_id, department, name, type);
		return getList(detailedLists);
	}
	
	public int insertDetailedList(DetailedList detailedList) {
		String type = detailedList.getType();
		String user_id = detailedList.getUser_id();
		String now = WebTime.getNetworkTime();
		String bigger_time;
		String smaller_time;
		if(Integer.parseInt(now.substring(6, 7))>=8) {
			bigger_time= (Integer.parseInt(now.substring(0, 4))+1)+"-08-01";
			smaller_time = now.substring(0, 4)+"-07-31";
		}
		else {
			bigger_time=now.substring(0, 4)+"-08-01";
			smaller_time = (Integer.parseInt(now.substring(0, 4))-1)+"-07-31";
		}
		List<DetailedList> detailedLists = detailedListDao.checkTypeAndYear(user_id, type, bigger_time, smaller_time);
		if(detailedLists.isEmpty()||detailedLists==null) {
			detailedList.setId(UUIDUtils.getUUID());
			return detailedListDao.insertDetailedList(setDetailedList(detailedList));
		}	
		else return -2;
	}

	public int updateDetailedList(DetailedList detailedList, String user_id) {
		detailedList.setUser_id(user_id);
		String type = detailedList.getType();
		String now = WebTime.getNetworkTime();
		String bigger_time;
		String smaller_time;
		if(Integer.parseInt(now.substring(6, 7))>=8) {
			bigger_time= (Integer.parseInt(now.substring(0, 4))+1)+"-08-01";
			smaller_time = now.substring(0, 4)+"-07-31";
		}
		else {
			bigger_time=now.substring(0, 4)+"-08-01";
			smaller_time = (Integer.parseInt(now.substring(0, 4))-1)+"-07-31";
		}
		List<DetailedList> detailedLists = detailedListDao.checkTypeAndYear(user_id, type, bigger_time, smaller_time);
		for(DetailedList de: detailedLists) {
			if(!de.getId().equals(detailedList.getId()))return -2;
		}
		return detailedListDao.updateDetailedList(setDetailedList(detailedList));		
	}

	public int deleteDetailedList(String id, String user_id) {
		return detailedListDao.deleteDetailedList(id,user_id);
	}

	public DetailedList selectListById(String id) {
		return getDetailedList(detailedListDao.selectListById(id));
	}

	@Override
	public List<DetailedList> selectMyListPage(String user_id, String department, String name, String type,
			String page) {
		// TODO Auto-generated method stub
		if(null!=department) {
			HashMap<String, String> hashmap = departmentDao.Select();
			Iterator<Entry<String, String>> iter = hashmap.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<String,String> entry = (Map.Entry<String,String>) iter.next();
				if (department.equals(entry.getValue())) {
					department = (String) entry.getKey();
					break;
				}
			}
			}
		List<DetailedList> detailedLists = detailedListDao.selectMyList(user_id, department, name, type);		
		int total=detailedLists.size();
		PageBean pageBean=new PageBean(Integer.parseInt(page),7);
		List<DetailedList> list1234 =new  ArrayList<DetailedList>();
		if(total!=0) {			
			for(int i=pageBean.getStart();i<(pageBean.getStart()+7)&&i<total;i++) {
				list1234.add(detailedLists.get(i));
			
			}
		}
		return getList(list1234);
	}



}

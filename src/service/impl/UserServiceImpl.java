package service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DepartmentDao;
import dao.UserDao;
import model.Item_track;
import model.PageBean;
import model.User;
import service.UserService;

@Service
public class UserServiceImpl implements UserService {

	
	private UserDao userDao;
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	private DepartmentDao departmentDao;

	@Autowired
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	@Override
	public User login(String account) {

		return userDao.login(account);
	}

	public String getDepartmentId(String name,Map hashmap) {
		Iterator iter = hashmap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			if (name.equals(entry.getValue())) {
				String id = (String) entry.getKey();
				return id;

			}

		}
		return null;
	}
	
	@Override
	public int insertUser(User user) {

		String account = user.getAccount();

		if (userDao.login(account) != null) {

			return 0;
		} else {
			// 由部门获取对应id
			String name = user.getDepartment();
			Map<String, String> hashmap = departmentDao.Select();
			user.setDepartment(getDepartmentId(name,hashmap));
			}
			user.setState("2");
			user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			return userDao.insertUser(user);
	}

	@Override
	public int selectByDept(String department) {
		return userDao.selectByDept(department);
	}
	@Override
	public List<User> SelectUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, String> hashmap = departmentDao.Select();
		Map<String, Object> my_map = new HashMap<String, Object>();
		my_map.putAll(map);
		String dept = (String) my_map.get("department");
		if(dept!=null) {
			my_map.replace("department", getDepartmentId(dept,hashmap));
		}
		List<User> list = userDao.SelectUser(my_map);
		List<User> new_list = new ArrayList<User>();
		for (User a : list) {
			String id = a.getDepartment();
			String val = (String) hashmap.get(id);
			a.setDepartment(val);
			new_list.add(a);
		}
		return new_list;
	}
	
	
	public List<User> SelectUserPage(Map<String, Object> map,String page) {
		// TODO Auto-generated method stub
		List<User> list = SelectUser(map);
		int total=list.size();
		PageBean pageBean=new PageBean(Integer.parseInt(page),7);
		List<User> list12345 =new  ArrayList<User>();
		if(total!=0) {			
			for(int i=pageBean.getStart();i<(pageBean.getStart()+7)&&i<total;i++) {
				list12345.add(list.get(i));		
			}
		}
		return list12345;
	}



	@Override
	public User SelectUser(String id) {
		// TODO Auto-generated method stub
		// id获取部门
		HashMap<String, String> hashmap = departmentDao.Select();
		User user = userDao.SelectUser(id);
		String dept_id = user.getDepartment();
		String val = (String) hashmap.get(dept_id);
		user.setDepartment(val);
		return user;
	}

	@Override
	public int Update(User user) {
		// TODO Auto-generated method stub
		String name = user.getDepartment();
		Map<String, String> hashmap = departmentDao.Select();
		Iterator<Entry<String, String>> iter = hashmap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			if (name.equals(entry.getValue())) {
				String id = (String) entry.getKey();
				user.setDepartment(id);
			}

		}

		return userDao.Update(user);
	}

	@Override
	public int DeleteUser(String id) {
		// TODO Auto-generated method stub
		return userDao.DeleteUser(id);
	}

	@Override
	public int Repassword(String id) {
		// TODO Auto-generated method stub
		return userDao.Repassword(id);
	}



}

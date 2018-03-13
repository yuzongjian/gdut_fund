package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import dao.DetailedListDao;
import model.DetailedList;

public class DetailedListDaoImpl extends SqlSessionDaoSupport implements DetailedListDao {

	public List<DetailedList> selectAllMyList(String user_id, String department) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id",user_id);
		map.put("department", department);
		return this.getSqlSession().selectList("model.detailedList.mapper.selectAllMyList", map);
	}

	public List<DetailedList> selectMyList(String user_id, String department, String name, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("department",department);
		map.put("name", name);
		map.put("type", type);
		return this.getSqlSession().selectList("model.detailedList.mapper.selectMyList", map);
	}

	public int insertDetailedList(DetailedList detailedList) {
		return this.getSqlSession().insert("model.detailedList.mapper.insertDetailedList", detailedList);
	}

	public int updateDetailedList(DetailedList detailedList) {
		return this.getSqlSession().update("model.detailedList.mapper.updateDetailedList", detailedList);
	}

	public int deleteDetailedList(String id, String user_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("user_id", user_id);
		return this.getSqlSession().delete("model.detailedList.mapper.deleteDetailedList", map);
	}

	public DetailedList selectListById(String id) {
		return this.getSqlSession().selectOne("model.detailedList.mapper.selectDetailedListById", id);
	}

	public List<DetailedList> checkTypeAndYear(String user_id, String type, String bigger_time, String smaller_time) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("user_id", user_id);
		map.put("type", type);
		map.put("bigger_time", bigger_time);
		map.put("smaller_time", smaller_time);
		return this.getSqlSession().selectList("model.detailedList.mapper.selectForInsert",map);
	}

}

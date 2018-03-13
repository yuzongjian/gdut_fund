package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import dao.Item_trackDao;
import model.Item_track;
import util.UUIDUtils;

public class Item_trackDaoImpl extends SqlSessionDaoSupport implements Item_trackDao {

	@Override
	public List<Item_track> SelectAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectList("model.item_track.mapper.SelectAll", map);

	}

	@Override
	public Item_track SelectById(String id) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectOne("model.item_track.mapper.SelectById", id);
	}

	@Override
	public int Updata(Item_track item_track) {

		return this.getSqlSession().update("model.item_track.mapper.Updata", item_track);
	}

	@Override
	public int DeleteById(String id) {
		// TODO Auto-generated method stub
		return this.getSqlSession().delete("model.item_track.mapper.DeleteById", id);
	}

	@Override
	public int selectByDept(String department, Integer firstYear, Integer secondYear) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("department", department);
		map.put("firstYear", firstYear);
		map.put("secondYear", secondYear);
		return this.getSqlSession().selectOne("model.item_track.mapper.selectByDept", map);
	}

	@Override
	public int addItem_track(Item_track item_track) {
		// TODO Auto-generated method stub
		return this.getSqlSession().insert("model.item_track.mapper.addItem_track", item_track);
	}
}

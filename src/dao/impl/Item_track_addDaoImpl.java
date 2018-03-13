package dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import dao.Item_track_addDao;
import model.Item_track_add;

public class Item_track_addDaoImpl extends SqlSessionDaoSupport implements Item_track_addDao {

	@Override
	public int Update(List<Item_track_add> list) {
		// TODO Auto-generated method stub
		return this.getSqlSession().update("model.item_track_add.mapper.Update", list);
	}

	@Override
	public int insert(Item_track_add item_track_add) {
		
		return this.getSqlSession().insert("model.item_track_add.mapper.Insert", item_track_add);
	}

	@Override
	public int selectCount(String track_id) {
		return this.getSqlSession().selectOne("model.item_track_add.mapper.SelectCount", track_id);
	}

	@Override
	public int delete(int id) {
		return this.getSqlSession().delete("model.item_track_add.mapper.Delete", id);
	}

}

package dao;

import java.util.List;

import model.Item_track_add;

public interface Item_track_addDao {
	public int Update(List<Item_track_add> list);
	public int insert(Item_track_add item_track_add);
	public int selectCount(String track_id);
	public int delete(int id);
}


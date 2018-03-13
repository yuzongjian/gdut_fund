package dao;

import java.util.List;
import java.util.Map;

import model.Item_track;

public interface Item_trackDao {

	public List<Item_track> SelectAll(Map<String, Object> map);

	public Item_track SelectById(String id);

	public int Updata(Item_track item_track);

	public int DeleteById(String id);
	
	public int selectByDept(String department, Integer firstYear, Integer secondYear);
	
	public int addItem_track(Item_track item_track);
}

package service;

import java.util.List;
import java.util.Map;

import model.Item_track;
import model.Item_track_add;

public interface Item_trackService {
	public List<Item_track> SelectAll(Map<String, Object> map);
	public List<Item_track> SelectAllPage(Map<String, Object> map,String page);
	public Item_track SelectById(String id);

	public int Updata(Item_track item_track);

	public int DeleteById(String id);
	
	public int selectByDept(String department, Integer firstYear, Integer secondYear);

	public int addItem_track(Item_track item_track);
	

}

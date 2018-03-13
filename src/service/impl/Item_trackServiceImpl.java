package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.Item_trackDao;
import model.Item_track;
import model.Item_track_add;
import model.PageBean;
import service.Item_trackService;

@Service
public class Item_trackServiceImpl implements Item_trackService {

	private Item_trackDao item_trackDao;
	@Autowired
	public void setItem_trackDao(Item_trackDao item_trackDao) {
		this.item_trackDao = item_trackDao;
	}

	@Override
	public List<Item_track> SelectAll(Map<String, Object> map) {

		return item_trackDao.SelectAll(map);
	}

	@Override
	public Item_track SelectById(String id) {
		Item_track item_track = item_trackDao.SelectById(id);
		item_track.setId(id);
		return item_track;
	}

	@Override
	public int Updata(Item_track item_track) {

		return item_trackDao.Updata(item_track);
	}

	@Override
	public int DeleteById(String id) {

		return item_trackDao.DeleteById(id);
	}

	public int selectByDept(String department, Integer firstYear, Integer secondYear) {
		return item_trackDao.selectByDept(department, firstYear, secondYear);
	}
	
	@Override
	public int addItem_track(Item_track item_track) {
		// TODO Auto-generated method stub
		return item_trackDao.addItem_track(item_track);
	}
	@Override
	public List<Item_track> SelectAllPage(Map<String, Object> map, String page) {
		// TODO Auto-generated method stub
		
			List<Item_track> list1234 = item_trackDao.SelectAll(map);
			int total=list1234.size();
			PageBean pageBean=new PageBean(Integer.parseInt(page),7);
			List<Item_track> list12345 =new  ArrayList<Item_track>();
			if(total!=0) {			
				for(int i=pageBean.getStart();i<(pageBean.getStart()+7)&&i<total;i++) {
					list12345.add(list1234.get(i));		
				}
			}
			return list12345;
		}


	

}

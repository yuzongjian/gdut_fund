package service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.Item_track_addDao;
import model.Item_track_add;
import service.Item_track_addService;

@Service
public class Item_track_addServiceImpl implements Item_track_addService {

	private Item_track_addDao item_track_addDao;

	@Resource
	public void setItem_track_addDao(Item_track_addDao item_track_addDao) {
		this.item_track_addDao = item_track_addDao;
	}

	@Override
	public int Update(List<Item_track_add> list) {
		// TODO Auto-generated method stub
		return item_track_addDao.Update(list);
	}

	@Override
	public int insert(Item_track_add item_track_add) {
		
		return item_track_addDao.insert(item_track_add);
	}

	@Override
	public int selectCount(String track_id) {
		return item_track_addDao.selectCount(track_id);
	}

	@Override
	public int delete(int id) {
		return item_track_addDao.delete(id);
	}

}


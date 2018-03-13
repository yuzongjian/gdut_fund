package service;

import java.util.List;

import model.DetailedList;

public interface DetailedListService {
	
	List<DetailedList> selectAllMyList(String user_id,String deparmtent);
	List<DetailedList> selectAllMyListPage(String user_id,String deparmtent,String page);
	List<DetailedList> selectMyList(String user_id,String department, String name,String type);
	List<DetailedList> selectMyListPage(String user_id,String department, String name,String type,String page);
	DetailedList selectListById(String id);
	int insertDetailedList(DetailedList detailedList);
	int updateDetailedList(DetailedList detailedList, String user_id);
	int deleteDetailedList(String id,String user_id);

}

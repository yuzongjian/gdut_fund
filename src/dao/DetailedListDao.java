package dao;

import java.util.List;

import model.DetailedList;

public interface DetailedListDao {
	List<DetailedList> selectAllMyList(String user_id, String department);
	List<DetailedList> selectMyList(String user_id,String department, String name, String type);
	DetailedList selectListById(String id);
	int insertDetailedList(DetailedList detailedList);
	int updateDetailedList(DetailedList detailedList);
	int deleteDetailedList(String id, String user_id);
	List<DetailedList> checkTypeAndYear(String user_id,String type,String bigger_time,String smaller_time);
}

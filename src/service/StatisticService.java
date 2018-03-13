package service;

import java.util.List;

import model.Department;
import model.Statistic;

public interface StatisticService {

	List<Statistic> selectAllStatistic();
	List<Statistic> selectStatistic(String dept_name, String firstYear, String secondYear);
	List<Statistic> getStatistics(List<Department> depts, Integer firstYear, Integer secondYear);
	List<Department> getDepts();
}

package dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import dao.UserDao;
import model.User;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao{

	@Override
	public User login(String account) {
		
		 return this.getSqlSession().selectOne("model.user.mapper.login", account);
	
	}

	@Override
	public int insertUser(User user) {
		// TODO Auto-generated method stub
		return this.getSqlSession().insert("model.user.mapper.insertUser",user);
	}

	@Override
	public int selectByDept(String department) {
		return this.getSqlSession().selectOne("model.user.mapper.selectByDept",department);
	}


	@Override
	public List<User> SelectUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectList("model.user.mapper.SelectUser", map);
	}

	@Override
	public User SelectUser(String id) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectOne("model.user.mapper.Selectuser", id);
	}

	@Override
	public int Update(User user) {

		return this.getSqlSession().update("model.user.mapper.Update", user);
	}

	@Override
	public int DeleteUser(String id) {
		// TODO Auto-generated method stub
		return this.getSqlSession().delete("model.user.mapper.DeleteUser", id);
	}

	@Override
	public int Repassword(String id) {
		// TODO Auto-generated method stub
		return this.getSqlSession().update("model.user.mapper.Repassword", id);
	}

	
}

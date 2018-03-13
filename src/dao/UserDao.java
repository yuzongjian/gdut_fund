package dao;


import java.util.List;
import java.util.Map;

import model.User;

public interface UserDao {
    public User login(String account);
    public int insertUser(User user);
    public int selectByDept(String department);
    public List<User> SelectUser(Map<String, Object> map);

	public User SelectUser(String id);

	public int Update(User user);

	public int DeleteUser(String id);

	public int Repassword(String id);
}

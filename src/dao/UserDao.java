package dao;

import model.Page;
import model.Sex;
import model.Type;
import model.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
	public User login(User u);
	public List<User> getAll(Page page, Map map);
	public List<Sex> getAllSex();
	public List<Type> getAllType();
	public boolean saveUser(User user);
	public int getAllCount(Map map);
	public boolean delUserById(int id);

}

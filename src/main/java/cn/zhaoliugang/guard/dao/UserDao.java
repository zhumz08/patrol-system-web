package cn.zhaoliugang.guard.dao;

import java.util.List;

import cn.zhaoliugang.guard.bean.User;

public interface UserDao {
	
	public void save(User usr);
	public void update(User usr);
	public void delete(String[] ids);
	public List list();
	public User get(int id);
	public User get(String username);
	public List list(User usr);
	public String getTitle(String sep);

}

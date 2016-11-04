package cn.zhaoliugang.guard.dao;

import java.util.List;

import cn.zhaoliugang.guard.bean.GuardRecord;
import cn.zhaoliugang.guard.bean.User;

public interface GuardRecordDao {
	
	public void save(GuardRecord bean);
	public void update(GuardRecord bean);
	public void delete(String[] ids);
	public List list();
	public GuardRecord get(int id);
	public List list(GuardRecord bean);
	public String getTitle(String sep);
}

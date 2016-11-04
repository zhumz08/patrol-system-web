package cn.zhaoliugang.guard.dao;
import java.util.List;

import cn.zhaoliugang.guard.bean.GuardSite;

public interface GuardSiteDao {
	public void save(GuardSite bean);
	public void update(GuardSite bean);
	public void delete(String[] ids);
	public List list();
	public GuardSite getbyid(String id);
	public GuardSite getbyname(String name);
	public List list(GuardSite bean);
}

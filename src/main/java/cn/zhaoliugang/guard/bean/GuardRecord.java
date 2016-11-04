package cn.zhaoliugang.guard.bean;

public class GuardRecord {
	
	private int id;
	private int uid;
	private String username;
	private String name;
	private String guardtime;
	private String guardpicture;
	private String guardaddress;
	private String comments;
	private String starttime;
	private String endtime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGuardtime() {
		return guardtime;
	}
	public void setGuardtime(String guardtime) {
		this.guardtime = guardtime;
	}
	public String getGuardpicture() {
		return guardpicture;
	}
	public void setGuardpicture(String guardpicture) {
		this.guardpicture = guardpicture;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGuardaddress() {
		return guardaddress;
	}
	public void setGuardaddress(String guardaddress) {
		this.guardaddress = guardaddress;
	}
	
	
}

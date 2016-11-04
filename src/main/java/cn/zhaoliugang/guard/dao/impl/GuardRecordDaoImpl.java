package cn.zhaoliugang.guard.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.zhaoliugang.guard.bean.GuardRecord;
import cn.zhaoliugang.guard.bean.User;
import cn.zhaoliugang.guard.dao.GuardRecordDao;
import cn.zhaoliugang.guard.util.DBUtil;

public class GuardRecordDaoImpl implements GuardRecordDao {

	@Override
	public void save(GuardRecord bean) {
		String sql = "insert  into guardrecord_tbl "
				+ "(id,uid,guardtime,guardpicture,guardaddress,comments) "
				+ "values "
				+ "(0,?,?,?,?,?) ";
		DBUtil util = new DBUtil();
		Connection conn = util.getConn();
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bean.getUid());
			pstmt.setString(2, bean.getGuardtime());
			pstmt.setString(3, bean.getGuardpicture());
			pstmt.setString(4, bean.getGuardaddress());
			pstmt.setString(5, bean.getComments());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConn(conn);
		}
	}

	@Override
	public void update(GuardRecord bean) {
		String sql = "update guardrecord_tbl set "
				+ "uid=?,guardtime=?,guardpicture=?,guardaddress=?,comments=? "
				+ "where id=? ";
		Connection conn = DBUtil.getConn();
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, bean.getUid());
			pstmt.setString(2, bean.getGuardtime());
			pstmt.setString(3, bean.getGuardpicture());
			pstmt.setString(4, bean.getGuardaddress());
			pstmt.setString(5, bean.getComments());
			
			pstmt.setInt(6, bean.getId());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConn(conn);
		}
	}

	@Override
	public void delete(String[] ids) {
		String sql = "delete from guardrecord_tbl "
				+ "where id=? ";
		DBUtil util = new DBUtil();
		Connection conn = util.getConn();
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);

			if(ids!=null && ids.length>0){
				for(int i=0;i<ids.length;i++){
					int id = new Integer(ids[i]).intValue();
					pstmt.setInt(1, id);
					pstmt.executeUpdate();
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			util.closeConn(conn);
		}
	}

	@Override
	public List list() {
		String sql = "select id, uid,username,name,guardtime,guardpicture,guardaddress,comments"
				+ "from guardrecord_view ";
		String order = " order by guardtime desc ";
		
		sql = sql + order;
		
		DBUtil util = new DBUtil();
		Connection conn = util.getConn();

		List list = new ArrayList();
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			
			
			while(rs.next()){
				int id = rs.getInt(1);
				int uid = rs.getInt(2);
				String username = rs.getString(3);
				String name = rs.getString(4);
				String guardtime = rs.getString(5);
				String guardpicture = rs.getString(6);
				String guardaddress = rs.getString(7);
				String comments = rs.getString(8);
				
				GuardRecord bean = new GuardRecord();
				bean.setId(id);
				bean.setUid(uid);
				bean.setUsername(username);
				bean.setName(name);
				bean.setGuardtime(guardtime);
				bean.setGuardpicture(guardpicture);
				bean.setGuardaddress(guardaddress);
				bean.setComments(comments);
				
				list.add(bean);
			}
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			util.closeConn(conn);
		}
		
		return null;
	}

	@Override
	public GuardRecord get(int _id) {
		String sql = "select id,uid,username,name,guardtime,guardpicture,guardaddress,comments"
				+ "from guardrecord_view where id = ? ";
		
		DBUtil util = new DBUtil();
		Connection conn = util.getConn();

		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1,_id);

			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt(1);
				int uid = rs.getInt(2);
				String username = rs.getString(3);
				String name = rs.getString(4);
				String guardtime = rs.getString(5);
				String guardpicture = rs.getString(6);
				String guardaddress = rs.getString(7);
				String comments = rs.getString(8);
				
				GuardRecord bean = new GuardRecord();
				bean.setId(id);
				bean.setUid(uid);
				bean.setUsername(username);
				bean.setName(name);
				bean.setGuardtime(guardtime);
				bean.setGuardpicture(guardpicture);
				bean.setGuardaddress(guardaddress);
				bean.setComments(comments);

				return bean;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			util.closeConn(conn);
		}
		
		return null;
	}

	@Override
	public List list(GuardRecord _bean) {
		String sql = "select id,uid,username,name,guardtime,guardpicture,guardaddress,comments "
				+ "from guardrecord_view ";
		
		String cond = "";
		
		String _username = _bean.getUsername();
		String _name = _bean.getName();
		String starttime = _bean.getStarttime();
		String endtime = _bean.getEndtime();
		
		int flag = 0;
		if((_username != null && !_username.equals(""))
				&&(_name == null || _name.equals(""))){//only has username
			if((starttime==null || starttime.equals(""))
					&&(endtime==null || endtime.equals(""))){
				cond = "where username = ? ";//username
				flag = 1;
			}
			else if(starttime==null || starttime.equals("")){
				cond = "where username = ? and guardtime <= ? ";//username,endtime
				flag = 2;
			}
			else if(endtime==null || endtime.equals("")){
				cond = "where username = ? and guardtime >= ? ";//username,starttime
				flag = 3;
			}else{
				cond = "where username = ? and guardtime >= ? and guardtime <= ? ";//username,starttime,endtime
				flag = 4;
			}
		}
		else if((_username == null || _username.equals(""))
				&&(_name == null || _name.equals(""))){//not have username and name
			if((starttime==null || starttime.equals(""))
					&&(endtime==null || endtime.equals(""))){
				cond = "where 1 = 1 ";//no
				flag = 5;
			}
			else if(starttime==null || starttime.equals("")){
				cond = "where guardtime <= ? ";//endtime
				flag = 6;
			}
			else if(endtime==null || endtime.equals("")){
				cond = "where guardtime >= ? ";//starttime
				flag = 7;
			}else{
				cond = "where guardtime >= ? and guardtime <= ? ";//starttime,endtime
				flag = 8;
			}
		}else if((_username == null || _username.equals(""))
				&&(_name != null && !_name.equals(""))){//only has name
			if((starttime==null || starttime.equals(""))
					&&(endtime==null || endtime.equals(""))){
				cond = "where name = ? ";//name
				flag = 9;
			}
			else if(starttime==null || starttime.equals("")){
				cond = "where name = ? and guardtime <= ? ";//name,endtime
				flag = 10;
			}
			else if(endtime==null || endtime.equals("")){
				cond = "where name = ? and guardtime >= ? ";//name,starttime
				flag = 11;
			}else{
				cond = "where name = ? and guardtime >= ? and guardtime <= ? ";//name,starttime,endtime
				flag = 12;
			}
		}else if((_username != null && !_username.equals(""))
				&&(_name != null && !_name.equals(""))){//has username and name
			if((starttime==null || starttime.equals(""))
					&&(endtime==null || endtime.equals(""))){
				cond = "where (username = ? or name = ?) ";//username name
				flag = 13;
			}
			else if(starttime==null || starttime.equals("")){
				cond = "where (username = ? or name = ?) and guardtime <= ? ";//username name,endtime
				flag = 14;
			}
			else if(endtime==null || endtime.equals("")){
				cond = "where (username = ? or name = ?) and guardtime >= ? ";//username name,starttime
				flag = 15;
			}else{
				cond = "where (username = ? or name = ?) and guardtime >= ? and guardtime <= ? ";//username name,starttime,endtime
				flag = 16;
			}
		}
		
		String order = " order by guardtime desc ";
		
		sql = sql + cond + order;
		
		DBUtil util = new DBUtil();
		Connection conn = util.getConn();

		List list = new ArrayList();
		PreparedStatement stmt = null;
		
		System.out.println(sql);
		
		try {
			stmt = conn.prepareStatement(sql);

			if(flag==1){
				stmt.setString(1, _username);
			}else if(flag==2){
				stmt.setString(1, _username);
				stmt.setString(2, endtime);
			}else if(flag==3){
				stmt.setString(1, _username);
				stmt.setString(2, starttime);
			}else if(flag==4){
				stmt.setString(1, _username);
				stmt.setString(2, starttime);
				stmt.setString(3, endtime);
			}else if(flag==5){
				//no
			}else if(flag==6){
				stmt.setString(1, endtime);
			}else if(flag==7){
				stmt.setString(1, starttime);
			}else if(flag==8){
				stmt.setString(1, starttime);
				stmt.setString(2, endtime);
			}else if(flag==9){
				stmt.setString(1, _name);
			}else if(flag==10){
				stmt.setString(1, _name);
				stmt.setString(2, endtime);
			}else if(flag==11){
				stmt.setString(1, _name);
				stmt.setString(2, starttime);
			}else if(flag==12){
				stmt.setString(1, _name);
				stmt.setString(2, starttime);
				stmt.setString(3, endtime);
			}if(flag==13){
				stmt.setString(1, _username);
				stmt.setString(2, _name);
			}else if(flag==14){
				stmt.setString(1, _username);
				stmt.setString(2, _name);
				stmt.setString(3, endtime);
			}else if(flag==15){
				stmt.setString(1, _username);
				stmt.setString(2, _name);
				stmt.setString(3, starttime);
			}else if(flag==16){
				stmt.setString(1, _username);
				stmt.setString(2, _name);
				stmt.setString(3, starttime);
				stmt.setString(4, endtime);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt(1);
				int uid = rs.getInt(2);
				String username = rs.getString(3);
				String name = rs.getString(4);
				String guardtime = rs.getString(5);
				String guardpicture = rs.getString(6);
				String guardaddress = rs.getString(7);
				String comments = rs.getString(8);
				
				GuardRecord bean = new GuardRecord();
				bean.setId(id);
				bean.setUid(uid);
				bean.setUsername(username);
				bean.setName(name);
				bean.setGuardtime(guardtime);
				bean.setGuardpicture(guardpicture);
				bean.setGuardaddress(guardaddress);
				bean.setComments(comments);
				
				list.add(bean);
			}
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			util.closeConn(conn);
		}
		
		return null;
	}
	
	public String getTitle(String sep){
		return "帐户名"+sep+"巡更人员"+sep+"巡更时间"+sep+"巡更图片"+sep+"巡更地点"+sep+"备注信息";
	}

}

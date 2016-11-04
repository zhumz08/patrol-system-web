package cn.zhaoliugang.guard.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.zhaoliugang.guard.bean.User;
import cn.zhaoliugang.guard.dao.UserDao;
import cn.zhaoliugang.guard.util.DBUtil;

public class UserDaoImpl implements UserDao {

	public void save(User usr) {
		String sql = "insert  into user_tbl "
				+ "(id, username,password,type,status,"
				+ "name,idnumber,address,phonenumber,comments) "
				+ "values "
				+ "(0,?,?,?,?,?,?,?,?,?) ";
		DBUtil util = new DBUtil();
		Connection conn = util.getConn();
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, usr.getUsername());
			pstmt.setString(2, usr.getPassword());
			pstmt.setInt(3, usr.getType());
			pstmt.setInt(4, usr.getStatus());
			pstmt.setString(5, usr.getName());
			pstmt.setString(6, usr.getIdnumber());
			pstmt.setString(7, usr.getAddress());
			pstmt.setString(8, usr.getPhonenumber());
			pstmt.setString(9, usr.getComments());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			util.closeConn(conn);
		}

	}

	public void update(User usr) {
		String sql = "update user_tbl set "
				+ "username=?,password=?,type=?,status=?,name=?,"
				+ "idnumber=?,address=?,phonenumber=?,comments=? "
				+ "where id=? ";
		DBUtil util = new DBUtil();
		Connection conn = util.getConn();
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, usr.getUsername());
			pstmt.setString(2, usr.getPassword());
			pstmt.setInt(3, usr.getType());
			pstmt.setInt(4, usr.getStatus());
			pstmt.setString(5, usr.getName());
			pstmt.setString(6, usr.getIdnumber());
			pstmt.setString(7, usr.getAddress());
			pstmt.setString(8, usr.getPhonenumber());
			pstmt.setString(9, usr.getComments());
			pstmt.setInt(10, usr.getId());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			util.closeConn(conn);
		}
	}

	public void delete(String[] ids) {
		String sql = "delete from user_tbl "
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

	public List list() {
		String sql = "select id, username,password,type,status,"
				+ "name,idnumber,address,phonenumber,comments "
				+ "from user_tbl order by id desc ";
		DBUtil util = new DBUtil();
		Connection conn = util.getConn();

		List list = new ArrayList();
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			
			
			while(rs.next()){
				int id = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				int type = rs.getInt(4);
				int status = rs.getInt(5);
				String name = rs.getString(6);
				String idnumber = rs.getString(7);
				String address = rs.getString(8);
				String phonenumber = rs.getString(9);
				String comments = rs.getString(10);
				
				User usr = new User();
				usr.setId(id);
				usr.setUsername(username);
				usr.setPassword(password);
				usr.setType(type);
				usr.setStatus(status);
				usr.setName(name);
				usr.setIdnumber(idnumber);
				usr.setAddress(address);
				usr.setPhonenumber(phonenumber);
				usr.setComments(comments);
				
				list.add(usr);
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
	public List list(User bean) {
		String sql = "select id, username,password,type,status,"
				+ "name,idnumber,address,phonenumber,comments "
				+ "from user_tbl ";
		
		String cond = "";

		int flag = 0;
		String _username = bean.getUsername();
		String _name = bean.getName();
		if(_username == null ||_username.equals("")){
			if(_name==null || _name.equals("")){
				cond = "";
				flag = 1;
			}else{
				cond = "where name like ? ";
				flag = 2;
			}
		}else{
			if(_name==null || _name.equals("")){
				cond = "where username like ? ";
				flag = 3;
			}else{
				cond = "where username like ? or name like ? ";
				flag = 4;
			}
		}
		
		String order = " order by id desc ";
		
		sql = sql + cond + order;
		
		DBUtil util = new DBUtil();
		Connection conn = util.getConn();

		List list = new ArrayList();
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			if(flag==1){
				
			}else if(flag==2){
				stmt.setString(1, "%"+bean.getName()+"%");
			}else if(flag==3){
				stmt.setString(1, "%"+bean.getUsername()+"%");
			}else if(flag==4){
				stmt.setString(1, "%"+bean.getUsername()+"%");
				stmt.setString(2, "%"+bean.getName()+"%");
			}
			
			ResultSet rs = stmt.executeQuery();
			
			
			while(rs.next()){
				int id = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				int type = rs.getInt(4);
				int status = rs.getInt(5);
				String name = rs.getString(6);
				String idnumber = rs.getString(7);
				String address = rs.getString(8);
				String phonenumber = rs.getString(9);
				String comments = rs.getString(10);
				
				User usr = new User();
				usr.setId(id);
				usr.setUsername(username);
				usr.setPassword(password);
				usr.setType(type);
				usr.setStatus(status);
				usr.setName(name);
				usr.setIdnumber(idnumber);
				usr.setAddress(address);
				usr.setPhonenumber(phonenumber);
				usr.setComments(comments);
				
				list.add(usr);
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
	public User get(int id) {
		String sql = "select id, username,password,type,status,"
				+ "name,idnumber,address,phonenumber,comments "
				+ "from user_tbl where id = ? ";
		DBUtil util = new DBUtil();
		Connection conn = util.getConn();

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if(rs.next()){
				String username = rs.getString(2);
				String password = rs.getString(3);
				int type = rs.getInt(4);
				int status = rs.getInt(5);
				String name = rs.getString(6);
				String idnumber = rs.getString(7);
				String address = rs.getString(8);
				String phonenumber = rs.getString(9);
				String comments = rs.getString(10);
				
				User usr = new User();
				usr.setId(id);
				usr.setUsername(username);
				usr.setPassword(password);
				usr.setType(type);
				usr.setStatus(status);
				usr.setName(name);
				usr.setIdnumber(idnumber);
				usr.setAddress(address);
				usr.setPhonenumber(phonenumber);
				usr.setComments(comments);
				
				return usr;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			util.closeConn(conn);
		}
		return null;
	}

	@Override
	public User get(String username) {
		String sql = "select id, username,password,type,status,"
				+ "name,idnumber,address,phonenumber,comments "
				+ "from user_tbl where username = ? ";
		DBUtil util = new DBUtil();
		Connection conn = util.getConn();

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();

			if(rs.next()){
				int id = rs.getInt(1);
				String password = rs.getString(3);
				int type = rs.getInt(4);
				int status = rs.getInt(5);
				String name = rs.getString(6);
				String idnumber = rs.getString(7);
				String address = rs.getString(8);
				String phonenumber = rs.getString(9);
				String comments = rs.getString(10);
				
				User usr = new User();
				usr.setId(id);
				usr.setUsername(username);
				usr.setPassword(password);
				usr.setType(type);
				usr.setStatus(status);
				usr.setName(name);
				usr.setIdnumber(idnumber);
				usr.setAddress(address);
				usr.setPhonenumber(phonenumber);
				usr.setComments(comments);
				
				return usr;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			util.closeConn(conn);
		}
		return null;
	}
	
	public String getTitle(String sep){
		return "帐户名"+sep+"帐户类型"+sep+"帐户状态"+sep+"用户姓名"+sep+"身份证号"+sep+"地址"+sep+"手机号"+sep+"备注";
	}

}

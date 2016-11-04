package cn.zhaoliugang.guard.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.zhaoliugang.guard.util.DBUtil;

public class Tester {
	
	public static void main(String[] args){
		Tester test = new Tester();
		System.out.println("user:");
		test.list_user();
		System.out.println("guard:");
		test.list();
	}
	

	public void list_user(){
		DBUtil util = new DBUtil();
		Connection conn = util.getConn();
		String sql = "select * from user_tbl";
		Statement stmt;
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
				System.out.println(Integer.toString(id) +' ' + username +' '
						+password+ ' '+type+' '+status+' '+name+' '+idnumber+' '
						+address+' '+phonenumber+' '+comments);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			util.closeConn(conn);
		}
		
	}
	
	public void list(){
		DBUtil util = new DBUtil();
		Connection conn = util.getConn();
		String sql = "select * from guardrecord_view";
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String guardtime = rs.getString(3);
				String guardpicture = rs.getString(4);
				String comments = rs.getString(5);
				System.out.println(Integer.toString(id) +' ' + name +' '+guardtime+ ' '+guardpicture+' ' +comments);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			util.closeConn(conn);
		}
		
	}

}

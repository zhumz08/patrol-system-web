package cn.zhaoliugang.guard.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import cn.zhaoliugang.guard.bean.GuardRecord;
import cn.zhaoliugang.guard.bean.GuardSite;
import cn.zhaoliugang.guard.bean.User;
import cn.zhaoliugang.guard.dao.GuardSiteDao;
import cn.zhaoliugang.guard.util.DBUtil;

public class GuardSiteDaoImpl implements GuardSiteDao {

	@Override
	public void save(GuardSite bean) {
		String sql = "insert  into guardsite_tbl "
				+ "(id,guardsiteid,guardsitename,cameraid,cameraname,presetid) "
				+ "values "
				+ "(0,?,?,?,?,?) ";
		DBUtil util = new DBUtil();
		Connection conn = util.getConn();
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getGuardsiteid());
			pstmt.setString(2, bean.getGuardsitename());
			pstmt.setString(3, bean.getCameraid());
			pstmt.setString(4, bean.getCameraname());
			pstmt.setString(5, bean.getPresetid());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			util.closeConn(conn);
		}

	}

	@Override
	public void update(GuardSite bean) {
		String sql = "update guardsite_tbl set "
				+ "guardsitename=?,cameraid=?,cameraname=?,presetid=? "
				+ "where guardsiteid=? ";
		DBUtil util = new DBUtil();
		Connection conn = util.getConn();
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bean.getGuardsitename());
			pstmt.setString(2, bean.getCameraid());
			pstmt.setString(3, bean.getCameraname());
			pstmt.setString(4, bean.getPresetid());
			
			pstmt.setString(5, bean.getGuardsiteid());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			util.closeConn(conn);
		}
	}

	@Override
	public void delete(String[] ids) {
		String sql = "delete from guardsite_tbl "
				+ "where guardsiteid=? ";
		DBUtil util = new DBUtil();
		Connection conn = util.getConn();
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);

			if(ids!=null && ids.length>0){
				for(int i=0;i<ids.length;i++){
					String id = ids[i];
					pstmt.setString(1, id);
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

		String sql = "select id,guardsiteid,guardsitename,cameraid,cameraname,presetid "
				+ "from guardsite_tbl order by guardsiteid desc ";
		DBUtil util = new DBUtil();
		Connection conn = util.getConn();

		List list = new ArrayList();
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){

				int id = rs.getInt(1);
				String guardsiteid = rs.getString(2);
				String guardsitename = rs.getString(3);
				String cameraid = rs.getString(4);
				String cameraname = rs.getString(5);
				String presetid = rs.getString(6);
				
				GuardSite rec = new GuardSite();

				rec.setId(id);
				rec.setGuardsiteid(guardsiteid);
				rec.setGuardsitename(guardsitename);
				rec.setCameraid(cameraid);
				rec.setCameraname(cameraname);
				rec.setPresetid(presetid);
				
				list.add(rec);
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
	public GuardSite getbyid(String gid) {
		String sql = "select id,guardsiteid,guardsitename,cameraid,cameraname,presetid "
				+ "from guardsite_tbl where guardsiteid = ? ";
		
		DBUtil util = new DBUtil();
		Connection conn = util.getConn();

		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);

			stmt.setString(1,gid);
			//System.out.println("sql:"+sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt(1);
				String guardsiteid = rs.getString(2);
				String guardsitename = rs.getString(3);
				String cameraid = rs.getString(4);
				String cameraname = rs.getString(5);
				String presetid = rs.getString(6);
				
				GuardSite rec = new GuardSite();
				rec.setId(id);
				rec.setGuardsiteid(guardsiteid);
				rec.setGuardsitename(guardsitename);
				rec.setCameraid(cameraid);
				rec.setCameraname(cameraname);
				rec.setPresetid(presetid);

				return rec;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			util.closeConn(conn);
		}
		
		return null;
	}

	@Override
	public GuardSite getbyname(String name) {
		String sql = "select id,guardsiteid,guardsitename,cameraid,cameraname,presetid "
				+ "from guardsite_tbl where guardsitename = ? ";
		
		DBUtil util = new DBUtil();
		Connection conn = util.getConn();

		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);

			stmt.setString(1,name);

			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt(1);
				String guardsiteid = rs.getString(2);
				String guardsitename = rs.getString(3);
				String cameraid = rs.getString(4);
				String cameraname = rs.getString(5);
				String presetid = rs.getString(6);
				
				GuardSite rec = new GuardSite();
				rec.setId(id);
				rec.setGuardsiteid(guardsiteid);
				rec.setGuardsitename(guardsitename);
				rec.setCameraid(cameraid);
				rec.setCameraname(cameraname);
				rec.setPresetid(presetid);

				return rec;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			util.closeConn(conn);
		}
		
		return null;
	}

	@Override
	public List list(GuardSite bean) {
		String sql = "select id,guardsiteid,guardsitename,cameraid,cameraname,presetid "
				+ "from guardsite_tbl ";
		
		String cond = "";

		int flag = 0;
		String _guardsiteid = bean.getGuardsiteid();
		String _guardsitename = bean.getGuardsitename();
		if(_guardsiteid == null ||_guardsiteid.equals("")){
			if(_guardsitename==null || _guardsitename.equals("")){
				cond = "";
				flag = 1;
			}else{
				cond = "where guardsitename like ? ";
				flag = 2;
			}
		}else{
			if(_guardsitename==null || _guardsitename.equals("")){
				cond = "where guardsiteid like ? ";
				flag = 3;
			}else{
				cond = "where guardsiteid like ? or guardsitename like ? ";
				flag = 4;
			}
		}
		
		String order = " order by guardsiteid desc ";
		
		sql = sql + cond + order;
		
		DBUtil util = new DBUtil();
		Connection conn = util.getConn();

		List list = new ArrayList();
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			
			if(flag==1){
				//
			}else if(flag==2){
				stmt.setString(1, "%"+bean.getGuardsitename()+"%");
			}else if(flag==3){
				stmt.setString(1, "%"+bean.getGuardsiteid()+"%");
			}else if(flag==4){
				stmt.setString(1, "%"+bean.getGuardsiteid()+"%");
				stmt.setString(2, "%"+bean.getGuardsitename()+"%");
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt(1);

				String guardsiteid = rs.getString(2);
				String guardsitename = rs.getString(3);
				String cameraid = rs.getString(4);
				String cameraname = rs.getString(5);
				String presetid = rs.getString(6);
				
				GuardSite rec = new GuardSite();
				rec.setId(id);
				rec.setGuardsiteid(guardsiteid);
				rec.setGuardsitename(guardsitename);
				rec.setCameraid(cameraid);
				rec.setCameraname(cameraname);
				rec.setPresetid(presetid);
				
				list.add(rec);
			}
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			util.closeConn(conn);
		}
		return null;
	}

}

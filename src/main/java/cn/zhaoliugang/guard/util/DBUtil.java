package cn.zhaoliugang.guard.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBUtil {
	
	public final static String jdbc_properties = "/jdbc.properties";
	static Properties props = new Properties();
	private static DBUtil dbPool = new DBUtil();

	static{
		
		try {
			props.load(DBUtil.class.getResourceAsStream(jdbc_properties));
			
			dbPool = new DBUtil();
			dbPool.init(Integer.parseInt(props.getProperty("minimum")), Integer.parseInt(props.getProperty("maximum")));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	private HikariDataSource ds;
    
    /**
     * 初始化连接池
     * @param minimum
     * @param Maximum
     */
    public void init(int minimum,int Maximum){
        //连接池配置
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(props.getProperty("driverClass"));
        config.setJdbcUrl(props.getProperty("jdbcUrl"));
        config.setUsername(props.getProperty("user"));
        config.setPassword(props.getProperty("password"));
        config.addDataSourceProperty("cachePrepStmts",Boolean.valueOf(props.getProperty("cachePrepStmts")));
        config.addDataSourceProperty("prepStmtCacheSize", Integer.valueOf(props.getProperty("prepStmtCacheSize")));
        config.addDataSourceProperty("prepStmtCacheSqlLimit", Integer.valueOf(props.getProperty("prepStmtCacheSqlLimit")));
        config.setConnectionTestQuery("SELECT 1");
        config.setAutoCommit(true);
        //池中最小空闲链接数量
        config.setMinimumIdle(minimum);
        //池中最大链接数量
        config.setMaximumPoolSize(Maximum);
         
        ds = new HikariDataSource(config);
    }
     
    /**
     * 销毁连接池
     */
    public void shutdown(){
    	ds.close();
    }
     
    /**
     * 从连接池中获取链接
     * @return
     */
    public Connection getConnection(){
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            ds.resumePool();
            return null;
        }
    }
    
    
    public static Connection getConn(){
    	return dbPool.getConnection();
    }
    
    public static Connection closeConn(Connection conn){
    	if(conn!=null){
    		try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	return null;
    }
    
    
    
     
    public static void main(String[] args) throws SQLException {
    	DBUtil ds = new DBUtil();
        ds.init(10, 50);
        Connection conn = ds.getConnection();
        //......
        //最后关闭链接
        conn.close();
    }

}

package com.mp01.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.PooledConnection;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public class DBCP {
	
	private static PooledConnection pc = null;
	
	static {
		try {
			OracleConnectionPoolDataSource ocpds = new OracleConnectionPoolDataSource(); 
			
			Properties prop = new Properties();
			prop.load(new FileInputStream("resource/jdbc.properties"));
//			System.out.println(prop);
			
			ocpds.setURL(prop.getProperty("DATABASE_URL_LOCALHOST"));
			ocpds.setUser(prop.getProperty("DATABASE_USER_ID"));
			ocpds.setPassword(prop.getProperty("DATABASE_USER_PW"));

			pc = ocpds.getPooledConnection();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static Connection getConnection(boolean autoCommit) {
		Connection conn = null;
		try {
			conn = pc.getConnection();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void commit(Connection conn) {
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}	

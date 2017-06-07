package com.storeOperations.dao;

import javax.naming.*;
import javax.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class OracleStoreOperations {
	
	//private static DataSource OracleStoreOperations = null;
	private static Connection OracleStoreOperations = null;
	//private static Context context = null;
	
	public static Connection OracleStoreOperationsConn() {
		
		try {
			
//			if(OracleStoreOperations != null ) {
//				return OracleStoreOperations;
//			}
			
//			if(context == null) {
//				context = new InitialContext();
//			}
			
			
			Class.forName("oracle.jdbc.OracleDriver");
			//Connection connection = null;
			OracleStoreOperations = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:STORE","system","Atlanta@29");
			//connection.close();
			
			//OracleStoreOperations = (DataSource) context.lookup("storeOracle");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return OracleStoreOperations;
	}

}

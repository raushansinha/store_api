package com.storeOperations.dao;

import javax.naming.*;
import javax.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class OracleStoreOperations {
	
	public static Connection OracleStoreOperationsConn() throws Exception {
		
		Connection OracleStoreOperations = null;
		
		try {	
			
			Class.forName("oracle.jdbc.OracleDriver");
			OracleStoreOperations = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:STORE","system","Atlanta@29");
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return OracleStoreOperations;
	}
}
